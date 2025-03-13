package dao;

import bean.BookingBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDao {
    private static final String INSERT_BOOKING_SQL = "INSERT INTO bookings (customer_id, customer_name, telephone, current_location, destination, distance, vehicle_type, booking_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BOOKING_BY_ID_SQL = "SELECT * FROM bookings WHERE booking_id = ?";
    private static final String SELECT_ALL_BOOKINGS_BY_USER_ID_SQL = "SELECT * FROM bookings WHERE user_id = ?"; // Added this line
    private static final String UPDATE_BOOKING_SQL = "UPDATE bookings SET customer_name = ?, telephone = ?, current_location = ?, destination = ?, distance = ?, vehicle_type = ?, booking_date = ? WHERE booking_id = ?";
    private static final String DELETE_BOOKING_SQL = "DELETE FROM bookings WHERE booking_id = ?";

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Megacitycab?useSSL=false&serverTimezone=UTC", "root", "Kopikkah98@");
    }

    // Insert a new booking
    public void insertBooking(BookingBean booking) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOKING_SQL, Statement.RETURN_GENERATED_KEYS)) {
        	preparedStatement.setInt(1, booking.getCustomerId());
            preparedStatement.setString(2, booking.getCustomerName());
            preparedStatement.setString(3, booking.getTelephone());
            preparedStatement.setString(4, booking.getCurrentLocation());
            preparedStatement.setString(5, booking.getDestination());
            preparedStatement.setDouble(6, booking.getDistance());
            preparedStatement.setString(7, booking.getVehicleType());
            preparedStatement.setTimestamp(8, booking.getBookingDate());

            preparedStatement.executeUpdate();

            // Retrieve the generated booking ID
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    booking.setBookingId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Fetch a booking by ID
    public BookingBean selectBookingById(int bookingId) {
        BookingBean booking = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKING_BY_ID_SQL)) {
            preparedStatement.setInt(1, bookingId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int customerId = rs.getInt("customer_id");
                String customerName = rs.getString("customer_name");
                String telephone = rs.getString("telephone");
                String currentLocation = rs.getString("current_location");
                String destination = rs.getString("destination");
                double distance = rs.getDouble("distance");
                String vehicleType = rs.getString("vehicle_type");
                Timestamp bookingDate = rs.getTimestamp("booking_date");

                booking = new BookingBean();
                booking.setBookingId(bookingId);
                booking.setCustomerId(customerId);
                booking.setCustomerName(customerName);
                booking.setTelephone(telephone);
                booking.setCurrentLocation(currentLocation);
                booking.setDestination(destination);
                booking.setDistance(distance);
                booking.setVehicleType(vehicleType);
                booking.setBookingDate(bookingDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    // Fetch all bookings by user ID
    public List<BookingBean> selectAllBookingsByCustomerId(int customerId) { // Added this method
        List<BookingBean> bookings = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKINGS_BY_USER_ID_SQL)) {
            preparedStatement.setInt(1, customerId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int bookingId = rs.getInt("booking_id");
                String customerName = rs.getString("customer_name");
                String telephone = rs.getString("telephone");
                String currentLocation = rs.getString("current_location");
                String destination = rs.getString("destination");
                double distance = rs.getDouble("distance");
                String vehicleType = rs.getString("vehicle_type");
                Timestamp bookingDate = rs.getTimestamp("booking_date");

                BookingBean booking = new BookingBean();
                booking.setBookingId(bookingId);
                booking.setCustomerId(customerId);
                booking.setCustomerName(customerName);
                booking.setTelephone(telephone);
                booking.setCurrentLocation(currentLocation);
                booking.setDestination(destination);
                booking.setDistance(distance);
                booking.setVehicleType(vehicleType);
                booking.setBookingDate(bookingDate);

                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // Update a booking
    public void updateBooking(BookingBean booking) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKING_SQL)) {
            preparedStatement.setString(1, booking.getCustomerName());
            preparedStatement.setString(2, booking.getTelephone());
            preparedStatement.setString(3, booking.getCurrentLocation());
            preparedStatement.setString(4, booking.getDestination());
            preparedStatement.setDouble(5, booking.getDistance());
            preparedStatement.setString(6, booking.getVehicleType());
            preparedStatement.setTimestamp(7, booking.getBookingDate());
            preparedStatement.setInt(8, booking.getBookingId());

            preparedStatement.executeUpdate();
        }
    }

    // Delete a booking
    public void deleteBooking(int bookingId) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOKING_SQL)) {
            preparedStatement.setInt(1, bookingId);
            preparedStatement.executeUpdate();
        }
    }
}