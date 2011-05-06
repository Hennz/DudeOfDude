package net.it_tim.dude_of_dude.GUI.table_staf;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractListModel;

import net.it_tim.dude_of_dude.database.Groups;
import net.it_tim.dude_of_dude.database.Hosts;
import net.it_tim.dude_of_dude.database.HostsHome;

public class HostsListModel extends AbstractListModel {
	private static final long serialVersionUID = 1L;

	private HostsHome hh;
	private List<Hosts> host_list;
	private Groups hGroup;
	
	public HostsListModel(Groups group) {
		hh = new HostsHome();
		host_list = new ArrayList<Hosts>(group.getHostses());
		hGroup = group;
	}
	
	@Override
	public Object getElementAt(int arg0) {
		if (host_list == null)
			return null;
		String result = new String(host_list.get(arg0).getIpAdres() + " - " + host_list.get(arg0).getDescription());
		return result;
	}

	@Override
	public int getSize() {
		if (host_list == null)
			return 0;
		return host_list.size();
	}

	public void addHostToGroup(Hosts host) {
		if (host == null)
			return;
		try {
			host_list.add(host);
			Set<Groups> groupses = host.getGroupses();
			groupses.add(hGroup);
			hh.update(host);
			fireContentsChanged(this, 0, host_list.size());
		} catch (RuntimeException re) {
		}
	}

	public void removeHostFromGroup(int index) {
		try {
			Hosts host = host_list.get(index);
			Set<Groups> groupses = host.getGroupses();
			groupses.remove(hGroup);
			host.setGroupses(groupses);
			host_list.remove(index);
			hh.update(host);
			fireContentsChanged(this, 0, host_list.size());
		} catch (RuntimeException re) {}

	}
	
	/*
	public void updateGroup(int index, String new_name) {
		try {
			Groups gr = group_list.get(index);
			gr.setDescription(new_name);
			gh.update(gr);
			group_list.set(index, gr);
			fireContentsChanged(this, 0, group_list.size());
		} catch (RuntimeException re) {}
	}
	*/
}
