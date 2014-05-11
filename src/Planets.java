import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Planets {
	BufferedImage img = null;
	private float xCoordinate;
	private float yCoordinate;
	private Color color;
	private boolean planet;
	private int size;
	public Planets(float x, float y, Color Color, int sizeRan, int typeRan) {
		this.xCoordinate=x;
		this.yCoordinate=y;
		this.color=Color;
		this.size=sizeRan;
		if(typeRan==0){
			//grass
			try {
				img = ImageIO.read(new File("Planet1.png"));
			} catch (IOException e) {
			}
		} else 	if(typeRan==1){
			//lava
			try {
				img = ImageIO.read(new File("Planet2.png"));
			} catch (IOException e) {
			}
		} else {
			//ice
			try {
				img = ImageIO.read(new File("Planet3.png"));
			} catch (IOException e) {
			}
		}

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
		g2.drawImage(img, (int)xCoordinate, (int)yCoordinate, size, size, null);
	}
}
