package fr.uge.eiffelbikestore;

import fr.uge.eiffelbikestore.person.Student;
import fr.uge.eiffelbikestore.store.IStore;
import fr.uge.eiffelbikestore.store.Store;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            IStore store = new Store();
            store.addBike(1, new Student(1, "Brayan", "marie"));
            Naming.rebind("rmi://localhost/BikeShopService", store);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}