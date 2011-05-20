package net.it_tim.dude_of_dude.GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

import net.it_tim.dude_of_dude.GUI.table_staf.GroupsListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class GroupsSettings extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GroupsListModel grListModel = new GroupsListModel();
	private JList list;

	/**
	 * Create the frame.
	 */
	public GroupsSettings() {
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						GroupsSettings.class
								.getResource("/net/it_tim/dude_of_dude/icons/Papermart/Contacts.png")));
		setTitle("Group Management");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		list = new JList(grListModel);
		scrollPane.setViewportView(list);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);

		JButton btnAddGroup = new JButton("Add Group");
		btnAddGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String groupName = JOptionPane
						.showInputDialog("Enter group name: ");
				if (groupName != null)
					grListModel.addGroup(groupName);
			}
		});
		btnAddGroup
				.setIcon(new ImageIcon(
						GroupsSettings.class
								.getResource("/net/it_tim/dude_of_dude/icons/Papermart/Note-Add.png")));
		toolBar.add(btnAddGroup);

		JButton btnDeleteGroup = new JButton("Delete Group");
		btnDeleteGroup.addActionListener(new ActionListener() {
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
						grListModel.removeGroup(index);
				} else {
					JOptionPane.showMessageDialog(null,
							"~~~ You should select at least one entry ~~~");
				}
			}
		});
		btnDeleteGroup
				.setIcon(new ImageIcon(
						GroupsSettings.class
								.getResource("/net/it_tim/dude_of_dude/icons/Papermart/Note-Remove.png")));
		toolBar.add(btnDeleteGroup);

		JButton btnModifyGroup = new JButton("Modify Group");
		btnModifyGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				if (index != -1) {
					String groupName = JOptionPane.showInputDialog(null,
							"Enter new group name: ", list.getSelectedValue()
									.toString());
					if (groupName != null)
						grListModel.updateGroup(index, groupName);
				} else {
					JOptionPane.showMessageDialog(null,
							"~~~ You should select at least one entry ~~~");
				}
			}
		});
		btnModifyGroup
				.setIcon(new ImageIcon(
						GroupsSettings.class
								.getResource("/net/it_tim/dude_of_dude/icons/Papermart/Pencil.png")));
		toolBar.add(btnModifyGroup);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose
				.setIcon(new ImageIcon(
						GroupsSettings.class
								.getResource("/net/it_tim/dude_of_dude/icons/Signage/Close.png")));
		toolBar.add(btnClose);

	}

}
