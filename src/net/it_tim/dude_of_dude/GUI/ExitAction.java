package net.it_tim.dude_of_dude.GUI;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class ExitAction extends AbstractAction {
	private static final long serialVersionUID = 1L;

	public ExitAction()
	{
		super("Exit");
		putValue( Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_X) );
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		for (Frame frame : Frame.getFrames())
		{
			if (frame.isActive())
			{
				WindowEvent windowClosing = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(windowClosing);
			}
		}
	}

}
