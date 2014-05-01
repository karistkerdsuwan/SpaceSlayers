import java.awt.Color;
import java.awt.Graphics;


public class BounceProjectile extends Projectile{

	public BounceProjectile(float x, float y, float radius, float speedX,
			float speedY, Color color, boolean friend) {
		super(x, y, radius, speedX, speedY, color, friend);
		// TODO Auto-generated constructor stub
	}
	public void collideWalls() {
		if(this.x<-50)
			this.remove();
		else if (y - radius < 0) {
			speedY = -speedY;
			y = radius;
		} else if (y + radius > 700) {
			speedY = -speedY;
			y = 700 - radius;
		}
	}
	public void update(Graphics graphics) {
		collideWalls();
		y -= speedY;
		x -= speedX;
	}
}
