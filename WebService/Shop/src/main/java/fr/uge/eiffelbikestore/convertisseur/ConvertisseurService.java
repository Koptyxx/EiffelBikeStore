/**
 * ConvertisseurService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.uge.eiffelbikestore.convertisseur;

public interface ConvertisseurService extends javax.xml.rpc.Service {
    public java.lang.String getConvertisseurAddress();

    public fr.uge.eiffelbikestore.convertisseur.Convertisseur getConvertisseur() throws javax.xml.rpc.ServiceException;

    public fr.uge.eiffelbikestore.convertisseur.Convertisseur getConvertisseur(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
