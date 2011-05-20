package net.it_tim.dude_of_dude.GUI.table_staf;

import java.util.List;

import javax.swing.AbstractListModel;

import net.it_tim.dude_of_dude.database.Users;
import net.it_tim.dude_of_dude.database.UsersHome;

public class UsersListModel extends AbstractListModel {
	private static final long serialVersionUID = 1L;

	private UsersHome uh;
	private List<Users> user_list;

	@SuppressWarnings("unchecked")
	public UsersListModel() {
		uh = new UsersHome();
		user_list = uh.getAll();

	}

	@Override
	public Object getElementAt(int arg0) {
		if (user_list == null)
			return null;
		return user_list.get(arg0).getLogin();
	}

	@Override
	public int getSize() {
		if (user_list == null)
			return 0;
		return user_list.size();
	}

	public void addUser(Users user) {
		try {
			user_list.add(user);
			uh.persist(user);
			fireContentsChanged(this, 0, user_list.size());
		} catch (RuntimeException re) {
		}
	}

	public void removeUser(int index) {
		try {
			uh.delete(user_list.get(index));
			user_list.remove(index);
			fireContentsChanged(this, 0, user_list.size());
		} catch (RuntimeException re) {
		}

	}

	public void updateUser(int index, Users user) {
		try {
			uh.update(user);
			user_list.set(index, user);
			fireContentsChanged(this, 0, user_list.size());
		} catch (RuntimeException re) {
		}
	}

	public Users getUser(int index) {
		if (user_list == null)
			return new Users();
		return uh.findById(user_list.get(index).getUserId());
	}
}
