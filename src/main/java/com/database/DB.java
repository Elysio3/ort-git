package com.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class DB {

    private void instancierDriver() {
        /* Chargement du driver JDBC pour MySQL */
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
            /* Gérer les éventuelles erreurs ici. */
        }
    }

    public Connection createConnexion(String base, String username, String password) {
        this.instancierDriver();
        String url = "jdbc:mysql://localhost:3306/"+base;
        Connection conn = null;
        try {
            System.out.println("connecting to " + base + " with " + username + " : " + password);
            conn = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            System.out.println("Error while trying to connect to " + base + " : " + e);
        }
        return conn;
    }

    public ResultSet createSelect(Connection conn, String query) throws SQLException {

        Statement statement = conn.createStatement();
        ResultSet result = null;
        try {
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error while trying to execute query " + query + " : " + e);
        }
        return result;
    }

    public int createInsert(Connection conn, String query) throws SQLException {
        Statement statement = conn.createStatement();
        int result = 0;
        try {
            result = statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error while trying to execute query " + query + " : " + e);
        }
        return result;
    }
    public int createUpdate(Connection conn, String query) throws SQLException {
        Statement statement = conn.createStatement();
        int result = 0;
        try {
            result = statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error while trying to execute query " + query + " : " + e);
        }
        return result;
    }
    public int createDelete(Connection conn, String query) throws SQLException {
        Statement statement = conn.createStatement();
        int result = 0;
        try {
            result = statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error while trying to execute query " + query + " : " + e);
        }
        return result;
    }

}