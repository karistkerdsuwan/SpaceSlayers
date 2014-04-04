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
	public int touchedNum =0;
	
	boolean contact (float x, float y) {
		if(Math.sqrt((double) Math.pow((x - this.xCoordinate),2))<this.xRadius
			&&(Math.sqrt((double) Math.pow((y - this.yCoordinate),2)) <this.yRadius)){
			
			System.out.println(Math.sqrt((double) Math.pow((x - this.xCoordinate),2)));
			System.out.println(Math.sqrt((double) Math.pow((y - this.yCoordinate),2)));
			return true;
		} else {
			return false;
		}
		
	}
	
	void update(){
		for (int i=0; i<StateInformation.allObjects.size();i++){
			if(contact(StateInformation.allObjects.get(i).x, StateInformation.allObjects.get(i).y)){
				dead = true;
				touchedNum++;
				System.out.println("TOUCHED  " + touchedNum);
				
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
