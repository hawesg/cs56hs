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
	public static final String X_SCORE = "X Score";
	public static final String O_SCORE = "O Score";
	public static final String X_NAME = "X Name";
	public static final String O_NAME = "O Name";
	public static final String X_GENDER = "X Gender";
	public static final String O_GENDER = "O Gender";
	public static final String PLAYER_ONE = "Player 1";
	public static final String PLAYER_TWO = "Player 2";
	public static final String DIALOG = "Dialog";
	private PropertyChangeSupport pcSupport = new PropertyChangeSupport(this);
	private State winner = State.NO_STATE;
	//question stuff
	private ArrayList<Question> questions = new ArrayList<Question>();
	private Question currentQuestion = new Question("","",false);
	Random random = new Random();
	private Move currentMove;
	private String dialog = "";
	private int score = 0;
	private State currentPlayer = State.X;
	private Player player1 = new Player("Player 1", 'X', 'M');
	private Player player2 = new Player("Player 2", 'O', 'F');
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
	public boolean questionPending(){
		return currentMove!=null;
	}
	private void changePlayer(){
		State oldPlayer = currentPlayer;
	   	currentPlayer = (currentPlayer==State.O)?State.X:State.O;
	   	pcSupport.firePropertyChange(CURRENT_PLAYER, oldPlayer, currentPlayer);
	}
	private State oppositePlayer(){
		return (currentPlayer==State.O)?State.X:State.O;
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
	}
	private void setWinner(State newWinner){
		String titleText;
		this.winner=newWinner;
		if(winner==State.X){
			titleText="CONGRATULATIONS "+ player1.getName();
		}else if(winner==State.O){
			titleText="CONGRATULATIONS "+player2.getName();
		}else{
			titleText="HOLLYWOOD SQUARES";
		}
		int oldScore;
		pcSupport.firePropertyChange(WINNER, "oldwinner", titleText);
		if(winner==State.X){
			oldScore = player1.getScore();
			player1.incrementScore();
			pcSupport.firePropertyChange(X_SCORE, oldScore, player1.getScore());
		}else if(winner==State.O){
			oldScore = player2.getScore();
			player2.incrementScore();
			pcSupport.firePropertyChange(O_SCORE, oldScore, player2.getScore());
		}
			
	}
	public void answerQuestion(boolean answer){	
		currentGame.makeMove(currentMove.getRow(),currentMove.getCol(),currentQuestion.checkForWrong(answer));
	}	
	public boolean clickSquare(int row, int col){
		if(square[row][col]!=State.NO_STATE){
			return false;
		}
		if(winner != State.NO_STATE){
			return false;
		}
		if(currentMove!=null){
			return false;
		}
		//if((square[row][col]==State.NO_STATE) || (winner == State.NO_STATE) || (currentMove == null)){
			if(questions.isEmpty()){ 
				initializeQuestions();
			}
			String oldQuestion = currentQuestion.getQuestion();
			String oldAnswer = currentQuestion.getAnswer();
			currentQuestion=questions.get(0);
			questions.remove(0);
			pcSupport.firePropertyChange(CURRENT_QUESTION, oldQuestion, currentQuestion.getQuestion());
			pcSupport.firePropertyChange(CURRENT_ANSWER, oldAnswer, currentQuestion.getAnswer());
			currentMove=new Move(row,col);
			return true;
	//	}		
	}
	public void setPlayer(String name, char gender, int playerNumber){
		if(playerNumber==1){
			String oldName = player1.getName();
			String oldGender = player1.getGender();
			player1.setName(name);
			player1.setGender(gender);
			pcSupport.firePropertyChange(X_NAME, oldName, player1.getName());
			pcSupport.firePropertyChange(X_GENDER, oldGender, player1.getGender());			
		}
		else{
			String oldName = player2.getName();
			String oldGender = player2.getGender();
			player2.setName(name);
			player2.setGender(gender);
			pcSupport.firePropertyChange(O_NAME, oldName, player2.getName());
			pcSupport.firePropertyChange(O_GENDER, oldGender, player2.getGender());	
		}
	}
	public State getState(int row,int col){
		return square[row][col];
	}
	public void restart (){
		currentGame=new TicTacToe();
	}
   	// allow addition of listeners or observers
   	public void addPropertyChangeListener(PropertyChangeListener listener) {
    	pcSupport.addPropertyChangeListener(listener);
   	}
	private class TicTacToe{
		private boolean gameOver = false;
		public TicTacToe(){
			for(int i=0;i<3;i++){
				for(int j=0; j<3; j++){
					setState(State.NO_STATE,i,j);
				}
			}
			setWinner(State.NO_STATE);
		}	
		public boolean makeMove(int row, int col, boolean wrongAnswer){
			if(row>2||col>2||square[row][col]!=State.NO_STATE)
				return false;
			currentMove=null;
			if(wrongAnswer==false){
				setState(currentPlayer,row,col);
			} else {
				setState(oppositePlayer(),row,col);
				if(checkForWin(oppositePlayer())==true){
					setState(State.NO_STATE,row,col);
				}		
			} 
			if ( checkForWin(currentPlayer) == true ){
				setWinner(currentPlayer);
			}
			changePlayer();
			return true;
		}
		public boolean checkForWin(State x){
			// Check to see if the player wins by having 5 on the board
			for(int i=0, total=0;i<3;i++){
				for(int j=0; j<3; j++){
					if(square[i][j]==x){
						total++;
					}
					if(total==5){
						return true;
					}
				}
			}
			// Check Across
			for(int i=0;i<3;i++){	
				if(square[i][0]==x && square[i][1]==x && square[i][2]==x){
					return true;
				}
			}
			// Check Up and Down
			for(int i=0;i<3;i++){
				if(square[0][i]==x && square[1][i]==x && square[2][i]==x){
					return true;
				}
			}
			// Check Diagonals
			if(square[0][0]==x && square[1][1]==x && square[2][2]==x){
				return true;
			}
			if(square[2][0]==x && square[1][1]==x && square[0][2]==x){
				return true;
			}
			// Return false otherwise
			return false;
		}
	}
}