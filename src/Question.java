/** 	
*		Class - Question
*		Object to store a question
*		@author Garrett Hawes
*/

public class Question {
	private String question;
	private String correctAnswer;
	private String incorrectAnswer;
	private boolean correct;
	
	/** 	
	*		Construct a question
	*		@param question							Question 
	* 		@param correctAnswer					Right answer
	*		@param incorrectAnswer					Wrong answer
	*		@param correct							Set the question to true or false
	*/
	public Question(String question, String correctAnswer, String incorrectAnswer, boolean correct){
		this.question=question;
		this.correctAnswer=correctAnswer+".";
		this.incorrectAnswer=incorrectAnswer;
		this.correct=correct;
	}
	
	/** 	
	*		Check to see if the players answer is wrong
	*		@param x								The answer provided by the player true for agree false for disagree
	* 		@return 								True if the player was right false if not
	*/
	public boolean checkForWrong(boolean x){
		return x!=correct;
	}
	
	/** 	
	*		Provide the question
	*		@return 							Question
	*/
	public String getQuestion(){ 
		return question;
	}
	
	/** 	
	*		Provide the answer
	*		@return 							Answer
	*/
	public String getAnswer(){
		return (correct==true)?correctAnswer:incorrectAnswer;
	}
}
