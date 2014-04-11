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
	
//	public void collideBalls(Ball ball) {
//		float distance = (float) Math.sqrt(((this.x - ball.x) * (this.x - ball.x))
//	    + ((this.y - ball.y) * (this.y - ball.y)));
//		if (distance < this.radius + ball.radius)
//		{
//			// move ball i so that ball i touches ball j
//            double dx = (radius*2-distance)*(x-ball.x)/distance;
//            double dy = (radius*2-distance)*(y-ball.y)/distance;
//            x+=dx;
//            y+=dy;
//            
//            float a = getMoveAngle(); // angle of ball 1 
//            float b = ball.getMoveAngle(); //angle of ball 2
//            float c = (float) Math.toDegrees(Math.atan2((ball.y-y),ball.x-x));
//            
//            float ac = a-c;
//            float bc = b-c;
//            
//            float xSpeedi = (float) (getSpeed()*Math.cos(Math.toRadians(ac)));
//            float ySpeedi = (float) (getSpeed()*Math.sin(Math.toRadians(ac)));
//            
//            float xSpeedj = (float) (ball.getSpeed()*Math.cos(Math.toRadians(bc)));
//            float ySpeedj = (float) (ball.getSpeed()*Math.sin(Math.toRadians(bc)));
//            
//            float newSpeedi = (float) Math.sqrt(xSpeedj*xSpeedj + ySpeedi*ySpeedi);
//            float newSpeedj = (float) Math.sqrt(xSpeedi*xSpeedi + ySpeedj*ySpeedj);
//            
//            // compute the new angle
//            float newAnglei = (float) Math.toDegrees(Math.atan2(ySpeedi,xSpeedj));
//            float newAnglej = (float) Math.toDegrees(Math.atan2(ySpeedj,xSpeedi));
//            // rotate back to original orientation
//            newAnglei += c;
//            newAnglej += c;
//            
//            speedX = (float) (newSpeedi * Math.cos(Math.toRadians(newAnglei)));
//            speedY = (float) (newSpeedi * Math.sin(Math.toRadians(newAnglei)));
//            
//            ball.speedX = (float) (newSpeedj * Math.cos(Math.toRadians(newAnglej)));
//            ball.speedY = (float) (newSpeedj * Math.sin(Math.toRadians(newAnglej)));
//		}
//		
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
