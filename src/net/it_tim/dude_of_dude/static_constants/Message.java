package net.it_tim.dude_of_dude.static_constants;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Message {
	public static final String SVC_DOWN = "Service %s is DOWN! IP address: %s Timeout: %d ms Time: %s";
	public static final String SVC_UP = "Service %s is UP! IP address: %s Timeout: %d ms Time: %s";

    public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
