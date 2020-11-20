package com.example.dairbordzimbabwe.models;

public class User {

    private int user_id;
    private String first_name, email, last_name, username;

    public User(int user_id, String first_name, String email, String last_name, String username ) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.email = email;
        this.last_name = last_name;
        this.username = username;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getEmail() {
        return email;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUsername() {
        return username;
    }

}
