package com.bandmeetup.DAO;
import com.bandmeetup.model.Musician;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class MusicianDAO implements Dao<Musician> {

    @Override
    public Optional<Musician> get(String email) {
        // Sql statement for prepared statement
        String sql = "Select * from Musician join User on Musician.Email= User.Email where Musician.Email='"+email+"';";

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
                    Musician m= new Musician(result.getString("Email"),
                            result.getString("Fname")+" "+result.getString("LName"),
                            result.getString("Password"),
                            result.getString("UserType"),
                            result.getString("ProfileStatus"),
                            result.getString("Instruments"),
                            result.getString("Genre"),
                            result.getString("Location"),
                            result.getString("Bio"));
                    return Optional.of(m);
                }
                else{
                    // Otherwise, no user return empty
                    return Optional.empty();
                }
            }// Exceptions return empty
            catch (SQLException ex){
                return Optional.empty();
            }
        } catch(SQLException ex){
            return Optional.empty();
        }
    }

    @Override
    public List<Musician> getAll() {
        // SQL Statement for prepared statement
        String sql = "select * from User JOIN Musician ON User.Email = Musician.Email;";
        ArrayList<Musician> Musicians = new ArrayList<Musician>();
        try{
            //Try connection and prepared statement setup
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try{
                // Try Query
                ResultSet result = preparedStatement.executeQuery();
                if(result.next()){

                    // If result create new user and return Optional with value of created user
                    Musicians.add( new Musician(result.getString("Email"),
                            result.getString("Fname")+" "+result.getString("LName"),
                            result.getString("Password"),
                            result.getString("UserType"),
                            result.getString("ProfileStatus"),
                            result.getString("Instruments"),
                            result.getString("Genre"),
                            result.getString("Location"),
                            result.getString("Bio")));
                }
            }
            // Exceptions return empty arrays
            catch (SQLException ex){
                return Musicians;
            }
        }
        catch (SQLException ex){
            return Musicians;
        }
        return Musicians;
    }

    @Override
    public String save(Musician musician) {

        String sql = "INSERT INTO Musician(Email, FName, LName, Genre, ProfileStatus, Instruments, Bio, Location) VALUES(?,?,?,?,?,?,?,?)";

        String resp;
        try{
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,musician.getEmail());
            String[] arrOfStr = musician.getName().split(" ", 2);
            preparedStatement.setString(2,arrOfStr[0]);
            preparedStatement.setString(3,arrOfStr[1]);
            preparedStatement.setString(4,musician.getGenre());
            preparedStatement.setString(5,String.valueOf(musician.getStatus()));
            preparedStatement.setString(6,musician.getInstruments());
            preparedStatement.setString(7,musician.getBio());
            preparedStatement.setString(8,musician.getLocation());
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
    public void update(Musician musician) {

        String[] arrOfStr = musician.getName().split(" ", 2);
        String sql = "update Musician set"+
                "',FName='"+arrOfStr[0]+
                "',LName='"+arrOfStr[1]+
                "',Genre='"+musician.getGenre()+
                "',ProfileStatus='"+String.valueOf(musician.getStatus())+
                "',Instruments='"+musician.getInstruments()+
                "',Bio='"+musician.getBio()+
                "',Location='" +musician.getLocation()+
                "where Email='"+musician.getEmail()+"';";


        try{
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            preparedStatement.closeOnCompletion();

        }
        catch (SQLException ex){
           ex.getSQLState();
        }

    }




    @Override
    public void delete(Musician musician) {
        String sql = "DELETE FROM Musician where Email='"+musician.getEmail()+"';" +" DELETE FROM User where Email='"+musician.getEmail()+"';";
        try{
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            preparedStatement.closeOnCompletion();
        }
        catch (SQLException ex){
            ex.getSQLState();
        }
    }

    public List<Musician> findByStatus(String status) {

            // SQL Statement for prepared statement
            String sql = "select * from Musician where ProfileStatus='"+status+"';";
            ArrayList<Musician> Musicians = new ArrayList<Musician>();
            try{
                //Try connection and prepared statement setup
                Connection connection = ConnectDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                try{
                    // Try Query
                    ResultSet result = preparedStatement.executeQuery();
                    if(result.next()){

                        // If result create new user and return Optional with value of created user
                        Musicians.add( new Musician(result.getString("Email"),
                                result.getString("Fname")+" "+result.getString("LName"),
                                result.getString("Password"),
                                result.getString("UserType"),
                                result.getString("ProfileStatus"),
                                result.getString("Instruments"),
                                result.getString("Genre"),
                                result.getString("Location"),
                                result.getString("Bio")));
                    }
                }
                // Exceptions return empty arrays
                catch (SQLException ex){
                    return Musicians;
                }
            }
            catch (SQLException ex){
                return Musicians;
            }
            return Musicians;
        }
    public List<Musician> findByLocation(String location) {

        // SQL Statement for prepared statement
        String sql = "select * from Musician where Location='"+location+"';";
        ArrayList<Musician> Musicians = new ArrayList<Musician>();
        try{
            //Try connection and prepared statement setup
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try{
                // Try Query
                ResultSet result = preparedStatement.executeQuery();
                if(result.next()){

                    // If result create new user and return Optional with value of created user
                    Musicians.add( new Musician(result.getString("Email"),
                            result.getString("Fname")+" "+result.getString("LName"),
                            result.getString("Password"),
                            result.getString("UserType"),
                            result.getString("ProfileStatus"),
                            result.getString("Instruments"),
                            result.getString("Genre"),
                            result.getString("Location"),
                            result.getString("Bio")));
                }
            }
            // Exceptions return empty arrays
            catch (SQLException ex){
                return Musicians;
            }
        }
        catch (SQLException ex){
            return Musicians;
        }
        return Musicians;
    }
    public List<Musician> findByGenre(String genre) {

        // SQL Statement for prepared statement
        String sql = "select * from Musician where Genre='"+genre+"';";
        ArrayList<Musician> Musicians = new ArrayList<Musician>();
        try{
            //Try connection and prepared statement setup
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try{
                // Try Query
                ResultSet result = preparedStatement.executeQuery();
                if(result.next()){

                    // If result create new user and return Optional with value of created user
                    Musicians.add( new Musician(result.getString("Email"),
                            result.getString("Fname")+" "+result.getString("LName"),
                            result.getString("Password"),
                            result.getString("UserType"),
                            result.getString("ProfileStatus"),
                            result.getString("Instruments"),
                            result.getString("Genre"),
                            result.getString("Location"),
                            result.getString("Bio")));
                }
            }
            // Exceptions return empty arrays
            catch (SQLException ex){
                return Musicians;
            }
        }
        catch (SQLException ex){
            return Musicians;
        }
        return Musicians;
    }
    public List<Musician> findByInstruments(String instrument) {

        // SQL Statement for prepared statement
        String sql = "select * from Musician where Genre='"+instrument+"';";
        ArrayList<Musician> Musicians = new ArrayList<Musician>();
        try{
            //Try connection and prepared statement setup
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try{
                // Try Query
                ResultSet result = preparedStatement.executeQuery();
                if(result.next()){

                    // If result create new user and return Optional with value of created user
                    Musicians.add( new Musician(result.getString("Email"),
                            result.getString("Fname")+" "+result.getString("LName"),
                            result.getString("Password"),
                            result.getString("UserType"),
                            result.getString("ProfileStatus"),
                            result.getString("Instruments"),
                            result.getString("Genre"),
                            result.getString("Location"),
                            result.getString("Bio")));
                }
            }
            // Exceptions return empty arrays
            catch (SQLException ex){
                return Musicians;
            }
        }
        catch (SQLException ex){
            return Musicians;
        }
        return Musicians;
    }

}

