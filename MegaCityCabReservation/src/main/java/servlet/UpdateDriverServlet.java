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

@WebServlet("/UpdateDriver")
public class UpdateDriverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DriverDao driverDAO;

    public void init() {
        driverDAO = new DriverDao();
    }

    // Handle GET request (display update form)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int driverId = Integer.parseInt(request.getParameter("id"));
        DriverBean driver = driverDAO.selectDriverById(driverId);
        request.setAttribute("driver", driver);
        request.getRequestDispatcher("update-driver.jsp").forward(request, response);
    }

    // Handle POST request (process update form)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int driverId = Integer.parseInt(request.getParameter("driverId"));
        String username = request.getParameter("username");
        String nic = request.getParameter("nic");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String licenceNumber = request.getParameter("licenceNumber");
        String email = request.getParameter("email");
        String carType = request.getParameter("carType");
        boolean availability = Boolean.parseBoolean(request.getParameter("availability"));

        DriverBean driver = new DriverBean(username, nic, address, phone, licenceNumber, email, carType, "");
        driver.setDriverId(driverId);
        driver.setAvailability(availability);

        try {
            driverDAO.updateDriver(driver);
            response.sendRedirect("AdminDashboard");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to update driver details.");
            request.getRequestDispatcher("update-driver.jsp").forward(request, response);
        }
    }
}