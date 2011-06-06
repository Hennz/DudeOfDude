package net.it_tim.dude_of_dude;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import net.it_tim.dude_of_dude.database.Hosts;
import net.it_tim.dude_of_dude.database.HostsHome;
import net.it_tim.dude_of_dude.rmi.ServerControl;
import net.it_tim.dude_of_dude.rmi.ServerControlImp;
import net.it_tim.dude_of_dude.static_constants.Tools;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.sun.security.auth.module.UnixSystem;

public class Server {

	private static long uid = -1;
	private static Registry registry;
	private static ServerControlImp sci;
	private static ArrayList<Timer> timer_list = new ArrayList<Timer>();
	private static boolean isStarted = false;

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		Tools.coloredPrint(Tools.COLOR_GREEN, "~~~ Перевірка умов запуску ~~~",
				Tools.COLOR_WHITE);
		initRMI();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String os = new String(System.getProperty("os.name"));
		Tools.coloredPrint(Tools.COLOR_RED, os, Tools.COLOR_WHITE);

		if (os.equals("Linux") || os.equals("SunOS")) {
			UnixSystem unix_user = new UnixSystem();
			uid = unix_user.getUid();
			if (uid != 0) {
				Tools.coloredPrint(Tools.COLOR_RED,
						"!!! Потрібні супер права !!!", Tools.COLOR_WHITE);
				System.exit(0);
			}
		} else {
			Tools.coloredPrint("!!! Windows не підтримується... покищо :) !!!");
			System.exit(0);
		}

		Writer writer = null;

		try {
			String[] pid = ManagementFactory.getRuntimeMXBean().getName()
					.split("@");

			File file = new File("/var/run/dude_of_dude.pid");
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(pid[0]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		initPingThreads();
	}

	@SuppressWarnings("unchecked")
	private static void initPingThreads() {
		try {
			int thread_count = 0;
			HostsHome hh = new HostsHome();
			List host_list = hh.getAll();

			for (Hosts host : (List<Hosts>) host_list) {
				if (host.getToPing()) {
					PingThread ping_thread = new PingThread(host);
					Timer timer = new Timer();
					timer.schedule(ping_thread, 0, host.getIntervalMs());
					timer_list.add(timer);
					Thread.sleep(100);
					thread_count++;
				}
			}
			isStarted = true;
			System.out.println("Запущено потоків: " + thread_count);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	private static void initRMI() {
		try {
			Integer rmi_port = new Integer(2005);
			try {
				Configuration rmiConfig = new PropertiesConfiguration(
						"dod.properties");
				rmi_port = new Integer(rmiConfig.getInt("server.rmi.port"));
			} catch (ConfigurationException e2) {
				e2.printStackTrace();
			}

			sci = new ServerControlImp();
			ServerControl stub = (ServerControl) UnicastRemoteObject
					.exportObject(sci);
			registry = LocateRegistry.createRegistry(rmi_port.intValue());
			registry.bind("ServerControl", stub);
			Tools.coloredPrint(Tools.COLOR_GREEN,
					"~~~ Система віддаленого управління сервером запущена ~~~",
					Tools.COLOR_WHITE);
		} catch (Exception e) {
			Tools
					.coloredPrint(
							Tools.COLOR_RED,
							"!!! Система віддаленого управління сервером НЕ запущена !!!",
							Tools.COLOR_WHITE);
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void serverShutdown(final String msg) {
		try {
			UnicastRemoteObject.unexportObject(sci, true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		new Thread() {
			@Override
			public void run() {
				Tools.coloredPrint(Tools.COLOR_RED, msg, Tools.COLOR_WHITE);
				try {
					sleep(2000);
				} catch (InterruptedException e) {

				}
				Tools.coloredPrint(Tools.COLOR_RED, "Сервер вимкнено!", Tools.COLOR_WHITE);
				System.exit(0);
			}

		}.start();
	}

	public static void serverStop() {
		Tools.coloredPrint(Tools.COLOR_RED, "Зупиняю сервер",
				Tools.COLOR_WHITE);
		for (Timer timer : timer_list) {
			timer.cancel();
			timer.purge();
		}
		timer_list.clear();
		isStarted = false;
	}

	public static void serverStart() {
		initPingThreads();
	}

	public static boolean isStarted() {
		return isStarted;
	}
}
