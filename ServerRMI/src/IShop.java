import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IShop extends Remote {
    public void addBike(long id, PersonUGE personUGE) throws RemoteException;
    public void deleteBike(long id) throws RemoteException;

    public List<IBike> lookBike() throws RemoteException;
    public List<PersonUGE> lookPeople() throws RemoteException;
    public List<Bike> bikeLocator(long id) throws RemoteException;
}
