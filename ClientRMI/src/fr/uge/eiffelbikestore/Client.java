package fr.uge.eiffelbikestore;

import fr.uge.eiffelbikestore.person.PersonUGE;
import fr.uge.eiffelbikestore.person.Student;
import fr.uge.eiffelbikestore.shop.IShop;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        try {
            IShop shop = (IShop) Naming.lookup("rmi://localhost/BikeShopService");
            PersonUGE student = new Student(1, "John", "Doe");
            shop.addBike(1, student);
            shop.lookBike().forEach(x -> {
                try {
                    System.out.println("bike ID : " + x.getId());
                    System.out.println("owner ID : " + x.getOwner().getId());
                    System.out.println("owner firstname : " + x.getOwner().getFirstName());
                    System.out.println("owner lastname : " + x.getOwner().getLastName());
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}