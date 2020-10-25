package com.bandmeetup.model;

import java.util.ArrayList;

enum Status{
    Lookingtojoinaband,
    Lookingforabandmember,
    Lookingtostartaband,
    Lookingtojam,
}


public class Musician extends User{
    
    private Status status;
    private ArrayList<String> instruments;
    private ArrayList<String> Genre;
    private String location;
    private String bio;

    public Musician(String email, String name, String pw, String userType, Status status, ArrayList<String> instruments, ArrayList<String> Genre, String location, String bio){
        super(email,name, pw, userType);
        this.status = status;
        this.instruments = new ArrayList<String>();
        this.Genre = new ArrayList<String>();
        this.location = location;
        this.bio = bio;

    }
}
