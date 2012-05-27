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
		
	public void squareActionPerformed(ActionEvent ae) {
	}
	public void restart(ActionEvent ae){
		model.restart();
	} 
}
