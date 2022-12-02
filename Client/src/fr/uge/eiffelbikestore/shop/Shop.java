/**
 * Shop.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.uge.eiffelbikestore.shop;

public interface Shop extends java.rmi.Remote {
    public void loadBike() throws java.rmi.RemoteException;
    public fr.uge.eiffelbikestore.shop.Bike getBike(long id) throws java.rmi.RemoteException;
    public fr.uge.eiffelbikestore.shop.Bike[] showCart() throws java.rmi.RemoteException;
    public fr.uge.eiffelbikestore.shop.Bike[] allBike() throws java.rmi.RemoteException;
    public long getBikePrice(long id) throws java.rmi.RemoteException;
    public boolean getDispo(long id) throws java.rmi.RemoteException;
    public boolean addToCart(long id) throws java.rmi.RemoteException;
    public int validBuy() throws java.rmi.RemoteException;
    public int cartNbElem() throws java.rmi.RemoteException;
}
