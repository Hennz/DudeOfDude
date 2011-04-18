package net.it_tim.dude_of_dude;

import java.io.IOException;
import java.net.InetAddress;

public class Ping
{
	private boolean status;
	private InetAddress address;
	
	public Ping(String URL, int timeout) throws Exception {
        try
        {
            address = InetAddress.getByName(URL);
            status = address.isReachable(timeout);
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
    }
    
    public boolean isOnline() {
    	return status;
    }

}