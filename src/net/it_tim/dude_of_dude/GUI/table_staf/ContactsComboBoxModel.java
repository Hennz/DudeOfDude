package net.it_tim.dude_of_dude.GUI.table_staf;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import java.util.List;

import net.it_tim.dude_of_dude.database.Contacts;
import net.it_tim.dude_of_dude.database.ContactsHome;

public class ContactsComboBoxModel extends AbstractListModel implements
		ComboBoxModel {
	private static final long serialVersionUID = 1L;
	private ContactsHome ch;
	private List<Contacts> contact_list;
	private String selected = (String) null;
	
	@SuppressWarnings("unchecked")
	public ContactsComboBoxModel() {
		ch = new ContactsHome();
		contact_list = ch.getAll();
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
		return contact_list.get(arg0).getContact() + " - " + contact_list.get(arg0).getUsers().getLogin();
	}

	@Override
	public int getSize() {
		return contact_list.size();
	}
	
	public Contacts getSelectedConact(int arg0) {
		return contact_list.get(arg0);
	}

}
