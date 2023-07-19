package com.example.examplejsp.model;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

/**
 * Created by forest on 16.12.2014.
 */
public class User {
    private int id;

    private String username;
    private String password;
    private String fullName;
    private String address;
    private String email;
    private String town;

    private String profilePicture;

    public User(int id, String username, String password, String fullName, String address, String email, String town, String profilePicture) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.town = town;
        this.profilePicture = profilePicture;
    }

    public User(String username, String password, String fullName, String address, String email, String town, String profilePicture) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.town = town;
        this.profilePicture = profilePicture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", town='" + town + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}