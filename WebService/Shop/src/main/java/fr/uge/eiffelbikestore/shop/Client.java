package fr.uge.eiffelbikestore.shop;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.rpc.ServiceException;


public class Client {
	
	private long id;
	private Set<Long> cart = new HashSet<>();
	private int devise;
	/* 1 -> EUR
	 * 2 -> USD
	 * 3 -> JPY
	 * 4 -> GBP
	 * 5 -> AUD
	 */
	
	public Client () {
		
	}
	
	public Client(long id, int devise) throws ServiceException, RemoteException {
		 this.id = id;
		 this.devise = devise;
	}
	
	public int getDevise() {
		return devise;
	}
	
	public long getId() {
		return id;
	}
	
	public String deviseToString() {
		switch(devise) {
			case(1) : return "EUR";
			case(2) : return "USD";
			case(3) : return "JPY";
			case(4) : return "GBP";
			case(5) : return "AUD";
			default : return "erreur";
			
		}
	}
	
	
	public long[] allIdInCart() {
		long[] cartIds = new long[cart.size()];
		int i = 0;
		for(long elem : cart) {
			cartIds[i] = elem;
			i++;
		}
		return cartIds;
	}
	
	public void validCart() throws RemoteException {
		cart.clear();
	}
	
	public void addBikeToCart(long idBike) {
		cart.add(idBike);
	}
}

