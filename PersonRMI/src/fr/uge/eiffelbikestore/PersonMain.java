package fr.uge.eiffelbikestore;

import fr.uge.eiffelbikestore.person.IPersons;
import fr.uge.eiffelbikestore.person.Persons;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class PersonMain {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            IPersons persons = new Persons();
            Naming.rebind("rmi://localhost/Persons", persons);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}