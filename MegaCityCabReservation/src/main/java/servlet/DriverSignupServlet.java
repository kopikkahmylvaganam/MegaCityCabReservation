package servlet;

import bean.DriverBean;
import dao.DriverDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DriverSignup")
public class DriverSignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DriverDao driverDAO;

    public void init() {
        driverDAO = new DriverDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String nic = request.getParameter("nic");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String licenceNumber = request.getParameter("licencenumber");
        String email = request.getParameter("email");
        String carType = request.getParameter("carType");
        String password = request.getParameter("password");

        DriverBean newDriver = new DriverBean(username, nic, address, phone, licenceNumber, email, carType, password);

        try {
            driverDAO.insertDriver(newDriver);
            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to sign up. Please try again.");
            request.getRequestDispatcher("driversignup.jsp").forward(request, response);
        }
    }
}