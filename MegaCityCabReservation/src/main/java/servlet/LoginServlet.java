package servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.Logindao;
import bean.Loginbean;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get login details from form and check for null values
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorMessage", "Username and password are required.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Create Loginbean object and set data
        Loginbean loginBean = new Loginbean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        // Validate user credentials and determine role
        Logindao loginDao = new Logindao();
        try {
            String userRole = loginDao.validate(loginBean);

            // Redirect based on user role
            switch (userRole) {
                case "admin":
                    response.sendRedirect("admin_home.jsp");
                    break;
                case "customer":
                    response.sendRedirect("customer_dashboard.jsp");
                    break;
                case "driver":
                    response.sendRedirect("driver_dashboard.jsp");
                    break;
                default:
                    request.setAttribute("errorMessage", "Invalid username or password. Please try again.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: Please try again later.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
