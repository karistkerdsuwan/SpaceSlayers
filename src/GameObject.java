import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class GameObject {
	BufferedImage img = null;
	float x, y;           
	float speedX, speedY; 
	float radius;         
	private Color color; 
	public String type;
	public GameObject(float x, float y, float radius, float speedX, float speedY, Color Color) {
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.radius = radius;
		this.color = Color;
		this.type = "enemy";
		
		try {
		    img = ImageIO.read(new File("experimental.png"));
		} catch (IOException e) {
		}

	}

	public void draw(Graphics g) {
		Graphics g2 = g;
//		g2.setColor(this.color);
//		g2.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
		g2.drawImage(img, (int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius), null);
	}

	public void remove(){
		for(int i=0;i<StateInformation.allObjects.size();i++)
			if(StateInformation.allObjects.get(i).equals(this))
				StateInformation.allObjects.remove(i);
	}
	
	public void collideWalls(ContainerBox box) {
		float ballMinX = box.minX + radius;
		float ballMinY = box.minY + radius;
		float ballMaxX = box.maxX - radius;
		float ballMaxY = box.maxY - radius;

		if (x < ballMinX) {
			this.remove();
		} else if (x > ballMaxX) {
			this.remove();
		}

		if (y < ballMinY) {
			this.remove();
		} else if (y > ballMaxY) {
			this.remove();
		}
	}

	public void update(Graphics graphics) {
		if(this.x<-50||this.y<-10||this.y>800){
			this.remove();
		}
		y -= speedY;
		x -= speedX;
	}

}
