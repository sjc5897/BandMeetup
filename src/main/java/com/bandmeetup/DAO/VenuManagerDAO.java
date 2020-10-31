package com.bandmeetup.DAO;

import java.lang.StackWalker.Option;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.bandmeetup.model.VenuManager;
import org.springframework.stereotype.Component;

@Component
public class VenuManagerDAO implements Dao<VenuManager> {

    @Override
    public Optional<VenuManager> get(String email) {
        // Sql statement for prepared statement
        String sql = "select * from VenueManager join User ON VenueManager.Email = User.Email WHERE VenueManager.Email='"+email+"';";

        try{
            // Try and get connection
            Connection connection = ConnectDB.getConnection();
            // Setup prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            try{
                // Try executing statement
                ResultSet result = preparedStatement.executeQuery();
                if(result.next()){
                    // If result create new user and return Optional with value of created user
                    VenuManager vm = new VenuManager(result.getString("Email"),
                        result.getString("Name"),
                        result.getString("Password"),
                        result.getString("UserType"),
                        result.getString("Location"),
                        result.getString("Description"));
                    return Optional.of(vm);
                }
                else{
                    // Otherwise, no user return empty
                    return Optional.empty();
                }
            }// Exceptions return empty
            catch(SQLException ex){
                return Optional.empty();
            }
        }catch(SQLException ex){
            return Optional.empty();
        }
    }

    @Override
    public List<VenuManager> getAll() {
         // SQL Statement for prepared statement
         String sql = "select * from User JOIN VenueManager ON User.Email = VenueManager.Email;";
         ArrayList<VenuManager> VenuManagers = new ArrayList<VenuManager>();
         try{
             //Try connection and prepared statement setup
             Connection connection = ConnectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             try{
                 // Try Query
                 ResultSet result = preparedStatement.executeQuery();
                 if(result.next()){
 
                     // If result create new user and return Optional with value of created user
                    VenuManagers.add( new VenuManager(result.getString("Email"),
                        result.getString("Name"),
                        result.getString("Password"),
                        result.getString("UserType"),
                        result.getString("Location"),
                        result.getString("Description")));
                 }
             }
             // Exceptions return empty arrays
             catch (SQLException ex){
                 return VenuManagers;
             }
         }
         catch (SQLException ex){
             return VenuManagers;
         }
         return VenuManagers;
    }

    @Override
    public String save(VenuManager venumanager) {
        String sql = "INSERT INTO VenueManager(Email, Name, Location, Description) VALUES(?,?,?,?)";

        String resp;
        try{
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,venumanager.getEmail());
            preparedStatement.setString(2,venumanager.getName());
            preparedStatement.setString(3,venumanager.getLocation());
            preparedStatement.setString(4,venumanager.getDescription());
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
    public void update(VenuManager venumanager) {
        String sql = "update VenueManager set"+
                "',Name='"+venumanager.getName()+
                "',Location='"+venumanager.getLocation()+
                "',Description='" +venumanager.getDescription()+
                "where Email='"+venumanager.getEmail()+";";


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
    public void delete(VenuManager venumanager) {
        String sql = "DELETE FROM VenueManager WHERE Email='"+venumanager.getEmail()+"';"+"DELETE FROM User where Email ='"+venumanager.getEmail()+"';";
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
    
}
