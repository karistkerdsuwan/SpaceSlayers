import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;


public class PlayerShip {
	public int shieldStage;
	public float xCoordinate;
	public float yCoordinate;
	public int size = 30;
	public int xRadius;
	public int yRadius;
	public boolean dead;
	public static int invincible =0;
	public int speedUp=0;
	BufferedImage img = null;

	boolean contact (float x, float y, float checkXRadius, float checkYRadius) {
		float point12x=(this.xCoordinate);
		float point12y=this.yCoordinate-yRadius;

		float point3x=(this.xCoordinate+ xRadius);
		float point3y=this.yCoordinate;

		float point6x=(this.xCoordinate);
		float point6y=this.yCoordinate+ yRadius;

		float point9x=(this.xCoordinate-xRadius);
		float point9y=this.yCoordinate;

		if( (float) Math.sqrt(((point12x - x) * (point12x - x)) + ((point12y - y) * (point12y - y))) < checkXRadius){ // up
			return true;
		}

		if( (float) Math.sqrt(((point3x - x) * (point3x - x)) + ((point3y - y) * (point3y - y))) < checkXRadius){ // right
			return true;
		}

		if( (float) Math.sqrt(((point6x - x) * (point6x - x)) + ((point6y - y) * (point6y - y))) < checkXRadius){ // down
			return true;
		}

		if( (float) Math.sqrt(((point9x - x) * (point9x - x)) + ((point9y - y) * (point9y - y))) < checkXRadius){ // left
			return true;
		}

		if(Math.abs(x - this.xCoordinate) < .8*(checkXRadius + xRadius) && Math.abs(y - this.yCoordinate) < .8*(checkYRadius + yRadius)){
			return true;
		} else {
			return false;
		}

	}
	void update(Graphics g){
		
		StateInformation.shipXCoor = this.xCoordinate;
		StateInformation.shipYCoor = this.yCoordinate;
		if(invincible!=0){
			invincible--;
		}
		if(this.speedUp!=0){
			speedUp--;	
		}

		for (int i=0; i<StateInformation.allObjects.size();i++){//-1;i++){
			//			System.out.println("i: " +i);
			//			System.out.println("AllObjectsSize: " +StateInformation.allObjects.size());
			//			System.out.println("X " + StateInformation.allObjects.get(i).x);
			//			System.out.println("Y " + StateInformation.allObjects.get(i).y);

			if(contact(StateInformation.allObjects.get(i).x, StateInformation.allObjects.get(i).y,
					StateInformation.allObjects.get(i).radius, StateInformation.allObjects.get(i).radius)){
				if(StateInformation.allObjects.get(i).type.equals("enemy")){
					if(invincible==0){
						shieldStage--;
						invincible = 800;	
						StateInformation.combo=0;
					}
					if(shieldStage==0){
						this.dead=true;
					}
				} else if (StateInformation.allObjects.get(i).type.equals("friendly")){

				} else if (StateInformation.allObjects.get(i).type.equals("powerup")){
					if(((PowerUp)StateInformation.allObjects.get(i)).PowerupType==0){
						StateInformation.score+=10;
					} else if(((PowerUp)StateInformation.allObjects.get(i)).PowerupType==1){
						this.speedUp=1200;
					} else if(((PowerUp)StateInformation.allObjects.get(i)).PowerupType==2){
						if(this.shieldStage!=5){
							this.shieldStage++;
						} else {
							StateInformation.score+=50;
						}
					}
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
		
		try {
		    img = ImageIO.read(new File("ship.png"));
		} catch (IOException e) {
		}
	}
	void draw (Graphics g){

		if(invincible!=0){
			if(invincible%6==0){
				Graphics changeColor = g;
				changeColor.setColor(new Color (255,255,255, 100));
				changeColor.drawImage(img, (int)xCoordinate-xRadius, (int)yCoordinate-yRadius, this.xRadius * 2, this.yRadius * 2, null);
			}
		} else {
			Graphics changeColor = g;
			changeColor.setColor(Color.white);
			changeColor.drawImage(img, (int)xCoordinate-xRadius, (int)yCoordinate-yRadius, this.xRadius * 2, this.yRadius * 2, null);
		}
	}

}
