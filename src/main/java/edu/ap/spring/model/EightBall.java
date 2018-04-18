package edu.ap.spring.model;
import java.util.Random;

import org.springframework.stereotype.Component;

import edu.ap.spring.jpa.Question;
import edu.ap.spring.jpa.QuestionRepository;

@Component
public class EightBall {

    private QuestionRepository repository;
    
	private String[] answers = {"It is certain", 
								"Yes definitely", 
								"Most likely",
								"Outlook good",
								"Better not tell you now",
								"Cannot predict now",
								"Don't count on it", 
								"Outlook not so good"};
	
	public String getRandomAnswer(String question) {
		Random generator = new Random();
		int randomIndex = generator.nextInt(answers.length);
		String answer = "";
		
		try {
			Question found = repository.findByQuestion(question);
			answer = found.getAnswer();
		}
		catch(Exception ex) {
				if (repository.count() < answers.length) {
					while (AnswerOccupied(randomIndex)) {
						randomIndex = generator.nextInt(answers.length);
					}
					answer = answers[randomIndex];
				} else {
					answer = answers[randomIndex];
				}
		}
		
		return answer;
	}
	
	public boolean AnswerOccupied(int i) {
		String answer = answers[i];
		try {
			Question found = repository.findByAnswer(answer);
			return true; 
		}
		catch(Exception ex) {
			return false; 
		}
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
}
