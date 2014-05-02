import java.awt.Color;
import java.awt.Graphics;


public class rangedEnemy extends BasicEnemy{
	int fireTime;
	public rangedEnemy(float x, float y, float radius, float speedX,
			float speedY, Color color) {
		super(x, y, radius, speedX, speedY, color);
		fireTime=400;
	}
	public void fire(){
		StateInformation.allObjects.add(new Projectile(this.x-this.radius+3, this.y, 
				3, 2, 0, Color.RED,false));
		fireTime=400;
	}
	public void update(Graphics graphics) {
		if(this.x<-50||this.y<-10||this.y>800){
			this.remove();
		} else {
			int size=StateInformation.allObjects.size();
			for (int i=0; i<size;i++){
				if(StateInformation.allObjects.get(i).type.equals("friendly")){
					if(contact(StateInformation.allObjects.get(i).x, StateInformation.allObjects.get(i).y, StateInformation.allObjects.get(i).radius)){
						StateInformation.allObjects.get(i).remove();
						this.remove();
						size-=2;
					}
				}
			}
			y -= speedY;
			x -= speedX;
			fireTime--;
			if(fireTime==0){
				fire();
			}
		}
	}
}
