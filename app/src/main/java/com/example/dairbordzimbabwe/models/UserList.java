package com.example.dairbordzimbabwe.models;

import java.util.List;

public class UserList {

    private List<User> users;

    public UserList(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
