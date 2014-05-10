import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class MainScreen extends JFrame implements ActionListener {
	private static MainScreen mainscreen;
	public MainScreen (){
		JButton howToPlay, startGame;
		JLabel Title;	
		JPanel panel = new JPanel();

		Border textBorder = BorderFactory.createEmptyBorder(200,200,100,200);
		setSize(800, 800);
		this.setContentPane(panel);

		Title = new JLabel ("Space Slayers");
		Title.setBorder(textBorder);
		Title.setAlignmentX(CENTER_ALIGNMENT);

		startGame=new JButton ("Start");
		startGame.setAlignmentX(CENTER_ALIGNMENT);
		startGame.setFont(startGame.getFont().deriveFont(Font.BOLD).deriveFont(new Float(30)));
		startGame.setBorder(BorderFactory.createEmptyBorder(40,170,40,170));

		howToPlay=new JButton ("How to Play");
		howToPlay.setAlignmentX(CENTER_ALIGNMENT);
		howToPlay.setFont(howToPlay.getFont().deriveFont(Font.BOLD).deriveFont(new Float(30)));
		howToPlay.setBorder(BorderFactory.createEmptyBorder(40,119,40,120));

		setTitle("Space Slayers");

		panel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(Title);
		panel.add(startGame);
		panel.add(Box.createRigidArea(new Dimension (20,20)) );
		panel.add(howToPlay);
		this.setResizable(false);
		panel.setBackground(Color.black);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BoxLayout boxLay = new BoxLayout(panel, BoxLayout.Y_AXIS);
		this.setLayout(boxLay);

		startGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				Game game = new Game(mainscreen);
				mainscreen.setVisible(false);
			}
		});
		howToPlay.addActionListener(this);
	}
	public static void main (String args []){
		mainscreen = new MainScreen();
	}
	public void actionPerformed(ActionEvent arg0) {
	}
}

