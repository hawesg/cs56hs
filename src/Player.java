/** 	
*		Class - Player
*		The player objects for the model to store the pertinent data
*		@author Garrett Hawes
*/

public class Player {
	/** Name of player */
	private String name;
	/** Token either X or O */
	private char token;
	/** Gender either M or F */
	private char gender;
	/** Score in dollars */
	private int score=0;
	
	/** 	
	*		Construct Player and set values
	*		@param name							A string for the name
	* 		@param token						char either 'X' or 'O'
	*		@param gender						Gender as char either 'M' or 'F'
	*/
	public Player(String name, char token, char gender){
		this.name=name;
		this.token=token;
		this.gender=gender;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public void incrementScore(){
		score=score+1000;
	}
	
	public void resetScore(){
		score=0;
	}
	
	public int getScore(){
		return score;
	}
	
	public char getToken(){
		return token;
	}
	
	public void setToken(char token){
		this.token=token;
	}
	
	public String getGender(){
		return ""+gender;
	}
	
	public void setGender(char gender){
		this.gender=gender;
	}	
}
