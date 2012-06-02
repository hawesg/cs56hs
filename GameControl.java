import java.awt.event.ActionEvent;
import javax.swing.*;


public class GameControl {
	private GameModel model;
	
	public GameControl(GameModel model) {
		this.model = model;
	}
	
	public void answer(ActionEvent ae, boolean answer){
		model.answerQuestion(answer);
	}

	
	public void squareActionPerformed(ActionEvent ae, int row, int col) {
		model.clickSquare(row,col);
	}
	
	public void restart(ActionEvent ae){
		model.restart();
	}
	
	public void playAgain(ActionEvent ae){
		model.playAgain();
	}
	
	public void setPlayer(String name, int playerNumber, char gender){
		model.setPlayer(name,gender,playerNumber);
	}
	
	public boolean questionPending(){
		return model.questionPending();
	}
	
	public boolean checkIfSquareIsBlank(int row, int col){
		return model.getState(row,col)==State.NO_STATE;
	}
	
}
