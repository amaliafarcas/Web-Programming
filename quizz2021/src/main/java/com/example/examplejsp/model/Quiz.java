package com.example.examplejsp.model;

public class Quiz {
    private int id;
    private String title;
    private String listOfQuestions;

    public Quiz(int id, String title, String listOfQuestions) {
        this.id = id;
        this.title = title;
        this.listOfQuestions = listOfQuestions;
    }

    public Quiz(String title, String listOfQuestions) {
        this.title = title;
        this.listOfQuestions = listOfQuestions;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getListOfQuestions() {
        return listOfQuestions;
    }
}
