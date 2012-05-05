package hssquares;

public class Player {
	private String name;
	private char token;
	private int playerImage; //this will store a number say from 0-7 (if there are seven pics to choose from) so that instead of passing an image object around there will just be an array of images in the view and this will tell it which one to load 
	private int score;
	public Player(String name, char token, int playerImage){
		this.name=name;
		this.token=token;
		this.playerImage=playerImage;
		score = 0;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public char getToken(){
		return token;
	}
	public void setToken(char token){
		this.token=token;
	}
	public int getPlayerImage(){
		return playerImage;
	}
	public void setPlayerImage(int playerImage){
		this.playerImage=playerImage;
	}
	
}
