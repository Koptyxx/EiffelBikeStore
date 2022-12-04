/**
 * Banque.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.uge.eiffelbikestore.banque;

public interface Banque extends java.rmi.Remote {
    public long[] getClientId() throws java.rmi.RemoteException;
    public int nbCLient() throws java.rmi.RemoteException;
    public void addClient(long idClient, double montant) throws java.rmi.RemoteException;
    public double getArgentClient(long idClient) throws java.rmi.RemoteException;
    public void deleteClient(long client) throws java.rmi.RemoteException;
    public void addMontant(long idClient, double montant) throws java.rmi.RemoteException;
    public boolean isPossible(long idClient, double montant) throws java.rmi.RemoteException;
    public void subMontant(long idClient, double montant) throws java.rmi.RemoteException;
}
