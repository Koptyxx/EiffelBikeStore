package fr.uge.eiffelbikestore.transaction;

import fr.uge.eiffelbikestore.bike.IBike;
import fr.uge.eiffelbikestore.person.PersonUGE;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Transaction extends UnicastRemoteObject implements Remote {

    private final PersonUGE personUGE;
    private final IBike bike;

    public Transaction(PersonUGE personUGE, IBike bike) throws RemoteException {
        super();
        this.personUGE = personUGE;
        this.bike = bike;
    }

    @Override
    public String toString() {
        return "New transaction : " + personUGE + " rented " + bike;
    }
}
