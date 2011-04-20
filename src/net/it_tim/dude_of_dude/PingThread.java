package net.it_tim.dude_of_dude;

import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;

import net.it_tim.dude_of_dude.database.*;
import net.it_tim.dude_of_dude.static_constants.Message;

public class PingThread extends TimerTask {

	private Hosts host;
	
	public PingThread(Hosts host) {
		super();
		this.host = host;
	}
	
	@Override
	public synchronized void run() {
			Ping ping;
			PingHistory ph = new PingHistory();
			ph.setHosts(host);
			PingHistoryHome phh = new PingHistoryHome();
			try {
			long startTime = System.currentTimeMillis();
			ping = new Ping(host.getIpAdres(), host.getTimeoutMs().intValue());
			long timeOut = System.currentTimeMillis() - startTime;
			if (ping.isOnline()) {
				System.out
						.println(String
								.format(
										net.it_tim.dude_of_dude.static_constants.Message.SVC_UP,
										host.getDescription(), host.getIpAdres(), timeOut, Message.getDateTime()));

				ph.setStatus(true);
				ph.setStamp(new Date());
				ph.setTimeout(new Long(timeOut).intValue());
				phh.log_ping(ph);
			} else {
				System.out
				.println(String
						.format(
								net.it_tim.dude_of_dude.static_constants.Message.SVC_DOWN,
								host.getDescription(), host.getIpAdres(), timeOut, Message.getDateTime()));

				ph.setStatus(false);
				ph.setStamp(new Date());
				ph.setTimeout(new Long(timeOut).intValue());
				phh.log_ping(ph);
			} 
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
