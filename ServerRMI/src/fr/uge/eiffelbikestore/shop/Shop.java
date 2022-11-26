package fr.uge.eiffelbikestore.shop;

import fr.uge.eiffelbikestore.bike.Bike;
import fr.uge.eiffelbikestore.bike.IBike;
import fr.uge.eiffelbikestore.person.PersonUGE;
import fr.uge.eiffelbikestore.transaction.RestitutionState;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.stream.Collectors;

public class Shop extends UnicastRemoteObject implements IShop{

    private final Map<Long, Bike> bikes;
    private final Map<Long, PersonUGE> people = new HashMap<>();

    public Shop() throws RemoteException {
        super();
        this.bikes = new HashMap<>();
    }

    @Override
    public void addBike(long id, PersonUGE personUGE) throws RemoteException {
        Objects.requireNonNull(personUGE);
        bikes.put(id, new Bike(id, personUGE));
    }

    @Override
    public void deleteBike(long id) throws RemoteException {
        bikes.remove(id);
    }

    @Override
    public List<IBike> lookBike() throws RemoteException {
        return new ArrayList<>(bikes.values());
    }

    @Override
    public List<PersonUGE> lookPeople() throws RemoteException {
        return new ArrayList<>(people.values());
    }

    @Override
    public List<IBike> bikeLocator(long id) throws RemoteException {
        return bikes.values().stream().filter(x -> {
            try {
                return x.getOwner().getId() == id;
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public Bike searchBike(long id) throws RemoteException {
        var bike = bikes.get(id);
        if(bike == null)
            throw new IllegalStateException();
        return bike;
    }
    @Override
    public boolean rentRequest(PersonUGE personUGE, long id) throws RemoteException {
        var bike = bikes.get(id);
        if(bike == null){
            throw new IllegalStateException();
        }
        if (bike.getTenant() == null)
            bike.setTenant(personUGE);
        else{
            bike.addPersonneToQueue(personUGE);
            return false;
        }
        return true;
    }

    @Override
    public void stopActualLocation(long id, RestitutionState restitutionState) throws RemoteException {
        var bike = bikes.get(id);
        if(bike == null){
            throw new IllegalStateException();
        }
        if (bike.getTenant() == null){
            throw new IllegalStateException();
        }
        bike.endOfLocation(restitutionState);
    }

}
