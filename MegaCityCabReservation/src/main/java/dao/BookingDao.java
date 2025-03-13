package dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import bean.BookingBean;


public class BookingDao {
    private static final String INSERT_BOOKING_SQL = "INSERT INTO bookings (customer_id, customer_name, pickup_location, destination, car_type, booking_date, booking_time, driver_id, distance, total_fare) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_DRIVER_SQL = "SELECT driver_id FROM drivers WHERE car_type = ? AND is_available = TRUE LIMIT 1";
    private static final String UPDATE_DRIVER_SQL = "UPDATE drivers SET is_available = FALSE WHERE driver_id = ?";

    // Define base fare and per-kilometer rates for each car type
    private static final double SEDAN_BASE_FARE = 50.0;
    private static final double SEDAN_PER_KM_RATE = 10.0;

    private static final double SUV_BASE_FARE = 70.0;
    private static final double SUV_PER_KM_RATE = 15.0;

    private static final double LUXURY_BASE_FARE = 100.0;
    private static final double LUXURY_PER_KM_RATE = 20.0;

    public boolean addBooking(BookingBean bookingBean) throws SQLException {
        boolean result = false;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Megacitycab?useSSL=false&serverTimezone=UTC", "root", "Kopikkah98@");
             PreparedStatement psDriver = conn.prepareStatement(FIND_DRIVER_SQL);
             PreparedStatement psBooking = conn.prepareStatement(INSERT_BOOKING_SQL)) {

            // Find an available driver
            psDriver.setString(1, bookingBean.getCarType());
            try (ResultSet rsDriver = psDriver.executeQuery()) {
                if (rsDriver.next()) {
                    int driverId = rsDriver.getInt("driver_id");

                    // Mark the driver as unavailable
                    try (PreparedStatement psUpdateDriver = conn.prepareStatement(UPDATE_DRIVER_SQL)) {
                        psUpdateDriver.setInt(1, driverId);
                        psUpdateDriver.executeUpdate();
                    }

                    // Calculate distance (you can use a distance API or hardcode for simplicity)
                    double distance = calculateDistance(bookingBean.getPickupLocation(), bookingBean.getDestination());

                    // Calculate total fare based on car type and distance
                    double totalFare = calculateTotalFare(bookingBean.getCarType(), distance);

                    // Insert the booking with billing details
                    psBooking.setInt(1, bookingBean.getCustomerId()); // Retrieve customer ID from session
                    psBooking.setString(2, bookingBean.getCustomerName());
                    psBooking.setString(3, bookingBean.getPickupLocation());
                    psBooking.setString(4, bookingBean.getDestination());
                    psBooking.setString(5, bookingBean.getCarType());
                    psBooking.setDate(6, Date.valueOf(bookingBean.getBookingDate()));
                    psBooking.setTime(7, Time.valueOf(bookingBean.getBookingTime()));
                    psBooking.setInt(8, driverId);
                    psBooking.setDouble(9, distance);
                    psBooking.setDouble(10, totalFare);

                    result = psBooking.executeUpdate() > 0;
                } else {
                    throw new SQLException("No available drivers for the selected car type.");
                }
            }
        }

        return result;
    }

    // Method to calculate distance (you can replace this with a real distance API)
    private double calculateDistance(String pickupLocation, String destination) {
        // For simplicity, return a hardcoded distance (e.g., 10 km)
        return 10.0;
    }

    // Method to calculate total fare based on car type and distance
    private double calculateTotalFare(String carType, double distance) {
        double baseFare = 0.0;
        double perKmRate = 0.0;

        switch (carType) {
            case "Sedan":
                baseFare = SEDAN_BASE_FARE;
                perKmRate = SEDAN_PER_KM_RATE;
                break;
            case "SUV":
                baseFare = SUV_BASE_FARE;
                perKmRate = SUV_PER_KM_RATE;
                break;
            case "Luxury":
                baseFare = LUXURY_BASE_FARE;
                perKmRate = LUXURY_PER_KM_RATE;
                break;
            default:
                throw new IllegalArgumentException("Invalid car type: " + carType);
        }

        return baseFare + (distance * perKmRate);
    }
}