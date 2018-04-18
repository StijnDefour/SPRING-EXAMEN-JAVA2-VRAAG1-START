package edu.ap.spring.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
	
	@Id
	@GeneratedValue
	private Long id;

    private String question, answer;

	public Question(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
    
	@Override
	public String toString() {
		return "Question [question=" + question + ", answer=" + answer + "]";
	}
}
