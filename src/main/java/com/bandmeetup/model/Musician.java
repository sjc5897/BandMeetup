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

    public Musician() {
        super();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Musician(String email, String name, String pw, String userType, Status status, ArrayList<String> instruments, ArrayList<String> Genre, String location, String bio){
        super(email,name, pw, userType);
        this.status = status;
        this.instruments = new ArrayList<String>();
        this.Genre = new ArrayList<String>();
        this.location = location;
        this.bio = bio;

    }

    public ArrayList<String> getGenre() {
        return Genre;
    }

    public void setGenre(ArrayList<String> genre) {
        Genre = genre;
    }


    public ArrayList<String> getInstruments() {
        return instruments;
    }

    public void setInstruments(ArrayList<String> instruments) {
        this.instruments = instruments;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
