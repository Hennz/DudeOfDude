package net.it_tim.dude_of_dude.rmi;

import java.rmi.RemoteException;

import net.it_tim.dude_of_dude.DudeOfDude;

public class ServerControlImp implements ServerControl {

	public ServerControlImp() throws RemoteException {
		super();
	}

	@Override
	public void shutdown(String msg) throws RemoteException {
		DudeOfDude.serverShutdown(msg);
	}

	@Override
	public void stop(String msg) throws RemoteException {
		DudeOfDude.serverStop();
	}

}
