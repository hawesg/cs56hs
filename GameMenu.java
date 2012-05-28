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
				JTextField name = new JTextField();
				String[] choices = { "Male", "Female"};
				JComboBox sex = new JComboBox(choices);
				final JComponent[] inputs = new JComponent[] {
				                new JLabel("Name"),
				                name,
				                new JLabel("Gender"),
				                sex
				};
				JOptionPane.showMessageDialog(null, inputs, "Player 1 Setup", JOptionPane.PLAIN_MESSAGE);
            	if (control != null) {
               			char gender = (sex.getSelectedItem().toString().equals("Female"))?'F':'M';
	               		control.setPlayer(ae,name.getText(),1,gender);
            	}
         	}
      	}));
		menu.add(new JMenuItem(new AbstractAction("Setup Player 2") {
         	public void actionPerformed(ActionEvent ae) {
            	JTextField name = new JTextField();
				String[] choices = { "Male", "Female"};
				JComboBox sex = new JComboBox(choices);
				final JComponent[] inputs = new JComponent[] {
				                new JLabel("Name"),
				                name,
				                new JLabel("Gender"),
				                sex
				};
				JOptionPane.showMessageDialog(null, inputs, "Player 2 Setup", JOptionPane.PLAIN_MESSAGE);
            	if (control != null) {
					char gender = (sex.getSelectedItem().toString().equals("Female"))?'F':'M';
               		control.setPlayer(ae,name.getText(),2,gender);
            	}
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
				JOptionPane.showMessageDialog(new JFrame(),"Players take turns.  The first player to get three in a row or five total squares wins.  \nPlay:  The player whose turn it is chooses a square by clicking on it.   \nA question is asked the celebrity in that square and the celebrity answers.  \nThe player then either agrees or disagrees with the celebrity's answer by clicking on the appropriate button.  \nIf the player chooses correctly, they get the square.  \nIf they choose incorrectly, their opponent gets the square, unless it would result in three in a row for the opponent.  \nPlay then goes to the other player.",
				"Rules",
			    JOptionPane.PLAIN_MESSAGE);
    	 }
      	}));
		helpMenu.add(new JMenuItem(new AbstractAction("About") {
         	public void actionPerformed(ActionEvent ae) {
            	JOptionPane.showMessageDialog(new JFrame(),
				    "Hollywood Squares \nCreated By: Garrett Hawes, Julian Gerard, Gerald Font, Tom Panda, Bill, Anna\n 2012","About",JOptionPane.QUESTION_MESSAGE);
         	}
      	}));
      	menuBar.add(menu);
		menuBar.add(helpMenu);
    }

   	public JMenuBar getMenuBar() {
      	return menuBar;
   	}
}
