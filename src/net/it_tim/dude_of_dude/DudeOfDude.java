package net.it_tim.dude_of_dude;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.io.Console;
import com.sun.security.auth.module.UnixSystem;

import net.it_tim.dude_of_dude.GUI.GUI;
import net.it_tim.dude_of_dude.database.*;
import net.it_tim.dude_of_dude.static_constants.Tools;

public class DudeOfDude {

	private static long uid = -1;
	private static Console console = System.console();
	/**
	 * @param args
	 */

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Tools.coloredPrint(Tools.COLOR_GREEN, "~~~ Перевірка умов запуску ~~~", Tools.COLOR_WHITE);
		if (args.length > 0 && args[0].equals("-g")) {
			Tools.coloredPrint(Tools.COLOR_GREEN, "~~~ Запуск графічного інтерфейсу ~~~", Tools.COLOR_WHITE);
			new GUI();
			return;
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (System.getProperty("os.name").equals("Linux")) {		
		UnixSystem unix_user = new UnixSystem();
    	uid = unix_user.getUid();
    	if (uid != 0) {
    		Tools.coloredPrint(Tools.COLOR_RED, "!!! Потрібні супер права !!!", Tools.COLOR_WHITE);
            System.exit(-1);
    	}
		}

    	try {
			String username = console.readLine("[%s] > [%s]", Tools.getDateTime(), "Логін:");
			char[] passwd = console.readPassword("[%s] > [%s]", Tools.getDateTime(), "Пароль:");
			
			UsersHome usermanager = new UsersHome();
			
			if (usermanager.checkPassword(username, new String(passwd))) {
				Arrays.fill(passwd, ' ');
				Tools.coloredPrint(Tools.COLOR_GREEN, " ~~~ Вхід вдалий ~~~", Tools.COLOR_WHITE);
			} else {
				Arrays.fill(passwd, ' ');
				Tools.coloredPrint(Tools.COLOR_RED, " ~~~ Щось зламалось ~~~", Tools.COLOR_WHITE);
				System.exit(0);
			}

		} catch (NullPointerException ex) {
			ex.printStackTrace();
			Tools.coloredPrint(Tools.COLOR_RED, "!!! Системна консоль не доступна !!!", Tools.COLOR_WHITE);
			System.exit(-1);
		}


		try {
			HostsHome hh = new HostsHome();
			
			List host_list = hh.getAll();
			for (Hosts host: (List<Hosts>) host_list) {
				PingThread ping_thread = new PingThread(host);
				Timer timer = new Timer();
				timer.schedule(ping_thread, 0, host.getIntervalMs());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
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
		
		Tools.coloredPrint(Tools.COLOR_YELLOW, "~~~ To quit enter \"quit\" and press enter ~~~", Tools.COLOR_WHITE);
		while(true) {
			String cmd = console.readLine();
			if (cmd.equals("quit")) {
				DAO.close();
				System.exit(0);
			} else {
				Tools.coloredPrint(Tools.COLOR_RED, "~~~ unknown command ~~~", Tools.COLOR_WHITE);
			}
		}
	}

}
