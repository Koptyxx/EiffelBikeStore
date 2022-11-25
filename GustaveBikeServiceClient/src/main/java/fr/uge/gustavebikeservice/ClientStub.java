package fr.uge.gustavebikeservice;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class ClientStub {
	public static void main(String[] args) throws ServiceException, RemoteException {
		Hello service = new HelloServiceLocator().getHello();
		System.out.println(service.sayHello("Samy The First"));
	}
}