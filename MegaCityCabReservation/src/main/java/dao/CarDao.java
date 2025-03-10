package dao;

import bean.CarBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CarDao {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/Megacitycab?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Kopikkah98@";

    // Method to add a car
    public boolean addCar(CarBean car) {
        String sql = "INSERT INTO cars (car_type, base_fare, per_km_rate) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, car.getCarType());
            stmt.setDouble(2, car.getBaseFare());
            stmt.setDouble(3, car.getPerKmRate());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to get all cars
    public List<CarBean> getAllCars() {
        List<CarBean> carList = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CarBean car = new CarBean();
                car.setCarId(rs.getInt("car_id"));
                car.setCarType(rs.getString("car_type"));
                car.setBaseFare(rs.getDouble("base_fare"));
                car.setPerKmRate(rs.getDouble("per_km_rate"));
                carList.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    // Method to get a car by its ID
    public CarBean getCarById(int carId) {
        CarBean car = null;
        String sql = "SELECT * FROM cars WHERE car_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, carId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                car = new CarBean();
                car.setCarId(rs.getInt("car_id"));
                car.setCarType(rs.getString("car_type"));
                car.setBaseFare(rs.getDouble("base_fare"));
                car.setPerKmRate(rs.getDouble("per_km_rate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    // Method to update a car's details
    public boolean updateCar(CarBean car) {
        String sql = "UPDATE cars SET car_type = ?, base_fare = ?, per_km_rate = ? WHERE car_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, car.getCarType());
            stmt.setDouble(2, car.getBaseFare());
            stmt.setDouble(3, car.getPerKmRate());
            stmt.setInt(4, car.getCarId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to delete a car
    public boolean deleteCar(int carId) {
        String sql = "DELETE FROM cars WHERE car_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, carId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
