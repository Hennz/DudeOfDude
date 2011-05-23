package net.it_tim.dude_of_dude;

import javax.swing.JDialog;

import net.it_tim.dude_of_dude.GUI.Login;
import net.it_tim.dude_of_dude.static_constants.Tools;

public class AdminGUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Tools.coloredPrint(Tools.COLOR_GREEN,
					"~~~ Запуск графічного інтерфейсу ~~~", Tools.COLOR_WHITE);
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
