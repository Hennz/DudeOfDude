package net.it_tim.dude_of_dude.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import net.it_tim.dude_of_dude.GUI.table_staf.ContactsComboBoxModel;
import net.it_tim.dude_of_dude.GUI.table_staf.ContactsInGroupsListModel;
import net.it_tim.dude_of_dude.database.Contacts;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppendContactDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final ContactsComboBoxModel contactsModel = new ContactsComboBoxModel();
	private JComboBox comboBox;
	private ContactsInGroupsListModel cin;
	/**
	 * Create the dialog.
	 */
	public AppendContactDialog(ContactsInGroupsListModel contactsInGroupsListModel) {
		cin = contactsInGroupsListModel;
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel = new JLabel("Select contact to append:");
			lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			contentPanel.add(lblNewLabel, BorderLayout.NORTH);
		}
		{
			comboBox = new JComboBox(contactsModel);
			comboBox.setSelectedIndex(0);
			contentPanel.add(comboBox, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Object tmp_contact = new Object();
						tmp_contact = contactsModel.getSelectedConact(comboBox.getSelectedIndex());
						System.out.println(tmp_contact);
						if (tmp_contact instanceof Contacts)
							cin.addContact((Contacts) tmp_contact);
						dispose();
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
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		pack();
	}

}
