import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class MainWindow extends JFrame implements KeyListener{
	void update (StateInformation info){
		for (int objectCount=0;objectCount!=info.allObjects.size();objectCount++){
			
		}
	}
	
	MainWindow(){
		this.setTitle("Space");
		this.addKeyListener(this);
		this.setSize(new Dimension (700, 700));
//		GamePanel gPane = new GamePanel();
//		gPane.addKeyListener(this);
	//	this.add(boxbox);		
		this.setVisible(false);
		this.setVisible(true);

	}
	
	public static void main (String[] args){
		MainWindow start = new MainWindow();
	}
	public void keyPressed(KeyEvent event) {
		int keyInt =event.getKeyCode();
		char keyChar = event.getKeyChar();

	}
	public void keyReleased(KeyEvent event) {
//		System.out.println(event);
		
	}
	public void keyTyped(KeyEvent event) {
//		System.out.println(event);
		
	}
}
