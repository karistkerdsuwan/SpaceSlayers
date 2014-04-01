import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class MainWindow extends JFrame implements KeyListener{
	private PlayerShip ship;
	public Image bufferImage;
	public Graphics bufferGraphic;
	public boolean keyLetGo=false;
	public JPanel panel;
	
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
		panel = new JPanel();
		this.setContentPane(panel);

		this.addKeyListener(this);
		this.setSize(new Dimension (700, 700));
		this.setVisible(false);
		this.setVisible(true);
		ship = shipToMove;
		ship.draw(this.getGraphics());
		this.paint(this.getGraphics());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		InputMap map = new InputMap();
		
		Action a = new PlayerMovement();
		
		
		panel.getInputMap().put(KeyStroke.getKeyStroke("UP") , "upKey" );
		panel.getActionMap().put("upKey", a);
		
		panel.getInputMap().put(KeyStroke.getKeyStroke("DOWN") , "downKey" );
		panel.getActionMap().put("downKey", a);
		
		panel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT") , "rightKey" );
		panel.getActionMap().put("rightKey", a);
		
		panel.getInputMap().put(KeyStroke.getKeyStroke("LEFT") , "leftKey" );
		panel.getActionMap().put("leftKey", a);
		

	}
	public void keyPressed(KeyEvent event) {
		// CODE HERE DOES NOT ALLOW MULTIPLE BUTTONS TO BE HELD DOWN AT ONCE 
		// AND HAS A DELAY WHILE STARTING TO MOVE
		// CONSIDER IT BACKUP
		
//		this.repaint();
//		keyLetGo=false;
//
//		int keyInt =event.getKeyCode();
//		char keyChar = event.getKeyChar();
//
//		System.out.println(keyChar);
//		if (keyInt == 37){
//	//		System.out.println("Left Key Pressed");
//		//	System.out.println(ship.xCoordinate + " " + ship.yCoordinate);
//			ship.xCoordinate-=1;
//			
//		} else if (keyInt == 38){
//	//		System.out.println("Up Key Pressed");
//	//		System.out.println(ship.xCoordinate + " " + ship.yCoordinate);
//			ship.yCoordinate-=1;
//
//
//		} else if (keyInt ==39){
////			System.out.println("Right Key Pressed");
////			System.out.println(ship.xCoordinate + " " + ship.yCoordinate);
//			ship.xCoordinate+=1;
//
//		} else if (keyInt ==40){
//	//		System.out.println("Down Key Pressed");
//	//		System.out.println(ship.xCoordinate + " " + ship.yCoordinate);
//			ship.yCoordinate+=1;
//		} 
		}
	public void keyReleased(KeyEvent event) {
		keyLetGo=true;
	}
	public void keyTyped(KeyEvent event) {
			
	}
}
