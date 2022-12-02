package fr.uge.eiffelbikestore.shopWebService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.rpc.ServiceException;

//import fr.uge.eiffelbikestore.banque.Banque;
//import fr.uge.eiffelbikestore.banque.BanqueServiceLocator;
//import fr.uge.eiffelbikestore.banque.BanqueSoapBindingStub;
import fr.uge.eiffelbikestore.store.IStore;


public class Shop {
	
	private final Set<Bike> bikes;
	private final Set<Client> clients;
	
	public Shop() throws ServiceException, RemoteException {
		this.bikes = new HashSet<>();
		this.clients = new HashSet<>();

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
	
	public void registryClients(long id) throws RemoteException, ServiceException {
		clients.add(new Client(id));
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
	
	public Client getClient(long id) {
		for(Client elem : clients) {
			if(elem.getId() == id) {
				return elem;
			}
		}
		return null; 
	}

	
	
	public Bike[] allBike() {
		Bike[] bikesTab = new Bike[bikes.size()];
		int i = 0;
		for(Bike elem : bikes) {
			bikesTab[i] = elem;
			i++;
		}
		return bikesTab;
	}
	
	public Client[] allClients() {
		Client[] bikesTab = new Client[clients.size()];
		int i = 0;
		for(Client elem : clients) {
			bikesTab[i] = elem;
			i++;
		}
		return bikesTab;
	}

	
	
	public boolean addToCart(long idClient, long idBike) {
		Bike bike = getBike(idBike);
		Client client = getClient(idClient);
		if(bike == null || client == null)
			return false;
		else {
			client.addBikeToCart(bike);
			return true;
		}
	}
	
	public void validBuy(long idClient) throws MalformedURLException, RemoteException, NotBoundException, ServiceException {
		Client client = getClient(idClient);
		IStore store = ((IStore) Naming.lookup("rmi://localhost:1100/Store"));
		
		//Banque banque = new BanqueServiceLocator().getBanque();
		/* ((BanqueSoapBindingStub) banque).setMaintainSession(true);
		 
		long valueCart = client.cartValue();
		
		if(banque.isPossible(idClient, valueCart)) {
			long[] idBikes = client.validCart();
			for(int i = 0; i < idBikes.length; i++) {
				bikes.remove(idBikes[i]);
				store.deleteBike(idBikes[i]);
			}	
		}*/
		

	}
}