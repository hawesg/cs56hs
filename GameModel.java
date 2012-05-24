//import java.beans.PropertyChangeListener;
//import java.beans.PropertyChangeSupport;

import java.util.*;
import java.awt.event.*;
import java.beans.*;



public class GameModel {
	//properties for broadcast
	public static final String [][] SQUARE = { {"Square00", "Square01", "Square02"},{"Square10", "Square11", "Square12"},{"Square20", "Square21", "Square22"}};
	public static final String WINNER = "winner";
	private PropertyChangeSupport pcSupport = new PropertyChangeSupport(this);
	private SquareState winner = SquareState.NO_STATE;
	private QuestionList ql = new QuestionList();	
	private char currentPlayer = 'X';
	private Player player1 = new Player("Player 1", 'X', 0);
	private Player player2 = new Player("Player 2", 'O', 1);
	private SquareState [][] square=new SquareState[3][3];
	private TicTacToe currentGame = new TicTacToe();

	GameModel(){
		for(int i=0;i<3;i++)
			for(int j=0; j<3; j++)
				square[i][j]=SquareState.NO_STATE;
	}

 	public void setState(SquareState state, int row, int col) {
	      SquareState oldState = square[row][col];
	      this.square[row][col] = state;
	      pcSupport.firePropertyChange(SQUARE[row][col], oldState, state);
		  currentGame.makeMove(row,col,state.toString(),false);
		  if(currentGame.getState()!="-"){
			SquareState oldWinner = winner;
		  	if(currentGame.getState()=="X")
				winner=SquareState.X;
		  	else if(currentGame.getState()=="O")
				winner=SquareState.O;
			 pcSupport.firePropertyChange(WINNER, oldWinner, winner);
		  }
	   }
	public void makeMove(int row, int col){
		if(currentPlayer=='X')
			setState(SquareState.X,row,col);
		else
			setState(SquareState.O,row,col);
		currentPlayer=(currentPlayer=='X')?'O':'X';		
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
	public String getState(int row,int col){
		return square[row][col].toString();
	}
	public void restart (){
		for(int i=0;i<3;i++)
			for(int j=0; j<3; j++)
				setState(SquareState.NO_STATE, i, j);
		currentGame=new TicTacToe();
		SquareState oldWinner = winner;
		winner = SquareState.NO_STATE;
		pcSupport.firePropertyChange(WINNER, oldWinner, winner);
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
