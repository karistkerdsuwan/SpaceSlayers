import java.awt.Color;
import java.awt.Graphics;


public class PowerUp extends GameObject {
public int PowerupType;
	public PowerUp(float x, float y, float radius, float speedX, float speedY, Color color, int PowerupType) {
		super(x, y, radius, speedX, speedY, color);
		this.type="powerup";
		this.PowerupType = PowerupType;
		
		//0=Score
		//1=SpeedUp
		//2=ShieldRestore
		//3=
		
	}
	public void draw(Graphics g) {
		//setImage according to type of PowerUp
		if(this.PowerupType==0)
			g.setColor(Color.PINK);
		if(this.PowerupType==1)
			g.setColor(Color.CYAN);
		if(this.PowerupType==2)
			g.setColor(Color.MAGENTA);
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
		
	}
}
