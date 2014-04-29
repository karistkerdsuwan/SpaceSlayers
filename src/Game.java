import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
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
	
	Game (){
		
		slow=false;
		info = new StateInformation();
		info.allObjects = new ArrayList();
		ContainerBox box = new ContainerBox(2,2,700,700);
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
					
//					if(ship.invincible>400){
//						for(int counter=0;counter<StateInformation.allObjects.size();counter++){
//							StateInformation.allObjects.get(counter).speedX-=.05;
//						}
//					} else {
//						if(ship.invincible!=0)
//						for(int counter=0;counter<StateInformation.allObjects.size();counter++){
//							StateInformation.allObjects.get(counter).speedX+=.05;
//						}
//					}
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
				speedUpSpawn();
			}
			
		}

		private void enemySpawn() {
			int xRan = (int) (Math.random() * 300)+900;
			int yRan = (int) (Math.random() * 700);
			
//			System.out.println(xRan);
//			System.out.println(yRan);
//			System.out.println(ship.yCoordinate);
			
			if(time%20==0){
				info.allObjects.add(new rangedEnemy(xRan, yRan, 30, (float) .75, 0, Color.DARK_GRAY));
			} else {
				info.allObjects.add(new BasicEnemy(xRan, yRan, 30, (float) .75, 0, Color.gray));
			}
		}
		private void speedUpSpawn() {
			int xRan = (int) (Math.random() * 300)+900;
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

	public static void main (String[] args){
		Game game = new Game();
	}

	
}
