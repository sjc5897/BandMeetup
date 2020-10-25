package com.bandmeetup.model;

import com.bandmeetup.SQLiteConfig;

import java.sql.SQLException;

/**
 * Model class used to represent a generic user
 * Language: Java 13
 * Framework: Spring
 * Author: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/24/2020
 * Last Edit: 10/24/2020
 */
public class User {
    // Attributes
    private String email;                   //User's email address
    private String name;                    //User's username/name
    private String pw;                      //User's plain text password
    private AccountTypeEnum userType;       //User's account type

    public String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        email = email;
    }

    public String getName() {
        return name;
    }

    public static void setName(String name) {
        name = name;
    }


    public String getPw() {
        return pw;
    }

    public static void setPw(String pw) {
        pw = pw;
    }



    public AccountTypeEnum getUserType() {
        return userType;
    }
    public void setUserType(AccountTypeEnum userType) {
        this.userType = userType;
    }

    // Methods
    /**
     * Constructor for User Object
     * @param email     String, user's email address
     * @param name      String, user's username
     * @param pw        String, user's password
     * @param userType  String, user's account type
     */
    public User(String email, String name, String pw, String userType){
        this.email = email;
        this.name = name;
        this.pw = pw;
        this.userType = AccountTypeEnum.valueOf(userType);
    }

    public User() {

    }

    /**
     * Method for saving the user in the db
     * @return Boolean, represents success
     */
    public String persistUser() {
        String resp;
        try{
            resp = SQLiteConfig.insert_user(this.email,this.pw,this.userType.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            resp = "ERROR: Unknown";
        }
        return resp;
    }

}

/**
 * Enum for the account types
 */
enum AccountTypeEnum{
    Admin,
    VenueManager,
    Musician
}