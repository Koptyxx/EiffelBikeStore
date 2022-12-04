package fr.uge.eiffelbikestore;

import fr.uge.eiffelbikestore.bike.IBike;
import fr.uge.eiffelbikestore.person.IPersons;
import fr.uge.eiffelbikestore.person.PersonUGE;
import fr.uge.eiffelbikestore.person.Status;
import fr.uge.eiffelbikestore.store.IStore;
import fr.uge.eiffelbikestore.store.Store;
import fr.uge.eiffelbikestore.transaction.RestitutionState;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
public class StoreMain {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1100);
            IStore store = new Store();
            Naming.rebind("rmi://localhost:1100/Store", store);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}