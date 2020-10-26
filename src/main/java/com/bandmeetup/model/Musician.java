package com.bandmeetup.model;

import java.util.ArrayList;

/**
 * Class used to represent the Musician Object
 * Language: Java 13
 * Framework: Spring
 * Authors: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/24/20
 * Last Edit: 10/26/20
 */
public class Musician extends User{

    private Status status;                      //Status of the user
    private ArrayList<String> instruments;      //Array list of played instruments
    private ArrayList<String> Genre;            //Array list of preferred Genres
    private String location;                    //Location of user
    private String bio;                         //Their custom Bio

    /**
     * Custom constructor
     */
    public Musician(String email, String name, String pw, String userType, Status status, ArrayList<String> instruments, ArrayList<String> Genre, String location, String bio){
        super(email,name, pw, userType);
        this.status = status;
        this.instruments = new ArrayList<String>();
        this.Genre = new ArrayList<String>();
        this.location = location;
        this.bio = bio;
    }

    // Getters
    public Status getStatus() {
        return status;
    }

    public ArrayList<String> getGenre() {
        return Genre;
    }

    public ArrayList<String> getInstruments() {
        return instruments;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    //Setters
    public void setGenre(ArrayList<String> genre) {
        this.Genre = genre;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setInstruments(ArrayList<String> instruments) {
        this.instruments = instruments;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

/**
 * Enum repping Musician Status
 */
enum Status{
    Lookingtojoinaband,
    Lookingforabandmember,
    Lookingtostartaband,
    Lookingtojam,
}