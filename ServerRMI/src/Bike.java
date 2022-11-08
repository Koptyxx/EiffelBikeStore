import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Bike extends UnicastRemoteObject implements IBike {

    private long id;
    private PersonUGE owner;
    private Optional<PersonUGE> tenant;
    private List<Integer> marks;

    public Bike() throws RemoteException {}

    public Bike(long id, PersonUGE owner) throws RemoteException {
        super();
        this.id = id;
        this.owner = owner;
        this.tenant = Optional.empty();
        this.marks = new ArrayList<>();
    }

    /*public double getGlobalMark() {
        return marks.stream().mapToDouble(a -> a).average().orElse(-1.0);
    }*/

    @Override
    public Optional<PersonUGE> getTenant() {
        return tenant;
    }

    @Override
    public void setTenant(PersonUGE tenant) {
        this.tenant = Optional.ofNullable(tenant);
    }

    @Override
    public void setMark(List<Integer> marks) throws RemoteException {
        Objects.requireNonNull(marks);
        this.marks = marks;
    }

    @Override
    public void addMark(int mark){
        if(mark < 0 || mark > 5){
            System.out.println("Your mark has to be between 0 and 5 !");
            return;
        }
        marks.add(mark);
    }
    @Override
    public List<Integer> getMarks() throws RemoteException {
        return List.copyOf(marks);
    }

    @Override
    public void setId(long id) throws RemoteException {
        this.id = id;
    }

    @Override
    public long getId() throws RemoteException {
        return id;
    }

    @Override
    public void setOwner(PersonUGE personUGE) throws RemoteException {
        Objects.requireNonNull(personUGE);
        this.owner = personUGE;
    }

    @Override
    public PersonUGE getOwner() throws RemoteException {
        return owner;
    }



}
