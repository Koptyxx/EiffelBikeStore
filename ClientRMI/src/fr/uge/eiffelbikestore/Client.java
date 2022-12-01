package fr.uge.eiffelbikestore;

import fr.uge.eiffelbikestore.bike.IBike;
import fr.uge.eiffelbikestore.person.PersonUGE;
import fr.uge.eiffelbikestore.person.Student;
import fr.uge.eiffelbikestore.store.IStore;
import fr.uge.eiffelbikestore.transaction.RestitutionState;

import java.rmi.Naming;
import java.util.List;
import java.util.Set;

public class Client {
    public static void main(String[] args) {
        try {
            IStore store = (IStore) Naming.lookup("rmi://localhost/BikeShopService");
            PersonUGE studentJoe = new Student(1, "John", "Doe");
            PersonUGE studentJane = new Student(2, "Jane", "Dona");
            PersonUGE studentJosh = new Student(3, "Josh", "Donato");
            PersonUGE studentSamy = new Student(4, "Samy", "Ghamri");
            PersonUGE studentXhavit = new Student(5, "Xhavit", "Osaj");
            PersonUGE studentBrayan = new Student(6, "Brayan", "Marie");

            store.addBike(1, studentJoe);
            var test1 = store.rentRequest(studentJane, 1);
            var test2 = store.rentRequest(studentJosh, 1);
            store.stopActualLocation(1, RestitutionState.AVERAGE);
            store.stopActualLocation(1, RestitutionState.GOOD);
            Set<IBike> bikes = store.getCanBeBuy();
            bikes.forEach(x -> System.out.println(x));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}