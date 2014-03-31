import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class MainWindow extends JFrame implements KeyListener{
	private PlayerShip ship;
	public Image bufferImage;
	public Graphics bufferGraphic;

	public void update(Graphics g){
		if(bufferImage == null){
			bufferImage = createImage(this.getSize().width, this.getSize().width);
			bufferGraphic = bufferImage.getGraphics();
		}
		bufferGraphic.setColor(getBackground());
		bufferGraphic.fillRect(0, 0, this.getSize().width, this.getSize().height);
		bufferGraphic.setColor(getForeground());
		paint(bufferGraphic);
		g.drawImage(bufferImage, 0, 0, this);
	}
	
	public void paint (Graphics g){
		super.paint(g);
		
		ship.draw(g);

		for (int a =0; a <=Game.info.allObjects.size()-1;a++){
			Game.info.allObjects.get(a).update(g);
			Game.info.allObjects.get(a).draw(g);
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
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void keyPressed(KeyEvent event) {
		this.repaint();

		int keyInt =event.getKeyCode();
		char keyChar = event.getKeyChar();
		
		if (keyInt == 37){
	//		System.out.println("Left Key Pressed");
		//	System.out.println(ship.xCoordinate + " " + ship.yCoordinate);
			ship.xCoordinate-=6;
			
		} else if (keyInt == 38){
	//		System.out.println("Up Key Pressed");
	//		System.out.println(ship.xCoordinate + " " + ship.yCoordinate);
			ship.yCoordinate-=6;


		} else if (keyInt ==39){
//			System.out.println("Right Key Pressed");
//			System.out.println(ship.xCoordinate + " " + ship.yCoordinate);
			ship.xCoordinate+=6;

		} else if (keyInt ==40){
	//		System.out.println("Down Key Pressed");
	//		System.out.println(ship.xCoordinate + " " + ship.yCoordinate);
			ship.yCoordinate+=6;

		}

	}
	public void keyReleased(KeyEvent event) {
//		System.out.println(event);
		
	}
	public void keyTyped(KeyEvent event) {
//		System.out.println(event);
		
	}
}
