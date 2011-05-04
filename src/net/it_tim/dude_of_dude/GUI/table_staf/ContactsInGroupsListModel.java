package net.it_tim.dude_of_dude.GUI.table_staf;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractListModel;

import net.it_tim.dude_of_dude.database.Contacts;
//import net.it_tim.dude_of_dude.database.ContactsHome;

public class ContactsInGroupsListModel extends AbstractListModel {
	private static final long serialVersionUID = 1L;

	//private ContactsHome ch;
	private List<Contacts> contact_list;
	
	public ContactsInGroupsListModel(Set<Contacts> contact_set) {
		//ch = new ContactsHome();
		contact_list = new ArrayList<Contacts>(contact_set);

	}
	
	@Override
	public Object getElementAt(int arg0) {
		if (contact_list == null)
			return null;
		return contact_list.get(arg0).getContact();
	}

	@Override
	public int getSize() {
		if (contact_list == null)
			return 0;
		return contact_list.size();
	}
	
	public Contacts getContact(int index) {
		if (contact_list == null)
			return new Contacts();
		return contact_list.get(index);
	}
}
