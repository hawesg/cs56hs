import javax.swing.*; // For JPanel, etc.
import java.awt.*;           // For Graphics, etc.
import java.awt.geom.*;      // For Ellipse2D, etc.
public class Test extends JPanel{
 	private Image background;
	private Dimension size;
	private String playerName = "";
	private String score = "Score: 0";
	private String playerToken = "_"; 
	public Test(){
		GraphicsEnvironment env =
			 GraphicsEnvironment.getLocalGraphicsEnvironment();
		env.getAvailableFontFamilyNames();
		setFont(new Font("LED BOARD REVERSED", Font.PLAIN, 25));
		size = new Dimension();
		background = new ImageIcon(this.getClass().getResource("resources/PL_BK.png")).getImage();
		size.width = background.getWidth(null);
		size.height = background.getHeight(null);
		setPreferredSize(size);
	}
	public static void main(String [] args){
		JFrame frame = new JFrame("Hollywood Squares");
		frame.getContentPane().add(new Test()); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}	
	public void paintComponent( Graphics g ){
		super.paintComponent( g );
	    Graphics2D g2d = (Graphics2D) g;
	  	g2d.drawImage(background, 0, 0, null);
		g2d.setPaint(Color.pink);
		g2d.drawString(playerToken, 150, 55);
		g2d.setPaint(Color.white);
		g2d.drawString(score, 20, 105);
		g2d.drawString(playerName, 20, 155);
	}
	public void setName(String name){
		this.playerName=name;
	}
	public void setScore(int score){
		this.score = "Score: "+score;
	}
}