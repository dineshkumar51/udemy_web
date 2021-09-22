package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MysqlConnection {

    public static Connection getConnection()
    {
        Connection con = null;
        String url = "jdbc:mysql:// localhost:3306/udemy_updated";
        String user = "dinesh";
        String pass = "qwer";
        try {
            con = DriverManager.getConnection(url, user, pass);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeConnection(Connection con)
    {
        try
        {
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
