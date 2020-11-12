package com.bandmeetup;


import com.bandmeetup.DAO.ConnectDB;
import com.bandmeetup.DAO.EventDAO;
import com.bandmeetup.DAO.VenueManagerDAO;
import com.bandmeetup.model.Event;
import com.bandmeetup.model.VenueManager;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SQLiteConfig {
    static Connection conn = null;


    private static Connection connect() {

        String url = "jdbc:sqlite:./Data/BandDB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("DB: error "+e.getMessage());
        }
        return conn;
    }
    public static String insert_user(String E,String P,String T) throws ClassNotFoundException {


        String sql = "INSERT INTO USER (Email, Password, UserType) VALUES(?,?,?)";

        Class.forName("org.sqlite.JDBC");
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, E);
            pstmt.setString(2, P);
            pstmt.setString(3, T);

            pstmt.executeUpdate();
            pstmt.closeOnCompletion();
            return "Success";
        } catch (SQLException e) {
            return "ERROR: Email Invalid";
        }
    }
    public static boolean loginAuth(String email,String password) throws ClassNotFoundException {
        //TODO: We need to have this return or query role for authentication purposes.

        // An example where er can verify login
        boolean flag = false;
        //This will now query for only the email the user inputs
        String sql = "select Email, Password from USER WHERE Email=?;";
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1,email);
            try {
                ResultSet results = ps.executeQuery();
                if(results.next()) {
                    flag = results.getString("Password").equals(password);
                }
                else{
                    flag =  false;
                }
//                while (results.next()) {
//
//                    String Email = results.getString("Email");
//                    String Password =  results.getString("Password");
//                    System.out.println(Email);
//
//                    if ((email.equals(Email)) && (password.equals(Password))) {
//                        flag = true; }
//                    results.close();
//                }
                results.close();
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public static void main(String[] args) throws Exception {

//        boolean Pass =loginAuth("s@s","123456");
//        System.out.println(Pass);
//        update("D@d");
//        VenueManagerDAO vmdao=new VenueManagerDAO();
//        VenueManager vm= vmdao.getVenueManager("Sultan@rit.edu");
//        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
//        Date date = DATE_FORMAT.parse("10/19/2020");
//        Event v=new Event(1, "Ceremony", "MIT Graduation party at Building 10", date, vm );
//        String pass = save(v);
        EventDAO event= new EventDAO();
        List<Event> events = event.getAll();
        for (Event element: events) {
            System.out.println(element);
        }

    }

    public static void update(String email) throws ClassNotFoundException {

        String sql = "update Musician set FName='" + "Mohammed" + "' where Email='" + email + "';";
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.execute();
            ps.closeOnCompletion();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public static void save1() throws ClassNotFoundException {
        VenueManager nenumanager =new VenueManager("email", "name", "pw","VenueManager", "location", "description");

        String sql = "INSERT INTO VenueManager(Email, Name, Location, Description) VALUES(?,?,?,?)";


        try{
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,nenumanager.getEmail());
            preparedStatement.setString(2,nenumanager.getName());
            preparedStatement.setString(3,nenumanager.getLocation());
            preparedStatement.setString(4,nenumanager.getDescription());
            preparedStatement.execute();
            preparedStatement.closeOnCompletion();
        }
        catch (SQLException ex){

        }

    }


    public static String save(Event event) {
        String sql ="INSERT INTO Event(Title, Description, date, VenueManager) VALUES(?,?,?,?);";

        String resp;
        try{
            Connection connection = ConnectDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,event.getTitle());
            preparedStatement.setString(2,event.getDescription());
            System.out.println(event.getDate().toString());
            String newstring = new SimpleDateFormat("MM/dd/yyyy").format(event.getDate());
            System.out.println(newstring);
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



}
