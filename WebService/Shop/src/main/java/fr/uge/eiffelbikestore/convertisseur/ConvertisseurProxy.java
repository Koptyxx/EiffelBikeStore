package fr.uge.eiffelbikestore.convertisseur;

public class ConvertisseurProxy implements fr.uge.eiffelbikestore.convertisseur.Convertisseur {
  private String _endpoint = null;
  private fr.uge.eiffelbikestore.convertisseur.Convertisseur convertisseur = null;
  
  public ConvertisseurProxy() {
    _initConvertisseurProxy();
  }
  
  public ConvertisseurProxy(String endpoint) {
    _endpoint = endpoint;
    _initConvertisseurProxy();
  }
  
  private void _initConvertisseurProxy() {
    try {
      convertisseur = (new fr.uge.eiffelbikestore.convertisseur.ConvertisseurServiceLocator()).getConvertisseur();
      if (convertisseur != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)convertisseur)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)convertisseur)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (convertisseur != null)
      ((javax.xml.rpc.Stub)convertisseur)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public fr.uge.eiffelbikestore.convertisseur.Convertisseur getConvertisseur() {
    if (convertisseur == null)
      _initConvertisseurProxy();
    return convertisseur;
  }
  
  public double EURToAUD(double value) throws java.rmi.RemoteException{
    if (convertisseur == null)
      _initConvertisseurProxy();
    return convertisseur.EURToAUD(value);
  }
  
  public double EURToJPY(double value) throws java.rmi.RemoteException{
    if (convertisseur == null)
      _initConvertisseurProxy();
    return convertisseur.EURToJPY(value);
  }
  
  public double EURToUSD(double value) throws java.rmi.RemoteException{
    if (convertisseur == null)
      _initConvertisseurProxy();
    return convertisseur.EURToUSD(value);
  }
  
  public double EURToGBP(double value) throws java.rmi.RemoteException{
    if (convertisseur == null)
      _initConvertisseurProxy();
    return convertisseur.EURToGBP(value);
  }
  
  
}