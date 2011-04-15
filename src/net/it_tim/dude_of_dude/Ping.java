package net.it_tim.dude_of_dude;

import java.net.InetAddress;

import com.sun.security.auth.module.UnixSystem;

public class Ping
{
	private boolean status;
	private InetAddress address;
	private long uid = -1;
	
	public Ping(String URL, int timeout) throws SecurityException {
        try
        {
        	UnixSystem user = new UnixSystem();
        	uid = user.getUid();
        	
        	if (uid != 0) {
        		throw new SecurityException();
        	}

            address = InetAddress.getByName(URL);
            status = address.isReachable(timeout);
        } catch (SecurityException e) {
            System.out.println("!!!Useer must be root!!!");
            System.exit(-1);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
    }
    
    public boolean isOnline() {
    	return status;
    }

}