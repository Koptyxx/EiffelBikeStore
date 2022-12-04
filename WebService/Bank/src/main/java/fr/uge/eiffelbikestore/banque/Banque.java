package fr.uge.eiffelbikestore.banque;

import java.util.HashMap;
import java.util.Map;



public class Banque {
	private Map<Long, Double> bank = new HashMap<>();
	
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
	
	public double getArgentClient(long idClient) {
		for(long client : bank.keySet()) {
			if(client == idClient) {
				return bank.get(client);
			}
		}
		return -1; 
	}
	
	public void addClient(long idClient, double montant) {
		bank.put(idClient, montant);
	}
	
	public void deleteClient(long client) {
		bank.remove(client);
	}
	
	public void addMontant(long idClient, double montant) {
		for(long client : bank.keySet()) {
			if(client == idClient) {
				double oldMontant = bank.get(client);
				bank.put(client, oldMontant + montant);
			}
		}
	}
	
	public void subMontant(long idClient, double montant) {
		for(long client : bank.keySet()) {
			if(client == idClient) {
				double oldMontant = bank.get(client);
				bank.put(client, oldMontant - montant);
			}
		}
	}
	
	public boolean isPossible(long idClient, double montant) {
		for(long client : bank.keySet()) {
			if(client == idClient) {
				double oldMontant = bank.get(client);
				return oldMontant >= montant;
			}
		}
		return false;
	}

}
