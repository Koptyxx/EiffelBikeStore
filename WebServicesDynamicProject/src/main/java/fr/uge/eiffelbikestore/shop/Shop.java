package fr.uge.eiffelbikestore.shop;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import fr.uge.eiffelbikestore.store.IStore;


public class Shop {
	
	private final Set<Bike> bikes;
	
	private final Set<Bike> cart;
	
	public Shop() {
		this.bikes = new HashSet<>();
		this.cart = new HashSet<>();
	}

	
	public void loadBike() {
		
		try {
	
			IStore store = ((IStore) Naming.lookup("rmi://localhost:1100/Store"));
			long[] idBikes =  store.getCanBeBuyId();
			long[] priceBikes = store.getCanBeBuyPrice();
			bikes.clear();
			for(int i = 0; i < idBikes.length;i++) {
				bikes.add(new Bike(idBikes[i], priceBikes[i]));
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public long getBikePrice(long id) {
		for(Bike elem : bikes) {
			if (elem.getId() == id)
				return elem.getPrice();
		}
		return -1;
	}
	
	public boolean getDispo(long id) {
		for(Bike elem : bikes) {
			if(elem.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	public Bike getBike(long id) {
		for(Bike elem : bikes) {
			if(elem.getId() == id) {
				return elem;
			}
		}
		return null; 
	}
	
	public int cartNbElem() {
		return cart.size();
	}
	
	
	public Bike[] allBike() {
		Bike[] bikesTab = new Bike[bikes.size()];
		int i = 0;
		for(var elem : bikes) {
			bikesTab[i] = elem;
			i++;
		}
		return bikesTab;
	}
	
	public Bike[] showCart() {
		Bike[] bikesTab = new Bike[cart.size()];
		int i = 0;
		for(Bike elem : cart) {
			bikesTab[i] = elem;
			i++;
		}
		return bikesTab;
	}
	
	
	
	public boolean addToCart(long id) {
		Bike bike = getBike(id);
		if(bike == null)
			return false;
		else {
			cart.add(bike);
			return true;
		}
	}
	
	public int validBuy() throws MalformedURLException, RemoteException, NotBoundException {
		IStore store = ((IStore) Naming.lookup("rmi://localhost:1100/Store"));
		int totalPrice = 0;
		for(Bike elem : cart) {
			totalPrice += elem.getPrice();
			bikes.remove(elem);
			store.deleteBike(elem.getId());
		}
		cart.clear();
		return totalPrice;
	}
	
	
}
