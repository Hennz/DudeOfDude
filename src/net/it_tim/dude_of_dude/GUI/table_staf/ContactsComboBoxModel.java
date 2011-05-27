package net.it_tim.dude_of_dude.GUI.table_staf;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import net.it_tim.dude_of_dude.database.Contacts;
import net.it_tim.dude_of_dude.database.DatabaseHome;

public class ContactsComboBoxModel extends AbstractListModel implements
		ComboBoxModel {
	private static final long serialVersionUID = 1L;
	private DatabaseHome ch;
	private List<Contacts> contact_list;
	private String selected = (String) null;

	public ContactsComboBoxModel() {
		ch = new DatabaseHome();
		contact_list = ch.getAll(Contacts.class);
	}

	@Override
	public Object getSelectedItem() {
		return selected;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selected = (String) anItem;
	}

	@Override
	public Object getElementAt(int arg0) {
		return contact_list.get(arg0).getContact() + " - "
				+ contact_list.get(arg0).getUsers().getLogin();
	}

	@Override
	public int getSize() {
		return contact_list.size();
	}

	public Contacts getSelectedConact(int arg0) {
		return contact_list.get(arg0);
	}

}
