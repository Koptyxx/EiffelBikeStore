package fr.uge.eiffelbikestore.store;

import fr.uge.eiffelbikestore.bike.Bike;
import fr.uge.eiffelbikestore.bike.IBike;
import fr.uge.eiffelbikestore.person.PersonUGE;
import fr.uge.eiffelbikestore.transaction.RestitutionState;
import fr.uge.eiffelbikestore.transaction.Transaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.stream.Collectors;

public class Store extends UnicastRemoteObject implements IStore {

    private final Map<Long, Bike> bikes;
    private final Map<Long, PersonUGE> people = new HashMap<>();

    private final Set<IBike> canBeBuy = new HashSet<>();
    private final List<Transaction> transactionsHistory = new ArrayList<>();

    public Store() throws RemoteException {
        super();
        this.bikes = new HashMap<>();
    }

    @Override
    public IBike getBike(long id) throws RemoteException {
        return  bikes.get(id);
    }

    @Override
    public void addBike(long id, PersonUGE personUGE, int price) throws RemoteException {
        Objects.requireNonNull(personUGE);
        bikes.put(id, new Bike(id, personUGE, price));
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
        Bike bike = bikes.get(id);
        if(bike == null)
            throw new IllegalStateException();
        return bike;
    }
    @Override
    public boolean rentRequest(PersonUGE personUGE, long id) throws RemoteException {
        Bike bike = bikes.get(id);
        if(bike == null){
            throw new IllegalStateException();
        }
        if (bike.getTenant() == null)
            bike.setTenant(personUGE);
        else{
            bike.addPersonneToQueue(personUGE);
            return false;
        }
        transactionsHistory.add(new Transaction(personUGE, bike));
        return true;
    }

    @Override
    public void stopActualLocation(long id, RestitutionState restitutionState) throws RemoteException {
        Bike bike = bikes.get(id);
        if(bike == null){
            throw new IllegalStateException();
        }
        if (bike.getTenant() == null){
            throw new IllegalStateException();
        }
        bike.endOfLocation(restitutionState);
        canBeBuy.add(bike);
        if(bike.getTenant() != null) {
            transactionsHistory.add(new Transaction(bike.getTenant(), bike));
        }
    }

    @Override
    public List<Transaction> getTransactionsHistory() throws RemoteException {
        return (transactionsHistory);

    }


    @Override
    public Set<IBike> getCanBeBuy() throws RemoteException {
            return canBeBuy;

    }

    @Override
    public long[] getCanBeBuyId() throws RemoteException{
        long[] lstId = new long[canBeBuy.size()];
        int i = 0;
        for(IBike elem : canBeBuy){
            lstId[i] = elem.getId();
            i++;
        }
        return lstId;
    }

    @Override
    public long[] getCanBeBuyPrice() throws RemoteException {
        long[] lstId = new long[canBeBuy.size()];
        int i = 0;
        for(IBike elem : canBeBuy){
            lstId[i] = elem.getPrice();
            i++;
        }
        return lstId;
    }

}
