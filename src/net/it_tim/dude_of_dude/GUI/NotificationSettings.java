package net.it_tim.dude_of_dude.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import net.it_tim.dude_of_dude.GUI.table_staf.GroupsListModel;
import net.it_tim.dude_of_dude.GUI.table_staf.HostsListModel;

public class NotificationSettings extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private GroupsListModel grListModel;
	private HostsListModel hlm;
	private JList groupList, hostList;

	/**
	 * Create the dialog.
	 */
	public NotificationSettings() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		grListModel = new GroupsListModel();
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						NotificationSettings.class
								.getResource("/net/it_tim/dude_of_dude/icons/Mail/Airmail.png")));
		setTitle("Notifications Settings");
		setBounds(100, 100, 632, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JSplitPane splitPane = new JSplitPane();
			contentPanel.add(splitPane);
			{
				JScrollPane scrollPane = new JScrollPane();
				splitPane.setLeftComponent(scrollPane);
				{
					groupList = new JList(grListModel);
					groupList.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index = groupList.getSelectedIndex();
							if (index != -1) {
								hlm = new HostsListModel(grListModel
										.getGroup(index));
								hostList.setModel(hlm);
							}
						}
					});
					groupList
							.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					groupList.setSelectedIndex(0);
					scrollPane.setViewportView(groupList);
				}
			}
			{
				JPanel panel = new JPanel();
				splitPane.setRightComponent(panel);
				panel.setLayout(new BorderLayout(0, 0));
				{
					JToolBar toolBar = new JToolBar();
					toolBar.setFloatable(false);
					panel.add(toolBar, BorderLayout.NORTH);
					{
						JButton btnAddHost = new JButton("Add Host");
						btnAddHost.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								int index = groupList.getSelectedIndex();
								if (index != -1) {
									AppendHostDialog appendDialog = new AppendHostDialog(
											hlm);
									appendDialog.setVisible(true);
								}
							}
						});
						btnAddHost
								.setIcon(new ImageIcon(
										NotificationSettings.class
												.getResource("/net/it_tim/dude_of_dude/icons/Signage/Add_Square.png")));
						btnAddHost.setMnemonic('A');
						toolBar.add(btnAddHost);
					}
					{
						JButton btnRemoveHost = new JButton("Remove Host");
						btnRemoveHost.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								int index = hostList.getSelectedIndex();
								if (index != -1) {
									int opt = JOptionPane.showConfirmDialog(
											null,
											"<html><font color=red>~~~ Really delete? ~~~</font>"
													+ "<br>"
													+ hostList
															.getSelectedValue()
															.toString()
													+ "</html>", "WARNING",
											JOptionPane.YES_NO_OPTION);
									if (opt != 1)
										hlm.removeHostFromGroup(index);
								} else {
									JOptionPane
											.showMessageDialog(null,
													"~~~ You should select at least one entry ~~~");
								}
							}
						});
						btnRemoveHost.setMnemonic('R');
						btnRemoveHost
								.setIcon(new ImageIcon(
										NotificationSettings.class
												.getResource("/net/it_tim/dude_of_dude/icons/Signage/Remove_Square.png")));
						toolBar.add(btnRemoveHost);
					}
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					panel.add(scrollPane, BorderLayout.CENTER);
					{
						hlm = new HostsListModel(grListModel.getGroup(0));
						hostList = new JList(hlm);
						hostList
								.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						hostList.setSelectedIndex(0);
						scrollPane.setViewportView(hostList);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
