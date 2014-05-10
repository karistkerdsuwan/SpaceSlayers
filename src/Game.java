import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
	public PlayerShip ship;
	public MainWindow gameWindow;
	public static StateInformation info; 
	public int time;
	public int score;
	public boolean slow=false;
	Timer timer = new Timer();	

	Game (MainScreen Parent){
		slow=false;
		info = new StateInformation();
		info.allObjects = new ArrayList();
		ship = new PlayerShip();		
		gameWindow = new MainWindow(ship);	
		Graphics g = gameWindow.getGraphics();
		gameWindow.paint(g);

		TimerTask task = new TimerTask(){
			public void run (){			
				Graphics g = gameWindow.getGraphics();
				if(ship.dead){
					stop();
					gameWindow.update(gameWindow.getGraphics());
				} else {
					if(ship.invincible!=0){

					}
					gameWindow.update(g);
					gameWindow.ship.update(g);
				}
			}
		};
		timer.scheduleAtFixedRate(task, 100, 1);
		TimerTask spawn = new TimerTask(){
			public void run (){			
				time++;
				if(time%4==0){
					enemySpawn();
				}
				if(time%16==0){
					powerUpSpawn();
				}
			}
			private void enemySpawn() {
				int xRan = (int) (Math.random() * 300)+1200;
				int yRan = (int) (Math.random() * 670)+20;			
				if(time<150){		
					if(time%35==0){
						info.allObjects.add(new rangedEnemy(xRan, yRan, 30, (float) .75, 0, Color.DARK_GRAY,0));
					} else {
						info.allObjects.add(new BasicEnemy(xRan, yRan, 30, (float) .75, 0, Color.gray,0));
					}
				} else {
					if(time%25==0){
						info.allObjects.add(new rangedEnemy(xRan, yRan, 30, (float) .75, 0, Color.DARK_GRAY,0));
						if(time%30==0){							
							xRan = (int) (Math.random() * 300)+1200;
							yRan = (int) (Math.random() * 670)+20;
							info.allObjects.add(new DiagonalEnemy(xRan, yRan, 30, (float) .75, 0, Color.gray,0));
						}
					} else {
						xRan = (int) (Math.random() * 300)+1200;
						yRan = (int) (Math.random() * 670)+20;
						info.allObjects.add(new BasicEnemy(xRan, yRan, 30, (float) .75, 0, Color.gray,0));
						xRan = (int) (Math.random() * 300)+1200;
						yRan = (int) (Math.random() * 670)+20;
						info.allObjects.add(new BasicEnemy(xRan, yRan, 30, (float) .75, 0, Color.gray,0));
					}				
				}
			}
			private void powerUpSpawn() {
				int xRan = (int) (Math.random() * 300)+1200;
				int yRan = (int) (Math.random() * 700);
				info.allObjects.add(new PowerUp(xRan, yRan, 30, (float) .75, 0, Color.BLUE, (int) (Math.random() *3)));

			}
		};
		timer.scheduleAtFixedRate(spawn, 0, 40); // (spawn,  900, 800);
		
	}
	public void stop (){
		gameWindow.setScore(time);
		timer.cancel();

	}

}
