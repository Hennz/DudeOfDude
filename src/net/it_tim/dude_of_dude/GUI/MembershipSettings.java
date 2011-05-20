package net.it_tim.dude_of_dude.GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JList;

import net.it_tim.dude_of_dude.GUI.table_staf.ContactsListModel;
import net.it_tim.dude_of_dude.GUI.table_staf.GroupsListModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MembershipSettings extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GroupsListModel grListModel;
	private JList list;
	private JPanel panel;
	private JToolBar toolBar;
	private JButton btnAdd;
	private JButton btnDelete;
	private JList list_2;
	private ContactsListModel cin;
	private JScrollPane scrollPane;
	private JButton btnClose;
	private JScrollPane scrollPane_1;

	/**
	 * Create the frame.
	 */
	public MembershipSettings() {
		grListModel = new GroupsListModel();
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						MembershipSettings.class
								.getResource("/net/it_tim/dude_of_dude/icons/Papermart/Clipped ID.png")));
		setTitle("Membership Settings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);

		list = new JList(grListModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.setBackground(new Color(255, 255, 255));
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = list.getSelectedIndex();
				if (index != -1) {
					cin = new ContactsListModel(grListModel.getGroup(index));
					list_2.setModel(cin);
				}
			}
		});
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportView(list);
		splitPane.setLeftComponent(scrollPane_1);

		panel = new JPanel();

		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel.add(toolBar, BorderLayout.NORTH);

		btnAdd = new JButton("Add");
		btnAdd.setMnemonic('A');
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				if (index != -1) {
					AppendContactDialog appendDialog = new AppendContactDialog(
							cin);
					appendDialog.setVisible(true);
				}
			}
		});
		btnAdd
				.setIcon(new ImageIcon(
						MembershipSettings.class
								.getResource("/net/it_tim/dude_of_dude/icons/Signage/Add_Square.png")));
		toolBar.add(btnAdd);

		btnDelete = new JButton("Delete");
		btnDelete.setMnemonic('R');
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list_2.getSelectedIndex();
				if (index != -1) {
					int opt = JOptionPane.showConfirmDialog(null,
							"<html><font color=red>~~~ Really delete? ~~~</font>"
									+ "<br>"
									+ list_2.getSelectedValue().toString()
									+ "</html>", "WARNING",
							JOptionPane.YES_NO_OPTION);
					if (opt != 1)
						cin.removeContactFromGroup(index);
				} else {
					JOptionPane.showMessageDialog(null,
							"~~~ You should select at least one entry ~~~");
				}
			}
		});
		btnDelete
				.setIcon(new ImageIcon(
						MembershipSettings.class
								.getResource("/net/it_tim/dude_of_dude/icons/Signage/Remove_Square.png")));
		toolBar.add(btnDelete);

		btnClose = new JButton("Close");
		btnClose.setMnemonic('C');
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setHorizontalAlignment(SwingConstants.RIGHT);
		btnClose
				.setIcon(new ImageIcon(
						MembershipSettings.class
								.getResource("/net/it_tim/dude_of_dude/icons/Signage/Close.png")));
		toolBar.add(btnClose);

		cin = new ContactsListModel(grListModel.getGroup(0));

		list_2 = new JList(cin);
		list_2.setSelectedIndex(0);
		list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_2.setBackground(new Color(255, 255, 255));
		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(list_2);

	}

}
