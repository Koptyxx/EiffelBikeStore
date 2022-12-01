package fr.uge.eiffelbikestore.person;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface IPersons extends Remote {

    Map<Long, PersonUGE> getPersons() throws RemoteException;
    void setPersons(Map<Long, PersonUGE> persons) throws RemoteException;
    long createPerson(long id, String firstName, String lastName, Status status) throws RemoteException;

    PersonUGE getPersonById(long id) throws RemoteException;
}
