package com.bandmeetup;
import com.bandmeetup.ConnectDB;
import com.bandmeetup.model.Musician;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MusicionExe implements MusicianDAO{
    @Override
    public Musician extractUser(ResultSet rs) throws SQLException {

        Musician m = new Musician();
        Musician.setEmail( rs.getString("email") );
        Musician.setName( rs.getString("FName") + rs.getString("LName"));
        Musician.setPw( rs.getString("pass") );
        return m;
    }


    @Override
    public Musician getMusician(String email) {

        Connection connection = ConnectDB.getConnection();
        try {
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Musician WHERE email=" + email);
            if(rs.next())
            {
                return  extractUser(rs);
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public  List<Musician>  getAllMusician() {
        Connection connection = ConnectDB.getConnection();
        try {
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Musician");
            List<Musician> Musicians = new ArrayList<Musician>();

            while(rs.next())

            {
                Musicians.add(extractUser(rs));

            }

            return Musicians;

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return null;

    }


    @Override
    public Musician getUserByUserNameAndPassword(){
        Musician m =new Musician();
        return m;

    }

    @Override
    public boolean insertMusician(){

        return true;
    }

    @Override
    public boolean updateMusician(){
        return true;

    }

    @Override
    public boolean deleteMusician(){
        return true;
    }

}
