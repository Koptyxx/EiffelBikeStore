package fr.uge.eiffelbikestore.person;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class Employee extends UnicastRemoteObject implements PersonUGE {

    private long id;
    private String firstName;
    private String lastName;

    public Employee() throws RemoteException {}

    public Employee(long id, String firstName, String lastName) throws RemoteException{
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public long getId() throws RemoteException {
        return id;
    }

    @Override
    public void setId(long id) throws RemoteException {
        this.id = id;
    }

    @Override
    public String getFirstName() throws RemoteException {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) throws RemoteException {
        Objects.requireNonNull(firstName);
        this.firstName = firstName;
    }

    @Override
    public String getLastName() throws RemoteException{
        return lastName;
    }

    @Override
    public void setLastName(String lastName) throws RemoteException {
        Objects.requireNonNull(lastName);
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Employee " + firstName + " " + lastName;}
}
