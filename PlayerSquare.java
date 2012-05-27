import javax.swing.*; 
import java.awt.*;                
public class PlayerSquare extends JPanel{
 	private Image background;
	private Image avitar;
	private Image avitarOn;
	private boolean active;
	private Dimension size;
	private String playerName = "";
	private String score = "Score: 0";
	private String playerToken; 
	public PlayerSquare(String playerToken){
		this.playerToken = playerToken;
		GraphicsEnvironment env =
			 GraphicsEnvironment.getLocalGraphicsEnvironment();
		env.getAvailableFontFamilyNames();
		setFont(new Font("LED BOARD REVERSED", Font.PLAIN, 25));
		size = new Dimension();
		background = new ImageIcon(this.getClass().getResource("resources/PL_BK.png")).getImage();
		avitar = new ImageIcon(this.getClass().getResource("resources/PL_1.png")).getImage();
		avitarOn = new ImageIcon(this.getClass().getResource("resources/PL_1_ON.png")).getImage();
		size.width = background.getWidth(null)+35;
		size.height = background.getHeight(null)+184;
		setPreferredSize(size);
		setBackground(new Color(17,45,164));
	}
	public static void main(String [] args){
		JFrame frame = new JFrame("Hollywood Squares");
		frame.getContentPane().add(new PlayerSquare("X")); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}	
	public void setActive(boolean active){
		this.active=active;
		repaint();
	}
	public void paintComponent( Graphics g ){
		super.paintComponent( g );
	    Graphics2D g2d = (Graphics2D) g;
		if(active){
			g2d.drawImage(avitarOn, 17, 0, null);
		}else{
			g2d.drawImage(avitar, 17, 0, null);
		}
		g2d.drawImage(background, 17, 184, null);
		g2d.setPaint(Color.pink);
		g2d.drawString(playerToken, 167, 239);
		g2d.setPaint(Color.white);
		g2d.drawString(score, 37, 289);
		g2d.drawString(playerName, 37, 339);
	}
	public void setName(String name){
		this.playerName=name;
	}
	public void setScore(String score){
		this.score = "Score: "+score;
		repaint();
	}
}