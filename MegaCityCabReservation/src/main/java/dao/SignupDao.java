package dao;

import java.sql.*;

import bean.SignupBean;

public class SignupDao {
	private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customers (username, nic, address, phone, email, password) VALUES (?, ?, ?, ?, ?, ?)";

    public int registerUser(SignupBean signupBean) throws SQLException {
        int result = 0;
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?user=root", "root", "Kopikkah98@");
             PreparedStatement ps = conn.prepareStatement(INSERT_CUSTOMER_SQL)) {

            ps.setString(1, signupBean.getUsername());
            ps.setString(2, signupBean.getNic());
            ps.setString(3, signupBean.getAddress());
            ps.setString(4, signupBean.getPhone());
            ps.setString(5, signupBean.getEmail());
            ps.setString(6, signupBean.getPassword());

            result = ps.executeUpdate();
        }

        return result;
    }
}

