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
	
	//question stuff
	private Random random = new Random();
	private ArrayList<Question> questions = new ArrayList<Question>();
	private Question currentQuestion = new Question("","","",true);
	
	private State winner = State.NO_STATE;
	private Move currentMove;
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
		return currentMove!=null||winner!=State.NO_STATE;
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
		String[] questionArray = {"What popular band was once named Carl and the Passions?","How many rings are there on the Olympic flag?","Who has won the most Oscars?","If you suffered from pogonophobia what would you be afraid of?","What in business terms is the IMF?","Ringo Starr narrates which children's TV series?","Which country grows the most fruit?","In Casablanca what is the name of the nightclub?","How did Alfred Nobel make his money?","What does an alopecia sufferer lack?","If you suffered from Triskaidekaphobia what would you be afraid of?","What would an ichthyologist study?","What type of acid is used in car batteries?","What is the national flower of Japan?","What was the world's first high-level programming language?","Consumption was the former name of which disease?","Which American state is nicknamed The Diamond State?","Who wrote Willie Wonka and the Chocolate Factory?","Who, at USA customs declared, \"Nothing but my genius\"?","Who discovered blood circulation?","Who wrote Brave New World?","What martial arts name means \"gentle way\"?","Duvali, Dushira and Holi are religious days in which religion?","In which industry did John Davidson Rockefeller get rich?","The Mau Mau were terrorists in which country in the late 50's and early 60's?","If you suffer from epistaxis what is wrong?","What animals name translates as \"water horse\"?","Which two metals are alloyed to make pewter?","A polyandric women has more than one...","Barry Allen was the alter ego of which DC comic superhero?","In Norse mythology what is the name of the ultimate battle?","What is the capital of Morocco?","What did Linus Torwalds invent and write?","What did Zambia and Zimbabwe used to be called?","What is the staple food of one third of the world's population?","Ageusia is the loss of which sense?","Crazy Horse and Sitting Bull were born in which US state?","In 1901 who first transmitted radio signals across the Atlantic?","What is the state capital of New Jersey?","Who sold Louisiana to the USA in 1803?","What's missing from ale that's included in beer?","Who is the only American president elected unopposed?","What was the first credit card?","What did Britain swap Havana for with Spain in 1763?","What was Norman Bates' hobby in Psycho?","In Kansas what can a waiter not legally do in a teacup?","Which country has the lowest birth rate?"};
		String[] correctAnswerArray = { "Beach Boys","5","Walt Disney","Beards","International Monetary Fund","Thomas the tank engine","China","Rick's","He invented Dynamite","Hair","The Number 13","Fish","Sulphuric","Chrysanthemum","IBM FORTRAN","Tuberculosis","Delaware","Roald Dahl","Oscar Wilde","William Harvey","Aldous Huxley","Judo","Hindu","Oil","Kenya","Nosebleed","Hippopotamus","Tin and Lead","Husband","The Flash","Ragnarok","Rabat","Linux computer operating system","Rhodesia","Rice","Taste","South Dakota","Marconi","Trenton","Napoleon","Hops","George Washington","Diners Club","Florida","Stuffing birds","Serve wine","Vatican City" };
		String[] incorrectAnswerArray = { "Beatles","7","Jack Nicholson","Spiders","International Monetary Foundation","Sesame Street","The United States","Casa Blanca","He invented penicillin","Vitamin C","Snakes","Insects","Hydrochloric","Japanese Tulip","Basic","Pneumonia","Rhode Island","Richard Stills","Andy Warhol","Richard Anderson","Kurt Vonnegut","Kung Fu","Buddhism","Steel","Brazil","Ear aches","Camel","Tin and Copper","Rib","The Green Lantern","Razternon","Borran","The C programming language","Zambabwe","Potatoes","Smell","North Dakota","Alexandar Graham Bell","Jersey City","The King of Spain","Wheat","Thomas Jefferson","Visa","The Philippines","Rock Collecting","Serve water","Haiti" };
		for(int i=0;i<questionArray.length;i++){
			boolean correct=(random.nextInt(2)==1)?true:false;
			questions.add(new Question(questionArray[i],correctAnswerArray[i],incorrectAnswerArray[i],correct));
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
	
	public void restart(){
		String oldName1 = player1.getName();
		String oldGender1 = player1.getGender();
		String oldName2 = player2.getName();
		String oldGender2 = player2.getGender();
		int oldScore1 = player1.getScore();
		int oldScore2 = player2.getScore();
		State oldPlayer = currentPlayer;
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
	
	public void playAgain(){
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