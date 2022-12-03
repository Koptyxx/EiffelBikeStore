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

    private RestitutionState parseRestitutionState(Integer value) {
        switch(value) {
            case 1: return RestitutionState.MISERABLE; break;
            case 2: return RestitutionState.BAD; break;
            case 3: return RestitutionState.AVERAGE; break;
            case 4: return RestitutionState.GOOD; break;
            case 5: return RestitutionState.EXCELLENT;
            default: return null; break;
        }
    }

    public static void main(String[] args) {



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
            while(true) {
                Scanner in = new Scanner(System.in);
                try {
                    var line = in.nextLine().split(" ");
                    if(line[0] == "add") {
                        var id = Long.parseLong(line[1]);
                        var idProprio = Long.parseLong(line[2]);
                        var price = Integer.parseInt(line[3]);
                        if(store.getBike(id) != null) {
                            System.out.println("A bike with the given id already exist !");
                            continue;
                        }
                        if(persons.getPersonById(idProprio) == null) {
                            System.out.println("The person with the given id does not exist !");
                            continue;
                        }
                        if(price < 0){
                            System.out.println("A bike price cannot be negative");
                            continue;
                        }
                        store.addBike(Long.parseLong(line[1)), Long.parseLong(line[2]), Integer.parseInt(line[3]));
                        continue;
                    }
                    if(line[0] == "rent") {
                        var idPerson = Long.parseLong(line[1]);
                        var idVelo = Long.parseLong(line[2]);
                        if(persons.getPersonById(idPerson) == null) {
                            System.out.println("The person with the given id does not exist");
                            continue;
                        }
                        if(store.getBike(idVelo) == null) {
                            System.out.println("The bike with the given id doesn not exist in the store !");
                            continue;
                        }
                        store.rentRequest(idPerson, idVelo);
                        continue;
                    }
                    if(line[0] == "stop") {
                        var personId = Long.parseLong(line[1]);
                        var restitutionState = parseRestitutionState(Integer.parseInt(line[2]));
                        if(person.getPersonById(idPerson) == null) {
                            System.out.println("The person with the given id does not exist");
                            continue;
                        }
                        if(restitutionState == null){
                            System.out.println("The restitution state should be between 1 and 5 !");
                            continue;
                        }
                        store.stopActualLocation(personId, restitutionState);
                    }
                    if(line[0] == "help") {
                        help();
                    }
                }
                catch (NumberFormatException numberFormatException) {
                    System.out.println("Wrong command format, a number was expected, make sure that you typed the good informations. If you need help, simply type 'help'");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}