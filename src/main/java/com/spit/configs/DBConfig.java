package com.spit.configs;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Pravesh Ganwani
 */
public class DBConfig {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/postbook_db?useSSL=false","root","root");
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return con;
    }
    
    public static String getDriverName(){
        return "com.mysql.cj.jdbc.Driver";
    }
    
    public static String getUrl() {
        return "jdbc:mysql://localhost:3306/postbook_db?useSSL=false";
    }
    
    public static String getUsername() {
        return "root";
    }
    
    public static String getPassword() {
        return "root";
    }
}
