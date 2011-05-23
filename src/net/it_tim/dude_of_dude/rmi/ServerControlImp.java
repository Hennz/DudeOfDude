package net.it_tim.dude_of_dude.rmi;

import java.rmi.RemoteException;

import net.it_tim.dude_of_dude.Server;

public class ServerControlImp implements ServerControl {

	public ServerControlImp() throws RemoteException {
		super();
	}

	@Override
	public void shutdown(String msg) throws RemoteException {
		Server.serverShutdown(msg);
	}

	@Override
	public void stop() throws RemoteException {
		Server.serverStop();
	}

	@Override
	public void start() throws RemoteException {
		Server.serverStart();
	}

	@Override
	public boolean isStarted() throws RemoteException {
		return Server.isStarted();
	}

}
