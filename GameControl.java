import java.awt.event.ActionEvent;

public class GameControl {
	private GameModel model;
	
	public GameControl(GameModel model) {
		this.model = model;
	}
	public void answerAgree(ActionEvent ae){
		model.answerQuestion(true);
	}
	public void answerDisagree(ActionEvent ae){
		model.answerQuestion(false);
	}
	public boolean checkIfSquareIsBlank(int row, int col){
		return model.getState(row,col)==State.NO_STATE;
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
	public void setPlayer(ActionEvent ae, String name, int playerNumber, char gender){
		model.setPlayer(name,gender,playerNumber);
	}
	public boolean questionPending(){
		return model.questionPending();
	}
}
