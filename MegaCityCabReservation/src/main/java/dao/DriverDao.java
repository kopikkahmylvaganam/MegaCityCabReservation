package dao;

import bean.DriverBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDao {
    private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/Megacitycab?useSSL=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Kopikkah98@";

    private static final String INSERT_DRIVER_SQL = "INSERT INTO drivers (username, nic, address, phone, licence_number, email, car_type, password, availability) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_DRIVERS_SQL = "SELECT * FROM drivers";
    private static final String SELECT_DRIVER_BY_ID_SQL = "SELECT * FROM drivers WHERE driver_id = ?";
    private static final String UPDATE_DRIVER_SQL = "UPDATE drivers SET username = ?, nic = ?, address = ?, phone = ?, licence_number = ?, email = ?, car_type = ?, availability = ? WHERE driver_id = ?";
    private static final String DELETE_DRIVER_SQL = "DELETE FROM drivers WHERE driver_id = ?";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Insert a new driver
    public void insertDriver(DriverBean driver) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DRIVER_SQL)) {
            preparedStatement.setString(1, driver.getUsername());
            preparedStatement.setString(2, driver.getNic());
            preparedStatement.setString(3, driver.getAddress());
            preparedStatement.setString(4, driver.getPhone());
            preparedStatement.setString(5, driver.getLicenceNumber());
            preparedStatement.setString(6, driver.getEmail());
            preparedStatement.setString(7, driver.getCarType());
            preparedStatement.setString(8, driver.getPassword()); // Store hashed password
            preparedStatement.setBoolean(9, driver.isAvailability());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // Fetch all drivers
    public List<DriverBean> selectAllDrivers() {
        List<DriverBean> drivers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DRIVERS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int driverId = rs.getInt("driver_id");
                String username = rs.getString("username");
                String nic = rs.getString("nic");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String licenceNumber = rs.getString("licence_number");
                String email = rs.getString("email");
                String carType = rs.getString("car_type");
                boolean availability = rs.getBoolean("availability");

                DriverBean driver = new DriverBean(username, nic, address, phone, licenceNumber, email, carType, "");
                driver.setDriverId(driverId);
                driver.setAvailability(availability);
                drivers.add(driver);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return drivers;
    }

    // Fetch a driver by ID
    public DriverBean selectDriverById(int driverId) {
        DriverBean driver = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DRIVER_BY_ID_SQL)) {
            preparedStatement.setInt(1, driverId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String nic = rs.getString("nic");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String licenceNumber = rs.getString("licence_number");
                String email = rs.getString("email");
                String carType = rs.getString("car_type");
                boolean availability = rs.getBoolean("availability");

                driver = new DriverBean(username, nic, address, phone, licenceNumber, email, carType, "");
                driver.setDriverId(driverId);
                driver.setAvailability(availability);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return driver;
    }

    // Update a driver
    public void updateDriver(DriverBean driver) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DRIVER_SQL)) {
            preparedStatement.setString(1, driver.getUsername());
            preparedStatement.setString(2, driver.getNic());
            preparedStatement.setString(3, driver.getAddress());
            preparedStatement.setString(4, driver.getPhone());
            preparedStatement.setString(5, driver.getLicenceNumber());
            preparedStatement.setString(6, driver.getEmail());
            preparedStatement.setString(7, driver.getCarType());
            preparedStatement.setBoolean(8, driver.isAvailability());
            preparedStatement.setInt(9, driver.getDriverId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // Delete a driver
    public void deleteDriver(int driverId) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DRIVER_SQL)) {
            preparedStatement.setInt(1, driverId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}