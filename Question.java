public class Question {
	private String question;
	private String correctAnswer;
	private String incorrectAnswer;
	private int state; //1 is correct 2 is incorrect
	public Question(String question, String correctAnswer, String incorrectAnswer){
		this.question=question;
		this.correctAnswer=correctAnswer;
		this.incorrectAnswer=incorrectAnswer;
		state=(1 + (int)(Math.random() * ((100 - 1) + 1))%2); // Set a random number 1 or 2;
	}
	public int getState(){
		return state;
	}
	public String getQuestion(){
		return question;
	}
	public String getAnswer(){
		String answer = (state==1)?correctAnswer:incorrectAnswer; //return the answer according to the state
		return answer;
	}
}
