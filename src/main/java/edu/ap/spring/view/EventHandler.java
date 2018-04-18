package edu.ap.spring.view;

import java.awt.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ap.spring.jpa.Question;
import edu.ap.spring.jpa.QuestionRepository;
import edu.ap.spring.model.EightBall;

@Service
public class EventHandler {
	private UI ui;
    private QuestionRepository repository;
    
    @Autowired
    public void setRepository(QuestionRepository repository) {
    		this.repository = repository;
    }
    
    @Autowired
    public void setUI(UI ui) {
    		this.ui = ui;
    }

    public void whenButtonClicked(ActionEvent actionEvent) {
    		
    	String question = ui.getQuestionInput().getText();
    	

        EightBall eightball = new EightBall();
    	
    	String answer = eightball.getRandomAnswer(question);
    	
    	ui.getLabel2().setText(answer);
    	
		/*String username = ui.getUserName().getText();
		String password = ui.getPassword().getPassword().toString();
		User user = new User(username, password);
		
        repository.save(user);
        
        System.out.println(user.toString() + " saved in repository");
        System.out.println("Find all : ") ;
        repository.findAll().forEach(System.out::println);*/
    	
    }
}