package fr.uge.eiffelbikestore.shopWebService;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.rpc.ServiceException;


public class Client {
	
	private long id;
	private Set<Bike> cart = new HashSet<>();
	
	public Client () {
		
	}
	
	public Client(long id) throws ServiceException, RemoteException {
		 this.id = id;
	}
	
	
	public long getId() {
		return id;
	}
	
	
	public long cartValue() {
		long cartValue = 0;
		for(Bike elem : cart) {
			cartValue += elem.getPrice();
		}
		return cartValue;
	}
	
	public long[] validCart() throws RemoteException {
		long[] idList = new long[cart.size()];
		
		int i = 0;
		for(Bike elem : cart) {
			idList[i] = elem.getId();
			i++;
		}

		cart.clear();
		return idList;

	}
	
	public void addBikeToCart(Bike bike) {
		cart.add(bike);
	}
}

