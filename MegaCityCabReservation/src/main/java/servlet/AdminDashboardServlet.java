package servlet;

import bean.DriverBean;
import dao.DriverDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminDashboard")
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DriverDao driverDAO;

    public void init() {
        driverDAO = new DriverDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<DriverBean> drivers = driverDAO.selectAllDrivers();
            request.setAttribute("drivers", drivers);
            request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to fetch driver details.");
            request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
        }
    }
}