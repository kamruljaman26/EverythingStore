package com.everythingstore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // static connection
    private static Connection dbConnection = null;

    public static Connection getDBConnection() {

        // if connection already establish
        if (dbConnection != null){
            return dbConnection;
        }

        // load driver
        try {
            // type of driver being used
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // build db connection
        try {
            String URL = "jdbc:sqlite:everythingstore.sqlite";
            dbConnection = DriverManager.getConnection(URL);
            return dbConnection;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbConnection;
    }
}
