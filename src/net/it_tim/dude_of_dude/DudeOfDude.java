package net.it_tim.dude_of_dude;

import javax.mail.*;

import net.it_tim.dude_of_dude.db_classes.*;

public class DudeOfDude {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HostsHome hh = new HostsHome();
		Hosts hosts = new Hosts();
		hosts.setDescription("AXIS");
		hosts.setIntervalMs(new Long(60000));
		hosts.setIpAdres("192.168.33.110");
		hosts.setTimeoutMs(new Long(10000));
		hosts.setToPing(new Boolean(true));
		hh.add(hosts);
		String message = String.format(net.it_tim.dude_of_dude.static_constants.Message.SVC_UP, "Barn-e", "192.168.15.40", "13.00");
		System.out.println(message);
		Ping ping = new Ping("192.168.77.7", 1000);
		if (ping.isOnline()) {
			System.out.println("Service is online");
		} else {
			String recs[] = { "gofl@meta.ua" /*, "380676589174@sms.kyivstar.net"*/ };
			try {
				MailSender.postMail(recs, "Test msg", "Dude fuck's your Cisco!", "Anal@nosorog.net");
				System.out.println("Service is offline");
			} catch (MessagingException ex) {
				ex.printStackTrace();
			}
		}
	}

}
