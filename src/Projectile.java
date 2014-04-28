import java.awt.Color;


public class Projectile extends GameObject {

	public Projectile(float x, float y, float radius, float speedX,
		float speedY, Color color, boolean friend) {
		super(x, y, radius, speedX, speedY, color);
		if(friend){
			this.type = "friendly";
		} else {
			this.type = "enemy";
		}
	}
}
