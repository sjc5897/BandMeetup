package com.bandmeetup.model;

import com.bandmeetup.DAO.UserDAO;
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
    private String pw;                      //User's plain text password
    private AccountTypeEnum userType;       //User's account type

    // Getters
    public String getEmail() {
        return email;
    }

    public String getPw() {
        return pw;
    }

    public String getUserType() {
        return userType.toString();
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setUserType(AccountTypeEnum userType) {
        this.userType = userType;
    }

    // Methods

    public User(String email){
        this.email = email;
    }

    /**
     * Constructor for User Object
     *
     * @param email    String, user's email address
     * @param pw       String, user's password
     * @param userType String, user's account type
     */
    public User(String email, String pw, String userType) {
        this.email = email;
        this.pw = pw;
        this.userType = AccountTypeEnum.valueOf(userType);
    }

    /**
     * Simple method for authenticating user password matches
     * @param pw  String, user inputted passwords
     * @return    Boolean, is authenticated.
     */
    public boolean authenticate(String pw){
        return this.pw.equals(pw);
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