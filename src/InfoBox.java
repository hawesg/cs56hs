import java.beans.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** 	
*		Class - InfoBox
*		This is the dialog area at the bottom of the game play area where questions/answers and buttons to interact with the player are presented (agree, disagree, play again...)
*		Everything here other than the buttons are painted with java 2D buttons are playced in a layout and made to look nice with images aswell as setting the border to an empty border and setting the content area to not filled.
*		@author Garrett Hawes
*/

public class InfoBox extends JPanel{
	/** Background image */
	private Image background = new ImageIcon(this.getClass().getResource("resources/bottom.png")).getImage();; 
	/** image for agree button */																									
	private ImageIcon agreeIcon = new ImageIcon(this.getClass().getResource("resources/agree_OFF.png"));
	/** image for agree button rollover */						
	private ImageIcon agreeIconOn = new ImageIcon(this.getClass().getResource("resources/agree_ON.png"));
	/** image for disagree button  */					
	private ImageIcon disagreeIcon = new ImageIcon(this.getClass().getResource("resources/disagree_OFF.png"));	
	/** image for disagree button rollover */			
	private ImageIcon disagreeIconOn = new ImageIcon("resources/disagree_ON.png");			
	/** image for play again button */								
	private ImageIcon yesIcon = new ImageIcon(this.getClass().getResource("resources/yes_OFF.png"));
	/** image for play again button rollover */						
	private ImageIcon yesIconOn = new ImageIcon(this.getClass().getResource("resources/yes_ON.png"));
	/** Dimension to hold the size of the component */						
	private Dimension size;	
	/** current question */																								
	private String question = "";	
	/** current answer or dialog */																
	private String answer = "";		
	/** agree button */																						
	private JButton agree = new JButton();
	/** disagree button */
	private JButton disagree = new JButton();
	/** play againg button */
	private JButton playAgain = new JButton();
	/** the current controler */
	private GameControl control;
	
	/** 	
	*		Construct Info Box, setup the area with buttons and add listeners
	*/
	public InfoBox(){
		setBackground(new Color(17,45,164));
	    setFont(new Font("Dialog", Font.BOLD, 15));
		size = new Dimension();
		size.width = background.getWidth(null);
		size.height = background.getHeight(null);
		setPreferredSize(size);
		agree.setBorder(BorderFactory.createEmptyBorder());
		agree.setContentAreaFilled(false);
		disagree.setBorder(BorderFactory.createEmptyBorder());
		disagree.setContentAreaFilled(false);
		agree.setIcon(agreeIcon);
		disagree.setIcon(disagreeIcon);
		agree.setRolloverIcon(agreeIconOn);
		disagree.setRolloverIcon(disagreeIconOn);
		playAgain.setIcon(yesIcon);
		playAgain.setRolloverIcon(yesIconOn);
		playAgain.setBorder(BorderFactory.createEmptyBorder());
		playAgain.setContentAreaFilled(false);
		agree.addActionListener(new ActionListener() {
			// all the buttons do is call methods of the control
		    public void actionPerformed(ActionEvent e) {
		    	if (control != null) {
		    		control.answer(e,true); 
		    	}
			}
		});
		disagree.addActionListener(new ActionListener() {
			// all the buttons do is call methods of the control
		    public void actionPerformed(ActionEvent e) {
		    	if (control != null) {
		    		control.answer(e,false); 
		    	}
			}
		});
		playAgain.addActionListener(new ActionListener() {
			// all the buttons do is call methods of the control
		    public void actionPerformed(ActionEvent e) {
		    	if (control != null) {
		    		question="";
					answer="";
					control.playAgain(e); 
		    	}
			}
		});
	}

	/** 	
	*		Set the controler
	*		@param control 							Controler to comunicate with
	*/
	public void setGuiControl(GameControl control) {
      	this.control = control;
   	}

	/** 	
	*		Set the question to be displayed
	*		@param question 						Question for display
	*/
	public void setQuestion(String question){
		setFont(new Font("Dialog", Font.BOLD, 15));
		this.question=question;
	}
	
	/** 	
	*		Set the answer to be displayed, add the appropriate buttons 
	*		@param answer 							Answer for display
	*/
	public void setAnswer(String answer){
		add(agree);
		add(disagree);
		remove(playAgain);
		this.answer=answer;
		repaint();
		revalidate();
	}
	
	/** 	
	*		Set the dialog to be displayed and deal clear all buttons
	*		@param dialog 							Dialog for display
	*/
	public void setDialog(String dialog){
		remove(agree);
		remove(disagree);
		remove(playAgain);
		setFont(new Font("Dialog", Font.BOLD, 45));
		this.answer=dialog;
		this.question="";
		repaint();
		revalidate();
	}
	
	/** 	
	*		Clear the text display
	*/
	public void clear(){
		remove(agree);
		remove(disagree);
		remove(playAgain);
		this.answer="";
		this.question="";
		repaint();
		revalidate();
	}
	
	/** 	
	*		Game is over display the option for a new game
	*/
	public void setWinner(){
		remove(agree);
		remove(disagree);
		add(playAgain);
		setFont(new Font("Dialog", Font.BOLD, 35));
		this.answer="DO YOU WANT TO PLAY AGAIN?";
		this.question="";
		repaint();
		revalidate();
	}
	
	/** 	
	*		Paint the component, RenderingHints are so that the font will anti alias on windows computers, center text draw backgrounfd etc... 
	*		@param g 							Graphics object
	*/
	public void paintComponent( Graphics g ){
		RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		super.paintComponent( g );
	    Graphics2D g2d = (Graphics2D) g;
		g2d.addRenderingHints(renderHints);
		int questionLength = (int) g2d.getFontMetrics().getStringBounds(question, g2d).getWidth();
		int answerLength = (int) g2d.getFontMetrics().getStringBounds(answer, g2d).getWidth();
		int xQuestion = background.getWidth(null)/2 - questionLength/2; 
		int xAnswer = background.getWidth(null)/2 - answerLength/2; 
	  	g2d.drawImage(background, 0, 0, null);
		g2d.setPaint(Color.black);
		g2d.drawString(question, xQuestion, 80); 
		g2d.drawString(answer, xAnswer, 105); 
	}
}