import java.awt.Graphics;


public class PlayerShip {
	public int shieldStage;
	public float xCoordinate;
	public float yCoordinate;
	
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
		g.fillOval((int)xCoordinate, (int)yCoordinate, 70, 40);
	}
	
}
