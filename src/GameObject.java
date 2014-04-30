import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class GameObject {
	float x, y;           
	float speedX, speedY; 
	float radius;         
	private Color color; 
//	private static final Color DEFAULT_COLOR = Color.BLUE;
	public String type;
//	public GameObject(int width,int height){
//		Random rand = new Random();
//	    radius = 60;
//	    x = rand.nextInt(width - (int)radius * 2 - 20) + radius + 10;
//	    y = rand.nextInt(height - (int)radius * 2 - 20) + radius + 10;
//	    int speed = rand.nextInt(11) + 5;
//	    int angleInDegree = rand.nextInt(360);
//	    speedX = (float)(speed * (float)Math.cos(Math.toRadians(angleInDegree)));
//	    speedY = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
//	}
	public GameObject(float x, float y, float radius, float speedX, float speedY, Color color) {
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		// Convert (speed, angle) to (x, y), with y-axis inverted
//		this.speedX = (float)(speed * Math.cos(Math.toRadians(angleInDegree)));
//		this.speedY = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
		this.radius = radius;
		this.color = color;
		this.type = "enemy";
	}

//	public GameObject(float x, float y, float radius, float speed, float angleInDegree) {
//		this(x, y, radius, speed, angleInDegree, DEFAULT_COLOR);
//	}

	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
	}

	public void remove(){
		for(int i=0;i<StateInformation.allObjects.size();i++)
			if(StateInformation.allObjects.get(i).equals(this))
				StateInformation.allObjects.remove(i);
	}
	
	public void collideWalls(ContainerBox box) {
		// Get the ball's bounds, offset by the radius of the ball
		float ballMinX = box.minX + radius;
		float ballMinY = box.minY + radius;
		float ballMaxX = box.maxX - radius;
		float ballMaxY = box.maxY - radius;


		//x += speedX;
		//y += speedY;

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

//	public float getSpeed() {
//		return (float)Math.sqrt(speedX * speedX + speedY * speedY);
//	}

//	/** Return the direction of movement in degrees (counter-clockwise). */
//	public float getMoveAngle() {
//		return (float)Math.toDegrees(Math.atan2(-speedY, speedX));
//	}
//	public void move() {
//		x += speedX;
//		y += speedY;
//		
//	}
	public void update(Graphics graphics) {
		if(this.x<-50||this.y<-10||this.y>800){
			this.remove();
		}
		y -= speedY;
		x -= speedX;
	}

}
