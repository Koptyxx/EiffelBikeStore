package fr.uge.eiffelbikestore.main;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.rpc.ServiceException;

import fr.uge.eiffelbikestore.banque.Banque;
import fr.uge.eiffelbikestore.banque.BanqueServiceLocator;
import fr.uge.eiffelbikestore.banque.BanqueSoapBindingStub;
import fr.uge.eiffelbikestore.shop.Bike;
import fr.uge.eiffelbikestore.shop.Client;
import fr.uge.eiffelbikestore.shop.Shop;
import fr.uge.eiffelbikestore.shop.ShopServiceLocator;
import fr.uge.eiffelbikestore.shop.ShopSoapBindingStub;




public class GustaveBikeService {
	 public static void main(String[] args) throws RemoteException, ServiceException {
		 Shop shop = new ShopServiceLocator().getShop();
		 ((ShopSoapBindingStub) shop).setMaintainSession(true);

		 shop.loadBike();
		 
		 Bike[] lstBikes = shop.allBike();
		 for(int i = 0; i < lstBikes.length; i++) {
			 System.out.println(lstBikes[i].getId() +  " " + lstBikes[i].getPrice());
		 }
		 System.out.print(shop.getDispo(1));
		 shop.registryClients(1);
		 Client[] lstClient = shop.allClients();
		 System.out.println(lstClient.length);
		 //System.out.println(shop.getClient(3).getId());
		 
		 shop.addToCart(1, 1);
		 shop.validBuy(1);

		 
		 Banque banque = new BanqueServiceLocator().getBanque();
		 ((BanqueSoapBindingStub) banque).setMaintainSession(true);
		 
		 
		 banque.addClient(1, 50);
		 
		 System.out.println(banque.nbCLient());
		 System.out.println();

		 System.out.println(banque.getArgentClient(1));
		 //shop.validBuy(1);
		 //System.out.println(banque.getArgentClient(1));

		 
		 /*System.out.println(shop.cartNbElem());
		 System.out.println(shop.addToCart(1));
		 System.out.println(shop.cartNbElem());

		shop.validBuy();
		System.out.println(shop.cartNbElem());
		*/

	 }
}
