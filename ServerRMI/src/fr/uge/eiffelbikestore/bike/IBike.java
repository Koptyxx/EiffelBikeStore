package fr.uge.eiffelbikestore.bike;

import fr.uge.eiffelbikestore.person.PersonUGE;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IBike extends Remote {

    public void setId(long id) throws RemoteException;
    public long getId() throws RemoteException;

    public void setOwner(PersonUGE owner) throws RemoteException;
    public PersonUGE getOwner() throws RemoteException;

    public void setTenant(PersonUGE tenant) throws RemoteException;
    public PersonUGE getTenant() throws RemoteException;

    public void setMark(List<Integer> marks) throws RemoteException;
    public void addMark(int mark) throws RemoteException;
    public List<Integer> getMarks() throws RemoteException;
}
