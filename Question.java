public class Question {
	private String question;
	private String correctAnswer;
	private String incorrectAnswer;
	private boolean correct;
	public Question(String question, String correctAnswer, String incorrectAnswer, int answerSeed){
		this.question=question;
		this.correctAnswer=correctAnswer+".";
		this.incorrectAnswer=incorrectAnswer;
		this.correct=(answerSeed==1)?true:false;
	}
	public boolean checkForWrong(boolean x){
		return x!=correct;
	}
	public String getQuestion(){ 
		return question; //test
	}
	public String getAnswer(){
		return (correct==true)?correctAnswer:incorrectAnswer;
	}
}
