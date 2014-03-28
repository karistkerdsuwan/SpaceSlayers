import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Game {
	public PlayerShip ship;
	public MainWindow gameWindow;
	
	
	public StateInformation info; 
	Game (){
		info = new StateInformation();
		info.allObjects = new ArrayList();
		ContainerBox box = new ContainerBox(2,2,700,700);
		ship = new PlayerShip();		
		gameWindow = new MainWindow(ship);	
		Timer timer = new Timer();				

		TimerTask task = new TimerTask(){
			public void run (){
				gameWindow.update(info);
				System.out.println("running");
			}
		};
		timer.scheduleAtFixedRate(task, 700, 400);

	}
	public static void main (String[] args){
		Game game = new Game();
	}

	
}
