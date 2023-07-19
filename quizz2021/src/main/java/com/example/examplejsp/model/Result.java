package com.example.examplejsp.model;

public class Result {
    private int id;
    private String username;
    private int result;

    public Result(int id, String username, int result) {
        this.id = id;
        this.username = username;
        this.result = result;
    }

    public Result(String username, int result) {
        this.username = username;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", result=" + result +
                '}';
    }
}
