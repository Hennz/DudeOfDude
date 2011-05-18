package net.it_tim.dude_of_dude.GUI.table_staf;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.it_tim.dude_of_dude.database.Hosts;
import net.it_tim.dude_of_dude.database.HostsHome;

public class HostsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private List<Hosts> list;
	private HostsHome hh;
	
	String headers[] = { "IP", "Description", "Time out in ms",
    "Interval in ms", "To ping?", "Packet loss", "Down count" };
	
	@SuppressWarnings("unchecked")
	Class columnClasses[] = { String.class, String.class,
    Long.class, Long.class, Boolean.class, Short.class, Short.class };

	@SuppressWarnings("unchecked")
	public HostsTableModel() {
		hh = new HostsHome();
		list = hh.getAll();
	}
	
    public HostsTableModel(List<Hosts> aList){
        list = aList;
    }
	
	@Override
	public Class<?> getColumnClass(int arg0) {
		return columnClasses[arg0];
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public String getColumnName(int arg0) {
		return headers[arg0];
	}

	@Override
	public int getRowCount() {
		if (list == null)
			return 0;
		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (list == null)
			return null;
		
		switch (columnIndex) {
		case 0:
			return list.get(rowIndex).getIpAdres();
		case 1:
			return list.get(rowIndex).getDescription();
		case 2:
			return list.get(rowIndex).getTimeoutMs().toString();
		case 3:
			return list.get(rowIndex).getIntervalMs().toString();
		case 4:
			return list.get(rowIndex).getToPing();
		case 5: 
			return list.get(rowIndex).getPacketLoss();
		case 6:
			return list.get(rowIndex).getDownCount();
		}
		
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public void setValueAt(Object arg0, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			if (arg0 instanceof String)
			list.get(rowIndex).setIpAdres((String) arg0);
			break;
		case 1:
			if (arg0 instanceof String)
			list.get(rowIndex).setDescription((String) arg0);
			break;
		case 2:
			if (arg0 instanceof Long)
			list.get(rowIndex).setTimeoutMs( (Long)arg0 );
			break;
		case 3:
			if (arg0 instanceof Long)
			list.get(rowIndex).setIntervalMs( (Long)arg0 );
			break;
		case 4:
			if (arg0 instanceof Boolean)
			list.get(rowIndex).setToPing((Boolean) arg0);
			break;
		case 5:
			if (arg0 instanceof Short)
			list.get(rowIndex).setPacketLoss((Short) arg0);
			break;
		case 6:
			if (arg0 instanceof Short)
			list.get(rowIndex).setDownCount((Short) arg0);
			break;
		}
		
		try {
			hh.update(list.get(rowIndex));
		} catch (RuntimeException ex) {
			System.out.println(ex.getMessage());
		} 
		
		fireTableCellUpdated(rowIndex, columnIndex);

	}

	public void addRow() {
		Hosts host = new Hosts();
		host.setDescription("Dummy");
		host.setIpAdres("127.0.0.1");
		host.setTimeoutMs(new Long(200));
		host.setIntervalMs(new Long(60000));
		host.setToPing(false);
		host.setPacketLoss(new Short((short) 5));
		host.setDownCount(new Short((short) 1));
		
		try {
		list.add(host);
		hh.persist(host);
		} catch (RuntimeException ex) { }
		fireTableDataChanged();
	}
	
	public void deleteRow(int rowCount) {
		try {
			hh.delete(list.get(rowCount));
			list.remove(rowCount);
		} catch (RuntimeException ex) { }
		fireTableDataChanged();
	}
}
