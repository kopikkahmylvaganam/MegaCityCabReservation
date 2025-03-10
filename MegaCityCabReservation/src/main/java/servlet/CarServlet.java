package servlet;

import dao.CarDao;
import bean.CarBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Car") // Updated annotation
public class CarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CarDao carDao;

    @Override
    public void init() throws ServletException {
        super.init();
        carDao = new CarDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            int carId = Integer.parseInt(request.getParameter("carId"));
            CarBean car = carDao.getCarById(carId);
            request.setAttribute("car", car);
            request.getRequestDispatcher("edit_car.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int carId = Integer.parseInt(request.getParameter("carId"));
            carDao.deleteCar(carId);
            response.sendRedirect("/Car"); // Updated redirect URL
        } else {
            // View all cars
            request.setAttribute("carList", carDao.getAllCars());
            request.getRequestDispatcher("car.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carType = request.getParameter("car_type");
        double baseFare = Double.parseDouble(request.getParameter("base_fare"));
        double perKmRate = Double.parseDouble(request.getParameter("per_km_rate"));
        String carIdStr = request.getParameter("car_id");

        CarBean car = new CarBean();
        car.setCarType(carType);
        car.setBaseFare(baseFare);
        car.setPerKmRate(perKmRate);

        if (carIdStr != null && !carIdStr.isEmpty()) {
            // Update car
            car.setCarId(Integer.parseInt(carIdStr));
            boolean isUpdated = carDao.updateCar(car);
            if (isUpdated) {
                response.sendRedirect("/Car"); // Updated redirect URL
            } else {
                request.setAttribute("errorMessage", "Failed to update car.");
                request.getRequestDispatcher("edit_car.jsp").forward(request, response);
            }
        } else {
            // Add new car
            boolean isAdded = carDao.addCar(car);
            if (isAdded) {
                response.sendRedirect("/Car"); // Updated redirect URL
            } else {
                request.setAttribute("errorMessage", "Failed to add car.");
                request.getRequestDispatcher("add_car.jsp").forward(request, response);
            }
        }
    }
}