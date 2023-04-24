package com.controller;

import com.database.DB;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Client;
import com.model.Command;
import com.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Integer.valueOf;

@WebServlet(name = "IndexController", value = "/IndexController")
public class IndexController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log("DEBUG : request IndexController with GET method");
        System.out.println("DEBUG : request IndexController with GET method");

        if(!isLoggedIn(request)) {
            // redirect to login form if user's not connected
            response.sendRedirect("Login");
        } else {
            // switch on wich page is requested (index, forms, list, ...)
            if(request.getParameter("page") != null) {
                DB bdd = new DB();
                Connection conn = bdd.createConnexion("ort", "root", "");


                switch (request.getParameter("page")) {
                    default:
                        request.getRequestDispatcher("/view/indexView.jsp").forward(request, response);
                        break;
                    case "get-clients":
                        ArrayList<Client> clients = new ArrayList<>();
                        // query select
                        try {
                            ResultSet result = bdd.createSelect(conn, "SELECT * from client");
                            while ( result.next() ) {
                                // for each result, create client obj and add to list
                                int code = result.getInt( "code" );
                                String society = result.getString( "society" );
                                String name = result.getString( "name" );
                                String surname = result.getString( "surname" );
                                int phone = result.getInt( "phone" );
                                String address = result.getString( "address" );

                                Client client = new Client(code, society, name, surname, phone, address);
                                clients.add(client);
                            }
                            // return view with list
                            request.setAttribute("clients", clients);
                            request.getRequestDispatcher("/view/clientView.jsp").forward(request, response);
                            break;

                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.getRequestDispatcher("/view/errorView.jsp").forward(request, response);
                            break;
                        }
                    case "add-client":
                        request.getRequestDispatcher("/view/form/newClientView.jsp").forward(request, response);
                        break;
                    case "get-products":
                        ArrayList<Product> products = new ArrayList<>();
                        // query select
                        try {
                            ResultSet result = bdd.createSelect(conn, "SELECT * from product");
                            while ( result.next() ) {
                                // for each result, create client obj and add to list
                                int code = result.getInt( "code" );
                                String name = result.getString( "name" );
                                int quantity = result.getInt( "quantity" );

                                Product product = new Product(code, name, quantity);
                                products.add(product);
                            }
                            // return view with list
                            request.setAttribute("products", products);
                            request.getRequestDispatcher("/view/productView.jsp").forward(request, response);
                            break;

                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.getRequestDispatcher("/view/errorView.jsp").forward(request, response);
                            break;
                        }
                    case "add-product":
                        request.getRequestDispatcher("/view/form/newProductView.jsp").forward(request, response);
                        break;
                    case "get-commands":
                        ArrayList<Command> commands = new ArrayList<>();
                        ArrayList<Client> list_clients = new ArrayList<>();
                        // query select
                        try {
                            ResultSet result = bdd.createSelect(conn, "SELECT * from command");
                            while ( result.next() ) {
                                // for each result, create client obj and add to list
                                int code = result.getInt( "code" );
                                int code_client = result.getInt( "code_client" );

                                Command command = new Command(code, code_client);
                                commands.add(command);
                            }
                            result = bdd.createSelect(conn, "SELECT * from client");
                            while ( result.next() ) {
                                // for each result, create client obj and add to list
                                int code = result.getInt( "code" );
                                String society = result.getString( "society" );
                                String name = result.getString( "name" );
                                String surname = result.getString( "surname" );
                                int phone = result.getInt( "phone" );
                                String address = result.getString( "address" );

                                Client client = new Client(code, society, name, surname, phone, address);
                                list_clients.add(client);
                            }
                            // return view with list
                            request.setAttribute("commands", commands);
                            request.setAttribute("clients", list_clients);
                            request.getRequestDispatcher("/view/commandView.jsp").forward(request, response);
                            break;

                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.getRequestDispatcher("/view/errorView.jsp").forward(request, response);
                            break;
                        }
                    case "add-command":
                        // new Client list (for <select>)
                        list_clients = new ArrayList<Client>();
                        // query select
                        try {
                            ResultSet result = bdd.createSelect(conn, "SELECT * from client");
                            while ( result.next() ) {
                                // for each result, create client obj and add to list
                                int code = result.getInt( "code" );
                                String society = result.getString( "society" );
                                String name = result.getString( "name" );
                                String surname = result.getString( "surname" );
                                int phone = result.getInt( "phone" );
                                String address = result.getString( "address" );

                                Client client = new Client(code, society, name, surname, phone, address);
                                list_clients.add(client);
                            }
                            // return view with list
                            request.setAttribute("clients", list_clients);
                            request.getRequestDispatcher("/view/form/newCommandView.jsp").forward(request, response);
                            break;

                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.getRequestDispatcher("/view/errorView.jsp").forward(request, response);
                            break;
                        }
                }
            } else {
                request.getRequestDispatcher("/view/indexView.jsp").forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log("DEBUG : request IndexController with POST method");
        System.out.println("DEBUG : request IndexController with POST method");

        if(!isLoggedIn(request)) {
            // redirect to login form if user's not connected
            response.sendRedirect("Login");
        } else {
            // instance of database
            DB database = new DB();

            // switch on wich page is requested (index, forms, list, ...)
            if(request.getParameter("page") != null) {
                switch (request.getParameter("page")) {
                    default:
                        request.getRequestDispatcher("/view/indexView.jsp").forward(request, response);
                        break;
                    case "add-client":
                        try {
                            String society = request.getParameter("society");
                            String name = request.getParameter("name");
                            String surname = request.getParameter("surname");
                            int phone = Integer.parseInt(request.getParameter("phone"));
                            String address = request.getParameter("address");

                            Client c = new Client(0, society, name, surname, phone, address);
                            c.insert(database);

                            response.sendRedirect("Index?page=get-clients");
                            break;
                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.getRequestDispatcher("/view/errorView.jsp").forward(request, response);
                            break;
                        }
                    case "add-product":
                        try {
                            String name = request.getParameter("name");
                            int quantity = Integer.parseInt(request.getParameter("quantity"));

                            Product p = new Product(0, name, quantity);
                            p.insert(database);

                            response.sendRedirect("Index?page=get-products");
                            break;
                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.getRequestDispatcher("/view/errorView.jsp").forward(request, response);
                            break;
                        }
                    case "add-command":
                        try {
                            int code_client = Integer.parseInt(request.getParameter("code_client"));

                            Command c = new Command(0, code_client);
                            c.insert(database);

                            response.sendRedirect("Index?page=get-commands");
                            break;
                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.getRequestDispatcher("/view/errorView.jsp").forward(request, response);
                            break;
                        }
                    case "get-command-by-client":
                        DB bdd = new DB();
                        Connection conn = bdd.createConnexion("ort", "root", "");

                        int le_client = Integer.parseInt(request.getParameter("code_client"));

                        ArrayList<Command> commands = new ArrayList<>();
                        ArrayList<Client> list_clients = new ArrayList<>();

                        // query select
                        try {
                            ResultSet result = bdd.createSelect(conn, "SELECT * from command WHERE code_client = \"" + le_client + "\"");
                            while ( result.next() ) {
                                // for each result, create client obj and add to list
                                int code = result.getInt( "code" );
                                int code_client = result.getInt( "code_client" );

                                Command command = new Command(code, code_client);
                                commands.add(command);
                            }
                            result = bdd.createSelect(conn, "SELECT * from client");
                            while ( result.next() ) {
                                // for each result, create client obj and add to list
                                int code = result.getInt( "code" );
                                String society = result.getString( "society" );
                                String name = result.getString( "name" );
                                String surname = result.getString( "surname" );
                                int phone = result.getInt( "phone" );
                                String address = result.getString( "address" );

                                Client client = new Client(code, society, name, surname, phone, address);
                                list_clients.add(client);
                            }
                            // return view with list
                            request.setAttribute("commands", commands);
                            request.setAttribute("clients", list_clients);
                            request.getRequestDispatcher("/view/commandView.jsp").forward(request, response);
                            break;

                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.getRequestDispatcher("/view/errorView.jsp").forward(request, response);
                            break;
                        }
                }
            } else {
                request.getRequestDispatcher("/view/indexView.jsp").forward(request, response);
            }
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
