package org.example;


import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class DBConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/empdb";
    private static final String USER = "root";
    private static final String PASSWORD = "Sabi@5214";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
