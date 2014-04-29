import java.awt.Color;
import java.awt.Graphics;


public class BasicEnemy extends GameObject {

	public BasicEnemy(float x, float y, float radius, float speedX, float speedY, Color color) {
		super(x, y, radius, speedX, speedY, color);
	}
	public void update(Graphics graphics) {
		if(this.x<-50||this.y<-10||this.y>800){
			this.remove();
		} else {
			int size=StateInformation.allObjects.size();
			for (int i=0; i<size;i++){
				if(StateInformation.allObjects.get(i).type.equals("friendly")){
					if(contact(StateInformation.allObjects.get(i).x, StateInformation.allObjects.get(i).y, StateInformation.allObjects.get(i).radius)){
						this.remove();
						size--;
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
