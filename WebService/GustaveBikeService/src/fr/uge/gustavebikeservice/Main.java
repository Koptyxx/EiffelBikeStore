package fr.uge.gustavebikeservice;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import fr.uge.eiffelbikestore.banque.Banque;
import fr.uge.eiffelbikestore.banque.BanqueServiceLocator;
import fr.uge.eiffelbikestore.banque.BanqueSoapBindingStub;
import fr.uge.eiffelbikestore.shop.Bike;
import fr.uge.eiffelbikestore.shop.Client;
import fr.uge.eiffelbikestore.shop.Shop;
import fr.uge.eiffelbikestore.shop.ShopServiceLocator;
import fr.uge.eiffelbikestore.shop.ShopSoapBindingStub;

public class Main {
	 public static void main(String[] args) throws RemoteException, ServiceException, InterruptedException {
		 Shop shop = new ShopServiceLocator().getShop();
		 ((ShopSoapBindingStub) shop).setMaintainSession(true);
		 
		 System.out.println("Récupération des vélos auprès du store en cours...");
		 shop.loadBike();
		 System.out.println("Récupération terminée");

		 
		 Bike[] lstBikes = shop.allBike();
		 System.out.println("Liste des vélos disponibles à l'achat :");
		 for(int i = 0; i < lstBikes.length; i++) {
			 System.out.println("Vélo : " + lstBikes[i].getId() +  " prix " + lstBikes[i].getPrice() + " EUR");
		 }
		 
		 System.out.println("Enregistrement du client d'id : 1 qui paye en USD");
		 shop.registryClients(1, 2);
		 System.out.println("Enregistrement du client d'id : 2 qui paye en JPY");
		 shop.registryClients(2, 3);
		 
		 
		 System.out.println("Ajout du vélo d'id 1 dans le panier du client d'id 1");
		 shop.addToCart(1, 1);
		 System.out.println("Ajout du vélo d'id 2 dans le panier du client d'id 1");
		 shop.addToCart(1, 2);
		 System.out.println("Ajout du vélo d'id 3 dans le panier du client d'id 2");
		 shop.addToCart(2, 3);

		 
		 Banque banque = new BanqueServiceLocator().getBanque();
		 ((BanqueSoapBindingStub) banque).setMaintainSession(true);
		 
		 System.out.println("Enregistrement auprès de la banque du client 1 avec un monant de 100 USD");
		 banque.addClient(1, 100);
		 System.out.println("Enregistrement auprès de la banque du client 2 avec un monant de 10000 JPY");
		 banque.addClient(2, 10000);


		 System.out.println("Le client 1 possède " + banque.getArgentClient(1) + "  "  + shop.getClientDevise(1) + " sur son compte en banque.");
		 System.out.println("Validation du panier d'une valeur de " + shop.cartValueConverted(1) + " "  + shop.getClientDevise(1) );
		 shop.validBuy(1);
		 System.out.println("Le client 1 possède " + banque.getArgentClient(1) + " " +  shop.getClientDevise(1) + " sur son compte en banque.");
		 
		 System.out.println("Le client 2 possède " + banque.getArgentClient(2) + "  "  + shop.getClientDevise(2) + " sur son compte en banque.");
		 System.out.println("Validation du panier d'une valeur de " + shop.cartValueConverted(2) + " " + shop.getClientDevise(2));
		 shop.validBuy(2);
		 System.out.println("Le client 2 possède " + banque.getArgentClient(2) + " " +  shop.getClientDevise(2) + " sur son compte en banque.");

	 }
}
