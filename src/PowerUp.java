import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class PowerUp extends GameObject {
public int PowerupType;
public Image bufferImage;
	public PowerUp(float x, float y, float radius, float speedX, float speedY, Color color, int PowerupType) {
		super(x, y, radius, speedX, speedY, color);
		this.type="powerup";
		this.PowerupType = PowerupType;
		
		if(PowerupType==0){
			try {
				img = ImageIO.read(new File("Points.png"));
			} catch (IOException e) {
			}

		} else if (PowerupType==1){
			try {
				img = ImageIO.read(new File("Speed.png"));
			} catch (IOException e) {
			}

		} else {
			try {
				img = ImageIO.read(new File("Shields.png"));
			} catch (IOException e) {
			}

		}
		//0=Score
		//1=SpeedUp
		//2=ShieldRestore
		//3=
		
	}
	public void draw(Graphics g) {
		//setImage according to type of PowerUp
		g.drawImage(img,(int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius),null);
		
	}
}
