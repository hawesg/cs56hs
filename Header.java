import javax.swing.*; 
import java.awt.*; 
import java.io.*;
               
public class Header extends JPanel{
	private String text;
	private Dimension size;
	private Image background = new ImageIcon(this.getClass().getResource("resources/header.png")).getImage();;
	private JLabel headerLabel=new JLabel();
	public Header(String text){
			try {
			     GraphicsEnvironment ge = 
			         GraphicsEnvironment.getLocalGraphicsEnvironment();
			     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/lcdphone.TTF")));
			} catch (IOException ex) {
			     ex.printStackTrace();
			} catch (FontFormatException ex){
				 ex.printStackTrace();
			}
		this.text=text;
		GraphicsEnvironment env =
			 GraphicsEnvironment.getLocalGraphicsEnvironment();
		env.getAvailableFontFamilyNames();
		setFont(new Font("lcd phone", Font.PLAIN, 40));//25
		size = new Dimension();
		size.width = 460;//background.getWidth(null);
		size.height = 50;//background.getHeight(null);
		setPreferredSize(size);
		setBackground(new Color(17,45,164));
	 	//setBorder(BorderFactory.createLineBorder(Color.black));
	}
	public void setText(String text){
		this.text=text;
		repaint();
	}
	public void paintComponent( Graphics g ){
		RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		super.paintComponent( g );
		Graphics2D g2d = (Graphics2D) g;
		g2d.addRenderingHints(renderHints);
		int textLength = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
		int textPosition = 460/2 - textLength/2; 
		int backgrondPosition = 460/2 - background.getWidth(null)/2;
		g2d.drawImage(background, backgrondPosition, 0, null);
		g2d.setPaint(Color.white);
		g2d.drawString(text, textPosition, 41);
	
	}
	public static void main(String [] args){
		JFrame frame = new JFrame("Hollywood Squares");
		frame.getContentPane().add(new Header("HOLLYWOOD SQUARES")); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}