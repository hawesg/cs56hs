import java.awt.event.ActionEvent;
import javax.swing.*;

public class GameMenu {
   	private JMenuBar menuBar = new JMenuBar();
   	private GameControl control;

   	@SuppressWarnings("serial")

	public GameMenu(GameControl cntrl) {
      	this.control = cntrl;

		JMenu menu = new JMenu("Options");
      	menu.add(new JMenuItem(new AbstractAction("New Game") {
         	public void actionPerformed(ActionEvent ae) {
            	if (control != null) {
               		control.restart(ae);
            	}
         	}
      	}));
		menu.add(new JMenuItem(new AbstractAction("Setup Player 1") {
         	public void actionPerformed(ActionEvent ae) {
				setPlayer(1);
         	}
      	}));
		menu.add(new JMenuItem(new AbstractAction("Setup Player 2") {
         	public void actionPerformed(ActionEvent ae) {
				setPlayer(2);
         	}
      	}));
		menu.addSeparator();
		menu.add(new JMenuItem(new AbstractAction("Exit") {
         	public void actionPerformed(ActionEvent ae) {
            	System.exit(0);
         	}
      	}));

		JMenu helpMenu = new JMenu("Help");
      	helpMenu.add(new JMenuItem(new AbstractAction("Rules") {
         	public void actionPerformed(ActionEvent ae) {
				JOptionPane.showMessageDialog(new JFrame(),
					   "This game is played with two people. The players take turns. Upon his turn the player picks a square by clicking it.\n" 
					+"A question appears in the area below the squares,  and the celebrity in the chosen square  \"answers\" the question.\n"
					+"The player presses \"agree\" if he thinks the celebrities answer is correct, or \"disagree\" if he thinks it is incorrect.\n"
					+"If the player chooses correctly, he gets the square, and his corresponding letter (X or O) appears on that square.\n" 
					+"If he chooses incorrectly, his opponent gets the square, and his opponents letter (X or O) appears in that square. \n" 
					+"A player cannot win the game due to his opponent being wrong, so in such a case the square would remain blank.\n" 
					+"Once a player has answered, it is his opponent's turn to pick a square and decide whether the celebrity is correct. \n" 
					+"The first player to get either three squares in a row, or five total squares wins the game and gets 1000 dollars.",
					"Rules",
			    	JOptionPane.PLAIN_MESSAGE);
    	 }
      	}));
		helpMenu.add(new JMenuItem(new AbstractAction("About") {
         	public void actionPerformed(ActionEvent ae) {
            	JOptionPane.showMessageDialog(new JFrame(),
				    "Hollywood Squares \n"
					+"CS56 Final Project\n"
					+"Created By: \n"
					+"Garrett Hawes \n"
					+"Julian Gerard \n"
					+"Gerald Fontejon \n"
					+"Tom Panek \n"
					+"Bill Hardesty \n"
					+"Anna Lambrix\n"
					+"\u00a92012 Gartron Design\n"
					+"Santa Monica College","About",JOptionPane.PLAIN_MESSAGE);
         	}
      	}));

      	menuBar.add(menu);
		menuBar.add(helpMenu);
    }

	public void setPlayer(int playerNumber){
		JTextField name = new JTextField();
		String[] choices = { "Male", "Female"};
		JComboBox sex = new JComboBox(choices);
		final JComponent[] inputs = new JComponent[] {                
			new JLabel("Name"),
			name,
			new JLabel("Gender"),
			sex
		};
		JOptionPane.showMessageDialog(null, inputs, "Player "+playerNumber+" Setup", JOptionPane.PLAIN_MESSAGE);
        if (control != null) {
        	char gender = (sex.getSelectedItem().toString().equals("Female"))?'F':'M';
            String buffer=name.getText();
			if(buffer.length()>=9){
				buffer = buffer.substring(0, 8);
			}
			control.setPlayer(buffer,playerNumber,gender);
        }
	}

   	public JMenuBar getMenuBar() {
      	return menuBar;
   	}
}
