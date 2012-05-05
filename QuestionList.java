package hssquares;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionList {
	private ArrayList<Question> questions = new ArrayList();
	public QuestionList(){
		initializeQuestions();
		Collections.shuffle(questions);
	}
	public String getCurrentQuestion(){
		if(questions.isEmpty()) 
			initializeQuestions();
		return questions.get(0).getQuestion();
	}
	public String getCurrentAnswer(){
		return questions.get(0).getQuestion();
	}
	private void initializeQuestions(){
		for(int i=0;i<24;i++)
			questions.add(new Question("question"+i, "correctAnswer" + i, "incorrectAnswer"+i));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}