import java.awt.Color;
import java.awt.Graphics;


public class Planets {

	private float xCoordinate;
	private float yCoordinate;
	private Color color;
	private boolean planet;
	public Planets(float x, float y, Color Color) {
		this.xCoordinate=x;
		this.yCoordinate=y;
		this.color=Color;
	}
	
	public void update(){
		this.xCoordinate-=.1;
		if(this.xCoordinate<-500)
		this.remove();

	}
	public void remove(){
		for(int i=0;i<StateInformation.allPlanets.size();i++)
			if(StateInformation.allPlanets.get(i).equals(this)){
				StateInformation.allPlanets.remove(i);
				MainWindow.planet=0;
			}
	}
	public void draw(Graphics g) {
		Graphics g2=g;
		g2.setColor(this.color);
		g2.fillOval((int)this.xCoordinate, (int) this.yCoordinate, 500, 500);
	}
}
