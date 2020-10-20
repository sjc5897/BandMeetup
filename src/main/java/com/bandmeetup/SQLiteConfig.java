package com.bandmeetup;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public static void insert(String E,String N,String P,String T) throws ClassNotFoundException {


        String sql = "INSERT INTO Musician(Email, Name, Password,UserType) VALUES(?,?,?,?)";
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, E);
            pstmt.setString(2, N);
            pstmt.setString(3, P);
            pstmt.setString(4, T);

            pstmt.executeUpdate();
            pstmt.closeOnCompletion();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) throws Exception {


        insert("D@d","Devan","123123","musician");


    }


}
