package dao;

import java.sql.*;
import bean.Loginbean;

public class Logindao {
    private static final String CUSTOMER_LOGIN_SQL = "SELECT * FROM customers WHERE username = ? AND password = ?";
    private static final String DRIVER_LOGIN_SQL = "SELECT * FROM drivers WHERE username = ? AND password = ?";

    // This method validates user credentials and their roles
    public String validate(Loginbean loginBean) throws SQLException {
        // Hardcoded admin credentials
        String adminUsername = "admin";
        String adminPassword = "admin";
        
        // Check if the entered credentials match the admin credentials
        if (loginBean.getUsername().equals(adminUsername) && loginBean.getPassword().equals(adminPassword)) {
            return "admin"; // Admin login success
        }

        // Validate customer login from the database
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/your_database", "root", "your_password");
             PreparedStatement psCustomer = conn.prepareStatement(CUSTOMER_LOGIN_SQL);
             PreparedStatement psDriver = conn.prepareStatement(DRIVER_LOGIN_SQL)) {

            // Validate customer login
            psCustomer.setString(1, loginBean.getUsername());
            psCustomer.setString(2, loginBean.getPassword());
            ResultSet rsCustomer = psCustomer.executeQuery();
            if (rsCustomer.next()) {
                return "customer"; // Customer login success
            }

            // Validate driver login
            psDriver.setString(1, loginBean.getUsername());
            psDriver.setString(2, loginBean.getPassword());
            ResultSet rsDriver = psDriver.executeQuery();
            if (rsDriver.next()) {
                return "driver"; // Driver login success
            }
        }

        return "invalid"; // Invalid credentials
    }
}
