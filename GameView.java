import java.beans.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameView{
	private GameControl control;
	/* 2 dimensional array to deal with squares */
	private Square [][] squares = new Square[3][3];	
	private JPanel mainPanel = new JPanel();
	private InfoBox infoBox = new InfoBox();
	private JLabel title = new JLabel("Hollywood Squares");
	public GameView(GameModel model){
		GraphicsEnvironment env =
		       GraphicsEnvironment.getLocalGraphicsEnvironment();
		env.getAvailableFontFamilyNames();
		title.setFont(new Font("LED BOARD REVERSED", Font.PLAIN, 50));
		title.setForeground(Color.white);
		JPanel header = new JPanel();
		header.setBackground(new Color(17,45,164));
		header.add(title);
		mainPanel.setLayout(new BorderLayout());
		/* Setup Placeholders for the areas outside the grid */
		mainPanel.add(header,BorderLayout.NORTH);
		mainPanel.add(new JLabel(new ImageIcon("resources/left.png")),BorderLayout.EAST);
		mainPanel.add(new JLabel(new ImageIcon("resources/right.png")),BorderLayout.WEST);
		mainPanel.add(infoBox,BorderLayout.SOUTH);
		/* Setup Grid */
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(3,3));
		grid.setBackground(new Color(17,45,164));
		// initialize each square add add them to the grid.
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				squares[i][j]=new Square(i,j,model);
				grid.add(squares[i][j]);
			}
		}
		// add grid to main panel    	
		mainPanel.add(grid,BorderLayout.CENTER);
		// add a property change listener to the model to listen and 
		// respond to changes in the model's state
		model.addPropertyChangeListener(new PropChangeListener());
	}
	// set the control for this view
   	public void setGuiControl(GameControl control) {
      	this.control = control;
   	}

   	// get the main gui and its components for display
   	public JComponent getMainPanel() {
    	return mainPanel;
   	}
   	class PropChangeListener implements PropertyChangeListener{
		public void propertyChange(PropertyChangeEvent evt) {
			//loop through to check each square
	        for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){ 
					if (evt.getPropertyName().equals(GameModel.SQUARE[i][j])) {
							squares[i][j].setState(evt.getNewValue().toString());
	            	}	
				}
			}
			if(evt.getPropertyName().equals(GameModel.WINNER)){
				String buffer = (evt.getNewValue().toString().equals("_"))?"HOLLYWOOD SQUARES":("   Winner: " + evt.getNewValue().toString() + "   ");
				title.setText(buffer);
			}
			if(evt.getPropertyName().equals(GameModel.CURRENT_QUESTION)){
				infoBox.setQuestion(evt.getNewValue().toString());
			}
			if(evt.getPropertyName().equals(GameModel.CURRENT_ANSWER)){
				infoBox.setAnswer(evt.getNewValue().toString());
			}
	    }	
	}
}