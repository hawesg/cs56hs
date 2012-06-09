import javax.swing.*; 
import java.awt.*; 
import java.io.*;

/** 	
*		Class - Header
*		A simple display to show either hollywood squares or a message congratulating the current winner 
*		@author Garrett Hawes
*/
               
public class Header extends JPanel{
	private String text;
	private Dimension size;
	private Image background = new ImageIcon(this.getClass().getResource("resources/header.png")).getImage();;
	
	public Header(String text){
		loadFont();
		this.text=text;
		GraphicsEnvironment env =
			 GraphicsEnvironment.getLocalGraphicsEnvironment();
		env.getAvailableFontFamilyNames();
		setFont(new Font("lcd phone", Font.PLAIN, 40));//25
		size = new Dimension();
		size.width = 460;
		size.height = 50;
		setPreferredSize(size);
		setBackground(new Color(17,45,164));
	}
	
	/**
	*		Load the font from file and regester it with the os
	*/
	private void loadFont(){
		try {
			     GraphicsEnvironment ge = 
			         GraphicsEnvironment.getLocalGraphicsEnvironment();
			     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/lcdphone.TTF")));
			} catch (IOException ex) {
			     ex.printStackTrace();
			} catch (FontFormatException ex){
				 ex.printStackTrace();
			}
	}
	
	/**
	*		Set the text in the header field
	*		@param text								The text that you would like to show up in this field
	*/
	public void setText(String text){
		this.text=text;
		repaint();
	}
	
	/** 	
	*		Paint the component, RenderingHints are so that the font will anti alias on windows computers, center text draw backgrounfd etc... 
	*		@param g 							Graphics object
	*/
	protected void paintComponent( Graphics g ){
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
}