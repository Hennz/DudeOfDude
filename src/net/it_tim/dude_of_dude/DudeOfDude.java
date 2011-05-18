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
import com.sun.security.auth.module.UnixSystem;

import net.it_tim.dude_of_dude.GUI.GUI;
import net.it_tim.dude_of_dude.database.*;
import net.it_tim.dude_of_dude.rmi.ServerControl;
import net.it_tim.dude_of_dude.rmi.ServerControlImp;
import net.it_tim.dude_of_dude.static_constants.Tools;

public class DudeOfDude {

	private static long uid = -1;
	private static Registry registry;
	private static ServerControlImp sci;
	private static ArrayList<Timer> timer_list = new ArrayList<Timer>();
	/**
	 * @param args
	 */


	public static void main(String[] args) {
		Tools.coloredPrint(Tools.COLOR_GREEN, "~~~ Перевірка умов запуску ~~~", Tools.COLOR_WHITE);
		if (args.length > 0 && args[0].equals("-g")) {
			Tools.coloredPrint(Tools.COLOR_GREEN, "~~~ Запуск графічного інтерфейсу ~~~", Tools.COLOR_WHITE);
			new GUI();
			return;
		}
		initRMI();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String os = new String(System.getProperty("os.name"));
		Tools.coloredPrint(Tools.COLOR_RED, os, Tools.COLOR_WHITE);
		
		if ( os.equals("Linux") || os.equals("SunOS") ) {		
		UnixSystem unix_user = new UnixSystem();
    	uid = unix_user.getUid();
    	if (uid != 0) {
    		Tools.coloredPrint(Tools.COLOR_RED, "!!! Потрібні супер права !!!", Tools.COLOR_WHITE);
            System.exit(0);
    	}
		} else {
			Tools.coloredPrint("!!! Windows not supported... yet !!!");
			System.exit(0);
		}
		
		Writer writer = null;

        try {
    		String[] pid = ManagementFactory.getRuntimeMXBean().getName().split("@");
    		
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
			HostsHome hh = new HostsHome();
			List host_list = hh.getAll();
			
			for (Hosts host : (List<Hosts>) host_list) {
				if (host.getToPing()) {
					PingThread ping_thread = new PingThread(host);
					Timer timer = new Timer();
					timer.schedule(ping_thread, 0, host.getIntervalMs());
					timer_list.add(timer);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void initRMI() {
        try {
        	sci = new ServerControlImp();
        	ServerControl stub = (ServerControl)UnicastRemoteObject.exportObject(sci);
            registry = LocateRegistry.createRegistry(2005);
            registry.bind("ServerControl", stub);
            Tools.coloredPrint(Tools.COLOR_GREEN, "~~~ Система віддаленого управління сервером запущена ~~~", Tools.COLOR_WHITE);
        } catch (Exception e) {
        	Tools.coloredPrint(Tools.COLOR_RED, "!!! Система віддаленого управління сервером НЕ запущена !!!", Tools.COLOR_WHITE);
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
					// I don't care
				}
				Tools.coloredPrint(Tools.COLOR_RED, "Done!", Tools.COLOR_WHITE);
				System.exit(0);
			}

		}.start();
	}
	
	public static void serverStop() {
		Tools.coloredPrint(Tools.COLOR_RED, "Stopping server", Tools.COLOR_WHITE);
		for (Timer timer : timer_list) {
			timer.cancel();
			timer.purge();
		}
	}
}
