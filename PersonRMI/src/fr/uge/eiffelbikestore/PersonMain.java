package fr.uge.eiffelbikestore;

import fr.uge.eiffelbikestore.person.IPersons;
import fr.uge.eiffelbikestore.person.Persons;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class PersonMain {

    private Status parseStatus(Integer value) {
        switch(value) {
            case 1: return Status.STUDENT; break;
            case 2: return Status.EMPLOYEE; break;
            default: break;
        }
    }

    private void help() {
        System.out.println("Welcome to our application !\nHere are the commands that are available :\n" +
                "[add id firstName lastName status] in order to add a new person to the ");
    }
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            IPersons persons = new Persons();
            Naming.rebind("rmi://localhost/Persons", persons);
            while(true) {
                Scanner in = new Scanner(System.in);
                try {
                    var line = in.nextLine().split(" ");
                    if(line[0] == "add") {
                        var personId = Long.parseLong(line[1]);
                        var firstName = line[2];
                        var lastName = line[3];
                        var status = Integer.parseInt(line[4]);
                        if(persons.getPersonById(personId) != null) {
                            System.out.println("The given id already represent a person !");
                            continue;
                        }
                        if(status != 1 && status != 2) {
                            System.out.println("You have to choose to be either a student or an employee");
                            continue;
                        }
                        persones.createPerson(personId, firstName, lastName, status);
                    }
                    if(line[0] == "help") {
                        help();
                        continue:
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