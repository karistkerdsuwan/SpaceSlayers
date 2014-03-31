import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Game {
	public PlayerShip ship;
	public MainWindow gameWindow;
	public static StateInformation info; 
	public int time;
	
	Game (){
		info = new StateInformation();
		info.allObjects = new ArrayList();
		ContainerBox box = new ContainerBox(2,2,700,700);
		ship = new PlayerShip();		
		gameWindow = new MainWindow(ship);	
		Timer timer = new Timer();	
		Graphics g = gameWindow.getGraphics();
		gameWindow.paint(g);
		

		TimerTask task = new TimerTask(){
			public void run (){			
				Graphics g = gameWindow.getGraphics();

				gameWindow.update(g);

			}
		};
		timer.scheduleAtFixedRate(task, 100, 3);

	
	
	TimerTask spawn = new TimerTask(){
		public void run (){			

			time++;
			System.out.println(time);
			if(time%1800==0){
				enemnySpawn(time);
			}
		}

		private void enemnySpawn(int time) {
	//		if(time%1800==0){
				info.allObjects.add(new BasicEnemy(300, 300, 50, 5, 5));
	//		}
			
		}
	};
	timer.scheduleAtFixedRate(spawn, 900, 900);

}

	
	
	public static void main (String[] args){
		Game game = new Game();
	}

	
}
