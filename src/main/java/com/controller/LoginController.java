package com.controller;

import com.database.DB;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "LoginController", value = "/LoginController")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log("DEBUG : request LoginController with GET method");
        System.out.println("DEBUG : request LoginController with GET method");

        if(isLoggedIn(request)) {
            // redirect to Index if user is connected
            response.sendRedirect("Index");
        } else {
            request.getRequestDispatcher("/view/loginView.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log("DEBUG : request LoginController with POST method");
        System.out.println("DEBUG : request LoginController with POST method");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // connect to database to check user/pass
        DB database = new DB();
        Connection conn = database.createConnexion("ort", "root", "");
        try {
            // query get pass from user
            ResultSet result = database.createSelect(conn, "SELECT password from user WHERE username = \""+username+"\"");
            String valid_password = "";
            while ( result.next() ) {
                valid_password = result.getString( "password" );
            }

            // check matching passwords
            if(valid_password.equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("valid", "true");
                response.sendRedirect("Index");
            } else {
                request.getRequestDispatcher("/view/loginView.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // if error, re-send login form
            request.getRequestDispatcher("/view/loginView.jsp").forward(request, response);
        }

    }

    /**
     * Function to see if user is logged in
     * @param request the request
     * @return True|false
     */
    public boolean isLoggedIn(HttpServletRequest request) {
        // check session (if no or new : needs to log in)
        HttpSession session = request.getSession();
        if(session.isNew()) {
            // new session
            return false;
        } else {
            // not a new session
            // checks if session is valid
            if(session.getAttribute("valid") == "true") {
                return true;
            } else {
                return false;
            }
        }

    }
}
