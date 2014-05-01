import java.awt.Color;


public class DiagonalEnemy extends rangedEnemy{

	public DiagonalEnemy(float x, float y, float radius, float speedX,
			float speedY, Color color) {
		super(x, y, radius, speedX, speedY, color);
		// TODO Auto-generated constructor stub
	}
	public void fire(){
		if(this.y>StateInformation.shipYCoor){
			StateInformation.allObjects.add(new BounceProjectile(this.x-this.radius+3, this.y, 
					4, 2, -2, Color.RED,false));
		}else{
			StateInformation.allObjects.add(new BounceProjectile(this.x-this.radius+3, this.y, 
					4, 2, 2, Color.RED,false));
		}
		
		fireTime=400;
	}

}
