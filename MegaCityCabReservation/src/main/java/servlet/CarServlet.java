package servlet;

import dao.CarDao;
import bean.CarBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/Car")
public class CarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle GET requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            int carId = Integer.parseInt(request.getParameter("carId"));
            CarDao carDao = new CarDao();
            CarBean car = carDao.getCarById(carId);
            request.setAttribute("car", car);
            request.getRequestDispatcher("edit_car.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int carId = Integer.parseInt(request.getParameter("carId"));
            CarDao carDao = new CarDao();
            carDao.deleteCar(carId);
            response.sendRedirect("CarServlet");
        } else {
            // View all cars
            CarDao carDao = new CarDao();
            List<CarBean> carList = carDao.getAllCars();
            request.setAttribute("carList", carList);
            request.getRequestDispatcher("car.jsp").forward(request, response);
        }
    }

    // Handle POST requests for adding/updating cars
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carType = request.getParameter("car_type");
        double baseFare = Double.parseDouble(request.getParameter("base_fare"));
        double perKmRate = Double.parseDouble(request.getParameter("per_km_rate"));
        String carIdStr = request.getParameter("car_id");

        CarDao carDao = new CarDao();
        CarBean car = new CarBean();
        car.setCarType(carType);
        car.setBaseFare(baseFare);
        car.setPerKmRate(perKmRate);

        if (carIdStr != null && !carIdStr.isEmpty()) {
            car.setCarId(Integer.parseInt(carIdStr));
            carDao.updateCar(car);
        } else {
            carDao.addCar(car);
        }

        response.sendRedirect("CarServlet");
    }
}

