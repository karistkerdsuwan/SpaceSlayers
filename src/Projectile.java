import java.awt.Color;


public class Projectile extends GameObject {

	public Projectile(float x, float y, float radius, float speedX,
		float speedY, Color color) {
		super(x, y, radius, speedX, speedY, color);
		this.type = "friendly";
	}
}
