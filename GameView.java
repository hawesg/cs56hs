package hssquares;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

import javax.swing.*;

import swingmvc.MvcControl;
import swingmvc.MvcModel;



public class GameView extends JPanel{
	private Square[][] square = new Square[3][3]; 
	private GameControl control;
	public GameView(Game model){
		JPanel board = new JPanel();
		   // add a property change listener to the model to listen and 
	      // respond to changes in the model's state
	      model.addPropertyChangeListener(new PropertyChangeListener() {
	         public void propertyChange(PropertyChangeEvent evt) {
	            // if the state change is the one we're interested in...
	        	 if (evt.getPropertyName()==Game.SQUARE_ONE_NAME) {
	               square[0][0].setToken('X');
	            }
	            else  if (evt.getPropertyName()==Game.SQUARE_TWO_NAME) {
		               square[0][1].setToken('X');
		            }
	            else  if (evt.getPropertyName()==Game.SQUARE_THREE_NAME) {
		               square[0][2].setToken('X');
		            }
	            else  if (evt.getPropertyName()==Game.SQUARE_FOUR_NAME) {
		               square[1][0].setToken('X');
		            }
	            else  if (evt.getPropertyName()==Game.SQUARE_FIVE_NAME) {
		               square[1][1].setToken('X');
		            }
	            else if (evt.getPropertyName()==Game.SQUARE_SIX_NAME) {
		               square[1][2].setToken('X');
	            }
	            else if (evt.getPropertyName()==Game.SQUARE_SEVEN_NAME) {
		               square[2][0].setToken('X');
	            }
	            else if (evt.getPropertyName()==Game.SQUARE_EIGHT_NAME) {
		               square[2][1].setToken('X');
	            }
	            else if (evt.getPropertyName()==Game.SQUARE_NINE_NAME) {
		               square[2][2].setToken('X');
	            }
	         }
	      });
		board.setLayout(new GridLayout(3,3));
		for(int i=0;i<3;i++){
			for(int j=0; j<3; j++){
				board.add(square[i][j]=new Square());
			}
		}
		add(board);
	     square[0][0].addActionListener(new ActionListener() {
	         // all the buttons do is call methods of the control
	         public void actionPerformed(ActionEvent e) {
	            if (control != null) {
	               control.buttonOneActionPerformed(e); // e.g., here
	            }
	         }
	      });
	}
	public static void main(String args[]){
		Game model = new Game();
		GameView x = new GameView(model);
		JFrame frame = new JFrame();  
		frame.add(x);
	    frame.setTitle("Exercise16_8");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(300, 300);
	    frame.setLocationRelativeTo(null); // Center the frame
	    frame.setVisible(true);
		
	}
	  public void setGuiControl(GameControl control) {
	      this.control = control;
	   }
	class Square extends JButton{
		//State of the square
		private char token='.';
		Square(){
			this.setText(""+token);
		}
		//Set the token
		public void setToken(char x){
			token=x;
			setText(""+x);
		}
		//Reset the token to empty
		public void resetToken(){
			token='.';
			setText("");
		}
		//Get the state of the token
		public char getToken(){
			return token;
		}
	}
}
