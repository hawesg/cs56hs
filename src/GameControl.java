import java.awt.event.ActionEvent;
import javax.swing.*;

/** 	
*		Class - GameControl
*		This is a basic controler to handle two way comunication with the model.
*		@author Garrett Hawes
*/

public class GameControl {
	/** This is where the model is stored */
	private GameModel model;
	
	
	/** 	
	*		Constructor 
	*		@param model				pass the games model in
	*/
	public GameControl(GameModel model) {
		this.model = model;
	}
	
	/** 	
	*		Provide an anwer to the model 
	*		@param ae				ActionEvent from event handeler
	*		@param answer			Answer to the current question (true for agree, false for disagree)
	*/
	public void answer(ActionEvent ae, boolean answer){
		model.answerQuestion(answer);
	}

	/** 	
	*		click a square, pass ActionEvent and it's for the row and column in question
	*		@param ae 				ActionEvent from event handler
	*		@param row				row in question
	*		@param col				column in questions
	*/
	public void squareActionPerformed(ActionEvent ae, int row, int col) {
		model.clickSquare(row,col);
	}
	
	/** 	
	*		restart the game from scratch (player names, scores, questions etc...)
	*		@param ae 				ActionEvent from handeler 
	*/
	public void restart(ActionEvent ae){
		model.restart();
	}
	
	/** 	
	*		play another game without resetting players
	*		@param ae 				ActionEvent from handeler
	*/
	public void playAgain(ActionEvent ae){
		model.playAgain();
	}
	
	/** 	
	*		setup a players info
	*		@param name				The player in questions name (max 7 chars)
	*		@param playerNumber		Player number 1 or 2
	*		@param gender			Gender for the char 'M' or 'F'
	*/
	public void setPlayer(String name, int playerNumber, char gender){
		model.setPlayer(name,gender,playerNumber);
	}
	
	/** 	
	*		check if there is a question waiting to be answered 
	*		@return 				if there is a question to be answered
	*/
	public boolean questionPending(){
		return model.questionPending();
	}
	
	/** 	
	*		check to see if one of the squares on the board is blank.
	*		@param row				the row in which this square is located.
	*		@param col				the	column in which this square is located. 
	*		@return 				if the square referenced is State.NO_STATE (blank)
	*/
	public boolean checkIfSquareIsBlank(int row, int col){
		return model.getState(row,col)==State.NO_STATE;
	}
	
}
