package fr.uge.eiffelbikestore.person;

import fr.uge.eiffelbikestore.bike.IBike;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PersonUGE extends Remote {

    long getId() throws RemoteException;
    void setId(long id) throws RemoteException;
    String getFirstName() throws RemoteException;
    void setFirstName(String firstName) throws RemoteException;
    String getLastName() throws RemoteException;
    void setLastName(String lastName) throws RemoteException;

    public void notifyChange(IBike bike) throws RemoteException;
}
