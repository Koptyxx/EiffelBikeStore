package fr.uge.eiffelbikestore;

import fr.uge.eiffelbikestore.shop.IShop;
import fr.uge.eiffelbikestore.shop.Shop;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            IShop shop = new Shop();
            Naming.rebind("rmi://localhost/BikeShopService", shop);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}