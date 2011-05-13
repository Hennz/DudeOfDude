package net.it_tim.dude_of_dude;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Timer;
//import java.io.Console;
import com.sun.security.auth.module.UnixSystem;

import net.it_tim.dude_of_dude.GUI.GUI;
import net.it_tim.dude_of_dude.database.*;
import net.it_tim.dude_of_dude.static_constants.Tools;

public class DudeOfDude {

	private static long uid = -1;
	//private static Console console = System.console();
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
		
		String os = new String(System.getProperty("os.name"));
		
		if ( os.equals("Linux") || os.equals("Solaris") ) {		
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
		/*		
		try
        {
            daemonize();
        }
        catch (Throwable e)
        {
            System.err.println("Startup failed. " + e.getMessage());
            return;
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
		*/

		try {
			HostsHome hh = new HostsHome();

			List host_list = hh.getAll();
			for (Hosts host : (List<Hosts>) host_list) {
				if (host.getToPing()) {
					PingThread ping_thread = new PingThread(host);
					Timer timer = new Timer();
					timer.schedule(ping_thread, 0, host.getIntervalMs());
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
		/*
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

	static private void daemonize() throws Exception
    {
        System.in.close();
        System.out.close();
    }
    		*/
}
