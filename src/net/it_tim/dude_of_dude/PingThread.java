package net.it_tim.dude_of_dude;

import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;

import net.it_tim.dude_of_dude.database.*;
import net.it_tim.dude_of_dude.static_constants.Tools;

public class PingThread extends TimerTask {

	private Hosts host;
	private boolean status = false;
	private PingHistoryHome phh = new PingHistoryHome();
	private PingHistory pinghistory = new PingHistory();
	private boolean last_status = false;
	
	public PingThread(Hosts host) {
		super();
		this.host = host;
	}
		
	@Override
	public synchronized void run() {
			pinghistory = phh.getLastState(host);
			last_status = pinghistory.getStatus().booleanValue();
			
			if (host.getToPing()) {
				ping();
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
	
	private void notificate(Boolean status) {
		NotificatioinsHistory nh = new NotificatioinsHistory();
		nh.setHosts(host);
		NotificatioinsHistoryHome nhh = new NotificatioinsHistoryHome();
		
		nh.setStatus(status);
		nh.setStamp(new Date());
		nhh.notificate(nh);
	}
	
	private void formatedPrint(String format, Object... args) {
		System.out.println(String.format(format, args));
	}
	
	private void ping() {
	try {
		long startTime = System.currentTimeMillis();
		Ping ping = new Ping(host.getIpAdres(), host.getTimeoutMs().intValue());
		Integer timeOut = new Long(System.currentTimeMillis() - startTime).intValue();

		status = ping.isOnline();
		log(new Boolean(status), timeOut);

		if (status != last_status){
			if (status) {
				formatedPrint(Tools.SVC_UP, host.getDescription(), host.getIpAdres(), timeOut, Tools.getDateTime());
				notificate(status);
			} else {
				formatedPrint(Tools.SVC_DOWN, host.getDescription(), host.getIpAdres(), timeOut, Tools.getDateTime());
				notificate(status);
			}
		}
		
	} catch (IOException e) {e.printStackTrace(); System.exit(0);}
	
	}
}
