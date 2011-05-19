package net.it_tim.dude_of_dude;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.mail.MessagingException;

import net.it_tim.dude_of_dude.database.Contacts;
import net.it_tim.dude_of_dude.database.Groups;
import net.it_tim.dude_of_dude.database.Hosts;
import net.it_tim.dude_of_dude.database.NotificatioinsHistory;
import net.it_tim.dude_of_dude.database.NotificatioinsHistoryHome;
import net.it_tim.dude_of_dude.database.PingHistory;
import net.it_tim.dude_of_dude.database.PingHistoryHome;

public class SyncronizedMethods {
	public static synchronized void log(Hosts host, Boolean status,
			Integer timeOut) {
		PingHistory ph = new PingHistory();
		ph.setHosts(host);
		PingHistoryHome phh = new PingHistoryHome();

		ph.setStatus(status);
		ph.setStamp(new Date());
		ph.setTimeout(timeOut);
		phh.log_ping(ph);
	}

	public static synchronized void notificate(Hosts host, Boolean status) {
		NotificatioinsHistory nh = new NotificatioinsHistory();
		nh.setHosts(host);
		NotificatioinsHistoryHome nhh = new NotificatioinsHistoryHome();

		nh.setStatus(status);
		nh.setStamp(new Date());
		nhh.notificate(nh);
	}

	public static synchronized void sendMail(Hosts host, String message) {
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
