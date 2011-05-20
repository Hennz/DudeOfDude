package net.it_tim.dude_of_dude.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import net.it_tim.dude_of_dude.database.*;

public class Login extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JTextField loginField;
	private JPasswordField pwdField;

	/**
	 * Launch the application.
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

	/**
	 * Create the dialog.
	 */
	public Login() {
		final UsersHome usermanager = new UsersHome();

		setBounds(100, 100, 450, 131);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel loginPanel = new JPanel();
			loginPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
					null, null, null));
			contentPanel.add(loginPanel);
			{
				JLabel lblLogin = new JLabel("Login");
				loginPanel.add(lblLogin);
			}
			{
				loginField = new JTextField();
				loginPanel.add(loginField);
				loginField.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
					null, null));
			contentPanel.add(panel);
			{
				JLabel lblPassword = new JLabel("Password");
				panel.add(lblPassword);
				lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
				lblPassword.setVerticalAlignment(SwingConstants.BOTTOM);
			}
			{
				pwdField = new JPasswordField();
				panel.add(pwdField);
				pwdField.setColumns(12);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (usermanager.checkPassword(loginField.getText(),
								new String(pwdField.getPassword()))) {
							dispose();
							mainWnd mainWindow = new mainWnd();
							mainWindow.setVisible(true);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
