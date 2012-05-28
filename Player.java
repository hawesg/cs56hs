public class Player {
	private String name;
	private char token;
	private char gender;
	private int score=0;
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
		score++;
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
