import java.beans.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class InfoBox extends JPanel{
	private Image background;
	private ImageIcon agreeIcon = new ImageIcon(this.getClass().getResource("resources/agree_OFF.png"));
	private ImageIcon agreeIconOn = new ImageIcon(this.getClass().getResource("resources/agree_ON.png"));
	private ImageIcon disagreeIcon = new ImageIcon(this.getClass().getResource("resources/disagree_OFF.png"));
	private ImageIcon disagreeIconOn = new ImageIcon("resources/disagree_ON.png");
	private ImageIcon yesIcon = new ImageIcon(this.getClass().getResource("resources/yes_OFF.png"));
	private ImageIcon yesIconOn = new ImageIcon(this.getClass().getResource("resources/yes_ON.png"));
	private Dimension size;
	private String question = "";
	private String answer = "";
	private JButton agree = new JButton();
	private JButton disagree = new JButton();
	private JButton playAgain = new JButton();
	private GameControl control;
	
	public InfoBox(){
		setBackground(new Color(17,45,164));
	    setFont(new Font("Dialog", Font.BOLD, 15));
		size = new Dimension();
		background = new ImageIcon(this.getClass().getResource("resources/bottom.png")).getImage();
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

	public void setGuiControl(GameControl control) {
      	this.control = control;
   	}

	public void setQuestion(String question){
		setFont(new Font("Dialog", Font.BOLD, 15));
		this.question=question;
	}
	
	public void setAnswer(String answer){
		add(agree);
		add(disagree);
		remove(playAgain);
		this.answer=answer;
		repaint();
		revalidate();
	}
	
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
	
	public void clear(){
		remove(agree);
		remove(disagree);
		remove(playAgain);
		this.answer="";
		this.question="";
		repaint();
		revalidate();
	}
	
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
		g2d.drawString(question, xQuestion, 80); //160
		g2d.drawString(answer, xAnswer, 105); 
	}
}