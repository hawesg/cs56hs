package hssquares;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import swingmvc.State;


public class Game {
	//properties for broadcast
	public static final String SQUARE_ONE_NAME = "square1";
	public static final String SQUARE_TWO_NAME = "square2";
	public static final String SQUARE_THREE_NAME = "square3";
	public static final String SQUARE_FOUR_NAME = "square4";
	public static final String SQUARE_FIVE_NAME = "square5";
	public static final String SQUARE_SIX_NAME = "square6";
	public static final String SQUARE_SEVEN_NAME = "square7";
	public static final String SQUARE_EIGHT_NAME = "square8";
	public static final String SQUARE_NINE_NAME = "square9";
	private PropertyChangeSupport pcSupport = new PropertyChangeSupport(this);
	//private ArrayList questions = new ArrayList();
	private QuestionList ql = new QuestionList();	
	private char currentPlayer = 'X';
	private Player player1 = new Player("Player 1", 'X', 0);
	private Player player2 = new Player("Player 2", 'O', 1);
	private SquareState [][] square=new SquareState[3][3];
	//private TicTacToe currentGame = new TicTacToe();

	Game(){
		for(int i=0;i<3;i++)
			for(int j=0; j<3; j++)
				square[i][j]=SquareState.NO_STATE;
	}
	 public void setState(SquareState state, int row, int col) {
	      SquareState oldState = square[row][col];
	      this.square[row][col] = state;
	      // notify all listeners that the state property has changed
	      pcSupport.firePropertyChange(SQUARE_ONE_NAME, oldState, state);
	   }
	public void setPlayer(String name, int playerImage, int playerNumber){
		if(playerNumber==1){
			player1.setName(name);
			player1.setPlayerImage(playerImage);
		}
		else{
			player2.setName(name);
			player2.setPlayerImage(playerImage);
		}
	}
	
	 // allow addition of listeners or observers
	   public void addPropertyChangeListener(PropertyChangeListener listener) {
	      pcSupport.addPropertyChangeListener(listener);
	   }
	   
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
