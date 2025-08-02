package main;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class Engine {
    private final Questions questions;
    private int questionIndex = 0;
    private int correctAnswers = 0;
    private int incorrectAnswers = 0;

    public Engine() {
        this.questions = new Questions();    
    }

    public void postaviPitanje(JLabel lbl, JButton btnA, JButton btnB, JButton btnC, JButton btnD, JButton btnNextQuestion) {
        lbl.setText(questions.getQuestions()[questionIndex]);
        
        String[] answerChoices = questions.getAnswers()[questionIndex];
        
        btnA.setText("a) " + answerChoices[0]);
        btnB.setText("b) " + answerChoices[1]);
        btnC.setText("c) " + answerChoices[2]);
        btnD.setText("d) " + answerChoices[3]);
        
        btnNextQuestion.setEnabled(false);
    }

    public void checkAnswer(int choice) {
        boolean isCorrect = questions.getCorrectAnswers()[questionIndex] == choice;
        
        if (isCorrect) correctAnswers++;
        else incorrectAnswers++;
                
        questionIndex++;
    }
    
    public void displayResult(JButton[] btns, int choice) {
        int correctIndex = questions.getCorrectAnswers()[questionIndex];

        for (int i = 0; i < btns.length; i++) { 
            btns[i].setEnabled(false);
            if (i == correctIndex)
                btns[i].setBackground(Color.GREEN);                
            else if (i == choice)
                btns[i].setBackground(Color.RED);      
                     
            btns[i].setFont(new Font("Calibri", Font.BOLD, 14));             
        }
    }
    
    public void resetButtons(JButton[] btns) {
        for (JButton btn : btns) {
            btn.setEnabled(true);
            btn.setBackground(null);
            btn.setFont(new Font("Calibri", Font.PLAIN, 14));
        }
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void reset() {
        questionIndex = 0;
        correctAnswers = 0;
        incorrectAnswers = 0;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public int totalQuestions() {
        return questions.getQuestions().length;
    }
}
