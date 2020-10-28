package com.bandmeetup.DAO;
import com.bandmeetup.model.Musician;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MusicianDAO implements Dao<Musician> {

    @Override
    public Optional<Musician> get(String email) {
        return Optional.empty();
    }

    @Override
    public List<Musician> getAll() {
        return null;
    }

    @Override
    public String save(Musician musician) {
        return null;
    }

    @Override
    public void update(Musician musician) {

    }

    @Override
    public void delete(Musician musician) {

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
