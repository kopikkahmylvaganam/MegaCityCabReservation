package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import bean.SignupBean;

public class SignupDao {
    // SQL query to insert a new customer
    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customers (username, nic, address, phone, email, password) VALUES (?, ?, ?, ?, ?, ?)";
    
    // Database credentials
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Megacitycab?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Kopikkah98@";

    public int registerUser(SignupBean signupBean) {
        int result = 0;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");  // For MySQL 8.0+
            
            // Establish the database connection
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement ps = conn.prepareStatement(INSERT_CUSTOMER_SQL)) {

                // Prepare the SQL statement
                ps.setString(1, signupBean.getUsername());
                ps.setString(2, signupBean.getNic());
                ps.setString(3, signupBean.getAddress());
                ps.setString(4, signupBean.getPhone());
                ps.setString(5, signupBean.getEmail());
                ps.setString(6, signupBean.getPassword());

                // Execute the query
                result = ps.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection or SQL execution error.");
            e.printStackTrace();
        }

        return result;
    }
}
