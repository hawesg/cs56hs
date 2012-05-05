package hssquares;

class TicTacToe{
	private char[][] square = new char[3][3]; 
	private char winner = '-';
	private boolean gameOver = false;
	public TicTacToe(){
		for(int i=0;i<3;i++)
			for(int j=0; j<3; j++)
				square[i][j]='-';
	}
	
	public boolean makeMove(int row, int col, char token, boolean wrongAnswer){
		char buffer;
		if(row>2||col>2||square[row][col]!='.')
			return false;
		buffer=square[row][col];
		square[row][col]=token;
		if(wrongAnswer==true && checkForWin(token)){
			square[row][col]=buffer;
			return false;
		}
		if ( checkForWin(token) )
			winner=token;
		return true;
	}
	public boolean checkForWin(char x){
		// Check to see if the player wins by having 5 on the board
		for(int i=0, total=0;i<3;i++){
			for(int j=0; j<3; j++){
				if(square[i][j]==x)
					total++;
				if(total==5)
					return true;
			}
		}
		// Check Across
		for(int i=0;i<3;i++)
			if(square[i][0]==x && square[i][1]==x && square[i][2]==x)
				return true;
		// Check Up and Down
		for(int i=0;i<3;i++)
			if(square[0][i]==x && square[1][i]==x && square[2][i]==x)
				return true;
		// Check Diagonals
		if(square[0][0]==x && square[1][1]==x && square[2][2]==x)
			return true;
		if(square[2][0]==x && square[1][1]==x && square[0][2]==x)
			return true;
		// Return false otherwise
		return false;
	}
	public char getState(){
		return winner;
	}
	
}