import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Game {
	public PlayerShip ship;
	public MainWindow gameWindow;
	public static StateInformation info; 
	
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

	//			gameWindow.update(gameWindow.getGraphics());
	//			System.out.println("running");
			}
		};
		timer.scheduleAtFixedRate(task, 200, 4);

	}
	public static void main (String[] args){
		Game game = new Game();
	}

	
}
