import java.awt.Graphics;


public class PlayerShip {
	public int shieldStage;
	public int xCoordinate;
	public int yCoordinate;
	
	void update(){
		
	}
	
	PlayerShip(){
		shieldStage = 5;
		xCoordinate = 50;
		yCoordinate = 250;
	}
	
	void flicker(){
		
	}
	
	void draw (Graphics g){
		g.fillOval(xCoordinate, yCoordinate, 20, 20);
	}
	
}
