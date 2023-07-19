package com.example.examplejsp.model;

public class File {

    private int id;
    private int user_id;
    private String filename;
    private String filepath;
    private int size;

    public File(String filename, String filepath, int size) {
        this.filename = filename;
        this.filepath = filepath;
        this.size = size;
    }

    public File(int id, int user_id, String filename, String filepath, int size) {
        this.id = id;
        this.user_id = user_id;
        this.filename = filename;
        this.filepath = filepath;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                ", size=" + size +
                '}';
    }
}
