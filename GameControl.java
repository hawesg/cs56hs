package hssquares;

import java.awt.event.ActionEvent;

public class GameControl {
	  private Game model;

	   public GameControl(Game model) {
	      this.model = model;
	   }

	   // all this simplistic control does is change the state of the model, that's it
	   public void buttonOneActionPerformed(ActionEvent ae) {
	      model.setState(SquareState.X, 0,0);
	   }

}
