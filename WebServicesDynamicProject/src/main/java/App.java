import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import fr.uge.eiffelbikestore.shop.Bike;
import fr.uge.eiffelbikestore.shop.Shop;
import fr.uge.eiffelbikestore.shop.ShopServiceLocator;
import fr.uge.eiffelbikestore.shop.ShopSoapBindingStub;

public class App {
	public static void main(String[] args) throws RemoteException, ServiceException {
		 Shop shop = new ShopServiceLocator().getShop();
		 ((ShopSoapBindingStub) shop).setMaintainSession(true);

		 shop.loadBike();
		 
		 Bike[] lstBikes = shop.allBike();
		 for(int i = 0; i < lstBikes.length; i++) {
			 System.out.println(lstBikes[i].getId() +  " " + lstBikes[i].getPrice());
		 }
		 

	 }
}
