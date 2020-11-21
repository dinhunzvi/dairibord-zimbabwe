package com.example.dairbordzimbabwe.models;

import java.util.List;

public class UsersResponse {

    private List<User> users;

    public UsersResponse(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
