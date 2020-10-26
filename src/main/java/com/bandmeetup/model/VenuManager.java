package com.bandmeetup.model;
public class VenuManager extends User{

    private String location;
    private String description;

    public VenuManager(String email, String name, String pw, String userType, String location, String description){
        super(email, pw, userType);
        this.location = location;
        this.description = description;

    }
}
