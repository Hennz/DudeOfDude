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
import javax.swing.border.EmptyBorder;

import net.it_tim.dude_of_dude.GUI.table_staf.ContactsListModel;
import net.it_tim.dude_of_dude.GUI.table_staf.UsersListModel;
import net.it_tim.dude_of_dude.database.Contacts;
import net.it_tim.dude_of_dude.static_constants.Tools;

public class ContactsSettings extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ContactsListModel clm;
	private UsersListModel ulm = new UsersListModel();
	private JList userList, contactList;

	/**
	 * Create the dialog.
	 */
	public ContactsSettings() {
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						ContactsSettings.class
								.getResource("/net/it_tim/dude_of_dude/icons/Papermart/Contacts.png")));
		setTitle("Contacts Management");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 547, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JSplitPane splitPane = new JSplitPane();
			contentPanel.add(splitPane, BorderLayout.CENTER);
			{
				userList = new JList(ulm);
				userList.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = userList.getSelectedIndex();
						if (index != -1) {
							clm = new ContactsListModel(ulm.getUser(index));
							contactList.setModel(clm);
						}
					}
				});
				userList.setSelectedIndex(0);
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setViewportView(userList);
				splitPane.setLeftComponent(scrollPane);
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
						JButton btnAddUser = new JButton("Add Contact");
						btnAddUser.setMnemonic('A');
						btnAddUser.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								String email = JOptionPane
										.showInputDialog("Enter e-mail address: ");
								if (email != null)
									if (Tools.isValidEmailAddress(email)) {
										Contacts contact = new Contacts();
										contact.setContact(email);
										clm.addContact(contact);
									} else {
										JOptionPane.showMessageDialog(null,
												"~~~ Invalid e-mail ~~~");
									}

							}
						});
						btnAddUser
								.setIcon(new ImageIcon(
										ContactsSettings.class
												.getResource("/net/it_tim/dude_of_dude/icons/Signage/Add_Square.png")));
						toolBar.add(btnAddUser);
					}
					{
						JButton btnRemoveUser = new JButton("Remove Contact");
						btnRemoveUser.setMnemonic('R');
						btnRemoveUser
								.setIcon(new ImageIcon(
										ContactsSettings.class
												.getResource("/net/it_tim/dude_of_dude/icons/Signage/Remove_Square.png")));
						toolBar.add(btnRemoveUser);
					}
				}
				{
					clm = new ContactsListModel(ulm.getUser(0));
					contactList = new JList(clm);
					contactList.setSelectedIndex(0);
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					panel.add(scrollPane, BorderLayout.CENTER);
					scrollPane.setViewportView(contactList);
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
