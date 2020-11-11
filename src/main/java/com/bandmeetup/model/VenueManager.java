package com.bandmeetup.model;
public class VenueManager extends User{

    private String name;        //Name for company
    private String location;    //location of company
    private String description; //Brief description of company

    public VenueManager(String email, String name, String pw, String userType, String location, String description){
        super(email, pw, userType);
        this.name = name;
        this.location = location;
        this.description = description;
    }

    //Getters
    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
