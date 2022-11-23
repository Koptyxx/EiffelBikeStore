package fr.uge.eiffelbikestore.shop;

import fr.uge.eiffelbikestore.bike.Bike;
import fr.uge.eiffelbikestore.bike.IBike;
import fr.uge.eiffelbikestore.person.PersonUGE;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IShop extends Remote {
    void addBike(long id, PersonUGE personUGE) throws RemoteException;
    void deleteBike(long id) throws RemoteException;
    List<IBike> lookBike() throws RemoteException;
    Bike searchBike(long id) throws RemoteException;
    public List<PersonUGE> lookPeople() throws RemoteException;
    public List<Bike> bikeLocator(long id) throws RemoteException;
}
