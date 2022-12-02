package fr.uge.eiffelbikestore.shopWebService;

public class ShopProxy implements fr.uge.eiffelbikestore.shopWebService.Shop {
  private String _endpoint = null;
  private fr.uge.eiffelbikestore.shopWebService.Shop shop = null;
  
  public ShopProxy() {
    _initShopProxy();
  }
  
  public ShopProxy(String endpoint) {
    _endpoint = endpoint;
    _initShopProxy();
  }
  
  private void _initShopProxy() {
    try {
      shop = (new fr.uge.eiffelbikestore.shopWebService.ShopServiceLocator()).getShop();
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
  
  public fr.uge.eiffelbikestore.shopWebService.Shop getShop() {
    if (shop == null)
      _initShopProxy();
    return shop;
  }
  
  public fr.uge.eiffelbikestore.shopWebService.Client getClient(long id) throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    return shop.getClient(id);
  }
  
  public boolean getDispo(long id) throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    return shop.getDispo(id);
  }
  
  public fr.uge.eiffelbikestore.shopWebService.Bike getBike(long id) throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    return shop.getBike(id);
  }
  
  public void loadBike() throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    shop.loadBike();
  }
  
  public long getBikePrice(long id) throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    return shop.getBikePrice(id);
  }
  
  public boolean addToCart(long idClient, long idBike) throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    return shop.addToCart(idClient, idBike);
  }
  
  public fr.uge.eiffelbikestore.shopWebService.Bike[] allBike() throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    return shop.allBike();
  }
  
  public void validBuy(long idClient) throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    shop.validBuy(idClient);
  }
  
  public fr.uge.eiffelbikestore.shopWebService.Client[] allClients() throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    return shop.allClients();
  }
  
  public void registryClients(long id) throws java.rmi.RemoteException{
    if (shop == null)
      _initShopProxy();
    shop.registryClients(id);
  }
  
  
}