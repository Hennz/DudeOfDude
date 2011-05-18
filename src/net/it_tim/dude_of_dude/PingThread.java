package net.it_tim.dude_of_dude;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TimerTask;

import javax.mail.MessagingException;

import net.it_tim.dude_of_dude.database.*;
import net.it_tim.dude_of_dude.static_constants.Tools;

public class PingThread extends TimerTask {

	private Hosts host;
	private boolean status = false;
	private boolean packetLoss = false;
	private PingHistoryHome phh = new PingHistoryHome();
	private PingHistory pinghistory = new PingHistory();
	private boolean last_status = true;
	private int down_count = 0;

	public PingThread(Hosts host) {
		super();
		this.host = host;
	}

	@Override
	public synchronized void run() {
		pinghistory = phh.getLastState(host);
		if (pinghistory != null) {
			last_status = pinghistory.getStatus().booleanValue();
		} else {
			last_status = true;
		}
			ping();
	}

	private synchronized void log(Boolean status, Integer timeOut) {
		PingHistory ph = new PingHistory();
		ph.setHosts(host);
		PingHistoryHome phh = new PingHistoryHome();

		ph.setStatus(status);
		ph.setStamp(new Date());
		ph.setTimeout(timeOut);
		phh.log_ping(ph);
	}

	private synchronized void notificate(Boolean status) {
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

	private synchronized void ping() {
		try {
			
			Ping ping = new Ping(host.getIpAdres(), host.getTimeoutMs()
					.intValue(), host.getPacketLoss());
			status = ping.isOnline();
			packetLoss = ping.isPacketLoss();
			
			if (( !status || packetLoss ) && down_count < host.getDownCount()) {
				down_count++;
				System.out.println(down_count + " down! unreported. Aval. downs: " + host.getDownCount() + " for " + host.getDescription());
				return;
			}
			
			Integer timeOut = ping.getTimeout();
			log(new Boolean(status), timeOut);
			down_count = 0;
			
			if (status != last_status) {
				if (status) {
					String message = String.format(Tools.MAIL_UP, host
							.getDescription(), host.getIpAdres(), timeOut,
							Tools.getDateTime());
					formatedPrint(Tools.SVC_UP, host.getDescription(), host
							.getIpAdres(), timeOut, Tools.getDateTime());
					notificate(status);
					sendMail(message);
				} else {
					String message = new String();
					if (packetLoss) {
						message = String.format(Tools.MAIL_P_LOSS, host
								.getDescription(), host.getIpAdres(), ping.getPacketLoss(),
								Tools.getDateTime());
						formatedPrint(Tools.SVC_P_LOSS, host.getDescription(),
								host.getIpAdres(), ping.getPacketLoss(), Tools.getDateTime());
					} else {
						message = String.format(Tools.MAIL_DOWN, host
								.getDescription(), host.getIpAdres(), timeOut,
								Tools.getDateTime());
						formatedPrint(Tools.SVC_DOWN, host.getDescription(),
								host.getIpAdres(), timeOut, Tools.getDateTime());
					}
					notificate(status);
					sendMail(message);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	private synchronized void sendMail(String message) {
		ArrayList<String> recipients = new ArrayList<String>();
		Set<Groups> group_set = host.getGroupses();
		if (group_set != null) {
			Iterator<Groups> gr_iterator = group_set.iterator();
			while (gr_iterator.hasNext()) {
				Set<Contacts> contact_set = gr_iterator.next().getContactses();
				Iterator<Contacts> cont_iterator = contact_set.iterator();
				while (cont_iterator.hasNext()) {
					recipients.add(cont_iterator.next().getContact());
				}
			}
			try {
				MailSender.postMail(recipients.toArray(new String[recipients
						.size()]), host.getDescription(), message,
						"dude_of_dude@meta.ua");
			} catch (MessagingException mex) {
			}
		}
	}

}
