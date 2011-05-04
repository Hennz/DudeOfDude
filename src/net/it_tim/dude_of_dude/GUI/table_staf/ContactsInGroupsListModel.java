package net.it_tim.dude_of_dude.GUI.table_staf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractListModel;

import net.it_tim.dude_of_dude.database.Contacts;
import net.it_tim.dude_of_dude.database.Groups;
import net.it_tim.dude_of_dude.database.GroupsHome;
//import net.it_tim.dude_of_dude.database.ContactsHome;

public class ContactsInGroupsListModel extends AbstractListModel {
	private static final long serialVersionUID = 1L;

	private GroupsHome gh;
	private List<Contacts> contact_list;
	private Groups group;
	
	public ContactsInGroupsListModel(Groups group) {
		gh = new GroupsHome();
		contact_list = new ArrayList<Contacts>(group.getContactses());
		this.group = group;
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
	
	public void addContact(Contacts contact) {
		if (contact == null)
			return;
		try {
		contact_list.add(contact);
		Set<Contacts> contact_set = new HashSet<Contacts>(contact_list);
		group.setContactses(contact_set);
		gh.update(group);
		fireContentsChanged(this, 0, contact_list.size());
		} catch (RuntimeException re) {}
	}
	
	public void removeContact(int index) {
		if (index == -1)
			return;
		try {
		contact_list.remove(index);
		Set<Contacts> contact_set = new HashSet<Contacts>(contact_list);
		group.setContactses(contact_set);
		gh.update(group);
		fireContentsChanged(this, 0, contact_list.size());
		} catch (RuntimeException re) {}
	}
}
