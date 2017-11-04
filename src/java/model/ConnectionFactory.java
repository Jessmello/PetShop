package model;

import java.sql.*;
import java.util.logging.*;

/**
 *
 * @author ciro
 */
public class ConnectionFactory {
    private static final String DATABASE_URL = "jdbc:mysql://localhost/petshop";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "1234";
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (Exception ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
}
