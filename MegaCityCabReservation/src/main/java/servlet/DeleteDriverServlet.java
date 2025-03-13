package servlet;

import dao.DriverDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteDriver")
public class DeleteDriverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DriverDao driverDAO;

    public void init() {
        driverDAO = new DriverDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int driverId = Integer.parseInt(request.getParameter("id"));
        try {
            driverDAO.deleteDriver(driverId);
            response.sendRedirect("AdminDashboard");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to delete driver.");
            request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
        }
    }
}