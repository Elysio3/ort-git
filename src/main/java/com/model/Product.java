package com.model;

import com.database.DB;

import java.sql.Connection;
import java.sql.SQLException;

public class Product {

    private int code;
    private String name;
    private int quantity;

    public Product(int code, String name, int quantity) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }

    public boolean insert(DB database) throws SQLException {

        Connection conn = database.createConnexion("ort", "root", "");
        String query = "INSERT INTO product(name, quantity) VALUES"
                +   "("
                +   "\""+this.name+"\","
                +   "\""+this.quantity+"\""
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
                +   "name=\""+this.name+"\","
                +   "quantity=\""+this.quantity+"\""
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
