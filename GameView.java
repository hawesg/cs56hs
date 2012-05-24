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
	public GameView(GameModel model){
		// add a property change listener to the model to listen and 
	    // respond to changes in the model's state
	    model.addPropertyChangeListener(new PropertyChangeListener() {
	         public void propertyChange(PropertyChangeEvent evt) {
	            // if the state change is the one we're interested in...
	         	for(int i=0;i<3;i++){
					for(int j=0;j<3;j++){ 
						if (evt.getPropertyName().equals(GameModel.SQUARE[i][j])) {
	               			System.out.println(evt.getPropertyName() + evt.getNewValue().toString());
							squares[i][j].setState(evt.getNewValue().toString());
							//stateField.setText(evt.getNewValue().toString()); // show it in the GUI
	            		}	
					}
				}
				if(evt.getPropertyName().equals(GameModel.WINNER)){
					infoBox.winner("Winner: " + evt.getNewValue().toString());
				}
	         }
	      });
		mainPanel.setLayout(new BorderLayout());
		/* Setup Placeholders for the areas outside the grid */
		mainPanel.add(new JLabel(new ImageIcon("resources/title.png")),BorderLayout.NORTH);
		mainPanel.add(new JLabel(new ImageIcon("resources/left.png")),BorderLayout.EAST);
		mainPanel.add(new JLabel(new ImageIcon("resources/right.png")),BorderLayout.WEST);
		/*mainPanel.add(new JLabel(new ImageIcon("resources/bottom.png")),BorderLayout.SOUTH);*/
	/*	JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		bottom.add(infoBox,BorderLayout.NORTH);
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(17,45,164));
		buttonPane.add(new JButton("Agree"));
		buttonPane.add(new JButton("Disagree"));
		bottom.add(buttonPane,BorderLayout.SOUTH);*/
		mainPanel.add(infoBox,BorderLayout.SOUTH);
		/* Setup Grid */
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(3,3));
		grid.setBackground(Color.BLUE);
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				squares[i][j]=new Square(i,j,model);
				grid.add(squares[i][j]);
			}
		}    	
		mainPanel.add(grid,BorderLayout.CENTER);
	}
	// set the control for this view
   public void setGuiControl(GameControl control) {
      this.control = control;
   }

   // get the main gui and its components for display
   public JComponent getMainPanel() {
      return mainPanel;
   }
 
	class InfoBox extends JPanel{
		private JLabel q = new JLabel("<html>Question: What is the national anthem of Canada?<br>Jay-Z: Oh Canada</html>");
		private JLabel a = new JLabel("<html>Jay-Z: Oh Canada</html>");
		private JLabel winner = new JLabel(); 
		private String win = "WINNER: _";
		private Image castle;
		private Dimension size;
		public InfoBox(){
			GraphicsEnvironment env =
		      GraphicsEnvironment.getLocalGraphicsEnvironment();
		    env.getAvailableFontFamilyNames();
		    setFont(new Font("LCD PHONE", Font.PLAIN, 100));
			/*q.setFont(new Font("sansserif", Font.BOLD, 15));
			setLayout(new BorderLayout());
			add(q,BorderLayout.CENTER);
			q.setHorizontalAlignment( SwingConstants.CENTER );
		/*	q.setFont(new Font("sansserif", Font.BOLD, 36));
			a.setFont(new Font("sansserif", Font.BOLD, 36));
			winner.setFont(new Font("sansserif",Font.BOLD, 50));
			setLayout(new GridLayout(2,1));
			add(q);
			add(a);*/
			size = new Dimension();
		    castle = new ImageIcon(this.getClass().getResource("resources/bottom.png")).getImage();
			size.width = castle.getWidth(null);
			size.height = castle.getHeight(null);
			setPreferredSize(size);
			/*setPreferredSize(new Dimension(1109, 160));
			setMinimumSize(new Dimension(1109, 160));
			setMaximumSize(new Dimension(1109, 160));
			setBackground(new Color(17,45,164));*/
		}
		public void winner(String win){
			//this.q.setText("<html> "+ win + "</html>");
			this.win = win;
			this.repaint();
		}
		public void paintComponent( Graphics g ){
	    	super.paintComponent( g );
	    	Graphics2D g2d = (Graphics2D) g;
	  		g2d.drawImage(castle, 0, 0, null);
			g2d.setPaint(Color.black);
			g2d.drawString(win, 270, 150);
	    }
	}
}