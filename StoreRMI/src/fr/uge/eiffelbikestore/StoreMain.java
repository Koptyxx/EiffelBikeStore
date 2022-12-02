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
import java.util.Set;

public class StoreMain {
    public static void main(String[] args) {
        try {
            IStore store = new Store();

            IPersons persons = (IPersons) Naming.lookup("rmi://localhost/Persons");
            long studentJoeId = persons.createPerson(1, "John", "Doe", Status.STUDENT);
            long studentJaneId = persons.createPerson(2, "Jane", "Dona", Status.STUDENT);
            long employeeJoshId = persons.createPerson(3, "Josh", "Donato", Status.EMPLOYEE);

            PersonUGE studentJoe = persons.getPersonById(studentJoeId);
            //PersonUGE studentJane = persons.getPersonById(studentJaneId);
            //PersonUGE employeeJosh = persons.getPersonById(employeeJoshId);
            store.addBike(1, studentJoe, 10);
            store.addBike(2, studentJoe, 20);

            boolean test1 = store.rentRequest(studentJoe, 1);
            boolean test2 = store.rentRequest(studentJoe, 2);
            store.stopActualLocation(1, RestitutionState.AVERAGE);
            store.stopActualLocation(2, RestitutionState.GOOD);
            /*Set<IBike> bikes = store.getCanBeBuy();
            bikes.forEach(System.out::println);*/

            LocateRegistry.createRegistry(1100);
            Naming.rebind("rmi://localhost:1100/Store", store);
            Arrays.stream(store.getCanBeBuyId()).forEach(System.out::println);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}