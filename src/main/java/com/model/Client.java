package com.model;

import com.database.DB;

import java.sql.Connection;
import java.sql.SQLException;

public class Client {
    private int code;
    private String society;
    private String name;
    private String surname;
    private int phone;
    private String address;

    public Client(int code, String society, String name, String surname, int phone, String address) {
        this.code = code;
        this.society = society;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public boolean insert(DB database) throws SQLException {

        Connection conn = database.createConnexion("ort", "root", "");
        String query = "INSERT INTO client(society, name, surname, phone, address) VALUES"
                +   "("
                +   "\""+this.society+"\","
                +   "\""+this.name+"\","
                +   "\""+this.surname+"\","
                +   "\""+this.phone+"\","
                +   "\""+this.address+"\""
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
        String query = "UPDATE client SET"
                +   "society=\""+this.society+"\","
                +   "name=\""+this.name+"\","
                +   "surname=\""+this.surname+"\","
                +   "phone=\""+this.phone+"\","
                +   "address=\""+this.address+"\","
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
        String query = "DELETE FROM client WHERE code=\""+this.code+"\"";

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

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
