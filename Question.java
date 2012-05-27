public class Question {
	private String question;
	private String answer;
	private boolean correct;
	public Question(String question, String answer, boolean correct){
		this.question=question;
		this.answer=answer;
		this.correct=correct;
	}
	public boolean checkForWrong(boolean x){
		return x!=correct;
	}
	public String getQuestion(){ 
		return question;
	}
	public String getAnswer(){
		return answer;
	}
}
