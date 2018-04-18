package edu.ap.spring.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UI implements InitializingBean {
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.setProperty("java.awt.headless", "false");
	}
	
	@Autowired
	EventHandler eventHandler;
	
	private JFrame jFrame;
	private JLabel label1, label2;
	private JTextField QuestionInput;
	private JPanel controlPanel;
    private JButton btnGetAnswer;
	
    public UI() {}
    
    public void setupUI() {
    	jFrame = new JFrame("Spring JFrame");
    	jFrame.setLayout(new FlowLayout());
    	
    	controlPanel = new JPanel();
	    controlPanel.setLayout(new GridLayout(2, 2));

	    label1 = new JLabel("Question : ");
	    
	    QuestionInput = new JTextField(15);
		QuestionInput.setDragEnabled(true);
		
		label2 = new JLabel("(Answer)");
		
		btnGetAnswer = new JButton();
		btnGetAnswer.setText("Get answer");
		btnGetAnswer.setTransferHandler(new TransferHandler("text"));
		btnGetAnswer.addActionListener(eventHandler::whenButtonClicked);
		
		controlPanel.add(label1);
		controlPanel.add(QuestionInput);
		controlPanel.add(label2);
		controlPanel.add(btnGetAnswer);
		
		jFrame.add(controlPanel);
        
		jFrame.setSize(400, 400);
		jFrame.setLocationRelativeTo(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.pack();
		jFrame.setVisible(true);
    }
    
   	public JTextField getQuestionInput() {
		return QuestionInput;
	}
	   	
	public JLabel getLabel2() {
		return label2;
	}

	public JButton getButton() {
        return btnGetAnswer;
    }
}
