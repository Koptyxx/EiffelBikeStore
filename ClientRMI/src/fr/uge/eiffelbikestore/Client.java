package fr.uge.eiffelbikestore;

import fr.uge.eiffelbikestore.bike.IBike;
import fr.uge.eiffelbikestore.person.PersonUGE;
import fr.uge.eiffelbikestore.person.Student;
import fr.uge.eiffelbikestore.shop.IShop;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        try {
            IShop shop = (IShop) Naming.lookup("rmi://localhost/BikeShopService");
            PersonUGE studentJoe = new Student(1, "John", "Doe");
            PersonUGE studentJane = new Student(2, "Jane", "Dona");
            PersonUGE studentJosh = new Student(3, "Josh", "Donato");

            shop.addBike(1, studentJoe);
            var test1 = shop.rentRequest(studentJane, 1);
            System.out.println(shop.searchBike(1).getQueue());
            System.out.println(shop.searchBike(1).getTenant().getFirstName());
            var test2 = shop.rentRequest(studentJosh, 1);
            System.out.println(shop.searchBike(1).getQueue());
            shop.stopActualLocation(1);
            System.out.println(shop.searchBike(1).getQueue());
            System.out.println(shop.searchBike(1).getTenant().getFirstName());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}