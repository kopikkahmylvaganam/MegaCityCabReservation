package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Loginbean;

public class Logindao {
    private static final String CUSTOMER_LOGIN_SQL = "SELECT * FROM customers WHERE username = ? AND password = ?";
    private static final String DRIVER_LOGIN_SQL = "SELECT * FROM drivers WHERE username = ? AND password = ?";

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Megacitycab?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Kopikkah98@";

    // Hardcoded admin credentials
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    // This method validates user credentials and their roles
    public String validate(Loginbean loginBean) throws SQLException {
        // Check if the entered credentials match the admin credentials
        if (loginBean.getUsername().equals(ADMIN_USERNAME) && loginBean.getPassword().equals(ADMIN_PASSWORD)) {
            return "admin"; // Admin login success
        }

        Connection conn = null;
        PreparedStatement psCustomer = null;
        PreparedStatement psDriver = null;
        ResultSet rsCustomer = null;
        ResultSet rsDriver = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Validate customer login
            psCustomer = conn.prepareStatement(CUSTOMER_LOGIN_SQL);
            psCustomer.setString(1, loginBean.getUsername());
            psCustomer.setString(2, loginBean.getPassword());
            rsCustomer = psCustomer.executeQuery();
            if (rsCustomer.next()) {
                return "customer"; // Customer login success
            }

            // Validate driver login
            psDriver = conn.prepareStatement(DRIVER_LOGIN_SQL);
            psDriver.setString(1, loginBean.getUsername());
            psDriver.setString(2, loginBean.getPassword());
            rsDriver = psDriver.executeQuery();
            if (rsDriver.next()) {
                return "driver"; // Driver login success
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found.");
        } finally {
            // Close resources in the finally block
            if (rsCustomer != null) rsCustomer.close();
            if (rsDriver != null) rsDriver.close();
            if (psCustomer != null) psCustomer.close();
            if (psDriver != null) psDriver.close();
            if (conn != null) conn.close();
        }

        return "invalid"; // Invalid credentials
    }
}
