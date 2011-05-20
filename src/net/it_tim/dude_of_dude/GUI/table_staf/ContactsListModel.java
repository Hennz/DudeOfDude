package net.it_tim.dude_of_dude.GUI.table_staf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractListModel;

import net.it_tim.dude_of_dude.database.Contacts;
import net.it_tim.dude_of_dude.database.ContactsHome;
import net.it_tim.dude_of_dude.database.Groups;
import net.it_tim.dude_of_dude.database.GroupsHome;
import net.it_tim.dude_of_dude.database.Users;

public class ContactsListModel extends AbstractListModel {
	private static final long serialVersionUID = 1L;

	private GroupsHome gh;
	private ContactsHome ch;
	private List<Contacts> contact_list;
	private Groups group;
	private Users user;

	public ContactsListModel(Groups group) {
		gh = new GroupsHome();
		contact_list = new ArrayList<Contacts>(group.getContactses());
		this.group = group;
	}

	public ContactsListModel(Users user) {
		ch = new ContactsHome();
		contact_list = new ArrayList<Contacts>(user.getContactses());
		this.user = user;
	}

	@SuppressWarnings("unchecked")
	public ContactsListModel() {
		ch = new ContactsHome();
		contact_list = ch.getAll();
	}

	@Override
	public Object getElementAt(int arg0) {
		if (contact_list == null)
			return null;
		String result = contact_list.get(arg0).getContact() + " - "
				+ contact_list.get(arg0).getUsers().getLogin();
		return result;
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

	public void addContactToGroup(Contacts contact) {
		if (contact == null)
			return;
		try {
			contact_list.add(contact);
			Set<Contacts> contact_set = new HashSet<Contacts>(contact_list);
			group.setContactses(contact_set);
			gh.update(group);
			fireContentsChanged(this, 0, contact_list.size());
		} catch (RuntimeException re) {
		}
	}

	public void removeContactFromGroup(int index) {
		if (index == -1)
			return;
		try {
			contact_list.remove(index);
			Set<Contacts> contact_set = new HashSet<Contacts>(contact_list);
			group.setContactses(contact_set);
			gh.update(group);
			fireContentsChanged(this, 0, contact_list.size());
		} catch (RuntimeException re) {
		}
	}

	public void addContact(Contacts contact) {
		if (contact == null)
			return;
		try {
			contact_list.add(contact);
			contact.setUsers(user);
			ch.persist(contact);
			fireContentsChanged(this, 0, contact_list.size());
		} catch (RuntimeException re) {
		}
	}

	public void removeContact(int index) {
		if (index == -1)
			return;
		try {
			ch.delete(contact_list.get(index));
			contact_list.remove(index);
			fireContentsChanged(this, 0, contact_list.size());
		} catch (RuntimeException re) {
		}
	}
}
