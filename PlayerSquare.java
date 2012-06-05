import javax.swing.*; 
import java.awt.*; 
import java.io.*;
import java.awt.RenderingHints;
//http://www.javaworld.com/javaworld/jw-08-1998/jw-08-media.html
               
public class PlayerSquare extends JPanel{
 	private Image background;
	private Image avitar;
	private Image avitarOn;
	private boolean active;
	private Dimension size;
	Color color;
	private String playerName = "";
	private String score = "0";
	private String playerToken; 
	private String gender;
	
	public PlayerSquare(String playerToken, String playerName, String gender){
		loadFont();
		color = (playerToken.equals("O"))?new Color(152,130,225):new Color(252, 100, 8);
		this.playerToken = playerToken;
		this.gender = gender;
		this.playerName = playerName;
		GraphicsEnvironment env =
			 GraphicsEnvironment.getLocalGraphicsEnvironment();
		env.getAvailableFontFamilyNames();
		setFont(new Font("lcd phone", Font.PLAIN, 35));//25//lcd phone
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
		frame.getContentPane().add(new PlayerSquare("X","GARRETT","M")); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}	
	
	private void loadFont(){
		try {
		     GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/LCDPHONE.TTF")));
		} catch (FontFormatException ex){
			 ex.printStackTrace();
		} catch (IOException  ex){
			ex.printStackTrace();
		}	
	}	
	public void setActive(boolean active){
		this.active=active;
		repaint();
	}
	
	public void paintComponent( Graphics g ){
		super.paintComponent( g );
	    Graphics2D g2d = (Graphics2D) g;
		// Anti Aliasing
		RenderingHints rh = g2d.getRenderingHints(); 
		rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints (rh);
		if(active){
			g2d.drawImage(avitarOn, 17, 0, null);
		}else{
			g2d.drawImage(avitar, 17, 0, null);
		}
		g2d.drawImage(background, 17, 184, null);
		g2d.setPaint(Color.white);
		g2d.drawString("PLAYER", 37, 236);
		g2d.setPaint(color);
		g2d.drawString(playerToken, 181, 236);
		g2d.setPaint(Color.white);
		g2d.drawString(score, 63, 289);
		g2d.drawString(playerName, 37, 343);
	}
	
	public void setName(String name){
		this.playerName=name;
		repaint();
	}
	
	public void setScore(String score){
		this.score = score;
		repaint();
	}
	
	public void setGender(String gender){
		this.gender=gender;
		loadImages();
		repaint();
	}
}