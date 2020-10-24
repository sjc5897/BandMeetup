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

    /**
     * Method for saving the user in the db
     * @return Boolean, represents success
     */
    public boolean persistUser() {
        try{
            SQLiteConfig.insert(this.email,this.name,this.pw,"musician");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

}

/**
 * Enum for the account types
 */
enum AccountTypeEnum{
    MUSICIAN,
    VENUE,
    ADMIN
}