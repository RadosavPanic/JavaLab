package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener {
    
    JFrame frame = new JFrame();
    
    JLabel lblCorrect = new JLabel();
    JLabel lblIncorrect = new JLabel();
    JLabel lblQuestion = new JLabel();
    
    JButton btnChoiceA = new JButton();
    JButton btnChoiceB = new JButton();
    JButton btnChoiceC = new JButton();
    JButton btnChoiceD = new JButton();
    JButton btnNextQuestion = new JButton("Next");        
    JButton btnAboutQuiz = new JButton("About quiz");

    Engine engine = new Engine();
    
    int choice = -1;

    public GUI() {    	
    	
        frame.setTitle("Quiz Smart Electron");        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(650, 540);        
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(240, 240, 240));        
        frame.setLayout(null);
        frame.setResizable(false);
        
        lblCorrect.setBounds(0, 10, 325, 30);
        lblCorrect.setBackground(new Color(200, 255, 200));
        lblCorrect.setForeground(new Color(25, 150, 0));
        lblCorrect.setHorizontalAlignment(SwingConstants.CENTER);
        lblCorrect.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblCorrect.setOpaque(true);
        lblCorrect.setText("Correct answers: " + engine.getCorrectAnswers());
        
        lblIncorrect.setBounds(325, 10, 325, 30);
        lblIncorrect.setBackground(new Color(255, 200, 200));
        lblIncorrect.setForeground(new Color(170, 0, 0));
        lblIncorrect.setHorizontalAlignment(SwingConstants.CENTER);
        lblIncorrect.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblIncorrect.setOpaque(true);        
        lblIncorrect.setText("Incorrect answers: " + engine.getIncorrectAnswers());

        lblQuestion.setBounds(0, 60, 650, 40);
        lblQuestion.setForeground(new Color(0, 0, 0));
        lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);        
        lblQuestion.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        	        
        btnChoiceA.setBounds(60, 150, 220, 40);
        btnChoiceA.setFont(new Font("Calibri", Font.PLAIN, 14));
        btnChoiceA.setHorizontalAlignment(2);
        btnChoiceA.setFocusable(false);
		
        btnChoiceB.setBounds(340, 150, 220, 40);
        btnChoiceB.setFont(new Font("Calibri", Font.PLAIN, 14));
        btnChoiceB.setHorizontalAlignment(2);
        btnChoiceB.setFocusable(false);
        
        btnChoiceC.setBounds(60, 220, 220, 40);
        btnChoiceC.setFont(new Font("Calibri", Font.PLAIN, 14));
        btnChoiceC.setHorizontalAlignment(2);
        btnChoiceC.setFocusable(false);

        btnChoiceD.setBounds(340, 220, 220, 40);
        btnChoiceD.setFont(new Font("Calibri", Font.PLAIN, 14));
        btnChoiceD.setHorizontalAlignment(2);
        btnChoiceD.setFocusable(false);
        
        btnNextQuestion.setBounds(250, 310, 120, 40);
        btnNextQuestion.setFont(new Font("Calibri", Font.PLAIN, 14));
        btnNextQuestion.setFocusable(false);
        btnNextQuestion.setEnabled(false);
        
        btnAboutQuiz.setBounds(525, 445, 100, 40);	
        btnAboutQuiz.setFont(new Font("Calibri", Font.PLAIN, 14));
        btnAboutQuiz.setFocusable(false);
        
        // 
        frame.add(lblQuestion); frame.add(lblCorrect); frame.add(lblIncorrect);
        frame.add(btnChoiceA); frame.add(btnChoiceB); frame.add(btnChoiceC); frame.add(btnChoiceD);         
        frame.add(btnNextQuestion); frame.add(btnAboutQuiz);
         
        btnChoiceA.addActionListener(this); btnChoiceB.addActionListener(this); btnChoiceC.addActionListener(this);
        btnChoiceD.addActionListener(this); btnNextQuestion.addActionListener(this); btnAboutQuiz.addActionListener(this);
        
        frame.setVisible(true);
        
        displayQuizInfo();

        engine.postaviPitanje(lblQuestion, btnChoiceA, btnChoiceB, btnChoiceC, btnChoiceD, btnNextQuestion); 
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == btnChoiceA) choice = 0;
        else if (src == btnChoiceB) choice = 1;
        else if (src == btnChoiceC) choice = 2;
        else if (src == btnChoiceD) choice = 3;
        
        if(src == btnChoiceA || src == btnChoiceB || src == btnChoiceC || src == btnChoiceD) {
        	
        	JButton[] btns = { btnChoiceA, btnChoiceB, btnChoiceC, btnChoiceD }; 
        	
        	engine.displayResult(btns, choice);
                        
        	engine.checkAnswer(choice);
        	        
        	lblCorrect.setText("Correct answers: " + engine.getCorrectAnswers());
        	lblIncorrect.setText("Incorrect answers: " + engine.getIncorrectAnswers());
        
        	btnNextQuestion.setEnabled(true); 
        }
        
        if (src == btnNextQuestion) {                                               
        	choice = -1;
            
            JButton[] btns = { btnChoiceA, btnChoiceB, btnChoiceC, btnChoiceD };
            engine.resetButtons(btns);

            if (engine.getQuestionIndex() < engine.totalQuestions()) { 
            	engine.postaviPitanje(lblQuestion, btnChoiceA, btnChoiceB, btnChoiceC, btnChoiceD, btnNextQuestion);
            } else { 
                lblQuestion.setText("End of the quiz!");
                btnChoiceA.setEnabled(false); btnChoiceB.setEnabled(false); btnChoiceC.setEnabled(false);                
                btnChoiceD.setEnabled(false); btnNextQuestion.setEnabled(false);
                                
                displayQuizEnd();
            }
        }
        
        if (src == btnAboutQuiz) displayQuizInfo();      
    }
    
    public void displayQuizInfo() {
    	String message = """
    		    Welcome to the *Smart Electron* quiz!

    		    • The quiz consists of 10 physics-related questions.
    		    • To win, you need to answer all 10 questions correctly.
    		    • There is no time limit for completing the quiz.
    		    • There is also no time limit for answering individual questions.

    		    Good luck!
    		    """;

            JOptionPane.showMessageDialog(null, message, "Quiz info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void displayQuizEnd() {
        String message;
        String title = "End of the quiz";

        if (engine.getCorrectAnswers() == engine.totalQuestions()) {
        	message = "Congratulations, you're a true genius!\n\nPlay again?";
        } else { 
        	message = "Just a bit more to win!\n" +
        	          "Correct answers: " + engine.getCorrectAnswers() +
        	          "\nIncorrect answers: " + engine.getIncorrectAnswers() +
        	          "\n\nPlay again?";

        }

        int izbor = JOptionPane.showConfirmDialog(frame, message, title, JOptionPane.YES_NO_OPTION);

        if (izbor == JOptionPane.YES_OPTION) reset();
        else System.exit(0);
    }

    public void reset() {
    	engine.reset();
    	choice = -1;
         
        btnChoiceA.setEnabled(true); btnChoiceB.setEnabled(true);
        btnChoiceC.setEnabled(true); btnChoiceD.setEnabled(true);
        btnNextQuestion.setEnabled(true);
        
        lblCorrect.setText("Correct answers: " + engine.getCorrectAnswers());
    	lblIncorrect.setText("Incorrect answers: " + engine.getIncorrectAnswers());
        
        engine.postaviPitanje(lblQuestion, btnChoiceA, btnChoiceB, btnChoiceC, btnChoiceD, btnNextQuestion);
    }    
}