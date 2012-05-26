import java.awt.event.ActionEvent;

public class GameControl {
	  private GameModel model;

	   public GameControl(GameModel model) {
	      this.model = model;
	   }
		
	   public void squareActionPerformed(ActionEvent ae) {
	   }
	   public void restart(ActionEvent ae){
		model.restart();
	} 

}
