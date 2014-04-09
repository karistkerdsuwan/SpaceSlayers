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
	Timer timer = new Timer();	
	
	Game (){
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
					gameWindow.update(g);
					gameWindow.ship.update();
				}
			}
		};
		timer.scheduleAtFixedRate(task, 100, 1);
	
	TimerTask spawn = new TimerTask(){
		public void run (){			

			time++;
			if(time%4==0){
				enemnySpawn(time);
			}
		}

		private void enemnySpawn(int time) {
			int xRan = (int) (Math.random() * 300)+800;
			int yRan = (int) (Math.random() * 700);
			
//			System.out.println(xRan);
//			System.out.println(yRan);
//			System.out.println(ship.yCoordinate);
			
			info.allObjects.add(new BasicEnemy(xRan, yRan, 50, (float) .75, 0, Color.gray));
		}
	};
	timer.scheduleAtFixedRate(spawn, 900, 800);
}
	
	public void stop (){
		gameWindow.setScore(time);
		timer.cancel();

	}

	public static void main (String[] args){
		Game game = new Game();
	}

	
}
