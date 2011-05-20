package net.it_tim.dude_of_dude.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import net.it_tim.dude_of_dude.GUI.table_staf.HostsComboBoxModel;
import net.it_tim.dude_of_dude.GUI.table_staf.HostsListModel;
import net.it_tim.dude_of_dude.database.Hosts;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AppendHostDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private HostsComboBoxModel hostsModel;
	private JComboBox comboBox;
	private HostsListModel hlm;

	/**
	 * Create the dialog.
	 */
	public AppendHostDialog(HostsListModel hostsInGroupsListModel) {
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						AppendHostDialog.class
								.getResource("/net/it_tim/dude_of_dude/icons/Emporium/Home.png")));
		setTitle("Select host");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		hostsModel = new HostsComboBoxModel();
		hlm = hostsInGroupsListModel;
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel = new JLabel("Select host:");
			lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			contentPanel.add(lblNewLabel, BorderLayout.NORTH);
		}
		{
			comboBox = new JComboBox(hostsModel);
			comboBox.setSelectedIndex(0);
			contentPanel.add(comboBox, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Object tmp_host = new Object();
						tmp_host = hostsModel.getSelectedHost(comboBox
								.getSelectedIndex());
						if (tmp_host instanceof Hosts)
							hlm.addHostToGroup((Hosts) tmp_host);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		pack();
	}

}
