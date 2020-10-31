package com.bandmeetup.DAO;
import com.bandmeetup.model.Musician;
import com.bandmeetup.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MusicianDAO implements Dao<Musician> {

    @Override
    public Optional<Musician> get(String email) {
        // Sql statement for prepared statement
        String sql = "select * from User JOIN Musician ON User.Email = Musician.Email WHERE User.Email="+email+";";

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

        String sql = "INSERT INTO Musician(Email, Password, FName, LName, Genre, ProfileStatus, Instruments, Bio, Location) VALUES(?,?,?,?,?,?,?,?,?)";
        String resp;
        try{
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,musician.getEmail());
            preparedStatement.setString(2,musician.getPw());
            String[] arrOfStr = musician.getName().split(" ", 2);
            preparedStatement.setString(3,arrOfStr[0]);
            preparedStatement.setString(4,arrOfStr[1]);
            preparedStatement.setString(6,musician.getGenre());
            preparedStatement.setString(7,String.valueOf(musician.getStatus()));
            preparedStatement.setString(8,musician.getInstruments());
            preparedStatement.setString(9,musician.getBio());
            preparedStatement.setString(10,musician.getLocation());
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
                "Password="+musician.getPw()+
                ",FName="+arrOfStr[0]+
                ",LName="+arrOfStr[1]+
                ",Genre="+musician.getGenre()+
                ",ProfileStatus="+String.valueOf(musician.getStatus())+
                ",Instruments="+musician.getInstruments()+
                ",Bio="+musician.getBio()+
                ",Location=" +musician.getLocation()+
                "where Email="+musician.getEmail()+";";

        try{
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeQuery();
            preparedStatement.closeOnCompletion();

        }
        catch (SQLException ex){
           ex.getSQLState();
        }

    }




    @Override
    public void delete(Musician musician) {
        String sql = "DELETE FROM Musician where Email="+musician.getEmail()+";" +" DELETE FROM User where Email="+musician.getEmail()+";";
        try{
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeQuery();
            preparedStatement.closeOnCompletion();
        }
        catch (SQLException ex){
            ex.getSQLState();
        }
    }
}
//    @Override
//    public Musician extractUser(ResultSet rs) throws SQLException {
//
//        Musician m = new Musician();
//        m.setEmail( rs.getString("email") );
//        m.setName( rs.getString("FName") + rs.getString("LName"));
//        m.setPw( rs.getString("pass") );
//        return m;
//    }
//
//
//    @Override
//    public Musician getMusician(String email) {
//
//        Connection connection = ConnectDB.getConnection();
//        try {
//            Statement stmt = connection.createStatement();
//
//            ResultSet rs = stmt.executeQuery("SELECT * FROM Musician WHERE email=" + email);
//            if(rs.next())
//            {
//                return  extractUser(rs);
//            }
//        } catch (SQLException ex) {
//
//            ex.printStackTrace();
//        }
//        return null;
//
//    }
//
//    @Override
//    public  List<Musician>  getAllMusician() {
//        Connection connection = ConnectDB.getConnection();
//        try {
//            Statement stmt = connection.createStatement();
//
//            ResultSet rs = stmt.executeQuery("SELECT * FROM Musician");
//            List<Musician> Musicians = new ArrayList<Musician>();
//
//            while(rs.next())
//
//            {
//                Musicians.add(extractUser(rs));
//
//            }
//
//            return Musicians;
//
//        } catch (SQLException ex) {
//
//            ex.printStackTrace();
//        }
//
//        return null;
//
//    }
//
//
//    @Override
//    public Musician getUserByUserNameAndPassword(){
//        Musician m =new Musician();
//        return m;
//
//    }
//
//    @Override
//    public boolean insertMusician(){
//
//        return true;
//    }
//
//    @Override
//    public boolean updateMusician(){
//        return true;
//
//    }
//
//    @Override
//    public boolean deleteMusician(){
//        return true;
//    }
//
//}
