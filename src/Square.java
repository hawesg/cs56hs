import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

/** 	
*		Class - Square 
*		A component to display celeberty squares for the grid
*		@author Garrett Hawes
*/

class Square extends JButton {
	/** Store states of button */
	private boolean active=false;
	private ImageIcon off;
	private ImageIcon on;
	private ImageIcon o;
	private ImageIcon x;
	private ImageIcon xOn;
	private ImageIcon oOn;
	private int row;
	private int col;
	
	/** 	
	*		Construct a square from images found under reasources they folow the nameing model SQ_X_Y_Z.png where X is the row Y is the column and Z is either OFF, ON, X or O
	*		@param row							Row for square
	* 		@param col							Column for square
	*/
	public Square(int row, int col){
		setBorder(BorderFactory.createEmptyBorder());
		setContentAreaFilled(false);
		this.row=row;
		this.col=col;
		/* Load Button Images */
		off = new ImageIcon("resources/SQ_"+row+"_"+col+"_OFF.png");
		on = new ImageIcon("resources/SQ_"+row+"_"+col+"_ON.png");
		o = new ImageIcon("resources/SQ_"+row+"_"+col+"_O.png");
		x = new ImageIcon("resources/SQ_"+row+"_"+col+"_X.png");
		oOn = new ImageIcon("resources/SQ_"+row+"_"+col+"_O_ON.png");
		xOn = new ImageIcon("resources/SQ_"+row+"_"+col+"_X_ON.png");
		this.setIcon(off);
		/* Add rollover icon; */
		this.setRolloverIcon(on);
		setPreferredSize(new Dimension(150,150));
	}
	
	/** 	
	*		Set the state of the square
	*		@param state						Type State indicating the state for the square: State.X, State.O or State.NO_STATE
	*/
	public void setState(String state){
		active = true;
		if(state=="X"){
			setRolloverIcon(null);
			setIcon(x);
		}else if(state=="O"){
			setRolloverIcon(null);
			setIcon(o);
		}else if(state=="O_ON"){
			setRolloverIcon(null);
			setIcon(oOn);
		}else if(state=="X_ON"){
				setRolloverIcon(null);
				setIcon(xOn);
		}else{
			reset();
		}
	}
	
	/** 	
	*		Set the square back to it's blank state
	*/
	public void reset(){
		active = false;
		this.setIcon(off);
		this.setRolloverIcon(on);
	}
	
	/** 	
	*		Get the row for the square
	*		@return 						 Row
	*/
	public int getRow(){
		return row;
	}
	
	/** 	
	*		Get the column for the square
	*		@return 						 Column
	*/
	public int getCol(){
		return col;
	}
	
	/** 	
	*		Set the square to active when a square is selected and the question is waiting to be answered
	*/
	public void setOn(){
		this.setIcon(on);
	}
	
	/** 	
	*		Block the square from having a rollover
	*/
	public void block(){
		this.setRolloverIcon(null);
	}
	
	
	/** 	
	*		Set rollover if the square is free
	*/
	public void unblock(){
		if(active==false){
			this.setRolloverIcon(on);
		}
	}
}