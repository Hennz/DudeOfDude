package net.it_tim.dude_of_dude.GUI.table_staf;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import java.util.List;

import net.it_tim.dude_of_dude.database.Hosts;
import net.it_tim.dude_of_dude.database.HostsHome;

public class HostsComboBoxModel extends AbstractListModel implements
		ComboBoxModel {
	private static final long serialVersionUID = 1L;
	private HostsHome hh;
	private List<Hosts> host_list;
	private String selected = (String) null;
	
	@SuppressWarnings("unchecked")
	public HostsComboBoxModel() {
		hh = new HostsHome();
		host_list = hh.getAll();
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
		return host_list.get(arg0).getIpAdres() + " - " + host_list.get(arg0).getDescription();
	}

	@Override
	public int getSize() {
		return host_list.size();
	}
	
	public Hosts getSelectedHost(int arg0) {
		return host_list.get(arg0);
	}

}
