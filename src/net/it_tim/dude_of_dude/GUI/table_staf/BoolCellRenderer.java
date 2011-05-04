package net.it_tim.dude_of_dude.GUI.table_staf;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class BoolCellRenderer extends JCheckBox implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	public BoolCellRenderer() {
		super();
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object value,
			boolean arg2, boolean arg3, int arg4, int arg5) {
		if (value == null){
			return this;
		}
		
		if (value instanceof Boolean) {
			if (((Boolean) value).booleanValue()) {
				setBackground(new Color(0, 255, 0));
			} else {
				setBackground(new Color(255, 0, 0));
			}

			setSelected(((Boolean) value).booleanValue());
		} else {
			setSelected(false);
		}
		return this; 
	}

}
