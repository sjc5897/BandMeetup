package com.bandmeetup.DAO;

import com.bandmeetup.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This is a Data Access Object for the User Class. Implements the Generic Dao interface
 * Language: Java 13
 * Framework: Spring
 * Author: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/29/2020
 * Last Edit: 10/29/2020
 */
public class UserDAO implements Dao<User> {

    /**
     * Gets a user object from the database via primary key, email address
     * @param email String, requested email address
     * @return      Optional, Either User Object or Empty.
     */
    public Optional<User> get(String email) {
        // Sql statement for prepared statement
        String sql = "SELECT * FROM USER WHERE Email=?;";
        try {
            // Try and get connection
            Connection connection = ConnectDB.getConnection();

            // Setup prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            try{
                // Try executing statement
                ResultSet result = preparedStatement.executeQuery();
                if(result.next()){
                    // If result create new user and return Optional with value of created user
                    User u = new User(result.getString("Email"),
                                      result.getString("Password"),
                                      result.getString("UserType"));
                    return Optional.of(u);
                }
                else{
                    // Otherwise, no user return empty
                    return Optional.empty();
                }
            }
            // Exceptions return empty
            catch (SQLException ex){
                return Optional.empty();
            }
        }
        catch(SQLException ex){
            return Optional.empty();
        }

    }

    /**
     * Overridden method to get all Users
     * @return  Returns, a list of user objects
     */
    @Override
    public List<User> getAll() {
        // SQL Statement for prepared statement
        String sql = "SELECT * FROM USER";
        ArrayList<User> users = new ArrayList<User>();
        try{
            //Try connection and prepared statement setup
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try{
                // Try Query
                ResultSet result = preparedStatement.executeQuery();
                if(result.next()){
                    // Add each database entry to the list as user objects
                    users.add(new User(result.getString("Email"),
                                       result.getString("Password"),
                                       result.getString("UserType")));
                }
            }
            // Exceptions return empty arrays
            catch (SQLException ex){
                return users;
            }
        }
        catch (SQLException ex){
            return users;
        }
        return users;
    }

    /**
     * Save method for the User Object
     * @param user User object to be added
     * @return     Success message
     */
    @Override
    public String save(User user) {

        String sql = "INSERT INTO USER (Email, Password, UserType) VALUES(?,?,?)";
        String resp;
        try{
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getPw());
            preparedStatement.setString(3,user.getUserType());

            preparedStatement.execute();
            preparedStatement.closeOnCompletion();
            resp = "Success";
        }
        catch (SQLException ex){
             resp = "Error: Email Invalid";
        }
        return resp;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
