package edu.ap.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import edu.ap.spring.jpa.*;
import edu.ap.spring.model.EightBall;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTest {
    
	@Autowired
	private EightBall eightBall;

	@Autowired
	private QuestionRepository repository;

	@Autowired
	private Singleton singleton;	
	
    @Test
    public void testQuestion() {
    		String answer = eightBall.getRandomAnswer("kysymykset1");
    		
        if(Arrays.asList(eightBall.getAnswers()).contains(answer)) {
        		singleton.setGrade(3, "testQuestion");
        }
    }
    
    @Test
    public void testRepo() {
    		Question question = new Question("kysymykset2", "vastauksineen2");
    		repository.save(question);
    		Question found = repository.findByQuestion("kysymykset2");
    		
        if(found.getAnswer().contains("vastauksineen2")) {
        		singleton.setGrade(2, "testRepo");
        }
    }
    
    @Test
    public void testSameAnswer() {
    		String answer1 = eightBall.getRandomAnswer("kysymykset3");
    		String answer2 = eightBall.getRandomAnswer("kysymykset3");
    		String answer3 = eightBall.getRandomAnswer("kysymykset3");
    		
	    	if(answer1.equals(answer2) && answer2.equals(answer3)) {
	    		singleton.setGrade(4, "testSameAnswer");
	    }
    }
    
    @Test
    public void testDistribution() {
    		String[] original_answers = eightBall.getAnswers();
    		String[] answers = new String[original_answers.length];
    		for(int i = 0; i < original_answers.length; i++) {
    			answers[i] = eightBall.getRandomAnswer("selkomukauttaja" + i);
    		}
    		Arrays.sort(answers);
    		Arrays.sort(original_answers);
    		
	    	if(Arrays.equals(answers, original_answers)) {
	    		singleton.setGrade(4, "testDistribution");
	    	}
    }
    
    @Test
    public void getFileChanged() {
    		String path = SpringTest.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    		File f = new File(path.substring(0, path.indexOf("/target")) + "/src/test/java/edu/ap/spring/test/SpringTest.java");
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    		System.out.println("SpringTest last modified : " + sdf.format(f.lastModified()));
    }
}
