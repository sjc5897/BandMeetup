package com.bandmeetup.model;

import javax.lang.model.element.Element;

/**
 * Class used to represent the Musician Object
 * Language: Java 13
 * Framework: Spring
 * Authors: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/24/20
 * Last Edit: 10/26/20
 */
public class Musician extends User{

    private String name;
    private Status status;                      //Status of the user
    private String instruments;                 //Array list of played instruments
    private String Genre;                       //Array list of preferred Genres
    private String location;                    //Location of user
    private String bio;                         //Their custom Bio

    /**
     * Custom constructor
     */
    public Musician(String email, String name, String pw, String userType, String status, String instruments, String Genre, String location, String bio){
        super(email, pw, userType);
        this.name= name;

        this.status = this.status.valueOf(status);
        this.instruments = instruments;
        this.Genre = Genre;
        this.location = location;
        this.bio = bio;
    }

    public Musician(String email, String name, String status, String instruments, String Genre, String location, String bio) {
        super(email);
        this.name = name;
        this.status = this.status.valueOf(status);;
        this.instruments = instruments;
        this.Genre = Genre;
        this.location = location;
        this.bio = bio;
    }
    

    // Getters
    public Status getStatus() {

        return status;
    }

    public String getStatusSpace(){
        return this.status.toStringSpace();
    }

    public String getGenre() {

        return Genre;
    }

    public String getInstruments() {

        return instruments;
    }

    public String getBio() {
        return bio;
    }
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    //Setters
    public void setGenre(String genre) {
        this.Genre = genre;
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    public void setInstruments(String instruments) {
        this.instruments = instruments;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

   
}

/**
 * Enum repping Musician Status
 */
enum Status {
    Lookingtojoinaband {
        @Override
        public String toStringSpace() {
            return "Looking to join a Band";
        }
    },
    Lookingforabandmember {
        @Override
        public String toStringSpace() {
            return "Looking for a new Band Member";
        }
    },
    Lookingtostartaband {
        @Override
        public String toStringSpace() {
            return "Looking to start a Band";
        }
    },
    Lookingtojam {
        @Override
        public String toStringSpace() {
            return "Just looking to Jam";
        }
    };

    public String toStringSpace() {
        return this.toStringSpace();
    }
}