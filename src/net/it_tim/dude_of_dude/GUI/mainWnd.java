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

import net.it_tim.dude_of_dude.GUI.table_staf.boolCellRenderer;
import net.it_tim.dude_of_dude.GUI.table_staf.hostsTM;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class mainWnd extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private hostsTM hostTableModel = new hostsTM();
	/**
	 * Create the frame.
	 */
	public mainWnd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 640, 320);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(hostTableModel);
		scrollPane.setViewportView(table);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		toolBar.setFloatable(false);
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hostTableModel.addRow();
			}
		});
		btnNewButton.setIcon(new ImageIcon(mainWnd.class.getResource("/net/it_tim/dude_of_dude/icons/Signage/Add_Square.png")));
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
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
		btnMembership.setIcon(new ImageIcon(mainWnd.class.getResource("/net/it_tim/dude_of_dude/icons/Emporium/Home.png")));
		toolBar.add(btnMembership);
		
		JButton btnGroups = new JButton("Groups");
		btnGroups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GroupsSettings grSet = new GroupsSettings();
				grSet.setVisible(true);
			}
		});
		btnGroups.setIcon(new ImageIcon(mainWnd.class.getResource("/net/it_tim/dude_of_dude/icons/Papermart/Contacts.png")));
		toolBar.add(btnGroups);
		
		JButton btnContacts = new JButton("Contacts");
		btnContacts.setIcon(new ImageIcon(mainWnd.class.getResource("/net/it_tim/dude_of_dude/icons/Mail/Send.png")));
		toolBar.add(btnContacts);
		
		JButton btnUsers = new JButton("Users");
		btnUsers.setIcon(new ImageIcon(mainWnd.class.getResource("/net/it_tim/dude_of_dude/icons/Papermart/Clipped ID.png")));
		toolBar.add(btnUsers);
		table.setDefaultRenderer(Boolean.class, new boolCellRenderer());
		table.getColumnModel().getColumn(4).setMinWidth(30);
		table.getColumnModel().getColumn(4).setMaxWidth(30);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);
	}
}
