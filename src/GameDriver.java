import javax.swing.*;
import java.awt.*;

/** 	
*		Class - GameDriver
*		This is the main c1lass it s1imply sets up the model, view and controler & menu, places the view in a frame and then opens them in a thread safe manner.
*		@author Garrett Hawes
*/

public class GameDriver {
	private static void createAndShowUI() {
		// crea te the model/view/control and connect them together
		GameModel model = new GameModel();
		GameView view = new GameView(model);
		GameControl control = new GameControl(model);
		GameMenu menu = new GameMenu(control);
		
		view.setGuiControl(control);
		
		// create the GUI to display the view
		JFrame frame = new JFrame("Hollywood Squares");
		frame.getContentPane().add(view.getMainPanel()); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menu.getMenuBar()); 
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
