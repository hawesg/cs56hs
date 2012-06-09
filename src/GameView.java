import java.beans.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/** 	
*		Class - GameView
*		The main view for the game holds a header, 9 squares, an info box and two player squares
*		@author Garrett Hawes
*/

public class GameView{
	/**  controler */
	private GameControl control;
	/** 2 dimensional array of square panels */
	private Square [][] squares = new Square[3][3];	
	/**  The main panel to be returned to the driver */
	private JPanel mainPanel = new JPanel();
	/**  bottom section responsible for displaying dialog */
	private InfoBox infoBox;
	/** the header at the top, this displays HOLLYWOOD SQUARES normaly and the winner at the end of a game */
	private Header title = new Header("HOLLYWOOD SQUARES");
	/** player1 display area */
	private PlayerSquare player1 = new PlayerSquare("X","Player1","M");
	/** player2 display area */
	private PlayerSquare player2 = new PlayerSquare("O","Player2","F");
	
	/** 	
	*		Construct GameView, 
	*		@param model 							The model to comunicate with
	*/
	public GameView(GameModel model){
		infoBox=new InfoBox();
		mainPanel.setBackground(new Color(17,45,164));
		JPanel header = new JPanel();
		header.setBackground(new Color(17,45,164));
		header.add(title);
		mainPanel.setLayout(new BorderLayout());
		/* Setup Placeholders for the areas outside the grid */
		mainPanel.add(header,BorderLayout.NORTH);
		mainPanel.add(player1,BorderLayout.WEST);
		mainPanel.add(player2,BorderLayout.EAST);
		player1.setActive(true);
		mainPanel.add(infoBox,BorderLayout.SOUTH);
		/* Setup Grid */
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(3,3));
		grid.setBackground(new Color(17,45,164));
		// initialize each square add add them to the grid.
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				squares[i][j]=new Square(i,j);
				squares[i][j].addActionListener(new SquareListener(i,j));
				grid.add(squares[i][j]);
			}
		}
		// add grid to main panel    
		mainPanel.add(grid,BorderLayout.CENTER);
		// add a property change listener to the model to listen and 
		// respond to changes in the model's state
		model.addPropertyChangeListener(new PropChangeListener());
	}
	
	/** 	
	*		Set the controler for this view and the infobox to comunicate with the model
	*		@param control 							The controler to comunicate with
	*/
   	public void setGuiControl(GameControl control) {
      	this.control = control;
		infoBox.setGuiControl(control);
   	}

	/** 	
	*		Get the main gui for display
	*		@return 							The main panel for display
	*/
   	public JComponent getMainPanel() {
		JPanel panel = new JPanel();
		panel.add(mainPanel,BorderLayout.CENTER);
		panel.setBackground(new Color(17,45,164));
    	return panel;
   	}

	/** 	
	*		Class - SquareListener
	*		An action listener for the squares to comunicate with the controler
	*		@author Garrett Hawes
	*/
	class SquareListener implements ActionListener{
		/** row that the listener is created for */
		int row;
		/** column that the listener is created for */
		int col;
		
		/** 	
		*		Construct with row and col
		*		@param row 							Row that the listener is created for
		*		@param col							Column that the listener is created for
		*/
		public SquareListener(int row, int col){
			this.row=row;
			this.col=col;
		}
		
		/** 	
		*		Action Performed, if it is a valid move and there isnt a move waiting to to be completed then block all other squares and send the move to the controler
		*		@param e							action event
		*/
		public void actionPerformed(ActionEvent e) {
			if(control != null){
				if(control.checkIfSquareIsBlank(row,col)&&control.questionPending()==false){
					squares[row][col].setOn();
					for(int i=0;i<3;i++){
						for(int j=0;j<3;j++){
							squares[i][j].block();
						}
					}
				  	control.squareActionPerformed(e,row,col); 
				}	 
			}    
       	}
	}
	
	/** 	
	*		Class - PropChangeListener 
	*		A listener to intercept updated from the model
	*		@author Garrett Hawes
	*/
   	class PropChangeListener implements PropertyChangeListener{
		
		/** 	
		*		Listen for bradcasts from the model and react acordingly
		*		@param evt							Property Change Event
		*/
		public void propertyChange(PropertyChangeEvent evt) {
			//loop through to check each square
	        for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){ 
					if (evt.getPropertyName().equals(GameModel.SQUARE[i][j])) {
						squares[i][j].setState(evt.getNewValue().toString());
						String buffer = (evt.getNewValue().toString().equals("_"))?"":" GETS THE SQUARE";
						if(evt.getNewValue().toString().equals("X")){
							buffer="X"+buffer;
						}else if(evt.getNewValue().toString().equals("O")){
							buffer="CIRCLE"+buffer;
						}
						infoBox.setDialog(buffer);
	            	}	
				}
			}
			if(evt.getPropertyName().equals(GameModel.WINNER)){
				String buffer = evt.getNewValue().toString();
				title.setText(buffer);
				if(buffer.equals("HOLLYWOOD SQUARES")){
					infoBox.clear();
				}else{
					infoBox.setWinner();
				}	
			}
			if(evt.getPropertyName().equals(GameModel.CURRENT_QUESTION)){
				infoBox.setQuestion(evt.getNewValue().toString());
			}
			if(evt.getPropertyName().equals(GameModel.CURRENT_ANSWER)){
				infoBox.setAnswer(evt.getNewValue().toString());
			}
			if(evt.getPropertyName().equals(GameModel.X_SCORE)){
				player1.setScore(evt.getNewValue().toString());
			}
			if(evt.getPropertyName().equals(GameModel.O_SCORE)){
				player2.setScore(evt.getNewValue().toString());
			}
			if(evt.getPropertyName().equals(GameModel.X_NAME)){
				player1.setName(evt.getNewValue().toString());
			}
			if(evt.getPropertyName().equals(GameModel.O_NAME)){
				player2.setName(evt.getNewValue().toString());
			}
			if(evt.getPropertyName().equals(GameModel.X_GENDER)){
				player1.setGender(evt.getNewValue().toString());
			}
			if(evt.getPropertyName().equals(GameModel.O_GENDER)){
				player2.setGender(evt.getNewValue().toString());
			}
			if(evt.getPropertyName().equals(GameModel.CURRENT_PLAYER)){
				for(int i=0;i<3;i++){
					for(int j=0;j<3;j++){
						squares[i][j].unblock();
					}
				}
				if(evt.getNewValue().toString().equals("X")){
					player1.setActive(true);
					player2.setActive(false);
				}else{
					player1.setActive(false);
					player2.setActive(true);
				}
				
			}
	    }	
	}
}