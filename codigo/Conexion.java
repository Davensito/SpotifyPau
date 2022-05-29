package com.example.codigo;
import  java.sql.*;

public class Conexion {

    static Connection conexion() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/spotify", "root",
                    "dbrootpass");
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
