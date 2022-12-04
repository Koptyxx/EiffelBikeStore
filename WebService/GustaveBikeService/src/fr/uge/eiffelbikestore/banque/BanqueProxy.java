package fr.uge.eiffelbikestore.banque;

public class BanqueProxy implements fr.uge.eiffelbikestore.banque.Banque {
  private String _endpoint = null;
  private fr.uge.eiffelbikestore.banque.Banque banque = null;
  
  public BanqueProxy() {
    _initBanqueProxy();
  }
  
  public BanqueProxy(String endpoint) {
    _endpoint = endpoint;
    _initBanqueProxy();
  }
  
  private void _initBanqueProxy() {
    try {
      banque = (new fr.uge.eiffelbikestore.banque.BanqueServiceLocator()).getBanque();
      if (banque != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)banque)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)banque)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (banque != null)
      ((javax.xml.rpc.Stub)banque)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public fr.uge.eiffelbikestore.banque.Banque getBanque() {
    if (banque == null)
      _initBanqueProxy();
    return banque;
  }
  
  public long[] getClientId() throws java.rmi.RemoteException{
    if (banque == null)
      _initBanqueProxy();
    return banque.getClientId();
  }
  
  public int nbCLient() throws java.rmi.RemoteException{
    if (banque == null)
      _initBanqueProxy();
    return banque.nbCLient();
  }
  
  public void addClient(long idClient, double montant) throws java.rmi.RemoteException{
    if (banque == null)
      _initBanqueProxy();
    banque.addClient(idClient, montant);
  }
  
  public double getArgentClient(long idClient) throws java.rmi.RemoteException{
    if (banque == null)
      _initBanqueProxy();
    return banque.getArgentClient(idClient);
  }
  
  public void deleteClient(long client) throws java.rmi.RemoteException{
    if (banque == null)
      _initBanqueProxy();
    banque.deleteClient(client);
  }
  
  public void addMontant(long idClient, double montant) throws java.rmi.RemoteException{
    if (banque == null)
      _initBanqueProxy();
    banque.addMontant(idClient, montant);
  }
  
  public boolean isPossible(long idClient, double montant) throws java.rmi.RemoteException{
    if (banque == null)
      _initBanqueProxy();
    return banque.isPossible(idClient, montant);
  }
  
  public void subMontant(long idClient, double montant) throws java.rmi.RemoteException{
    if (banque == null)
      _initBanqueProxy();
    banque.subMontant(idClient, montant);
  }
  
  
}