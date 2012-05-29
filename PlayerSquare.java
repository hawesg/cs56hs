import javax.swing.*; 
import java.awt.*;                
public class PlayerSquare extends JPanel{
 	private Image background;
	private Image avitar;
	private Image avitarOn;
	private boolean active;
	private Dimension size;
	Color color;
	private String playerName = "";
	private String score = "$0";
	private String playerToken; 
	private String gender;
	public PlayerSquare(String playerToken, String playerName, String gender){
		color = (playerToken.equals("O"))?new Color(255, 0, 255):Color.orange;
		this.playerToken = playerToken;
		this.gender = gender;
		this.playerName = playerName;
		GraphicsEnvironment env =
			 GraphicsEnvironment.getLocalGraphicsEnvironment();
		env.getAvailableFontFamilyNames();
		setFont(new Font("LED BOARD REVERSED", Font.PLAIN, 25));
		size = new Dimension();
		loadImages();
		size.width = background.getWidth(null)+35;
		size.height = background.getHeight(null)+184;
		setPreferredSize(size);
		setBackground(new Color(17,45,164));
	}
	private void loadImages(){
			background = new ImageIcon(this.getClass().getResource("resources/PL_BK.png")).getImage();
			avitar = new ImageIcon(this.getClass().getResource("resources/PL_"+gender+".png")).getImage();
			avitarOn = new ImageIcon(this.getClass().getResource("resources/PL_"+gender+"_"+playerToken+".png")).getImage();
	}
	public static void main(String [] args){
		JFrame frame = new JFrame("Hollywood Squares");
		frame.getContentPane().add(new PlayerSquare("X","Player 1","M")); 
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
		g2d.setPaint(color);
		g2d.drawString(playerToken, 172, 233);
		g2d.setPaint(Color.white);
		g2d.drawString(score, 37, 289);
		g2d.drawString(playerName, 37, 339);
	}
	public void setName(String name){
		this.playerName=name;
		repaint();
	}
	public void setScore(String score){
		this.score = "$"+score;
		repaint();
	}
	public void setGender(String gender){
		this.gender=gender;
		loadImages();
		repaint();
	}
}