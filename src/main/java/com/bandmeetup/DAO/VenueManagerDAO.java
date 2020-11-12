package com.bandmeetup.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bandmeetup.model.Musician;
import com.bandmeetup.model.VenueManager;
import org.springframework.stereotype.Component;

@Component
public class VenueManagerDAO implements Dao<VenueManager> {

    @Override
    public Optional<VenueManager> get(String email) {
        // Sql statement for prepared statement
        String sql = "select * from User JOIN VenueManager ON User.Email = VenueManager.Email WHERE User.Email=?;";

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
                    VenueManager vm = new VenueManager(result.getString("Email"),
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
    public List<VenueManager> getAll() {
         // SQL Statement for prepared statement
         String sql = "select * from User JOIN VenueManager ON User.Email = VenueManager.Email;";
         ArrayList<VenueManager> VenuManagers = new ArrayList<VenueManager>();
         try{
             //Try connection and prepared statement setup
             Connection connection = ConnectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             try{
                 // Try Query
                 ResultSet result = preparedStatement.executeQuery();
                 while(result.next()){
 
                     // If result create new user and return Optional with value of created user
                    VenuManagers.add( new VenueManager(result.getString("Email"),
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
    public String save(VenueManager venumanager) {
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
    public boolean update(VenueManager venumanager) {
        String sql = "UPDATE VenueManager SET Name=?, Location=?, Description=? WHERE Email=?;";
        try{
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,venumanager.getName());
            preparedStatement.setString(2, venumanager.getLocation());
            preparedStatement.setString(3, venumanager.getDescription());
            preparedStatement.setString(4,venumanager.getEmail());
            preparedStatement.executeUpdate();
            preparedStatement.closeOnCompletion();
            return true;

        }
        catch (SQLException ex){
           ex.getSQLState();
           return false;
        }

    }

    @Override
    public void delete(VenueManager venumanager) {
        String sql = "DELETE Venuemanager WHERE Email="+venumanager.getEmail()+";"+"DELETE FROM User where Email ="+venumanager.getEmail()+";";
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

    public VenueManager getVenueManager(String email) {
        // Sql statement for prepared statement
        String sql = "select * from VenueManager join User ON VenueManager.Email = User.Email WHERE VenueManager.Email='" + email + "';";
        VenueManager vm = null;
        try {
            // Try and get connection
            Connection connection = ConnectDB.getConnection();
            // Setup prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try {
                // Try executing statement
                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    // If result create new user and return Optional with value of created user
                    vm = new VenueManager(result.getString("Email"),
                            result.getString("Name"),
                            result.getString("Password"),
                            result.getString("UserType"),
                            result.getString("Location"),
                            result.getString("Description"));
                    return vm;
                }

            } catch (SQLException ex) {
                return vm;
            }
        } catch (SQLException ex) {
            return vm;
        }
        return vm;
    }
    public List<VenueManager> findByLocation(String location){

        // SQL Statement for prepared statement
        String sql = "select * from VenueManager where Location='"+location+"';";
        ArrayList<VenueManager> vm = new ArrayList<VenueManager>();
        try{
            //Try connection and prepared statement setup
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try{
                // Try Query
                ResultSet result = preparedStatement.executeQuery();
                while(result.next()){

                    // If result create new user and return Optional with value of created user
                    vm.add( new VenueManager(result.getString("Email"),
                            result.getString("Name"),
                            result.getString("Location"),
                            result.getString("Description")));
                }
            }
            // Exceptions return empty arrays
            catch (SQLException ex){
                return vm;
            }
        }
        catch (SQLException ex){
            System.out.print(ex);
            return vm;
        }
        return vm;
    }
    public List<VenueManager> findByName(String name){

        // SQL Statement for prepared statement

        String sql = "select * from VenueManager where Name like'%"+name+"%';";
        ArrayList<VenueManager> vm = new ArrayList<VenueManager>();
        try{
            //Try connection and prepared statement setup
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try{
                // Try Query
                ResultSet result = preparedStatement.executeQuery();
                while(result.next()){

                    // If result create new user and return Optional with value of created user
                    vm.add( new VenueManager(result.getString("Email"),
                            result.getString("Name"),
                            result.getString("Location"),
                            result.getString("Description")));
                }
            }
            // Exceptions return empty arrays
            catch (SQLException ex){
                return vm;
            }
        }
        catch (SQLException ex){
            return vm;
        }
        return vm;
    }
    
}
