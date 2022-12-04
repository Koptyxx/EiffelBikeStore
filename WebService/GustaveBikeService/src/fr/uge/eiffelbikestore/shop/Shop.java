/**
 * Shop.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.uge.eiffelbikestore.shop;

public interface Shop extends java.rmi.Remote {
    public fr.uge.eiffelbikestore.shop.Client getClient(long id) throws java.rmi.RemoteException;
    public double cartValueConverted(long idClient) throws java.rmi.RemoteException;
    public void loadBike() throws java.rmi.RemoteException;
    public fr.uge.eiffelbikestore.shop.Client[] allClients() throws java.rmi.RemoteException;
    public boolean getDispo(long id) throws java.rmi.RemoteException;
    public boolean addToCart(long idClient, long idBike) throws java.rmi.RemoteException;
    public void validBuy(long idClient) throws java.rmi.RemoteException;
    public fr.uge.eiffelbikestore.shop.Bike getBike(long id) throws java.rmi.RemoteException;
    public fr.uge.eiffelbikestore.shop.Bike[] allBike() throws java.rmi.RemoteException;
    public void registryClients(long id, int devise) throws java.rmi.RemoteException;
    public long getBikePrice(long id) throws java.rmi.RemoteException;
    public java.lang.String getClientDevise(long idClient) throws java.rmi.RemoteException;
}
