import java.awt.Color;
import java.awt.Graphics;


public class PlayerShip {
	public int shieldStage;
	public float xCoordinate;
	public float yCoordinate;
	public int size = 30;
	public int flickerTime;
	public int xRadius;
	public int yRadius;
	public boolean dead;
	
	boolean contact (float x, float y, float checkXRadius, float checkYRadius) {
		if(Math.sqrt((double) Math.pow((x - (this.xCoordinate+25)),2))<this.xRadius+checkXRadius
			&&(Math.sqrt((double) Math.pow((y - this.yCoordinate),2)) <this.yRadius+checkYRadius)){
			
			System.out.println(Math.sqrt((double) Math.pow((y - this.yCoordinate),2)));
			return true;
		} else {
			return false;
		}
		
	}
	
	void update(){
		for (int i=0; i<StateInformation.allObjects.size();i++){
			if(contact(StateInformation.allObjects.get(i).x, StateInformation.allObjects.get(i).y,
					StateInformation.allObjects.get(i).radius, StateInformation.allObjects.get(i).radius)){
				dead = true;				
			}
		}
	}
	
	PlayerShip(){
		shieldStage = 5;
		xCoordinate = 50;
		yCoordinate = 250;
		this.yRadius=21;
		this.xRadius=36;
		
	}
	
	void flicker(){
	}
	
	void draw (Graphics g){
		Graphics changeColor = g;
		changeColor.setColor(Color.white);
		changeColor.fillOval((int)xCoordinate, (int)yCoordinate, 70, 40);
	}
	
}
