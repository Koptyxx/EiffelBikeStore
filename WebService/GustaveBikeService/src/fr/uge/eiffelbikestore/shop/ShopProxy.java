package fr.uge.eiffelbikestore.shop;

public class ShopProxy implements fr.uge.eiffelbikestore.shop.Shop {
  private String _endpoint = null;
  private fr.uge.eiffelbikestore.shop.Shop shop = null;
  
  public ShopProxy() {
    _initShopProxy();
  }
  
  public ShopProxy(String endpoint) {
    _endpoint = endpoint;
    _initShopProxy();
  }
  
  private void _initShopProxy() {
    try {
      shop = (new fr.uge.eiffelbikestore.shop.ShopServiceLocator()).getShop();
      if (shop != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)shop)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)shop)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (shop != null)
      ((javax.xml.rpc.Stub)shop)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public fr.uge.eiffelbikestore.shop.Shop getShop() {
    if (shop == null)
      _initShopProxy();
    return shop;
  }
  
  public fr.uge.eiffelbikestore.shop.Client getClient(long id) throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    return shop.getClient(id);
  }
  
  public void loadBike() throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    shop.loadBike();
  }
  
  public fr.uge.eiffelbikestore.shop.Client[] allClients() throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    return shop.allClients();
  }
  
  public boolean getDispo(long id) throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    return shop.getDispo(id);
  }
  
  public boolean addToCart(long idClient, long idBike) throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    return shop.addToCart(idClient, idBike);
  }
  
  public void validBuy(long idClient) throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    shop.validBuy(idClient);
  }
  
  public fr.uge.eiffelbikestore.shop.Bike getBike(long id) throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    return shop.getBike(id);
  }
  
  public fr.uge.eiffelbikestore.shop.Bike[] allBike() throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    return shop.allBike();
  }
  
  public void registryClients(long id, int devise) throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    shop.registryClients(id, devise);
  }
  
  public long getBikePrice(long id) throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    return shop.getBikePrice(id);
  }
  
  public java.lang.String getClientDevise(long idClient) throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    return shop.getClientDevise(idClient);
  }
  
  public double cartValueConverted(long idClient) throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    return shop.cartValueConverted(idClient);
  }
  
  
}