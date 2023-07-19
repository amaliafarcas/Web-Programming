package com.example.examplejsp.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private Statement stmt;

    public Database() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/web", "root", "rootpw");
            stmt = con.createStatement();
            System.out.println("connected " + stmt.toString());
        } catch(Exception ex) {
            System.out.println("Error during connect: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public String register(String username, String password, String fullName, String email, String address, String town, String profilePicture) {
        String result = "error";
        System.out.println("register" + username + " " + password);
        try {
            System.out.println("try");
            String sql = "INSERT INTO users (username, password, name, email, address, hometown, picture) " +
                    "VALUES ('" + username + "', '" + password + "', '" + fullName + "', '" + email + "', '" + address + "', '" + town + "', '" + profilePicture + "')";

            int rowsAffected =stmt.executeUpdate(sql);

            System.out.println(rowsAffected);
             /*tmt.executeUpdate("INSERT INTO users VALUES ('" + username + "', '" + password + "', '" + fullName + "', '" + email + "', '" + address + "', '" + town + "', '" + profilePicture + "')");
*/
            if (rowsAffected > 0) {
                result = "success";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<User> getUsersSearch(String search){

        List<User> searchResults = new ArrayList<>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM users WHERE " +
                    "username LIKE '%" + search + "%' OR " +
                    "name LIKE '%" + search + "%' OR " +
                    "address LIKE '%" + search + "%' OR " +
                    "email LIKE '%" + search + "%' OR " +
                    "hometown LIKE '%" + search + "%'"
            );

            User user = null;
            while (rs.next()) {
                // Retrieve the column values from the ResultSet
                String retrievedUsername = rs.getString("username");
                String fullName = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String town = rs.getString("hometown");
                String profilePicture = rs.getString("picture");

                // Create a User object with the retrieved data
                user = new User(retrievedUsername, "", fullName, address, email, town, profilePicture);
                //System.out.println(user);
                searchResults.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("size " + searchResults.size());
        return searchResults;

    }


    public boolean updateUserProfile(String username, String fullName, String email, String address, String town, String profilePicture) {
        boolean success = false;

        try {
                String sql = "UPDATE users SET name='" + fullName + "', email='" + email + "', address='" + address + "', hometown='" + town + "', picture= '" + profilePicture + "' WHERE username='" + username + "'";

                int rowsAffected =stmt.executeUpdate(sql);

                if (rowsAffected > 0) {
                    success = true;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public int getCountUserFiles(User user) {
        ResultSet rs;
        int count = 0;
        try {
            String query = "SELECT COUNT(*) FROM files WHERE user_id LIKE '%" + user.getId() + "%'";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    public List<File> getUserFiles(User user, int recordsPerPage, int currentPage){

        List<File> searchResults = new ArrayList<>();
        ResultSet rs;
        try {
            int totalRows = getCountUserFiles(user);

            int offset = (currentPage - 1) * recordsPerPage;
            //System.out.println("offset "+ offset + recordsPerPage);
            String query = "SELECT * FROM files WHERE user_id = '" + user.getId() + "%' LIMIT " + offset + ", " + recordsPerPage;
            rs = stmt.executeQuery(query);


            File file = null;
            while (rs.next()) {
                //System.out.println("while");
                // Retrieve the column values from the ResultSet
                String filename = rs.getString("filename");
                String filepath = rs.getString("filepath");
                int size = rs.getInt("size");

                // Create a User object with the retrieved data
                file = new File(filename, filepath, size);
                //System.out.println(file);
                searchResults.add(file);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("size " + searchResults.size());
        return searchResults;
    }

    public boolean addQuestion(String[] text, String[] correctAnswer, String[] wrongAnswer) {
        boolean result = false;

        try {
            int rowsAffected = 0;
            for (int i = 0; i < text.length; i++) {
                String sql = "INSERT INTO question (text, correctAnswer, wrongAnswer) " +
                        "VALUES ('" + text[i] + "', '" + correctAnswer[i] + "', '" + wrongAnswer[i] + "')";
                rowsAffected += stmt.executeUpdate(sql);
            }

            System.out.println(rowsAffected);

            if (rowsAffected == text.length) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Question> getQuestions(){

        List<Question> searchResults = new ArrayList<>();
        ResultSet rs;
        try {
            String query = "SELECT * FROM question";
            rs = stmt.executeQuery(query);

            Question product = null;
            while (rs.next()) {

                int id = rs.getInt("id");
                String text = rs.getString("text");
                String correctAnswer = rs.getString("correctAnswer");
                String wrongAnswer = rs.getString("wrongAnswer");

                product = new Question(id, text, correctAnswer, wrongAnswer);
                searchResults.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("size " + searchResults.size());
        return searchResults;
    }

    public String getQuestionsCodes(String[] text){
        StringBuilder bld = new StringBuilder();
        ResultSet rs;
        try {
            for (String s : text) {
                String query = "SELECT * FROM question WHERE text = '" + s + "' LIMIT 1";
                rs = stmt.executeQuery(query);
                while(rs.next()) {
                    int id = rs.getInt("id");
                    bld.append(id);
                    bld.append(" ");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bld.toString();
    }

    public boolean makeQuiz(String title, String[] text, String[] correctAnswer, String[] wrongAnswer){
        boolean result = addQuestion(text, correctAnswer, wrongAnswer);
        if(!result){
            return false;
        }

        result = false;
        try {
            String questions = getQuestionsCodes(text);
            String sql = "INSERT INTO quiz (title, questions) " +
                        "VALUES ('" + title + "', '" + questions + "')";

            int rowsAffected = stmt.executeUpdate(sql);

            //System.out.println(rowsAffected);

            if (rowsAffected == text.length) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

}