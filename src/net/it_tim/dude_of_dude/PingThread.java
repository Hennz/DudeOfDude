package net.it_tim.dude_of_dude;

import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;

import net.it_tim.dude_of_dude.database.*;
import net.it_tim.dude_of_dude.static_constants.Message;

public class PingThread extends TimerTask {

	private Hosts host;
	private boolean status = false;
	private PingHistoryHome phh = new PingHistoryHome();
	private PingHistory ph = new PingHistory();
	private boolean last_status = false;
	
	public PingThread(Hosts host) {
		super();
		this.host = host;
	}
		
	@Override
	public synchronized void run() {
			Ping ping;
			ph = phh.getLastState(host);
			last_status = ph.getStatus();
			try {
			long startTime = System.currentTimeMillis();

			ping = new Ping(host.getIpAdres(), host.getTimeoutMs().intValue());

			Integer timeOut = new Long(System.currentTimeMillis() - startTime).intValue();
			if (ping.isOnline()) {
				status = true;
				log(new Boolean(status), timeOut);
			} else {
				status = false;
				log(new Boolean(status), timeOut);
			} 
			
			if (status != last_status){
				if (status) {
					formatedPrint(Message.SVC_UP, host.getDescription(), host.getIpAdres(), timeOut, Message.getDateTime());
				} else {
					formatedPrint(Message.SVC_DOWN, host.getDescription(), host.getIpAdres(), timeOut, Message.getDateTime());
				}

			}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	private void log(Boolean status, Integer timeOut) {
		PingHistory ph = new PingHistory();
		ph.setHosts(host);
		PingHistoryHome phh = new PingHistoryHome();
		
		ph.setStatus(status);
		ph.setStamp(new Date());
		ph.setTimeout(timeOut);
		phh.log_ping(ph);
	}
	
	private void formatedPrint(String format, Object... args) {
		System.out.println(String.format(format, args));
	}
}
