package net.it_tim.dude_of_dude.GUI;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

import javax.swing.JTable;

import net.it_tim.dude_of_dude.GUI.table_staf.BoolCellRenderer;
import net.it_tim.dude_of_dude.GUI.table_staf.HostsTableModel;
import net.it_tim.dude_of_dude.rmi.ServerControl;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.ListSelectionModel;

public class mainWnd extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private HostsTableModel hostTableModel = new HostsTableModel();
	/**
	 * Create the frame.
	 */
	public mainWnd() {
		setMinimumSize(new Dimension(640, 320));
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainWnd.class.getResource("/net/it_tim/dude_of_dude/icons/Mail/Airmail.png")));
		setTitle("Dude of Dude");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 671, 320);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('F');
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnTools = new JMenu("Tools");
		mnTools.setMnemonic('T');
		menuBar.add(mnTools);
		
		JMenuItem mntmNotifications = new JMenuItem("Notifications");
		mntmNotifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NotificationSettings notificationSettings = new NotificationSettings();
				notificationSettings.setVisible(true);
			}
		});
		mntmNotifications.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnTools.add(mntmNotifications);
		
		JMenuItem mntmMembership = new JMenuItem("Membership");
		mntmMembership.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MembershipSettings members = new MembershipSettings();
				members.setVisible(true);
			}
		});
		mntmMembership.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
		mnTools.add(mntmMembership);
		
		JMenuItem mntmGroups = new JMenuItem("Groups");
		mntmGroups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GroupsSettings grSet = new GroupsSettings();
				grSet.setVisible(true);
			}
		});
		mntmGroups.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mnTools.add(mntmGroups);
		
		JMenuItem mntmContacts = new JMenuItem("Contacts");
		mntmContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContactsSettings contactsSettings = new ContactsSettings();
				contactsSettings.setVisible(true);
			}
		});
		mntmContacts.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnTools.add(mntmContacts);
		
		JMenuItem mntmUsers = new JMenuItem("Users");
		mntmUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManagement usrMng = new UserManagement();
				usrMng.setVisible(true);
			}
		});
		mntmUsers.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
		mnTools.add(mntmUsers);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(hostTableModel);
		table.setCellSelectionEnabled(true);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		toolBar.setFloatable(false);
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setMnemonic('A');
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hostTableModel.addRow();
			}
		});
		btnNewButton.setIcon(new ImageIcon(mainWnd.class.getResource("/net/it_tim/dude_of_dude/icons/Signage/Add_Square.png")));
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setMnemonic('D');
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selected = table.getSelectedRow();
				if (selected != -1) {
				int opt = JOptionPane.showConfirmDialog(null, "~~~ Really delete? ~~~", "WARNING", JOptionPane.YES_NO_OPTION);
				if (opt != 1)
					hostTableModel.deleteRow(table.getSelectedRow());
				} else {
					JOptionPane.showMessageDialog(null, "~~~ You must select row to delete it ;) ~~~");
				}
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(mainWnd.class.getResource("/net/it_tim/dude_of_dude/icons/Signage/Remove_Square.png")));
		toolBar.add(btnNewButton_1);
		
		JButton btnMembership = new JButton("Membership");
		btnMembership.setMnemonic('M');
		btnMembership.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MembershipSettings members = new MembershipSettings();
				members.setVisible(true);
			}
		});
		
		JButton btnNotifications = new JButton("Notifications");
		btnNotifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NotificationSettings notificationSettings = new NotificationSettings();
				notificationSettings.setVisible(true);
			}
		});
		btnNotifications.setMnemonic('N');
		btnNotifications.setIcon(new ImageIcon(mainWnd.class.getResource("/net/it_tim/dude_of_dude/icons/Mail/Airmail.png")));
		toolBar.add(btnNotifications);
		btnMembership.setIcon(new ImageIcon(mainWnd.class.getResource("/net/it_tim/dude_of_dude/icons/Emporium/Home.png")));
		toolBar.add(btnMembership);
		
		JButton btnGroups = new JButton("Groups");
		btnGroups.setMnemonic('G');
		btnGroups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GroupsSettings grSet = new GroupsSettings();
				grSet.setVisible(true);
			}
		});
		btnGroups.setIcon(new ImageIcon(mainWnd.class.getResource("/net/it_tim/dude_of_dude/icons/Papermart/Contacts.png")));
		toolBar.add(btnGroups);
		
		JButton btnContacts = new JButton("Contacts");
		btnContacts.setMnemonic('C');
		btnContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContactsSettings contactsSettings = new ContactsSettings();
				contactsSettings.setVisible(true);
			}
		});
		btnContacts.setIcon(new ImageIcon(mainWnd.class.getResource("/net/it_tim/dude_of_dude/icons/Mail/Send.png")));
		toolBar.add(btnContacts);
		
		JButton btnUsers = new JButton("Users");
		btnUsers.setMnemonic('U');
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserManagement usrMng = new UserManagement();
				usrMng.setVisible(true);
			}
		});
		btnUsers.setIcon(new ImageIcon(mainWnd.class.getResource("/net/it_tim/dude_of_dude/icons/Papermart/Clipped ID.png")));
		toolBar.add(btnUsers);
		
		JButton btnStopServer = new JButton("Stop Server");
		btnStopServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		            System.setSecurityManager(new RMISecurityManager());
		        try {
		        	Registry registry = LocateRegistry.getRegistry("192.168.33.116", 2005);
		            ServerControl server = (ServerControl)registry.lookup("ServerControl");
		            server.stop("Hello");
		        } catch (Exception ex) {
		            System.err.println("ServerControl exception:");
		            ex.printStackTrace();
		        }
			}
		});
		toolBar.add(btnStopServer);
		table.setDefaultRenderer(Boolean.class, new BoolCellRenderer());
		table.getColumnModel().getColumn(4).setMinWidth(30);
		table.getColumnModel().getColumn(4).setMaxWidth(30);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);
	}
}
