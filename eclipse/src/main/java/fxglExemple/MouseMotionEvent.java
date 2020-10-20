package fxglExemple;

import java.awt.Event;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class MouseMotionEvent implements MouseListener, MouseMotionListener {
	private int X, Y;

	public MouseMotionEvent(JPanel... pns) {
		for (JPanel panel : pns) {
			panel.addMouseListener(this);
			panel.addMouseMotionListener(this);

		}
	}

//Inutile ?
	public void mouseDragged(MouseEvent event) {
//		event.getComponent().setLocation((event.getX()+event.getComponent().getX()-X),(event.getY()+event.getComponent().getY()-Y));
	}

	public void mouseMoved(MouseEvent event) {
		event.getComponent().getMousePosition();
		System.out.println("YES");
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub
		X = event.getX();
		Y = event.getY();
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
