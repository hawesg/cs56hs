import java.beans.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InfoBox extends JPanel{
	private JLabel winner = new JLabel(); 
	private String win = "WINNER: _";
	private Image background;
	private Dimension size;
	private String question = "Q: What is the national anthem of canada?";
	private String answer = "A: Oh Canada";
	public InfoBox(){
	    setFont(new Font("Dialog", Font.BOLD, 15));
		size = new Dimension();
		background = new ImageIcon(this.getClass().getResource("resources/bottom.png")).getImage();
		size.width = background.getWidth(null);
		size.height = background.getHeight(null);
		setPreferredSize(size);
		add(new JButton("Agree"));
		add(new JButton("Disagree"));
	}
	public void winner(String win){
		this.win = win;
		this.repaint();
	}
	public void setQuestion(String question){
		this.question=question;
	}
	public void setAnswer(String answer){
		this.answer=answer;
		repaint();
	}
	public void paintComponent( Graphics g ){
		super.paintComponent( g );
	    Graphics2D g2d = (Graphics2D) g;
	  	g2d.drawImage(background, 0, 0, null);
		g2d.setPaint(Color.black);
		g2d.drawString(question, 280, 100); //20
		g2d.drawString(answer, 280, 140); //60
	}
}