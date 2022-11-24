package fr.uge.eiffelbikestore.bike;

import fr.uge.eiffelbikestore.person.PersonUGE;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Bike extends UnicastRemoteObject implements IBike {

    private long id;
    private PersonUGE owner;
    private PersonUGE tenant;
    private List<Integer> marks;

    private final Queue<PersonUGE> queue = new ArrayDeque<>();

    public Bike() throws RemoteException {}

    public Bike(long id, PersonUGE owner) throws RemoteException {
        super();
        this.id = id;
        this.owner = owner;
        this.marks = new ArrayList<>();
    }

    public double getGlobalMark() {
        return marks.stream()
                .mapToDouble(a -> a)
                .average()
                .orElse(-1.0);
    }

    @Override
    public PersonUGE getTenant() {
        return tenant;
    }

    @Override
    public void setTenant(PersonUGE tenant) {
        Objects.requireNonNull(tenant);
        this.tenant = tenant;
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
    public void setOwner(PersonUGE owner) throws RemoteException {
        Objects.requireNonNull(owner);
        this.owner = owner;
    }

    @Override
    public PersonUGE getOwner() throws RemoteException {
        return owner;
    }
    @Override
    public void addPersonneToQueue(PersonUGE personUGE) throws RemoteException{
        Objects.requireNonNull(personUGE);
        queue.add(personUGE);
    }
    @Override
    public void endOfLocation() throws RemoteException{
        tenant = queue.poll();
    }

    @Override
    public Queue<PersonUGE> getQueue()throws RemoteException {
        return queue;
    }
}
