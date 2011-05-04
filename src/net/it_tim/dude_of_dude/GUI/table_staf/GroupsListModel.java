package net.it_tim.dude_of_dude.GUI.table_staf;

import java.util.List;

import javax.swing.AbstractListModel;

import net.it_tim.dude_of_dude.database.Groups;
import net.it_tim.dude_of_dude.database.GroupsHome;

public class GroupsListModel extends AbstractListModel {
	private static final long serialVersionUID = 1L;

	private GroupsHome gh;
	private List<Groups> group_list;
	
	@SuppressWarnings("unchecked")
	public GroupsListModel() {
		gh = new GroupsHome();
		group_list = gh.getAll();

	}
	
	@Override
	public Object getElementAt(int arg0) {
		if (group_list == null)
			return null;
		return group_list.get(arg0).getDescription();
	}

	@Override
	public int getSize() {
		if (group_list == null)
			return 0;
		return group_list.size();
	}
	
	public Groups getGroup(int index) {
		if (group_list == null)
			return new Groups();
		return gh.findById(group_list.get(index).getGroupId());
	}
	
	public void addGroup(String description) {
		Groups gr = new Groups();
		gr.setDescription(description);
		try {
		group_list.add(gr);
		gh.persist(gr);
		fireContentsChanged(this, 0, group_list.size());
		} catch (RuntimeException re) {}
	}

	public void removeGroup(int index) {
		try {
			gh.delete(group_list.get(index));
			group_list.remove(index);
			fireContentsChanged(this, 0, group_list.size());
		} catch (RuntimeException re) {}

	}
	
	public void updateGroup(int index, String new_name) {
		try {
			Groups gr = group_list.get(index);
			gr.setDescription(new_name);
			gh.update(gr);
			group_list.set(index, gr);
			fireContentsChanged(this, 0, group_list.size());
		} catch (RuntimeException re) {}
	}
}
