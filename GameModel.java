import java.util.*;
import java.awt.event.*;
import java.beans.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;


public class GameModel {
	//properties for broadcast
	public static final String [][] SQUARE = { {"Square00", "Square01", "Square02"},{"Square10", "Square11", "Square12"},{"Square20", "Square21", "Square22"}};
	public static final String WINNER = "Winner";
	public static final String CURRENT_PLAYER = "Current Player";
	public static final String CURRENT_QUESTION = "Current Question";
	public static final String CURRENT_ANSWER = "Current Answer";
	/*public static final String X_SCORE = "X Score";
	public static final String O_SCORE = "O Score";
	public static final String X_NAME = "X Name";
	public static final String O_NAME = "O Name";*/
	public static final String PLAYER_ONE = "Player 1";
	public static final String PLAYER_TWO = "Player 2";
	private PropertyChangeSupport pcSupport = new PropertyChangeSupport(this);
	private State winner = State.NO_STATE;
	//question stuff
	private ArrayList<Question> questions = new ArrayList<Question>();
	private Question currentQuestion = new Question("","",false);
	Random random = new Random();
	private int score = 0;
	private char currentPlayer = 'X';
	private Player player1 = new Player("Player 1", 'X', 0);
	private Player player2 = new Player("Player 2", 'O', 1);
	private State [][] square=new State[3][3];
	private TicTacToe currentGame = new TicTacToe();
	GameModel(){
		for(int i=0;i<3;i++){
			for(int j=0; j<3; j++){
				square[i][j]=State.NO_STATE;
			}
		}
		initializeQuestions();
	}
	private void initializeQuestions(){
		for(int i=0;i<24;i++){
			boolean answerState = (random.nextInt(2)==1)?true:false;
			questions.add(new Question("question"+i, "answer" + i + answerState, answerState));
		}
		Collections.shuffle(questions);
	}
 	public void setState(State state, int row, int col) {
		State oldState = square[row][col];
	   	this.square[row][col] = state;
	   	pcSupport.firePropertyChange(SQUARE[row][col], oldState, state);
		currentGame.makeMove(row,col,state.toString(),false);
		if(currentGame.getState()!="-"){
			State oldWinner = winner;
		  	if(currentGame.getState()=="X"){
				winner=State.X;
			} else if(currentGame.getState()=="O"){
				winner=State.O;
			}
			pcSupport.firePropertyChange(WINNER, oldWinner, winner);
		}
	}
	public void makeMove(int row, int col){
		if(square[row][col]==State.NO_STATE && winner == State.NO_STATE){
			if(questions.isEmpty()){ 
				initializeQuestions();
			}
			String oldQuestion = currentQuestion.getQuestion();
			String oldAnswer = currentQuestion.getAnswer();
			currentQuestion=questions.get(0);
			questions.remove(0);
			pcSupport.firePropertyChange(CURRENT_QUESTION, oldQuestion, currentQuestion.getQuestion());
			pcSupport.firePropertyChange(CURRENT_ANSWER, oldAnswer, currentQuestion.getAnswer());
			if(currentPlayer=='X')
				setState(State.X,row,col);
			else
				setState(State.O,row,col);
			currentPlayer=(currentPlayer=='X')?'O':'X';
		}		
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
		for(int i=0;i<3;i++){
			for(int j=0; j<3; j++){
				setState(State.NO_STATE, i, j);
			}
		}
		currentGame=new TicTacToe();
		State oldWinner = winner;
		winner = State.NO_STATE;
		pcSupport.firePropertyChange(WINNER, oldWinner, winner);
	}
   	// allow addition of listeners or observers
   	public void addPropertyChangeListener(PropertyChangeListener listener) {
    	pcSupport.addPropertyChangeListener(listener);
   	}
}