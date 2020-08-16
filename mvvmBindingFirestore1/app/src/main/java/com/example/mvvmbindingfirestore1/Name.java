package com.example.mvvmbindingfirestore1;

public class Name {

    private int id;
    private String username;

    public Name(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public Name() {
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
