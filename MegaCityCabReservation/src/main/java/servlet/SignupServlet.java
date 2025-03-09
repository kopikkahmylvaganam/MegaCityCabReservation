package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.SignupDao;
import bean.SignupBean;

@WebServlet("/Signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters and handle potential null values
        String username = request.getParameter("username") != null ? request.getParameter("username").trim() : "";
        String nic = request.getParameter("nic") != null ? request.getParameter("nic").trim() : "";
        String address = request.getParameter("address") != null ? request.getParameter("address").trim() : "";
        String phone = request.getParameter("phone") != null ? request.getParameter("phone").trim() : "";
        String email = request.getParameter("email") != null ? request.getParameter("email").trim() : "";
        String password = request.getParameter("password") != null ? request.getParameter("password").trim() : "";

        // Basic validation
        if (username.isEmpty() || nic.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // Create and populate a SignupBean object
        SignupBean signupBean = new SignupBean();
        signupBean.setUsername(username);
        signupBean.setNic(nic);
        signupBean.setAddress(address);
        signupBean.setPhone(phone);
        signupBean.setEmail(email);
        signupBean.setPassword(password);

        // Register the user
        SignupDao signupDAO = new SignupDao();
        try {
            int result = signupDAO.registerUser(signupBean);
            if (result > 0) {
                response.sendRedirect("login.jsp");  // Redirect to login page on success
            } else {
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
        } catch (Exception e) {  // Handle all exceptions here
            e.printStackTrace();
            if (e.getMessage().contains("Duplicate entry")) {
                request.setAttribute("errorMessage", "Username, NIC, email, or phone already exists.");
            } else {
                request.setAttribute("errorMessage", "Unexpected error: " + e.getMessage());
            }
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }
}
