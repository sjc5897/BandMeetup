package com.bandmeetup;


import java.sql.*;

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


        String sql = "INSERT INTO Musician(Email, FName, Password) VALUES(?,?,?,?)";
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
    public static boolean loginAuth(String email,String password) throws ClassNotFoundException {

    // An example where er can verify login
        boolean flag = false;

        String sql = "select Email, Password from User;";
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            try {
                ResultSet results = ps.executeQuery();

                while (results.next()) {

                    String Email = results.getString("Email");
                    String Password =  results.getString("Password");
                    System.out.println(Email);

                    if ((email.equals(Email)) && (password.equals(Password))) {
                        flag = true; }
                    results.close();
                }
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


        insert("D@2d","Dean","12123","musician");
        boolean Pass =loginAuth("s@s","123456");
        System.out.println(Pass);



    }


}
