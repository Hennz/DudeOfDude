package net.it_tim.dude_of_dude.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerControl extends Remote {
	public void shutdown(String msg) throws RemoteException;
	public void stop() throws RemoteException;
	public void start() throws RemoteException;
	public boolean isStarted() throws RemoteException;
}
