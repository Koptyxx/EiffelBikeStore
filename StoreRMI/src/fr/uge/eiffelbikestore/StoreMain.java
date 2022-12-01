package fr.uge.eiffelbikestore;

import fr.uge.eiffelbikestore.bike.IBike;
import fr.uge.eiffelbikestore.person.IPersons;
import fr.uge.eiffelbikestore.person.PersonUGE;
import fr.uge.eiffelbikestore.person.Status;
import fr.uge.eiffelbikestore.store.IStore;
import fr.uge.eiffelbikestore.store.Store;
import fr.uge.eiffelbikestore.transaction.RestitutionState;

import java.rmi.Naming;
import java.util.Set;

public class StoreMain {
    public static void main(String[] args) {
        try {
            //LocateRegistry.createRegistry(1099);
            IStore store = new Store();
            IPersons persons = (IPersons) Naming.lookup("rmi://localhost/Persons");
            long studentJoeId = persons.createPerson(1, "John", "Doe", Status.STUDENT);
            long studentJaneId = persons.createPerson(2, "Jane", "Dona", Status.STUDENT);
            long employeeJoshId = persons.createPerson(3, "Josh", "Donato", Status.EMPLOYEE);

            PersonUGE studentJoe = persons.getPersonById(studentJoeId);
            PersonUGE studentJane = persons.getPersonById(studentJaneId);
            PersonUGE employeeJosh = persons.getPersonById(employeeJoshId);
            store.addBike(1, studentJoe);
            var test1 = store.rentRequest(studentJane, 1);
            var test2 = store.rentRequest(employeeJosh, 1);
            store.stopActualLocation(1, RestitutionState.AVERAGE);
            store.stopActualLocation(1, RestitutionState.GOOD);
            Set<IBike> bikes = store.getCanBeBuy();
            bikes.forEach(System.out::println);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}