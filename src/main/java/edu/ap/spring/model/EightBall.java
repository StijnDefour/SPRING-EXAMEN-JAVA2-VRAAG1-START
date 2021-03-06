package edu.ap.spring.model;
import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.ap.spring.jpa.Question;
import edu.ap.spring.jpa.QuestionRepository;

@Component
public class EightBall {

    private QuestionRepository repository;
    
    @Autowired
    public void setRepository(QuestionRepository repository) {
    		this.repository = repository;
    }
    
	private String[] answers = {"It is certain", 			//
								"Yes definitely", 			//
								"Most likely",       		//
								"Outlook good",				//
								"Better not tell you now",	//	
								"Cannot predict now",		//
								"Don't count on it", 		//
								"Outlook not so good"};		//
	
	public String getRandomAnswer(String question) {		
		
		Random generator = new Random();
		int randomIndex = generator.nextInt(answers.length);
		String answer = "";
		
		Question found = new Question("", "");
		boolean gevonden = false;
		
		try {
			found = repository.findByQuestion(question);
			gevonden = true;
			return found.getAnswer();
		}
		catch(Exception ex) {}
		
		if (gevonden) {
			Long cnt = 0L;
			try {
				cnt = repository.count();
			}
			catch(Exception ex) {}
			
			
			if (cnt < answers.length) {
				while (this.AnswerOccupied(randomIndex) == true) {
					randomIndex = generator.nextInt(answers.length);
				}
			} 
			
			answer = answers[randomIndex];
			
			Question saveQuestion = new Question(question, answer);
			repository.save(saveQuestion);
		}
		
		return answer;
	}
	
	private boolean AnswerOccupied(int i) {
		String testanswer = answers[i];
		
		Iterable<Question> questions = repository.findAll();
		for (Question q : questions) {
		    if (q.getAnswer() == testanswer) {
		    	return true;
		    }
		}	
		
		return false;
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
}
