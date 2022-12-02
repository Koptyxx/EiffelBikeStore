package fr.uge.eiffelbikestore.person;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Persons extends UnicastRemoteObject implements IPersons{

    private Map<Long, PersonUGE> persons;

    public Persons() throws RemoteException{
        super();
        this.persons = new HashMap<>();
    }

    @Override
    public Map<Long, PersonUGE> getPersons() throws RemoteException {
        return persons;
    }

    @Override
    public void setPersons(Map<Long, PersonUGE> persons) throws RemoteException {
        this.persons = persons;
    }

    @Override
    public long createPerson(long id, String firstName, String lastName, Status status) throws RemoteException {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        PersonUGE person = new Person(id, firstName, lastName, status);
        persons.put(person.getId(), person);
        return person.getId();
    }

    @Override
    public PersonUGE getPersonById(long id) throws RemoteException {
        return persons.get(id);
    }
}
