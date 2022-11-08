import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.stream.Collectors;

public class Shop extends UnicastRemoteObject implements IShop{

    private final Map<Long, Bike> bikes = new HashMap<>();
    private final Map<Long, PersonUGE> people = new HashMap<>();

    protected Shop() throws RemoteException {
        super();
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
    public List<Bike> bikeLocator(long id) throws RemoteException {
        return bikes.values().stream().filter(x -> {
            try {
                return x.getOwner().getId() == id;
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }




    /*@Override
    public Bike searchBike(long id) throws RemoteException {
        var bike = bikes.get(id);
        if(Objects.isNull(bike))
            throw new IllegalStateException();
        return bike;
    }*/

}
