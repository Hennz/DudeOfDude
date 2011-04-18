package net.it_tim.dude_of_dude;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.io.Console;
import com.sun.security.auth.module.UnixSystem;
import net.it_tim.dude_of_dude.db_classes.*;

public class DudeOfDude {

	private static long uid = -1;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("~~~ Перевірка умов запуску ~~~");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
    	UnixSystem unix_user = new UnixSystem();
    	uid = unix_user.getUid();
    	if (uid != 0) {
            System.out.println("!!! Потрібні супер права !!!");
            //System.exit(-1);
    	}

    	try {
			Console console = System.console();

			String username = console.readLine("[%s]", "Логін:");
			char[] passwd = console.readPassword("[%s]", "Пароль:");
			
			UsersHome usermanager = new UsersHome();
			
			if (usermanager.checkPassword(username, new String(passwd))) {
				Arrays.fill(passwd, ' ');
				System.out.println(" ~~~ Вхід вдалий ~~~");
			} else {
				Arrays.fill(passwd, ' ');
				System.out.println(" ~~~ Щось зламалось ~~~");
				System.exit(0);
			}

		} catch (NullPointerException ex) {
			ex.printStackTrace();
			System.out.println("!!! Системна консоль не доступна !!!");
			//System.exit(-1);
		}


		try {
			HostsHome hh = new HostsHome();
			
			List host_list = hh.getAll();

			for (Hosts host: (List<Hosts>) host_list) {
				PingThread ping_thread = new PingThread(host);
				Timer timer = new Timer();
				timer.schedule(ping_thread, 0, host.getIntervalMs());
			}
			/*
			ping = new Ping("192.168.77.7", 1000);
			if (ping.isOnline()) {
				System.out
						.println(String
								.format(
										net.it_tim.dude_of_dude.static_constants.Message.SVC_UP,
										"Barn-e", "192.168.77.7", "now"));
			} else {
				String recs[] = { "gofl@meta.ua" /*
												 * ,
												 * "380676589174@sms.kyivstar.net"
												 *};
				try {
					MailSender.postMail(recs, "Test msg",
							"Dude fuck's your Cisco!", "Anal@nosorog.net");
					System.out.println("Service is offline");
				} catch (MessagingException ex) {
					ex.printStackTrace();
				}
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
