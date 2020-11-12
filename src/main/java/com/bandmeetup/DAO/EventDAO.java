package com.bandmeetup.DAO;


import com.bandmeetup.model.Event;
import com.bandmeetup.model.VenueManager;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



@Component
public class EventDAO implements Dao<Event>{

    @Override
    public String save(Event event) {
        String sql ="INSERT INTO Event(Title, Description, date, VenueManager) VALUES(?,?,?,?);";

        String resp;
        try{
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,event.getTitle());
            preparedStatement.setString(2,event.getDescription());
            preparedStatement.setString(3,new SimpleDateFormat("MM/dd/yyyy").format(event.getDate()));
            preparedStatement.setString(4,event.getVenueManager().getEmail());
            preparedStatement.execute();
            preparedStatement.closeOnCompletion();
            resp = "Success";
        }
        catch (SQLException ex){
            resp = "Error: Inserting a new event went wrong";
        }
        return resp;

    }
    @Override
    public List<Event> getAll() {
        VenueManagerDAO vm = new VenueManagerDAO();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String sql = "select * from Event;";
        ArrayList<Event> events = new ArrayList<Event>();
        try{
            //Try connection and prepared statement setup
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try{
                // Try Query
                ResultSet result = preparedStatement.executeQuery();
                while(result.next()){
                    // If result create new user and return Optional with value of created user
                    events.add( new Event(result.getInt("ID"),
                            result.getString("Title"),
                            result.getString("Description"),
                            format.parse(result.getString("date")),
                            vm.getVenueManager(result.getString("VenueManager"))));
                }
            }
            // Exceptions return empty arrays
            catch (SQLException ex){
                return events;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException ex){
            return events;
        }
        return events;

         }
    @Override
    public boolean update(Event event) {


        String sql = "UPDATE  Event SET Title=?,Description=?,Date=? where ID=?;";
        try{
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,event.getTitle());
            preparedStatement.setString(2,event.getDescription());
            preparedStatement.setString(3,event.getSimpleDate());
            preparedStatement.setInt(4,event.getID());
            preparedStatement.execute();
            preparedStatement.closeOnCompletion();
            return true;

        }
        catch (SQLException ex){
            ex.getSQLState();
            return false;
        }

    }

    @Override
    public void delete(Event event) {

        String sql = "DELETE FROM Event where ID='"+event.getID()+"';";
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
    public Optional<Event> get(String email){ return Optional.empty(); }

    public Optional<Event> getById(Integer Id){
        VenueManagerDAO vm = new VenueManagerDAO();
        SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
        String sql = "Select * from Event where ID=?;";
        try{
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,Id.toString());
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()){
                 Event e = new Event(res.getInt("ID"),
                            res.getString("Title"),
                            res.getString("Description"),
                            format.parse(res.getString("date")),
                            vm.getVenueManager(res.getString("VenueManager")));
                 return Optional.of(e);
            }
        }catch (Exception ex){
            return Optional.empty();
        }
        return Optional.empty();

    }

    public List<Event> getEvents(String email) {
        VenueManagerDAO vm = new VenueManagerDAO();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String sql = "select * from Event where VenueManager='"+email+"';";
        ArrayList<Event> events = new ArrayList<Event>();
        try{
            //Try connection and prepared statement setup
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try{
                // Try Query
                ResultSet result = preparedStatement.executeQuery();
                while(result.next()){
                    // If result create new user and return Optional with value of created user
                    events.add( new Event(result.getInt("ID"),
                            result.getString("Title"),
                            result.getString("Description"),
                            format.parse(result.getString("date")),
                            vm.getVenueManager(result.getString("VenueManager"))));
                }
            }
            // Exceptions return empty arrays
            catch (SQLException ex){
                return events;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException ex){
            return events;
        }
        return events;

    }
}
