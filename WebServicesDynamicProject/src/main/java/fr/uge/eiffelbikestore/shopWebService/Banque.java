package fr.uge.eiffelbikestore.shopWebService;

import java.util.HashMap;
import java.util.Map;



public class Banque {
	private Map<Long, Long> bank = new HashMap<>();
	
	public Banque() {
		
	}
	
	public long[] getClientId() {
		long[] clientLst = new long[bank.size()];
		int i = 0;
		for(long client : bank.keySet()) {
			clientLst[i]=client;
		}
		return clientLst;
	}
	
	public int nbCLient() {
		return bank.size();
	}
	
	public long getArgentClient(long idClient) {
		for(long client : bank.keySet()) {
			if(client == idClient) {
				return bank.get(client);
			}
		}
		return -1; 
	}
	
	public void addClient(long idClient, long montant) {
		bank.put(idClient, montant);
	}
	
	public void deleteClient(long client) {
		bank.remove(client);
	}
	
	public void addMontant(long idClient, long montant) {
		for(long client : bank.keySet()) {
			if(client == idClient) {
				long oldMontant = bank.get(client);
				bank.put(client, oldMontant + montant);
			}
		}
	}
	
	public void subMontant(long idClient, long montant) {
		for(long client : bank.keySet()) {
			if(client == idClient) {
				long oldMontant = bank.get(client);
				bank.put(client, oldMontant - montant);
			}
		}
	}
	
	public boolean isPossible(long idClient, long montant) {
		for(long client : bank.keySet()) {
			if(client == idClient) {
				long oldMontant = bank.get(client);
				return oldMontant <= montant;
			}
		}
		return false;
	}

}
