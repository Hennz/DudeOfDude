package net.it_tim.dude_of_dude.static_constants;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class Tools {
	public static final String SVC_DOWN = "Service %s is \u001b[91mDOWN!\u001b[97m IP address: %s Timeout: %d ms Time: %s";
	public static final String SVC_UP = "Service %s is \u001b[92mUP!\u001b[97m IP address: %s Timeout: %d ms Time: %s";
	public static final String COLOR_RED = "\u001b[91m";
	public static final String COLOR_GREEN = "\u001b[92m";
	public static final String COLOR_WHITE = "\u001b[97m";
	public static final String COLOR_YELLOW = "\u001b[93m";

    public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

	public static boolean isValidEmailAddress(String emailAddress) {
		String expression = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = emailAddress;
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		return matcher.matches();

	}

	public static void coloredPrint(String ... args) {
		StringBuilder message = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			message.append(args[i]);
		}
		System.out.println(message.toString());
	}
}
