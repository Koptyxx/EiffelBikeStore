package fr.uge.eiffelbikestore.store;

import fr.uge.eiffelbikestore.bike.IBike;
import fr.uge.eiffelbikestore.person.PersonUGE;
import fr.uge.eiffelbikestore.transaction.RestitutionState;
import fr.uge.eiffelbikestore.transaction.Transaction;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

public interface IStore extends Remote {
    void addBike(long id, PersonUGE personUGE, int price) throws RemoteException;
    public IBike getBike(long id) throws RemoteException;
    void deleteBike(long id) throws RemoteException;
    List<IBike> lookBike() throws RemoteException;
    IBike searchBike(long id) throws RemoteException;
    public List<PersonUGE> lookPeople() throws RemoteException;
    public List<IBike> bikeLocator(long id) throws RemoteException;
    public boolean rentRequest(PersonUGE personUGE, long id) throws RemoteException;
    public void stopActualLocation(long id, RestitutionState restitutionState) throws RemoteException;
    public List<Transaction> getTransactionsHistory() throws RemoteException;
    public Set<IBike> getCanBeBuy() throws RemoteException;
    public long[] getCanBeBuyId() throws RemoteException;
    public long[] getCanBeBuyPrice() throws RemoteException;
}
