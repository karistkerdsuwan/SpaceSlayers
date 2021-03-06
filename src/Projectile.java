import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile extends GameObject {
	public Projectile(float x, float y, float radius, float speedX,
		float speedY, Color color, boolean friend) {
		super(x, y, radius, speedX, speedY, color);
		if(friend){
			this.type = "friendly";
			try {
				img = ImageIO.read(new File("friendlyBullet.png"));
			} catch (IOException e) {
			}
		} else {
			this.type = "enemy";
			
		}
	}
	public void update(Graphics graphics) {
		if(this.x<-50||this.y<-10||this.y>800||this.x>1200){
			this.remove();
		}
		y -= speedY;
		x -= speedX;
	}

}
