package com.main.lms.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private String user = "postgres";
    private String password = "password";
    private String url = "jdbc:postgresql://localhost/library";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
