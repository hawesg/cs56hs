public class Question {
	private String question;
	private String answer;
	private boolean correct;
	public Question(String question, String answer, boolean correct){
		this.question=question;
		this.answer=answer;
		this.correct=correct;
	}
	public boolean isCorrect(){
		return correct;
	}
	public String getQuestion(){ 
		return question;
	}
	public String getAnswer(){
		//String answer = (state==1)?correctAnswer:incorrectAnswer; //return the answer according to the state
		return answer;
	}
}
