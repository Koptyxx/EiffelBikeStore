package fr.uge.eiffelbikestore;

import fr.uge.eiffelbikestore.person.PersonUGE;
import fr.uge.eiffelbikestore.person.Student;
import fr.uge.eiffelbikestore.shop.IShop;
import fr.uge.eiffelbikestore.transaction.RestitutionState;

import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        try {
            IShop shop = (IShop) Naming.lookup("rmi://localhost/BikeShopService");
            PersonUGE studentJoe = new Student(1, "John", "Doe");
            PersonUGE studentJane = new Student(2, "Jane", "Dona");
            PersonUGE studentJosh = new Student(3, "Josh", "Donato");
            PersonUGE studentSamy = new Student(4, "Samy", "Ghamri");
            PersonUGE studentXhavit = new Student(5, "Xhavit", "Osaj");
            PersonUGE studentBrayan = new Student(6, "Brayan", "Marie");

            shop.addBike(1, studentJoe);
            var test1 = shop.rentRequest(studentJane, 1);
            var test2 = shop.rentRequest(studentJosh, 1);
            shop.stopActualLocation(1, RestitutionState.AVERAGE);
            shop.stopActualLocation(1, RestitutionState.GOOD);
            System.out.println(shop.getTransactionsHistory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}