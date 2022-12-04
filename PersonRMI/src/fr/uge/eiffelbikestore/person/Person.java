package fr.uge.eiffelbikestore.person;

import fr.uge.eiffelbikestore.bike.IBike;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class Person extends UnicastRemoteObject implements PersonUGE {
    private long id;
    private String firstName;
    private String lastName;
    private Status status;
    private final Object lock = new Object();

    public Person() throws RemoteException {}

    public Person(long id, String firstName, String lastName, Status status) throws RemoteException {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    @Override
    public long getId() throws RemoteException {
        synchronized (lock) {
            return id;
        }
    }

    @Override
    public void setId(long id) throws RemoteException {
        synchronized (lock) {
            this.id = id;
        }
    }

    @Override
    public String getFirstName() throws RemoteException {
        synchronized (lock) {
            return firstName;
        }
    }

    @Override
    public void setFirstName(String firstName) throws RemoteException {
        synchronized (lock) {
            Objects.requireNonNull(firstName);
            this.firstName = firstName;
        }
    }

    @Override
    public String getLastName() throws RemoteException {
        synchronized (lock) {
            return lastName;
        }
    }

    @Override
    public void setLastName(String lastName) throws RemoteException {
        synchronized (lock) {
            Objects.requireNonNull(lastName);
            this.lastName = lastName;
        }
    }

    @Override
    public Status getStatus() throws RemoteException {
        synchronized (lock) {
            return this.status;
        }
    }

    @Override
    public void setStatus(Status status) throws RemoteException {
        synchronized (lock) {
            Objects.requireNonNull(status);
            this.status = status;
        }
    }

    @Override
    public void notifyChange(IBike bike) throws RemoteException {
        System.out.println(firstName + " "  + lastName + " vous êtes devenue le locataire du vélo numéro " + bike.getId());
    }


    @Override
    public String toString() {
        return "Student " + firstName + " " + lastName;
    }
}
