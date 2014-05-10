import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BasicEnemy extends GameObject {
	public int health;
	public BasicEnemy(float x, float y, float radius, float speedX, float speedY, Color color, int health) {
		super(x, y, radius, speedX, speedY, color);
		this.health=health;
		try {
		    img = ImageIO.read(new File("basicEnemy.png"));
		} catch (IOException e) {
		}

	}
	public void update(Graphics graphics) {
		if(this.x<-50||this.y<-10||this.y>800){
			this.remove();
		} else {
			int size=StateInformation.allObjects.size();
			for (int i=0; i<size;i++){
				if(StateInformation.allObjects.get(i).type.equals("friendly")){
					if(contact(StateInformation.allObjects.get(i).x, StateInformation.allObjects.get(i).y, StateInformation.allObjects.get(i).radius)){
						if(health!=0){
							health--;						
							StateInformation.allObjects.get(i).remove();
							size--;
						} else {
							StateInformation.combo++;
							StateInformation.allObjects.get(i).remove();
							this.remove();
							size-=2;
						}
					}
				}
			}
			y -= speedY;
			x -= speedX;
		}
	}
	boolean contact (float x, float y, float checkRadius) {

		if(Math.abs(x - this.x) < .8*(checkRadius + radius) && Math.abs(y - this.y) < .8*(checkRadius + radius)){
			StateInformation.score=+5;
			return true;
		} else {
			return false;
		}

	}
}
