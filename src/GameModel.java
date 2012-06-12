import java.util.*;
import java.awt.event.*;
import java.beans.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

/** 	
*		Class - GameModel
*		The model that holds the information for the curent game aswell as the logic behind the game.
*		@author Garrett Hawes
*/

public class GameModel implements Runnable{
	//properties for broadcast
	/** 2D array to hold the brodcast names for the squares */
	public static final String [][] SQUARE = { {"Square00", "Square01", "Square02"},{"Square10", "Square11", "Square12"},{"Square20", "Square21", "Square22"}};
	/** Bradcast property for winner */
	public static final String WINNER = "Winner";
	/** Bradcast property for current player */																														
	public static final String CURRENT_PLAYER = "Current Player";	
	/** Bradcast property for current question */																									
	public static final String CURRENT_QUESTION = "Current Question";
	/** Bradcast property for current answer */																									
	public static final String CURRENT_ANSWER = "Current Answer";
	/** Bradcast property for player x's score */																									
	public static final String X_SCORE = "X Score";	
	/** Bradcast property for player o's score */																													
	public static final String O_SCORE = "O Score";	
	/** Bradcast property for player x's name */																												
	public static final String X_NAME = "X Name";
	/** Bradcast property for player o's name */																														
	public static final String O_NAME = "O Name";
	/** Bradcast property for player x's gender */																														
	public static final String X_GENDER = "X Gender";	
	/** Bradcast property for player o's gender */																												
	public static final String O_GENDER = "O Gender";
	/** Bradcast property for dialog */																												
	public static final String DIALOG = "Dialog";			
	/** Property broadcast  */																										
	private PropertyChangeSupport pcSupport = new PropertyChangeSupport(this);																							
	
	/** Random number to seed questions with true or false */
	private Random random = new Random();
	/** Array list of questions  */																															
	private ArrayList<Question> questions = new ArrayList<Question>();	
	/** The current question that is being worked with  */																								
	private Question currentQuestion = new Question("","","",true);																										
	
	/** The current winner of the game  */
	private State winner = State.NO_STATE;	
	/** Array list to hold the positions of the squares that contributed to a win */
	ArrayList<Move> winningSquares = new ArrayList<Move>();
	/** The current square that is waiting for an answer  */																															
	private Move currentMove;
	/** Current player  */																																			
	private State currentPlayer = State.X;	
	/** Player 1 object */																															
	private Player player1 = new Player("Player 1", 'X', 'M');	
	/** Player 2 object */																										
	private Player player2 = new Player("Player 2", 'O', 'F');	
	/** A 2d array States to represent the squares */																										
	private State [][] square=new State[3][3];	
	/** The current tic tac toe game */																														
	private TicTacToe currentGame = new TicTacToe();																													

	/** 	
	*		The run class for the winning animation 
	*/
	public void run() {
		try{
			while(winner!=State.NO_STATE){
				for(int i=0;i<9;i++){
					for(int j=0;j<winningSquares.size();j++){
						Move current = winningSquares.get(j);
						int row = current.getRow();
						int col = current.getCol();
						switch (square[row][col]) {
							case X:
					    		setState(State.X_ON,row,col);
				           		break;
					    	case X_ON:
					    		setState(State.X,row,col);
								break;
					   		case O:
					   			setState(State.O_ON,row,col);
					       		break;
							case O_ON:
								setState(State.O,row,col);
						}					
						if(i==0||i==1){
							Thread.sleep(700);
						}
					}
					if(i!=0&&i!=1){
						Thread.sleep(700);
					}
				}		
				Thread.sleep(400);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		return;
	}

	/** 	
	*		Loop through square and assign an inital setting of NO_STATE and then initialize the questions
	*/
	GameModel(){
		for(int i=0;i<3;i++){
			for(int j=0; j<3; j++){
				square[i][j]=State.NO_STATE;
			}
		}
		initializeQuestions();
	}
	
	/** 	
	*		Check to see if there is a question waiting to be answered
	*		@return 							True if there is no current move and no winner
	*/
	public boolean questionPending(){
		return currentMove!=null||winner!=State.NO_STATE;
	}
	
	/** 	
	*		Change the current player after a turn and notify the view
	*/
	private void changePlayer(){
		State oldPlayer = currentPlayer;
	   	currentPlayer = (currentPlayer==State.O)?State.X:State.O;
	   	pcSupport.firePropertyChange(CURRENT_PLAYER, oldPlayer, currentPlayer);
	}
	
	/** 
	*		Display either circle gets the square or x gets the square
	*		@param x							The player who is given a square 							
	*/
	private void setDialog(State x){
		String dialog = null;
		switch(x){
			case X: 
				dialog="X GETS THE SQUARE";
				break;
			case O: 
				dialog="CIRCLE GETS THE SQUARE";
				break;
		}
		pcSupport.firePropertyChange(DIALOG, null, dialog);
	}
	
	/** 	
	*		Get the oposite player for checking to see if a move is valid
	*		@return								State.X if the current player is O and State.O if the current player is X
	*/
	private State oppositePlayer(){
		return (currentPlayer==State.O)?State.X:State.O;
	}
	
	/** 	
	*		Initialize the questions from three arrays questions, correct answers and incorrect answers. This should hook into a database or serialization library like snakeYAML so that questions can be altered
	*		this loops through the arrays takes a random number 1 or 0 if the number is 1 crearte a question object with the correct answer set otherwise with the incorect answer set
	*/
	private void initializeQuestions(){
		String[] questionArray = {"What popular band was once named Carl and the Passions?","How many rings are there on the Olympic flag?","Who has won the most Oscars?","If you suffered from pogonophobia what would you be afraid of?","What in business terms is the IMF?","Ringo Starr narrates which children's TV series?","Which country grows the most fruit?","In Casablanca what is the name of the nightclub?","How did Alfred Nobel make his money?","What does an alopecia sufferer lack?","If you suffered from Triskaidekaphobia what would you be afraid of?","What would an ichthyologist study?","What type of acid is used in car batteries?","What is the national flower of Japan?","What was the world's first high-level programming language?","Consumption was the former name of which disease?","Which American state is nicknamed The Diamond State?","Who wrote Willie Wonka and the Chocolate Factory?","Who, at USA customs declared, \"Nothing but my genius\"?","Who discovered blood circulation?","Who wrote Brave New World?","What martial arts name means \"gentle way\"?","Duvali, Dushira and Holi are religious days in which religion?","In which industry did John Davidson Rockefeller get rich?","The Mau Mau were terrorists in which country in the late 50's and early 60's?","If you suffer from epistaxis what is wrong?","What animals name translates as \"water horse\"?","Which two metals are alloyed to make pewter?","A polyandric women has more than one...","Barry Allen was the alter ego of which DC comic superhero?","In Norse mythology what is the name of the ultimate battle?","What is the capital of Morocco?","What did Linus Torwalds invent and write?","What did Zambia and Zimbabwe used to be called?","What is the staple food of one third of the world's population?","Ageusia is the loss of which sense?","Crazy Horse and Sitting Bull were born in which US state?","In 1901 who first transmitted radio signals across the Atlantic?","What is the state capital of New Jersey?","Who sold Louisiana to the USA in 1803?","What's missing from ale that's included in beer?","Who is the only American president elected unopposed?","What was the first credit card?","What did Britain swap Havana for with Spain in 1763?","What was Norman Bates' hobby in Psycho?","In Kansas what can a waiter not legally do in a teacup?","Which country has the lowest birth rate?"};
		String[] correctAnswerArray = { "Beach Boys","5","Walt Disney","Beards","International Monetary Fund","Thomas the tank engine","China","Rick's","He invented Dynamite","Hair","The Number 13","Fish","Sulphuric","Chrysanthemum","IBM FORTRAN","Tuberculosis","Delaware","Roald Dahl","Oscar Wilde","William Harvey","Aldous Huxley","Judo","Hindu","Oil","Kenya","Nosebleed","Hippopotamus","Tin and Lead","Husband","The Flash","Ragnarok","Rabat","Linux computer operating system","Rhodesia","Rice","Taste","South Dakota","Marconi","Trenton","Napoleon","Hops","George Washington","Diners Club","Florida","Stuffing birds","Serve wine","Vatican City" };
		String[] incorrectAnswerArray = { "Beatles","7","Jack Nicholson","Spiders","International Monetary Foundation","Sesame Street","The United States","Casa Blanca","He invented penicillin","Vitamin C","Snakes","Insects","Hydrochloric","Japanese Tulip","Basic","Pneumonia","Rhode Island","Richard Stills","Andy Warhol","Richard Anderson","Kurt Vonnegut","Kung Fu","Buddhism","Steel","Brazil","Ear aches","Camel","Tin and Copper","Rib","The Green Lantern","Razternon","Borran","The C programming language","Zambabwe","Potatoes","Smell","North Dakota","Alexandar Graham Bell","Jersey City","The King of Spain","Wheat","Thomas Jefferson","Visa","The Philippines","Rock Collecting","Serve water","Haiti" };
		for(int i=0;i<questionArray.length;i++){
			boolean correct=(random.nextInt(2)==1)?true:false;
			questions.add(new Question(questionArray[i],correctAnswerArray[i],incorrectAnswerArray[i],correct));
		}
		Collections.shuffle(questions);
	}
	
	/** 	
	*		Set the state of one of the squares and broadcast to the view
	*		@param state						The state that you want to change to
	*		@param row							The row of the square you want to change
	*		@param col							The column of the square you want to change
	*/
 	public void setState(State state, int row, int col) {
		State oldState = square[row][col];
	   	this.square[row][col] = state;
	   	pcSupport.firePropertyChange(SQUARE[row][col], oldState, state);
	}
	
	/** 	
	*		Set the winner and broadcast to the view
	*		@param newWinner					The new winner that you would like to set
	*/
	private void setWinner(State newWinner){
		String titleText;
		//Thread t = 
		(new Thread(this)).start();
		//t.start();
		this.winner=newWinner;
		if(winner==State.X){
			titleText="CONGRATS "+ player1.getName();
		}else if(winner==State.O){
			titleText="CONGRATS "+player2.getName();
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
	
	/** 	
	*		Receive an answer for the current question from the controler and attempt the move
	*		@param answer						True for agree false for disagree
	*/
	public void answerQuestion(boolean answer){	
		currentGame.makeMove(currentMove.getRow(),currentMove.getCol(),currentQuestion.checkForWrong(answer));
	}	
	
	/** 	
	*		Receive a click event from the controler, and update the question if it is a valid move
	*		@param row						The row you are trying to click
	*		@param col						The column you are trying to click
	*		@return 						false if move is invalid ture if it is
	*/
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
	}
	
	/** 	
	*		Receive new player information from the controler then broadcast to the view
	*		@param name						The name of the player
	*		@param gender					The gender of the player to be set 'M' for male 'F' for female
	*		@param playerNumber				The player number to be set
	*/
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
	
	/** 	
	*		Get the state of a square for the controler
	*		@param row						The row for the square you want to check 
	*		@param col						The column for the square you want to check
	*		@return 						The state for the square 
	*/
	public State getState(int row,int col){
		return square[row][col];
	}
	 
	/** 	
	*		Restart the game from scratch, reset all the info and broadcast them to the view
	*/
	public void restart(){
		String oldName1 = player1.getName();
		String oldGender1 = player1.getGender();
		String oldName2 = player2.getName();
		String oldGender2 = player2.getGender();
		int oldScore1 = player1.getScore();
		int oldScore2 = player2.getScore();
		State oldPlayer = currentPlayer;
		winningSquares.clear();
	   	currentPlayer = State.X;
		player1 = new Player("Player 1", 'X', 'M');
		player2 = new Player("Player 2", 'O', 'F');
		pcSupport.firePropertyChange(X_NAME, oldName1, player1.getName());
		pcSupport.firePropertyChange(X_GENDER, oldGender1, player1.getGender());
		pcSupport.firePropertyChange(O_NAME, oldName2, player2.getName());
		pcSupport.firePropertyChange(O_GENDER, oldGender2, player2.getGender());
		pcSupport.firePropertyChange(X_SCORE, oldScore1, player1.getScore());
		pcSupport.firePropertyChange(O_SCORE, oldScore2, player2.getScore());
	   	pcSupport.firePropertyChange(CURRENT_PLAYER, oldPlayer, currentPlayer);
		currentGame=new TicTacToe();
	}
	
	/** 	
	*		Play another game without changing the players
	*/
	public void playAgain(){
		winningSquares.clear();
		currentGame=new TicTacToe();
	}
	
	/** 	
	*		Add a property change listener to the model
	*		@param listener					The listener from the view
	*/
   	public void addPropertyChangeListener(PropertyChangeListener listener) {
    	pcSupport.addPropertyChangeListener(listener);
   	}

	/** 	
	*		Class - TicTacToe
	*		The logic behind the underlying game of tic tac toe
	*		@author Garrett Hawes
	*/
	private class TicTacToe{

		/** 	
		*		New game of tic tac toe setting all the squares to NO_STATE and set the winner to NO_STATE
		*/
		public TicTacToe(){
			for(int i=0;i<3;i++){
				for(int j=0; j<3; j++){
					setState(State.NO_STATE,i,j);
				}
			}
			setWinner(State.NO_STATE);
		}	
		
		/** 	
		*		Make a move on the board 
		*		@param row						row in question
		*		@param col						col in question
		*		@param wrongAnswer				if the move to be made is a wrong answer or not 
		*		@return 						false if move is invalid 
		*/
		public boolean makeMove(int row, int col, boolean wrongAnswer){
			if(row>2||col>2||square[row][col]!=State.NO_STATE)
				return false;
			currentMove=null;
			if(wrongAnswer==false){
				setState(currentPlayer,row,col);
				setDialog(currentPlayer);
			} else {
				setState(oppositePlayer(),row,col);
				if(checkForWin(oppositePlayer())==true){
					setState(State.NO_STATE,row,col);
				}else{
					setDialog(oppositePlayer());	
				}	
			} 
			if ( checkForWin(currentPlayer) == true ){
				setWinner(currentPlayer);
			}
			changePlayer();
			return true;
		}
		
		/** 	
		*		Check for a win
		*		@param x						The player to check for a win 		
		*/
		public boolean checkForWin(State x){
			// Check to see if the player wins by having 5 on the board
			int total=0;
			for(int i=0;i<3;i++){
				for(int j=0; j<3; j++){
					if(square[i][j]==x){
						total++;
						winningSquares.add(new Move(i,j));
					}
				}
			}
			if(total>=5){
				return true;
			}else{
				winningSquares.clear();
			}
			// Check Across
			for(int i=0;i<3;i++){	
				if(square[i][0]==x && square[i][1]==x && square[i][2]==x){
				//	setState((x==State.X)?State.X_ON:State.O_ON, i, 0);
					//square[i][0]=(x==State.X)?State.X_On:State.O_ON;
					winningSquares.add(new Move(i,0));
					winningSquares.add(new Move(i,1));
					winningSquares.add(new Move(i,2));
					return true;
				}
			}
			// Check Up and Down
			for(int i=0;i<3;i++){
				if(square[0][i]==x && square[1][i]==x && square[2][i]==x){
					winningSquares.add(new Move(0,i));
					winningSquares.add(new Move(1,i));
					winningSquares.add(new Move(2,i));
					return true;
				}
			}
			// Check Diagonals
			if(square[0][0]==x && square[1][1]==x && square[2][2]==x){
				winningSquares.add(new Move(0,0));
				winningSquares.add(new Move(1,1));
				winningSquares.add(new Move(2,2));
				return true;
			}
			if(square[2][0]==x && square[1][1]==x && square[0][2]==x){
				winningSquares.add(new Move(2,0));
				winningSquares.add(new Move(1,1));
				winningSquares.add(new Move(0,2));
				return true;
			}
			// Return false otherwise
			return false;
		}
	}
}