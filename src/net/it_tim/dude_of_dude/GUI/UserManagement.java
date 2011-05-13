package net.it_tim.dude_of_dude.GUI;

import javax.swing.JToolBar;
import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;

import net.it_tim.dude_of_dude.Md5Hash;
import net.it_tim.dude_of_dude.GUI.table_staf.UsersListModel;
import net.it_tim.dude_of_dude.database.Users;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class UserManagement extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField loginField;
	private JTextField nameField;
	private JTextField surnameField;
	private UsersListModel uListMod = new UsersListModel();
	private JList list;
	private JPasswordField passwordField;
	private JTextField textField;
	private Users user = (Users) null;
	private JPanel panel_1;

	/**
	 * Create the application.
	 */
	public UserManagement() {
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						UserManagement.class
								.getResource("/net/it_tim/dude_of_dude/icons/Papermart/ID.png")));
		setTitle("User Management");
		setResizable(false);
		setBounds(100, 100, 474, 277);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.WEST);

		list = new JList(uListMod);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					panel_1.setVisible(true);
					loadUserInfo(list.getSelectedIndex());
				}
			}
		});
		list.setPreferredSize(new Dimension(200, 17));
		list.setMaximumSize(new Dimension(200, 17));
		list.setMinimumSize(new Dimension(200, 17));
		scrollPane.setViewportView(list);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		getContentPane().add(toolBar, BorderLayout.NORTH);

		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Users user = new Users();
				user.setComment("null");
				user.setLogin("default");
				user.setName("null");
				user.setSurname("null");
				user.setPassword(new Md5Hash("null").toString());
				uListMod.addUser(user);
			}
		});
		btnAddUser
				.setIcon(new ImageIcon(
						UserManagement.class
								.getResource("/net/it_tim/dude_of_dude/icons/Signage/Add_Square.png")));
		toolBar.add(btnAddUser);

		JButton btnRemoveUser = new JButton("Remove User");
		btnRemoveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				if (index != -1) {
					int opt = JOptionPane.showConfirmDialog(null,
							"<html><font color=red>~~~ Really delete? ~~~</font>"
									+ "<br>"
									+ list.getSelectedValue().toString()
									+ "</html>", "WARNING",
							JOptionPane.YES_NO_OPTION);
					if (opt != 1)
						uListMod.removeUser(index);
				} else {
					JOptionPane.showMessageDialog(null,
							"~~~ You should select at least one entry ~~~");
				}
			}
		});
		btnRemoveUser
				.setIcon(new ImageIcon(
						UserManagement.class
								.getResource("/net/it_tim/dude_of_dude/icons/Signage/Remove_Square.png")));
		toolBar.add(btnRemoveUser);

		JButton btnModifyUser = new JButton("Modify user");
		btnModifyUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				if (index != -1) {
					panel_1.setVisible(true);
					loadUserInfo(index);
				} else {
					JOptionPane.showMessageDialog(null,
							"~~~ You should select at least one entry ~~~");
				}
			}
		});
		btnModifyUser
				.setIcon(new ImageIcon(
						UserManagement.class
								.getResource("/net/it_tim/dude_of_dude/icons/Papermart/Highlighter.png")));
		toolBar.add(btnModifyUser);

		panel_1 = new JPanel();
		panel_1.setVisible(false);
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(14, 16, 39, 15);

		loginField = new JTextField();
		loginField.setBounds(14, 37, 114, 24);
		loginField.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(146, 16, 70, 15);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(14, 62, 40, 15);

		nameField = new JTextField();
		nameField.setBounds(14, 83, 114, 24);
		nameField.setColumns(10);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(146, 62, 63, 15);

		surnameField = new JTextField();
		surnameField.setBounds(146, 83, 103, 24);
		surnameField.setColumns(10);

		JLabel lblComment = new JLabel("Comment");
		lblComment.setBounds(14, 108, 66, 15);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(65, 166, 74, 26);
		btnOk.setActionCommand("OK");
		btnOk.setIcon(new ImageIcon(UserManagement.class
				.getResource("/net/it_tim/dude_of_dude/icons/Signage/OK.png")));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (user == null) {
					panel_1.setVisible(false);
				} else {

					user.setLogin(loginField.getText());
					String pwd = new String(passwordField.getPassword());
					if (!pwd.equals("null"))
						user.setPassword(new Md5Hash(pwd).toString());
					pwd = " ";
					user.setName(nameField.getText());
					user.setSurname(surnameField.getText());
					user.setComment(textField.getText());
					uListMod.updateUser(list.getSelectedIndex(), user);
					user = (Users) null;
					panel_1.setVisible(false);
				}
			}
		});

		getRootPane().setDefaultButton(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(146, 166, 101, 26);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.setVisible(false);
			}
		});
		btnCancel
				.setIcon(new ImageIcon(
						UserManagement.class
								.getResource("/net/it_tim/dude_of_dude/icons/Signage/Close.png")));
		panel_1.setLayout(null);

		passwordField = new JPasswordField();
		passwordField.setForeground(Color.RED);
		passwordField.setBounds(146, 37, 103, 24);
		panel_1.add(passwordField);
		panel_1.add(btnOk);
		panel_1.add(btnCancel);
		panel_1.add(lblNewLabel);
		panel_1.add(lblPassword);
		panel_1.add(loginField);
		panel_1.add(lblSurname);
		panel_1.add(lblName);
		panel_1.add(lblComment);
		panel_1.add(nameField);
		panel_1.add(surnameField);

		textField = new JTextField();
		textField.setBounds(14, 135, 235, 24);
		panel_1.add(textField);
		textField.setColumns(10);
	}

	private void loadUserInfo(int index) {
		user = uListMod.getUser(index);
		loginField.setText(user.getLogin());
		nameField.setText(user.getName());
		surnameField.setText(user.getSurname());
		textField.setText(user.getComment());
		passwordField.setText("null");
	}
}
