/**
 * ShopService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.uge.eiffelbikestore.shopWebService;

public interface ShopService extends javax.xml.rpc.Service {
    public java.lang.String getShopAddress();

    public fr.uge.eiffelbikestore.shopWebService.Shop getShop() throws javax.xml.rpc.ServiceException;

    public fr.uge.eiffelbikestore.shopWebService.Shop getShop(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
