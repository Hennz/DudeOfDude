package net.it_tim.dude_of_dude;

import net.it_tim.dude_of_dude.db_classes.Hosts;

public class PingThread implements Runnable {

	private Hosts host;
	
	public PingThread(Hosts host) {
		super();
		this.host = host;
	}
	
	@Override
	public void run() {
		Ping ping;
		try {
		long startTime = System.currentTimeMillis();
		ping = new Ping(host.getIpAdres(), host.getTimeoutMs().intValue());
		long timeOut = System.currentTimeMillis() - startTime;
		if (ping.isOnline()) {
			System.out
					.println(String
							.format(
									net.it_tim.dude_of_dude.static_constants.Message.SVC_UP,
									host.getDescription(), host.getIpAdres(), timeOut, "now"));
		} else {
			System.out
			.println(String
					.format(
							net.it_tim.dude_of_dude.static_constants.Message.SVC_DOWN,
							host.getDescription(), host.getIpAdres(), timeOut, "now"));
		} } catch (Exception e) {
			e.printStackTrace();
		}
	}

}
