import java.awt.Color;


public class PowerUp extends GameObject {

	public PowerUp(float x, float y, float radius, float speedX, float speedY, Color color) {
		super(x, y, radius, speedX, speedY, color);
		this.type="powerup";
		
	}
}
