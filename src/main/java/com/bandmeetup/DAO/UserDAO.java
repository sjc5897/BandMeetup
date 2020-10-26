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


    public Optional<User> get(String email) {
        String sql = "SELECT * FROM USER WHERE Email=?;";
        try {
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            try{
                ResultSet result = preparedStatement.executeQuery();
                if(result.next()){
                    User u = new User(result.getString("Email"),
                                      result.getString("Password"),
                                      result.getString("UserType"));
                    return Optional.of(u);
                }
                else{
                    return Optional.empty();
                }
            }
            catch (SQLException ex){
                return Optional.empty();
            }
        }
        catch(SQLException ex){
            return Optional.empty();
        }

    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM USER";
        ArrayList<User> users = new ArrayList<User>();
        try{
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try{
                ResultSet result = preparedStatement.executeQuery();
                if(result.next()){
                    users.add(new User(result.getString("Email"),
                                       result.getString("Password"),
                                       result.getString("UserType")));
                }
            }catch (SQLException ex){
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
     * @param user
     * @return
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
