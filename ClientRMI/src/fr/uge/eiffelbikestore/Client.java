package fr.uge.eiffelbikestore;

import fr.uge.eiffelbikestore.bike.IBike;
import fr.uge.eiffelbikestore.person.IPersons;
import fr.uge.eiffelbikestore.person.PersonUGE;
import fr.uge.eiffelbikestore.person.Status;
import fr.uge.eiffelbikestore.store.IStore;
import fr.uge.eiffelbikestore.transaction.RestitutionState;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Objects;
import java.util.Scanner;

public class Client {

    private static Status parseStatus(Integer value) {
        switch(value) {
            case 1: return Status.STUDENT;
            case 2: return Status.EMPLOYEE;
            default: return null;
        }
    }

    private static RestitutionState parseRestitutionState(Integer value) {
        switch(value) {
            case 1: return RestitutionState.MISERABLE;
            case 2: return RestitutionState.BAD;
            case 3: return RestitutionState.AVERAGE;
            case 4: return RestitutionState.GOOD;
            case 5: return RestitutionState.EXCELLENT;
            default: return null;
        }
    }

    private static void help() {
        System.out.println("Welcome to our application !\n" +
                "Here are the commands that are available :\n" +
                "[addBike id idProprio price] in order to add a bike to the store\n" +
                "[rent idPerson idBike] in order to make a person renting a bike\n" +
                "[stop idPerson restitutionState] in order to make a person stop renting a bike\n" +
                "[addUser id firstName lastName status] in order to add a new person to the ");
    }

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        IPersons persons = (IPersons) Naming.lookup("rmi://localhost/Persons");
        IStore store = (IStore) Naming.lookup("rmi://localhost:1100/Store");
        while(true) {
            Scanner in = new Scanner(System.in);
            try {
                String[] line = in.nextLine().split(" ");
                if(Objects.equals(line[0], "addUser")) {
                    long personId = Long.parseLong(line[1]);
                    String firstName = line[2];
                    String lastName = line[3];
                    Status status = parseStatus(Integer.parseInt(line[4]));
                    if(persons.getPersonById(personId) != null) {
                        System.out.println("The given id already represent a person !");
                        continue;
                    }
                    if(status == null) {
                        System.out.println("You have to choose to be either a student or an employee");
                        continue;
                    }
                    persons.createPerson(personId, firstName, lastName, status);
                    System.out.println("L'utilisateur " + firstName + " " + lastName + " d'id " + personId + " a été ajouté à la base avec succès !");
                }
                if(Objects.equals(line[0], "addBike")) {
                    long id = Long.parseLong(line[1]);
                    PersonUGE proprio = persons.getPersonById(Long.parseLong(line[2]));
                    int price = Integer.parseInt(line[3]);
                    if(store.getBike(id) != null) {
                        System.out.println("A bike with the given id already exist !");
                        continue;
                    }
                    if(proprio == null) {
                        System.out.println("The person with the given id does not exist !");
                        continue;
                    }
                    if(price < 0){
                        System.out.println("A bike price cannot be negative");
                        continue;
                    }
                    store.addBike(id, proprio, price);
                    System.out.println("Le vélo d'id " + id + " a été ajouté avec succès !");
                    continue;
                }
                if(Objects.equals(line[0], "rent")) {
                    PersonUGE person = persons.getPersonById(Long.parseLong(line[1]));
                    IBike bike = store.getBike(Long.parseLong(line[2]));
                    if(person == null) {
                        System.out.println("The person with the given id does not exist");
                        continue;
                    }
                    if(bike == null) {
                        System.out.println("The bike with the given id doesn't not exist in the store !");
                        continue;
                    }
                    store.rentRequest(person, Long.parseLong(line[2]));
                    System.out.println("La demande de location de " + person.getFirstName() + " " + person.getLastName() + " a été prise en compte.");
                    continue;
                }
                if(Objects.equals(line[0], "stop")) {
                    IBike bike = store.getBike(Long.parseLong(line[1]));
                    RestitutionState restitutionState = parseRestitutionState(Integer.parseInt(line[2]));
                    if(bike == null) {
                        System.out.println("The bike with the given id does not exist");
                        continue;
                    }
                    if(restitutionState == null){
                        System.out.println("The restitution state should be between 1 and 5 !");
                        continue;
                    }
                    store.stopActualLocation(Long.parseLong(line[1]), restitutionState);
                    System.out.println("La location acutelle du vélo " + bike.getId() + " a été arretée.");
                }
                if(Objects.equals(line[0], "help")) {
                    help();
                }
            }
            catch (NumberFormatException numberFormatException) {
                System.out.println("Wrong command format, a number was expected, make sure that you typed the good informations. If you need help, simply type 'help'");
            }
        }
    }
}
