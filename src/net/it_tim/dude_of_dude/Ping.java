package net.it_tim.dude_of_dude;


import java.io.IOException;
import java.net.InetAddress;

public class Ping
{
	private boolean status, packetLoss;
	private InetAddress address;
	private float good, bad, result;
	private int timeOut;
	private final int COUNT = 50;
	
	public Ping(String URL, int timeout, short packet_loss) throws IOException {
        try
        {
        	boolean tmp_status;
            address = InetAddress.getByName(URL);
            for (int i = 0; i < COUNT; i++){
            	long startTime = System.currentTimeMillis();
            	tmp_status = address.isReachable(null, 255, timeout);
    			timeOut += new Long(System.currentTimeMillis() - startTime).intValue();
            	try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (tmp_status) {
					good++;
				} else {
					bad++;
				}
            }
        } catch (IOException ex) {
        	throw ex;
        }
        
		if ((bad > 0)) {
			result = (bad / COUNT) * 100;
			if (result > packet_loss && result < 90.0) {
				packetLoss = true;
				status = false;
			} else  if (result > 90.0){
				packetLoss = false;
				status = false;
			} else {
				packetLoss = false;
				status = true;
			}
		} else {
			packetLoss = false;
			status = true;
		}
    }
    
    public boolean isOnline() {
    	return status;
    }
    
    public boolean isPacketLoss() {
    	return packetLoss;
    }
    
    public Integer getPacketLoss() {
    	return new Float(result).intValue();
    }

    public Integer getTimeout() {
    	return timeOut/COUNT;
    }
}