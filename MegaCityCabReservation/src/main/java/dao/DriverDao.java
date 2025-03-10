package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import bean.DriverBean;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverDao {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Megacitycab?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Kopikkah98@";

    // Method to insert a new driver into the database
    public boolean registerDriver(DriverBean driver) {
        boolean result = false;
        
        String sql = "INSERT INTO drivers (username, nic, address, phone, licence_number, email, car_id, password) " +
                     "VALUES (?, ?, ?, ?, ?, ?, (SELECT car_id FROM cars WHERE car_type = ? LIMIT 1), ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, driver.getUsername());
            stmt.setString(2, driver.getNic());
            stmt.setString(3, driver.getAddress());
            stmt.setString(4, driver.getPhone());
            stmt.setString(5, driver.getLicenceNumber());
            stmt.setString(6, driver.getEmail());
            stmt.setString(7, driver.getCarType());
            stmt.setString(8, driver.getPassword());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                result = true;
            }
        } catch (SQLException e) {
            // Log or handle exception as appropriate
            e.printStackTrace();
        }
        
        return result;
    }

    // Method to fetch car types from the cars table
    public List<String> getCarTypes() {
        List<String> carTypes = new ArrayList<>();
        String sql = "SELECT DISTINCT car_type FROM cars";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                carTypes.add(rs.getString("car_type"));
            }
        } catch (SQLException e) {
            // Log or handle exception as appropriate
            e.printStackTrace();
        }
        
        return carTypes;
    }
}
