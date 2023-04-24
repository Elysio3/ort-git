package com.model;

import com.database.DB;

import java.sql.Connection;
import java.sql.SQLException;

public class Command {

    private int code;
    private int code_client;

    public Command(int code, int code_client) {
        this.code = code;
        this.code_client = code_client;
    }

    public boolean insert(DB database) throws SQLException {

        Connection conn = database.createConnexion("ort", "root", "");
        String query = "INSERT INTO command(code_client) VALUES"
                +   "("
                +   "\""+this.code_client+"\""
                +   ")";

        // execute query and get result
        if(database.createInsert(conn, query) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(DB database) throws SQLException {

        Connection conn = database.createConnexion("ort", "root", "");
        String query = "UPDATE product SET"
                +   "code_client=\""+this.code_client+"\""
                +   "WHERE code=\""+this.code+"\"";

        // execute query and get result
        if(database.createUpdate(conn, query) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(DB database) throws SQLException {

        Connection conn = database.createConnexion("ort", "root", "");
        String query = "DELETE FROM product WHERE code=\""+this.code+"\"";

        // execute query and get result
        if(database.createDelete(conn, query) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode_client() {
        return code_client;
    }

    public void setCode_client(int code_client) {
        this.code_client = code_client;
    }
}
