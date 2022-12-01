package fr.uge.eiffelbikestore.bike;

import fr.uge.eiffelbikestore.person.PersonUGE;
import fr.uge.eiffelbikestore.transaction.RestitutionState;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Queue;

public interface IBike extends Remote {
    public void setId(long id) throws RemoteException;
    public long getId() throws RemoteException;
    public void setOwner(PersonUGE owner) throws RemoteException;
    public PersonUGE getOwner() throws RemoteException;
    public void setTenant(PersonUGE tenant) throws RemoteException;
    public PersonUGE getTenant() throws RemoteException;
    void setPrice(int price) throws RemoteException;
    int getPrice() throws RemoteException;
    public void setMark(List<Integer> marks) throws RemoteException;
    public void addMark(int mark) throws RemoteException;
    public List<Integer> getMarks() throws RemoteException;
    public void addPersonneToQueue(PersonUGE personUGE) throws RemoteException;
    public void endOfLocation(RestitutionState restitutionState) throws RemoteException;
    public Queue<PersonUGE> getQueue()throws RemoteException;
}
