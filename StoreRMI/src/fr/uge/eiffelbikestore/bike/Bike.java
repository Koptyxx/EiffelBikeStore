package fr.uge.eiffelbikestore.bike;

import fr.uge.eiffelbikestore.person.PersonUGE;
import fr.uge.eiffelbikestore.transaction.RestitutionState;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Bike extends UnicastRemoteObject implements IBike {

    private long id;
    private PersonUGE owner;
    private PersonUGE tenant;
    private List<Integer> marks;
    private final Queue<PersonUGE> queue = new ArrayDeque<>();
    private int price;
    private final Map<PersonUGE, List<RestitutionState>> restitutionStateByPerson;
    public Bike(long id, PersonUGE owner, int price) throws RemoteException {
        super();
        this.id = id;
        this.owner = owner;
        this.price = price;
        this.marks = new ArrayList<>();
        this.restitutionStateByPerson = new HashMap<>();
    }
    public double getGlobalMark() {
        synchronized (queue) {
            return marks.stream()
                    .mapToDouble(a -> a)
                    .average()
                    .orElse(-1.0);
        }
    }
    @Override
    public PersonUGE getTenant() {
        synchronized (queue) {
            return tenant;
        }
    }

    @Override
    public void setTenant(PersonUGE tenant) {
        synchronized (queue) {
            Objects.requireNonNull(tenant);
            this.tenant = tenant;
            restitutionStateByPerson.putIfAbsent(tenant, new ArrayList<>());
            try {
                this.tenant.notifyChange(this);
            } catch (RemoteException e){
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void setMark(List<Integer> marks) throws RemoteException {
        synchronized (queue) {
            Objects.requireNonNull(marks);
            this.marks = marks;
        }
    }

    @Override
    public void addMark(int mark){
        synchronized (queue) {
            if(mark < 0 || mark > 5){
                System.out.println("Your mark has to be between 0 and 5 !");
                return;
            }
            marks.add(mark);
        }
    }
    @Override
    public List<Integer> getMarks() throws RemoteException {
        synchronized (queue) {
            return marks;
        }
    }

    @Override
    public void setId(long id) throws RemoteException {
        synchronized (queue) {
            this.id = id;
        }
    }

    @Override
    public long getId() throws RemoteException {
        synchronized (queue) {
            return id;
        }
    }

    @Override
    public void setOwner(PersonUGE owner) throws RemoteException {
        synchronized (queue) {
            Objects.requireNonNull(owner);
            this.owner = owner;
        }
    }
    @Override
    public PersonUGE getOwner() throws RemoteException {
        synchronized (queue) {
            return owner;
        }
    }
    @Override
    public int getPrice() {
        synchronized (queue) {
            return price;
        }
    }
    @Override
    public void setPrice(int price) {
        synchronized (queue) {
            this.price = price;
        }
    }

    @Override
    public void addPersonneToQueue(PersonUGE personUGE) throws RemoteException{
        synchronized (queue) {
            Objects.requireNonNull(personUGE);
            queue.add(personUGE);
        }
    }
    @Override
    public void endOfLocation(RestitutionState restitutionState) throws RemoteException{
        synchronized (queue) {
            PersonUGE headQueue = queue.poll();
            restitutionStateByPerson.get(tenant).add(restitutionState);
            if(headQueue != null){
                setTenant(headQueue);
            }
        }
    }

    @Override
    public Queue<PersonUGE> getQueue()throws RemoteException {
        synchronized (queue) {
            return queue;
        }
    }

    @Override
    public String toString() {
        return "Bike : id " + id + " marks "
                + marks + " price "
                + price;
    }
}
