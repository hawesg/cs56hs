import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
class Square extends JButton implements ActionListener {
	private GameModel model;
	/* Store states of button */
	private ImageIcon off;
	private ImageIcon on;
	private ImageIcon o;
	private ImageIcon x;
	private int row;
	private int col;
	public Square(int row, int col, GameModel model){
		this.row=row;
		this.col=col;
		this.addActionListener(this);
		this.model = model;
		/* Load Button Images */
		off = new ImageIcon("resources/small/SQ_"+row+"_"+col+"_OFF.png");
		on = new ImageIcon("resources/small/SQ_"+row+"_"+col+"_RO_OFF.png");
		o = new ImageIcon("resources/small/SQ_"+row+"_"+col+"_OsOFF.png");
		x = new ImageIcon("resources/small/SQ_"+row+"_"+col+"_XsOFF.png");
		this.setIcon(off);
		// Add rollover icon;
		this.setRolloverIcon(on);
		setPreferredSize(new Dimension(150,150));
	}
	public void setState(String state){
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
		this.setIcon(off);
		this.setRolloverIcon(on);
		addActionListener(this);
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	public void actionPerformed(ActionEvent e) { 
		model.clickSquare(row,col);
		this.setIcon(on);
		removeActionListener(this);
	}
}