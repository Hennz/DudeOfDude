package net.it_tim.dude_of_dude.GUI;

import javax.swing.JDialog;

public class GUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
