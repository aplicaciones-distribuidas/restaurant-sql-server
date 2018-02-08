package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import interfaces.NegocioTDA;
import rmi.NegocioManager;

public class Server {

	NegocioTDA remoteObject;

	public Server() {
		start();
	}

	private void start() {
		try {
			LocateRegistry.createRegistry(1098);
			remoteObject = new NegocioManager();
			Naming.rebind("//localhost:1098/restaurant", remoteObject);
			System.out.println("Binded to //localhost:1098/restaurant");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
