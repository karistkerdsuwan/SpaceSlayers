import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.MidiDevice.Info;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class MainWindow extends JFrame{
	public static PlayerShip ship;
	public Image bufferImage;
	public Graphics bufferGraphic;
	public JPanel panel;
	
	public static boolean keepRight = false;
	public static boolean keepLeft = false;
	public static boolean keepUp = false;
	public static boolean keepDown = false;
	
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

				Game.info.allObjects.get(a).draw(g);				
				Game.info.allObjects.get(a).update(g);
		}
	}
	
	MainWindow(PlayerShip shipToMove){
		this.setTitle("Space");		
		this.setFocusable(false);
		panel = new JPanel();
		panel.setName("SpacePanel");
		this.setContentPane(panel);
		panel.setBackground(Color.black);

		this.setSize(new Dimension (700, 700));
		this.setVisible(false);
		this.setVisible(true);
		ship = shipToMove;
		ship.draw(this.getGraphics());
		this.paint(this.getGraphics());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.requestFocus();
		
	//  Adds and subracts from the position of the ship based on player input during a loop;
	//	While the player does not let go of the movement button, the ship moves
		
		Timer timer = new Timer();	
		TimerTask task = new TimerTask(){
			public void run (){		
				if(MainWindow.keepDown&ship.yCoordinate<670){
					MainWindow.ship.yCoordinate +=1;
				}
				if(MainWindow.keepRight&ship.xCoordinate<700){
					MainWindow.ship.xCoordinate +=1;
				}
				if(MainWindow.keepUp&ship.yCoordinate>0){
					MainWindow.ship.yCoordinate -=1;
				}
				if(MainWindow.keepLeft&ship.xCoordinate>0){
					MainWindow.ship.xCoordinate -=1;
		}
			}
		};
		timer.scheduleAtFixedRate(task, 100, 5);

		
		InputMap map = new InputMap();
		
		Action PlayerMovementUp = new PlayerMovementUp();
		Action PlayerMovementDown = new PlayerMovementDown();
		Action PlayerMovementLeft = new PlayerMovementLeft();
		Action PlayerMovementRight = new PlayerMovementRight();

		Action PlayerMovementUpRelease = new PlayerMovementUpRelease();
		Action PlayerMovementDownRelease = new PlayerMovementDownRelease();
		Action PlayerMovementLeftRelease = new PlayerMovementLeftRelease();
		Action PlayerMovementRightRelease = new PlayerMovementRightRelease();

		panel.getInputMap().put(KeyStroke.getKeyStroke("UP") , "upKey" );
		panel.getActionMap().put("upKey", PlayerMovementUp);
		
		panel.getInputMap().put(KeyStroke.getKeyStroke("DOWN") , "downKey" );
		panel.getActionMap().put("downKey", PlayerMovementDown);
		
		panel.getInputMap().put(KeyStroke.getKeyStroke("LEFT") , "leftKey" );
		panel.getActionMap().put("leftKey", PlayerMovementLeft);
		
		panel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT") , "rightKey" );
		panel.getActionMap().put("rightKey", PlayerMovementRight);
		
		
		panel.getInputMap().put(KeyStroke.getKeyStroke("released UP") , "upKeyRelease" );
		panel.getActionMap().put("upKeyRelease", PlayerMovementUpRelease);
		
		panel.getInputMap().put(KeyStroke.getKeyStroke("released DOWN") , "downKeyRelease" );
		panel.getActionMap().put("downKeyRelease", PlayerMovementDownRelease);
		
		panel.getInputMap().put(KeyStroke.getKeyStroke("released LEFT") , "leftKeyRelease" );
		panel.getActionMap().put("leftKeyRelease", PlayerMovementLeftRelease);
		
		panel.getInputMap().put(KeyStroke.getKeyStroke("released RIGHT") , "rightKeyRelease" );
		panel.getActionMap().put("rightKeyRelease", PlayerMovementRightRelease);

	}
	
	static class PlayerMovementUp extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			keepUp = true;
		}
	}
	static class PlayerMovementDown extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			keepDown = true;
		}
	}
	static class PlayerMovementLeft extends AbstractAction {

		public void actionPerformed(ActionEvent event) {
			keepLeft = true;
		}
	}
	static class PlayerMovementRight extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			keepRight = true;
		}
	}
	
	static class PlayerMovementUpRelease extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			keepUp = false;
		}
	}
	static class PlayerMovementDownRelease extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			keepDown = false;
		}
	}
	static class PlayerMovementLeftRelease extends AbstractAction {

		public void actionPerformed(ActionEvent event) {
			keepLeft = false;
		}
	}
	static class PlayerMovementRightRelease extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			keepRight = false;

		}
	}
	static class PlayerSpace extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
		}
	}


}
