package fr.uge.eiffelbikestore.shop;

import fr.uge.eiffelbikestore.bike.IBike;
import fr.uge.eiffelbikestore.person.PersonUGE;
import fr.uge.eiffelbikestore.transaction.RestitutionState;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IShop extends Remote {
    void addBike(long id, PersonUGE personUGE) throws RemoteException;

    void deleteBike(long id) throws RemoteException;

    List<IBike> lookBike() throws RemoteException;

    IBike searchBike(long id) throws RemoteException;

    public List<PersonUGE> lookPeople() throws RemoteException;

    public List<IBike> bikeLocator(long id) throws RemoteException;

    public boolean rentRequest(PersonUGE personUGE, long id) throws RemoteException;

    public void stopActualLocation(long id, RestitutionState restitutionState) throws RemoteException;
}
