package fr.uge.eiffelbikestore.shop;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.rpc.ServiceException;

import fr.uge.eiffelbikestore.banque.BanqueServiceLocator;
import fr.uge.eiffelbikestore.banque.BanqueSoapBindingStub;
import fr.uge.eiffelbikestore.convertisseur.Convertisseur;
import fr.uge.eiffelbikestore.convertisseur.ConvertisseurServiceLocator;
import fr.uge.eiffelbikestore.convertisseur.ConvertisseurSoapBindingStub;
import fr.uge.eiffelbikestore.banque.Banque;
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
	
	public void registryClients(long id, int devise) throws RemoteException, ServiceException {
		clients.add(new Client(id, devise));
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
			client.addBikeToCart(idBike);
			return true;
		}
	}
	
	public String getClientDevise(long idClient) {
		return getClient(idClient).deviseToString();
	}
	
	public double cartValueConverted(long idClient) throws ServiceException, RemoteException {
		 Convertisseur convertisseur = new ConvertisseurServiceLocator().getConvertisseur();
		((ConvertisseurSoapBindingStub) convertisseur).setMaintainSession(true);
		Client client = getClient(idClient);
		
		double valueCart = 0;
		long[] idCart = client.allIdInCart();
		for(int i = 0; i < idCart.length; i++) {
			valueCart += getBike(idCart[i]).getPrice(); 
		}
		switch(client.getDevise()) {
			case 1 : return valueCart;
			case 2 : return convertisseur.EURToUSD(valueCart);
			case 3 : return convertisseur.EURToJPY(valueCart);
			case 4 : return convertisseur.EURToGBP(valueCart);
			case 5 : return convertisseur.EURToAUD(valueCart);
			default : return -1;
		}	
	}
	
	
	public void validBuy(long idClient) throws MalformedURLException, RemoteException, NotBoundException, ServiceException {
		Client client = getClient(idClient);
		IStore store = ((IStore) Naming.lookup("rmi://localhost:1100/Store"));
		
		Banque banque = new BanqueServiceLocator().getBanque();
		 ((BanqueSoapBindingStub) banque).setMaintainSession(true);
		 
		 Convertisseur convertisseur = new ConvertisseurServiceLocator().getConvertisseur();
		 ((ConvertisseurSoapBindingStub) convertisseur).setMaintainSession(true);
		 
		long[] idCart = client.allIdInCart();
		
		double finalPrice = cartValueConverted(idClient);		
				
		if(banque.isPossible(idClient, finalPrice)) {
			banque.subMontant(idClient, finalPrice);
			for(int i = 0; i < idCart.length; i++) {
				bikes.remove(getBike(idCart[i]));
				store.deleteBike(idCart[i]);
			}	
		}
	}
}