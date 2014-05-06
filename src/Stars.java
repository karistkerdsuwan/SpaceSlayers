import java.awt.Color;
import java.awt.Graphics;


public class Stars {

	private float xCoordinate;
	private float yCoordinate;
	private Color color;
	public Stars(float x, float y, Color Color) {
		this.xCoordinate=x;
		this.yCoordinate=y;
		this.color=Color;
	}
	public void update(){
			this.xCoordinate-=1;
			if(this.xCoordinate<0)
				this.remove();

	}
	public void remove(){
		for(int i=0;i<StateInformation.allStars.size();i++)
			if(StateInformation.allStars.get(i).equals(this))
				StateInformation.allStars.remove(i);
	}
	public void draw(Graphics g) {
		Graphics g2=g;
		g2.setColor(this.color);
		g2.fillOval((int)this.xCoordinate, (int) this.yCoordinate, 2, 2);
	}
}
