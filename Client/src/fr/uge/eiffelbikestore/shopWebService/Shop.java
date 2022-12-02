/**
 * Shop.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.uge.eiffelbikestore.shopWebService;

public interface Shop extends java.rmi.Remote {
    public fr.uge.eiffelbikestore.shopWebService.Client getClient(long id) throws java.rmi.RemoteException;
    public boolean getDispo(long id) throws java.rmi.RemoteException;
    public fr.uge.eiffelbikestore.shopWebService.Bike getBike(long id) throws java.rmi.RemoteException;
    public void loadBike() throws java.rmi.RemoteException;
    public void registryClients(long id) throws java.rmi.RemoteException;
    public long getBikePrice(long id) throws java.rmi.RemoteException;
    public boolean addToCart(long idClient, long idBike) throws java.rmi.RemoteException;
    public fr.uge.eiffelbikestore.shopWebService.Bike[] allBike() throws java.rmi.RemoteException;
    public void validBuy(long idClient) throws java.rmi.RemoteException;
    public fr.uge.eiffelbikestore.shopWebService.Client[] allClients() throws java.rmi.RemoteException;
}
