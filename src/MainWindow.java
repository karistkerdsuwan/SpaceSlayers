import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class MainWindow extends JFrame{
	public static PlayerShip ship;
	public Image bufferImage;
	public Graphics bufferGraphic;
	public JPanel panel;
	public int finalScore;
	public BufferedImage laserImg;
	public BufferedImage laserImg2;
	public BufferedImage laserImg3;

	public static boolean keepRight = false;
	public static boolean keepLeft = false;
	public static boolean keepUp = false;
	public static boolean keepDown = false;
	public static boolean keepFire = false;
	public static String lastKeyPressed = "blank";

	public static int dashTimerUp = 0;
	public static int dashTimerRight = 0;
	public static int dashTimerDown = 0;
	public static int dashTimerLeft = 0;

	public static int xMax = 0;
	public static int yMax = 0;

	public static boolean justDashed = false;
	public static int dashLimit = 0;
	public static int laserTime = 0;
	public static int planet=0;

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

		for (int a =0; a <=Game.info.allStars.size()-1;a++){
			Game.info.allStars.get(a).draw(g);
			Game.info.allStars.get(a).update();
		}

		if(Game.info.allStars.size()<198){
			int xRan = (int) (Math.random() * 500)+1400;
			int yRan = (int) (Math.random() * 700);

			StateInformation.allStars.add(new Stars(xRan, yRan, Color.yellow));
			xRan = (int) (Math.random() * 500)+900;
			yRan = (int) (Math.random() * 700);

			StateInformation.allStars.add(new Stars(xRan, yRan, Color.yellow));
		}


		for (int a =0; a <=Game.info.allStars.size()-1;a++){
			Game.info.allStars.get(a).draw(g);
			Game.info.allStars.get(a).update();
		}

		if(planet==0){
			int planetSpawn = (int) (Math.random() * 1)+1;
			if(planetSpawn==1){
				planet=1;
				int xRan = (int) (Math.random() * 500)+1500;
				int yRan = (int) (Math.random() * 600)+30;
				int sizeRan = (int) (Math.random() * 400)+200;
				int typeRan = (int) (Math.random() * 3);

				StateInformation.allPlanets.add(new Planets(xRan, yRan, Color.ORANGE, sizeRan, typeRan));
			}
		} else {			
			StateInformation.allPlanets.get(0).draw(g);
			StateInformation.allPlanets.get(0).update();
		}


		ship.draw(g);
		for (int a =0; a <=Game.info.allObjects.size()-1;a++){
			Game.info.allObjects.get(a).draw(g);
		}

		if(laserTime>4000){
			if(laserTime>4300){
				g.drawImage(laserImg,(int)ship.xCoordinate+ship.xRadius-20, (int) ship.yCoordinate-ship.yRadius-2, xMax, 50, null);
			} else if(laserTime>4100)	{		
				g.drawImage(laserImg2,(int)ship.xCoordinate+ship.xRadius-20, (int) ship.yCoordinate-ship.yRadius-2, xMax, 50, null);

			} else if(laserTime>4001)		{
				g.drawImage(laserImg3,(int)ship.xCoordinate+ship.xRadius-20, (int) ship.yCoordinate-ship.yRadius-2, xMax, 50, null);
			}
			for(int counter = 0; counter<StateInformation.allObjects.size();counter++){
				if(StateInformation.allObjects.get(counter).type.equals("enemy")&&
						Math.abs(StateInformation.allObjects.get(counter).y-ship.yCoordinate)<(ship.yRadius*1.7)&&
						StateInformation.allObjects.get(counter).x>ship.xCoordinate){			
					if(StateInformation.allObjects.get(counter).getClass().toString().equals("class Projectile")||
							StateInformation.allObjects.get(counter).getClass().toString().equals("class BounceProjectile")){
					} else {
						StateInformation.score+=3;
						StateInformation.combo++;
					}
					StateInformation.allObjects.get(counter).remove();
				}
			}
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
				g.fillRect(0, 0, xMax, yMax);
			}
			for (int a =0; a <=Game.info.allObjects.size()-1;a++){
				Game.info.allObjects.get(a).update(g);
			}
			if(StateInformation.combo!=0){
				g.setColor(Color.white);				
				if(StateInformation.combo<10){
					g.setFont(this.getFont().deriveFont(Font.ITALIC).deriveFont(new Float(90)));
					g.drawString(String.valueOf(StateInformation.combo), xMax-85, 120);
				} else if(StateInformation.combo<100){
					g.setFont(this.getFont().deriveFont(Font.ITALIC).deriveFont(new Float(110)));
					g.drawString(String.valueOf(StateInformation.combo), xMax-140, 122);
				} else if(StateInformation.combo<1000){
					g.setFont(this.getFont().deriveFont(Font.ITALIC).deriveFont(new Float(150)));
					g.drawString(String.valueOf(StateInformation.combo), xMax-260, 140);
				} else {
					g.setFont(this.getFont().deriveFont(Font.BOLD).deriveFont(new Float(200)));
					g.drawString("1000", xMax-480, 180);
				}
			}

		} else {

			// death animation goes here
			g.setColor(new Color (225, 225, 225, 205));
			g.fillRect(0, 0, xMax, yMax);
			g.setColor(Color.WHITE);
			g.setFont(this.getFont().deriveFont(Font.BOLD).deriveFont(new Float(400)));
			g.drawString("DEAD", 0, 320);
			g.setFont(this.getFont().deriveFont(Font.BOLD).deriveFont(new Float(43)));
			g.drawString("SCORE: " + String.valueOf(finalScore), 30, 380);

		}
	}

	MainWindow(PlayerShip shipToMove){

		StateInformation.allStars = new ArrayList();
		StateInformation.allPlanets = new ArrayList();

		for(int count =0; count<100;count++){
			int xRan = (int) (Math.random() * 1200);
			int yRan = (int) (Math.random() * 700);
			StateInformation.allStars.add(new Stars(xRan, yRan, Color.yellow));
		}


		justDashed = false;
		this.setTitle("Space");		
		this.setFocusable(false);
		panel = new JPanel();
		panel.setName("SpacePanel");
		this.setContentPane(panel);
		panel.setBackground(Color.black);

		this.setSize(new Dimension (1200, 700));
		this.xMax=1200;
		this.yMax=700;
		this.setVisible(false);
		this.setVisible(true);
		ship = shipToMove;
		ship.draw(this.getGraphics());
		this.paint(this.getGraphics());

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.requestFocus();
		try {
			laserImg = ImageIO.read(new File("laser.png"));
		} catch (IOException e) {
		}
		try {
			laserImg2 = ImageIO.read(new File("laser2.png"));
		} catch (IOException e) {
		}
		try {
			laserImg3 = ImageIO.read(new File("laser3.png"));
		} catch (IOException e) {
		}

		//  Adds and subracts from the position of the ship based on player input during a loop;
		//	While the player does not let go of the movement button, the ship moves

		Timer timer = new Timer();	
		TimerTask movement = new TimerTask(){
			public void run (){		
				if(!ship.dead){		
					if( laserTime != 0){
						laserTime --;
					}
					if(laserTime<4000&&laserTime!=0){
						if(MainWindow.ship.imgString.equals("shipLas.png")){
							try {
								MainWindow.ship.img = ImageIO.read(new File("shipNoLas.png"));
								MainWindow.ship.imgString="shipNoLas.png";
							} catch (IOException e) {
							}
						}
					} else {
						if(MainWindow.ship.imgString.equals("shipNoLas.png")){
							try {
								MainWindow.ship.img = ImageIO.read(new File("shipLas.png"));
								MainWindow.ship.imgString="shipLas.png";
							} catch (IOException e) {
							}
						}
					}

					if(laserTime<4000){
						if( dashLimit != 0){
							dashLimit--;
						}

						if(dashTimerRight!=0||dashTimerLeft!=0||dashTimerUp!=0||dashTimerDown!=0){
							if(dashTimerRight!=0){
								dashTimerRight--;
							}
							if(dashTimerLeft!=0){
								dashTimerLeft--;
							}
							if(dashTimerUp!=0){
								dashTimerUp--;
							}
							if(dashTimerDown!=0){
								dashTimerDown--;
							}

							if(	dashLimit==0&&MainWindow.keepDown&&!MainWindow.keepLeft&&!MainWindow.keepRight&&!MainWindow.keepUp&&lastKeyPressed.equals("down")){
								justDashed = true;
								dashTimerDown=0;
								lastKeyPressed="blank";
								dashLimit=200;
								if(ship.yCoordinate+200>yMax){
									ship.yCoordinate=yMax-ship.yRadius;
								} else {
									ship.yCoordinate +=200;
								}
								// down
							} else if(dashLimit==0&&!MainWindow.keepDown&&MainWindow.keepLeft&&!MainWindow.keepRight&&!MainWindow.keepUp&&lastKeyPressed.equals("left")){
								// left
								justDashed = true;
								dashTimerLeft=0;
								lastKeyPressed="blank";
								dashLimit=200;
								if(ship.xCoordinate-300<0){
									ship.xCoordinate=0+ship.xRadius;
								} else {
									ship.xCoordinate -=300+ship.xRadius;
								}
							} else if(dashLimit==0&&!MainWindow.keepDown&&!MainWindow.keepLeft&&MainWindow.keepRight&&!MainWindow.keepUp&&lastKeyPressed.equals("right")){
								// right
								justDashed = true;
								dashTimerRight=0;
								lastKeyPressed="blank";
								dashLimit=200;
								if(ship.xCoordinate+300>xMax){
									ship.xCoordinate=xMax-ship.xRadius;
								} else {
									ship.xCoordinate +=300;
								}

							} else if(dashLimit==0&&!MainWindow.keepDown&&!MainWindow.keepLeft&&!MainWindow.keepRight&&MainWindow.keepUp&&lastKeyPressed.equals("up")){
								// up
								justDashed = true;
								dashTimerUp=0;
								lastKeyPressed="blank";
								dashLimit=200;
								if(ship.yCoordinate-200<20){
									ship.yCoordinate=20+ship.yRadius;
								} else {
									ship.yCoordinate -=200;
								}
							}

						} else {
							lastKeyPressed = "blank";
							if(MainWindow.keepDown&&!MainWindow.keepLeft&&!MainWindow.keepRight&&!MainWindow.keepUp){
								// down
								dashTimerDown=120;
								dashTimerLeft=0;
								dashTimerUp=0;
								dashTimerRight=0;

							} else if(!MainWindow.keepDown&&MainWindow.keepLeft&&!MainWindow.keepRight&&!MainWindow.keepUp){
								// left
								dashTimerDown=0;
								dashTimerLeft=120;
								dashTimerUp=0;
								dashTimerRight=0;

							} else if(!MainWindow.keepDown&&!MainWindow.keepLeft&&MainWindow.keepRight&&!MainWindow.keepUp){
								// right
								dashTimerDown=0;
								dashTimerLeft=0;
								dashTimerUp=0;
								dashTimerRight=120;
							} else if(!MainWindow.keepDown&&!MainWindow.keepLeft&&!MainWindow.keepRight&&MainWindow.keepUp){
								// up
								dashTimerDown=0;
								dashTimerLeft=0;
								dashTimerUp=120;
								dashTimerRight=0;
							}
						}
					} 

					if(MainWindow.keepDown&ship.yCoordinate<yMax-ship.yRadius){
						if(laserTime<4000){
							if(ship.speedUp==0){
								MainWindow.ship.yCoordinate +=1.1;
							} else {
								MainWindow.ship.yCoordinate +=2.2;
							}
						} else{
							MainWindow.ship.yCoordinate +=0.7;
						}
					}
					if(MainWindow.keepRight&ship.xCoordinate<xMax-ship.xRadius){
						if(laserTime<4000){
							if(ship.speedUp==0){
								MainWindow.ship.xCoordinate +=1.1;
							} else {
								MainWindow.ship.xCoordinate +=2.2;
							}
						} else {
							MainWindow.ship.xCoordinate +=0.7;
						}
					}
					if(MainWindow.keepUp&ship.yCoordinate>20+ship.yRadius){
						if(laserTime<4000){
							if(ship.speedUp==0){
								MainWindow.ship.yCoordinate -=1.1;
							} else {
								MainWindow.ship.yCoordinate -=2.2;
							}
						} else {
							MainWindow.ship.yCoordinate -=0.7;
						}
					}
					if(MainWindow.keepLeft&ship.xCoordinate>ship.xRadius){
						if(laserTime<4000){
							if(ship.speedUp==0){
								MainWindow.ship.xCoordinate -=1.1;
							} else {
								MainWindow.ship.xCoordinate -=2.2;
							}
						} else {
							MainWindow.ship.xCoordinate -=0.7;
						}
					}
				}
			}
		};
		timer.scheduleAtFixedRate(movement, 100, 5);

		TimerTask fire = new TimerTask(){
			public void run (){	
				if (keepFire&&laserTime<4000){
					StateInformation.allObjects.add(new Projectile(ship.xCoordinate+ship.xRadius-3, ship.yCoordinate, 
							3, -2, 0, Color.yellow,true));
				} 
			}
		};
		timer.scheduleAtFixedRate(fire, 100, 200);

		InputMap map = new InputMap();

		Action PlayerMovementUp = new PlayerMovementUp();
		Action PlayerMovementDown = new PlayerMovementDown();
		Action PlayerMovementLeft = new PlayerMovementLeft();
		Action PlayerMovementRight = new PlayerMovementRight();
		Action PlayerFire = new PlayerFire();
		Action PlayerZ = new PlayerZ();

		Action PlayerMovementUpRelease = new PlayerMovementUpRelease();
		Action PlayerMovementDownRelease = new PlayerMovementDownRelease();
		Action PlayerMovementLeftRelease = new PlayerMovementLeftRelease();
		Action PlayerMovementRightRelease = new PlayerMovementRightRelease();
		Action PlayerFireRelease = new PlayerFireRelease();

		panel.getInputMap().put(KeyStroke.getKeyStroke("UP") , "upKey" );
		panel.getActionMap().put("upKey", PlayerMovementUp);

		panel.getInputMap().put(KeyStroke.getKeyStroke("DOWN") , "downKey" );
		panel.getActionMap().put("downKey", PlayerMovementDown);

		panel.getInputMap().put(KeyStroke.getKeyStroke("LEFT") , "leftKey" );
		panel.getActionMap().put("leftKey", PlayerMovementLeft);

		panel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT") , "rightKey" );
		panel.getActionMap().put("rightKey", PlayerMovementRight);

		panel.getInputMap().put(KeyStroke.getKeyStroke("SPACE") , "fireKey" );
		panel.getActionMap().put("fireKey", PlayerFire);

		panel.getInputMap().put(KeyStroke.getKeyStroke("Z") , "zKey" );
		panel.getActionMap().put("zKey", PlayerZ);


		panel.getInputMap().put(KeyStroke.getKeyStroke("released UP") , "upKeyRelease" );
		panel.getActionMap().put("upKeyRelease", PlayerMovementUpRelease);

		panel.getInputMap().put(KeyStroke.getKeyStroke("released DOWN") , "downKeyRelease" );
		panel.getActionMap().put("downKeyRelease", PlayerMovementDownRelease);

		panel.getInputMap().put(KeyStroke.getKeyStroke("released LEFT") , "leftKeyRelease" );
		panel.getActionMap().put("leftKeyRelease", PlayerMovementLeftRelease);

		panel.getInputMap().put(KeyStroke.getKeyStroke("released RIGHT") , "rightKeyRelease" );
		panel.getActionMap().put("rightKeyRelease", PlayerMovementRightRelease);

		panel.getInputMap().put(KeyStroke.getKeyStroke("released SPACE") , "fireKeyRelease" );
		panel.getActionMap().put("fireKeyRelease", PlayerFireRelease);

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
			if(justDashed){

			} else {
				lastKeyPressed = "up";
			}

		}
	}
	static class PlayerMovementDownRelease extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			keepDown = false;
			if(justDashed){
				justDashed=false;

			} else {
				lastKeyPressed = "down";
			}

		}
	}
	static class PlayerMovementLeftRelease extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			keepLeft = false;
			if(justDashed){
				justDashed=false;
			} else {
				lastKeyPressed = "left";
			}

		}
	}
	static class PlayerMovementRightRelease extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			keepRight = false;
			if(justDashed){
				justDashed=false;

			} else {
				lastKeyPressed = "right";
			}
		}
	}
	static class PlayerFire extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			keepFire = true;
		}
	}
	static class PlayerFireRelease extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			keepFire = false;
		}
	}

	static class PlayerZ extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			if(laserTime==0)
				laserTime = 5250;
		}
	}
}
