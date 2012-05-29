import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
class Square extends JButton{
	private GameModel model;
	/* Store states of button */
	private boolean active=false;
	private ImageIcon off;
	private ImageIcon on;
	private ImageIcon o;
	private ImageIcon x;
	private int row;
	private int col;
	public Square(int row, int col){
		setBorder(BorderFactory.createEmptyBorder());
		setContentAreaFilled(false);
		this.row=row;
		this.col=col;
		this.model = model;
		/* Load Button Images */
		off = new ImageIcon("resources/SQ_"+row+"_"+col+"_OFF.png");
		on = new ImageIcon("resources/SQ_"+row+"_"+col+"_ON.png");
		o = new ImageIcon("resources/SQ_"+row+"_"+col+"_O.png");
		x = new ImageIcon("resources/SQ_"+row+"_"+col+"_X.png");
		this.setIcon(off);
		// Add rollover icon;
		this.setRolloverIcon(on);
		setPreferredSize(new Dimension(150,150));
	}
	public void setState(String state){
		active = true;
		if(state=="X"){
			setRolloverIcon(null);
			setIcon(x);
		}else if(state=="O"){
			setRolloverIcon(null);
			setIcon(o);
		}else{
			reset();
		}
	}
	public void reset(){
		active = false;
		this.setIcon(off);
		this.setRolloverIcon(on);
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	public void setOn(){
		this.setIcon(on);
	}
	public void block(){
		this.setRolloverIcon(null);
	}
	public void unblock(){
		if(active==false){
			this.setRolloverIcon(on);
		}
	}
	public  boolean is_Active(){
		return active;
	}
}