import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class MainWindow extends JFrame implements KeyListener{
	private PlayerShip ship;
	void update (StateInformation info){
		super.paint(this.getGraphics());
//		this.getGraphics().drawRect(50, 50, 600, 600);
		ship.draw(this.getGraphics());
		for (int objectCount=0;objectCount!=info.allObjects.size();objectCount++){
			info.allObjects.get(objectCount).update(this.getGraphics());
		}
	}
	
	MainWindow(PlayerShip shipToMove){
		this.setTitle("Space");
		this.addKeyListener(this);
		this.setSize(new Dimension (700, 700));
		this.setVisible(false);
		this.setVisible(true);
		ship = shipToMove;
		ship.draw(this.getGraphics());
		this.paint(this.getGraphics());
	}
	
	public void keyPressed(KeyEvent event) {
		this.repaint();

		int keyInt =event.getKeyCode();
		char keyChar = event.getKeyChar();
		
		if (keyInt == 37){
			System.out.println("Left Key Pressed");
			System.out.println(ship.xCoordinate + " " + ship.yCoordinate);
			ship.xCoordinate-=4;
			
		} else if (keyInt == 38){
			System.out.println("Up Key Pressed");
			System.out.println(ship.xCoordinate + " " + ship.yCoordinate);
			ship.yCoordinate-=4;


		} else if (keyInt ==39){
			System.out.println("Right Key Pressed");
			System.out.println(ship.xCoordinate + " " + ship.yCoordinate);
			ship.xCoordinate+=4;

		} else if (keyInt ==40){
			System.out.println("Down Key Pressed");
			System.out.println(ship.xCoordinate + " " + ship.yCoordinate);
			ship.yCoordinate+=4;

		}

	}
	public void keyReleased(KeyEvent event) {
//		System.out.println(event);
		
	}
	public void keyTyped(KeyEvent event) {
//		System.out.println(event);
		
	}
}
