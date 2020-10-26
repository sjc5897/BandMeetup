package com.bandmeetup.DAO;

import com.bandmeetup.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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


    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
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
