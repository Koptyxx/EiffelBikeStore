import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public interface IBike extends Remote {

    public void setId(long id) throws RemoteException;
    public long getId() throws RemoteException;

    public void setOwner(PersonUGE personUGE) throws RemoteException;
    public PersonUGE getOwner() throws RemoteException;

    public void setTenant(PersonUGE personUGE) throws RemoteException;
    public Optional<PersonUGE> getTenant() throws RemoteException;

    public void setMark(List<Integer> marks) throws RemoteException;
    public void addMark(int mark) throws RemoteException;
    public List<Integer> getMarks() throws RemoteException;

}
