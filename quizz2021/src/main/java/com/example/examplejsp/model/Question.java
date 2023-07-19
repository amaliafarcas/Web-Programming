package com.example.examplejsp.model;

public class Question {
    private int id;
    private String text;
    private String correctAnswer;
    private String wrongAnswer;

    public Question(int id, String text, String correctAnswer, String wrongAnswer) {
        this.id = id;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer = wrongAnswer;
    }

    public Question(String text, String correctAnswer, String wrongAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer = wrongAnswer;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getWrongAnswer() {
        return wrongAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", wrongAnswer='" + wrongAnswer + '\'' +
                '}';
    }
}
