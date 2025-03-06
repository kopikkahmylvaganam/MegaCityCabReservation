package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.SQLException;

import dao.Logindao;
import bean.Loginbean;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Loginbean loginBean = new Loginbean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        Logindao loginDao = new Logindao();
        try {
            String userRole = loginDao.validate(loginBean);
            if (userRole.equals("admin")) {
                response.sendRedirect("admin_dashboard.jsp"); // Redirect to admin dashboard
            } else if (userRole.equals("customer")) {
                response.sendRedirect("customer_dashboard.jsp"); // Redirect to customer dashboard
            } else if (userRole.equals("driver")) {
                response.sendRedirect("driver_dashboard.jsp"); // Redirect to driver dashboard
            } else {
                request.setAttribute("errorMessage", "Invalid username or password. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
