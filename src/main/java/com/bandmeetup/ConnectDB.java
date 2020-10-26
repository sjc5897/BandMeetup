package com.bandmeetup;

import java.sql.*;

public class ConnectDB  {

    public static final String URL = "jdbc:sqlite:./Data/BandDB.db";
    static Connection conn = null;

    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL);
            return conn;
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }

    }

}
