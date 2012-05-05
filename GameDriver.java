package hssquares;
import javax.swing.*;

public class GameDriver {
	   private static void createAndShowUI() {

		      // create the model/view/control and connect them together
		      Game model = new Game();
		      GameView view = new GameView(model);
		      GameControl control = new GameControl(model);
		      view.setGuiControl(control);

		      // create the GUI to display the view
		      JFrame frame = new JFrame("Hollywood Squares");
		      frame.getContentPane().add(view); // add view here
		      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      frame.pack();
		      frame.setLocationRelativeTo(null);
		      frame.setVisible(true);
		   }

		   // call Swing code in a thread-safe manner per the tutorials
		   public static void main(String[] args) {
		      java.awt.EventQueue.invokeLater(new Runnable() {
		         public void run() {
		            createAndShowUI();
		         }
		      });
		   }
}
