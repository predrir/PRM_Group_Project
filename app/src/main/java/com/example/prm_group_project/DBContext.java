package com.example.prm_group_project;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    // Replace with your database details
    private static final String IP = "10.0.2.2"; // e.g., "192.168.1.100"
    private static final String PORT = "1433"; // default SQL Server port
    private static final String DB_NAME = "Miracle";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123456";

    public static Connection connect() {
        Connection connection = null;
        String connectionURL;

        try {
            // For Android SDK versions above 9, allow network operation in the main thread for testing purposes
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

            // Create the connection URL
            connectionURL = "jdbc:jtds:sqlserver://" + IP + ":" + PORT + "/" + DB_NAME + ";user=" + USERNAME + ";password=" + PASSWORD + ";";

            // Establish connection
            connection = DriverManager.getConnection(connectionURL);

            Log.i("Database Connection", "Connection successful");

        } catch (ClassNotFoundException e) {
            Log.e("Database Connection", "JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            Log.e("Database Connection", "Connection failed: " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
