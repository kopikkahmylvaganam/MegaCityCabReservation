package dao;

import bean.BillBean;
import java.sql.*;

public class BillDao {
    private static final String INSERT_BILL_SQL = "INSERT INTO bills (booking_id, base_fare, tax, total_amount) VALUES (?, ?, ?, ?)";

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Megacitycab?useSSL=false&serverTimezone=UTC", "root", "Kopikkah98@");
    }

    // Insert a new bill
    public void insertBill(BillBean bill) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BILL_SQL)) {
            preparedStatement.setInt(1, bill.getBookingId());
            preparedStatement.setDouble(2, bill.getBaseFare());
            preparedStatement.setDouble(3, bill.getTax());
            preparedStatement.setDouble(4, bill.getTotalAmount());
            preparedStatement.executeUpdate();
        }
    }
}