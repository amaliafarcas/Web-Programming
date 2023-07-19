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

    public User getUserByUsername(String username){
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select * from users where username='"+username+"'");
            User user = null;
            if (rs.next()) {
                // Retrieve the column values from the ResultSet
                int id = rs.getInt("id");
                String retrievedUsername = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String town = rs.getString("hometown");
                String profilePicture = rs.getString("picture");

                // Create a User object with the retrieved data
                user = new User(id, retrievedUsername, password, fullName, address, email, town, profilePicture);
                //System.out.println(email);
            }

            if(user!=null){
                return user;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getProductsSearch(String search){

        List<Product> searchResults = new ArrayList<>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM products WHERE " +
                    "name LIKE '%" + search +  "%'"
            );

            Product product = null;
            while (rs.next()) {
                int productCode = rs.getInt("id");
                String productName = rs.getString("name");
                String productDescription = rs.getString("description");

                product = new Product(productCode, productName, productDescription);
                searchResults.add(product);
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

    public List<Product> getProducts(){

        List<Product> searchResults = new ArrayList<>();
        ResultSet rs;
        try {
            String query = "SELECT * FROM products";
            rs = stmt.executeQuery(query);

            Product product = null;
            while (rs.next()) {

                int productCode = rs.getInt("id");
                String productName = rs.getString("name");
                String productDescription = rs.getString("description");

                product = new Product(productCode, productName, productDescription);
                searchResults.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("size " + searchResults.size());
        return searchResults;
    }

    public boolean addProduct(String[] productName, String[] productDescription) {
        boolean result = false;

        try {
            int rowsAffected = 0;
            for (int i = 0; i < productName.length; i++) {
                String sql = "INSERT INTO products (name, description) " +
                        "VALUES ('" + productName[i] + "', '" + productDescription[i] + "')";
                rowsAffected += stmt.executeUpdate(sql);
            }

            System.out.println(rowsAffected);

            if (rowsAffected == productName.length) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean addFile(int userId, String[] filenames, String[] filepaths, String[] sizes) {
        boolean result = false;

        try {
            int rowsAffected = 0;
            for (int i = 0; i < filenames.length; i++) {
                String sql = "INSERT INTO files (user_id, filename, filepath, size) " +
                        "VALUES ('" + userId + "', '" + filenames[i] + "', '" + filepaths[i] + "', " + sizes[i] + ")";
                rowsAffected += stmt.executeUpdate(sql);
            }

            System.out.println(rowsAffected);

            if (rowsAffected == filenames.length) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Order> getUserOrders(String username) {
        List<Order> searchResults = new ArrayList<>();
        ResultSet rs;
        try {
            String query = "SELECT * FROM orders WHERE " +
                    "user = '" + username +  "'";
            rs = stmt.executeQuery(query);

            Order order = null;
            while (rs.next()) {

                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");

                order = new Order(productId, quantity);
                searchResults.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("size " + searchResults.size());
        return searchResults;
    }

    public boolean placeOrder(List<Order> cart) {
        boolean result = false;
        try {
            int rowsAffected = 0;
            for (Order o : cart) {
                String sql = "INSERT INTO orders (user, product_id, quantity) " +
                        "VALUES ('" + o.getUsername() + "', " + o.getProductId() + ", " + o.getQuantity() + ")";
                rowsAffected += stmt.executeUpdate(sql);
            }

            System.out.println(rowsAffected);

            if (rowsAffected == cart.size()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}