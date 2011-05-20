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
	public void stop() throws RemoteException {
		DudeOfDude.serverStop();
	}

	@Override
	public void start() throws RemoteException {
		DudeOfDude.serverStart();
	}

	@Override
	public boolean isStarted() throws RemoteException {
		return DudeOfDude.isStarted();
	}

}
