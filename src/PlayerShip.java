import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;


public class PlayerShip {
	public int shieldStage;
	public float xCoordinate;
	public float yCoordinate;
	public int size = 30;
	public int xRadius;
	public int yRadius;
	public boolean dead;
	public static int invincible =0;
	public static int speedUp=0;
	
	boolean contact (float x, float y, float checkXRadius, float checkYRadius) {
		if(Math.abs(x - this.xCoordinate) < .8*(checkXRadius + xRadius) && Math.abs(y - this.yCoordinate) < .8*(checkYRadius + yRadius)){
			return true;
		} else {
			return false;
		}

	}

	void update(Graphics g){
		if(invincible!=0){
			invincible--;
		}
		if(this.speedUp!=0){
			speedUp--;	
		}

		for (int i=0; i<StateInformation.allObjects.size();i++){
			if(contact(StateInformation.allObjects.get(i).x, StateInformation.allObjects.get(i).y,
					StateInformation.allObjects.get(i).radius, StateInformation.allObjects.get(i).radius)){
				if(StateInformation.allObjects.get(i).type.equals("enemy")){
					if(invincible==0){
						shieldStage--;
						flicker(g);
					}
					if(shieldStage==0){
						this.dead=true;
					}
				} else if (StateInformation.allObjects.get(i).type.equals("friendly")){

				} else if (StateInformation.allObjects.get(i).type.equals("powerup")){
					this.speedUp=1200;
					StateInformation.allObjects.get(i).remove();
					StateInformation.score +=10;
				}
			}
		}
	}

	PlayerShip(){
		shieldStage = 5;
		xCoordinate = 50;
		yCoordinate = 250;
		this.yRadius=20;
		this.xRadius=35;
	}
	
	void flicker(Graphics g){
		invincible = 800;		
	}
	void draw (Graphics g){

		// if invincible is modulus divisible by 150, then change color one way and then change back

		Graphics changeColor = g;
		changeColor.setColor(Color.white);
		changeColor.fillOval((int)xCoordinate-xRadius, (int)yCoordinate-yRadius, this.xRadius * 2, this.yRadius * 2);
	}

}
