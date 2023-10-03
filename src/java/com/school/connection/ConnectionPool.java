package com.school.connection;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

public class ConnectionPool {

    private Connection con = null;

    public Connection startConnectionBD() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/centro_escolar_priv?useSSL=false";
            String user = "root";
            String pass = "";
            con = (Connection) DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return con;
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
