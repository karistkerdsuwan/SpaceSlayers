import java.awt.Color;


public class DiagonalEnemy extends rangedEnemy{

	public DiagonalEnemy(float x, float y, float radius, float speedX,
			float speedY, Color color, int health) {
		super(x, y, radius, speedX, speedY, color, health);
		// TODO Auto-generated constructor stub
	}
	public void fire(){
		if(this.y>StateInformation.shipYCoor){
			StateInformation.allObjects.add(new BounceProjectile(this.x-this.radius+3, this.y, 
					4, 2, (float) 1.5, Color.RED,false));
		}else{
			StateInformation.allObjects.add(new BounceProjectile(this.x-this.radius+3, this.y, 
					4, 2, (float) -1.5, Color.RED,false));
		}
		
		fireTime=400;
	}

}
