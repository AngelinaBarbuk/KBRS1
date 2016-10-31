package com.dao.connector;


import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by VVV on 20.07.2016.
 */
public class DBConnector {
    private static DBConnector datasource;
    private Connection connection;

    private DBConnector() throws IOException, SQLException, PropertyVetoException {
        Locale.setDefault(Locale.ENGLISH);
        try {
            Class.forName("com.mysql.jdbc.Driver");
           /* Class.forName("oracle.jdbc.driver.OracleDriver");*/
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection=DriverManager.getConnection("jdbc:mysql://localhost/kbrs_lab_1?user=root&password=1111&useSSL=true"); /*DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "angelina",
                "angelina");*/
        System.out.println("Load JDBC");

    }

    public static DBConnector getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DBConnector();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return connection;
    }

}
