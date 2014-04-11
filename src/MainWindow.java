import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
	public int finalScore;

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

	public void setScore (int time){
		this.finalScore = time+StateInformation.score;
	}

	public void paint (Graphics g){			
		super.paint(g);
		ship.draw(g);
		for (int a =0; a <=Game.info.allObjects.size()-1;a++){
			Game.info.allObjects.get(a).draw(g);		
		}

		if(!ship.dead){
			if(ship.invincible>680){
				if(ship.shieldStage==4){
					g.setColor(new Color (200,200,200,120));
				} else if(ship.shieldStage==3){
					g.setColor(new Color (210,210,210,140));
				} else if(ship.shieldStage==2){
					g.setColor(new Color (220,220,220,160));
				} else {
					g.setColor(new Color (225,225,225,180));
				}
				g.fillRect(0, 0, 950, 900);
			}
			for (int a =0; a <=Game.info.allObjects.size()-1;a++){
				Game.info.allObjects.get(a).update(g);
			}
		} else {
			
			// death animation goes here
			g.setColor(new Color (225, 225, 225, 205));
			g.fillRect(0, 0, 950, 750);
			g.setColor(Color.WHITE);
			g.setFont(this.getFont().deriveFont(Font.BOLD).deriveFont(new Float(300)));
			g.drawString("DEAD", 0, 300);
			g.setFont(this.getFont().deriveFont(Font.BOLD).deriveFont(new Float(100)));
			g.drawString("SCORE: "+ String.valueOf(finalScore), 15, 400);
				
		}
	}

	MainWindow(PlayerShip shipToMove){
		this.setTitle("Space");		
		this.setFocusable(false);
		panel = new JPanel();
		panel.setName("SpacePanel");
		this.setContentPane(panel);
		panel.setBackground(Color.black);

		this.setSize(new Dimension (900, 700));
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
				if(!ship.dead){
					if(MainWindow.keepDown&ship.yCoordinate<675){
						if(ship.speedUp==0){
							MainWindow.ship.yCoordinate +=1.1;
						} else {
							MainWindow.ship.yCoordinate +=2.2;
						}
					}
					if(MainWindow.keepRight&ship.xCoordinate<870){
						if(ship.speedUp==0){
							MainWindow.ship.xCoordinate +=1.1;
						} else {
							MainWindow.ship.xCoordinate +=2.2;
						}
					}
					if(MainWindow.keepUp&ship.yCoordinate>10){
						if(ship.speedUp==0){
							MainWindow.ship.yCoordinate -=1.1;
						} else {
							MainWindow.ship.yCoordinate -=2.2;
						}
					}
					if(MainWindow.keepLeft){//&ship.xCoordinate>0){
						if(ship.speedUp==0){
							MainWindow.ship.xCoordinate -=1.1;
						} else {
							MainWindow.ship.xCoordinate -=2.2;
						}
					}
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
