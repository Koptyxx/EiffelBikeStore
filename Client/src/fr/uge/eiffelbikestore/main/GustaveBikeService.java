package fr.uge.eiffelbikestore.main;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.rpc.ServiceException;

import fr.uge.eiffelbikestore.shop.Shop;
import fr.uge.eiffelbikestore.shop.Bike;
import fr.uge.eiffelbikestore.shop.ShopService;
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
		 
		 /*System.out.println(shop.cartNbElem());
		 System.out.println(shop.addToCart(1));
		 System.out.println(shop.cartNbElem());

		shop.validBuy();
		System.out.println(shop.cartNbElem());
		*/

	 }
}
