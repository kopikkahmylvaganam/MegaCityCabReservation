package servlet;

import bean.BookingBean;
import bean.DriverBean;
import bean.BillBean;
import dao.BookingDao;
import dao.BillDao;
import dao.DriverDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDao bookingDAO;
    private BillDao billDAO;
    private DriverDao driverDAO;

    public void init() {
        bookingDAO = new BookingDao();
        billDAO = new BillDao();
        driverDAO = new DriverDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String customerName = request.getParameter("customerName");
        String telephone = request.getParameter("telephone");
        String currentLocation = request.getParameter("currentLocation");
        String destination = request.getParameter("destination");
        double distance = Double.parseDouble(request.getParameter("distance"));
        String vehicleType = request.getParameter("vehicleType");
        String bookingDate = request.getParameter("bookingDate");
        String bookingTime = request.getParameter("bookingTime");

        // Combine booking date and time into a single Timestamp
        Timestamp bookingDateTime = Timestamp.valueOf(bookingDate + " " + bookingTime + ":00");

        // Find a random available driver for the selected car type
        DriverBean driver = driverDAO.findAvailableDriverByCarType(vehicleType);
        if (driver == null) {
            request.setAttribute("errorMessage", "No drivers available for the selected car type.");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }

        // Create and insert booking
        BookingBean newBooking = new BookingBean();
        newBooking.setCustomerId(customerId);
        newBooking.setCustomerName(customerName);
        newBooking.setTelephone(telephone);
        newBooking.setCurrentLocation(currentLocation);
        newBooking.setDestination(destination);
        newBooking.setDistance(distance);
        newBooking.setVehicleType(vehicleType);
        newBooking.setBookingDate(bookingDateTime);

        try {
            bookingDAO.insertBooking(newBooking);

            // Calculate the bill dynamically
            double baseFarePerKm = 0;
            switch (vehicleType.toLowerCase()) {
                case "sedan":
                    baseFarePerKm = 10.0; // $10 per km for sedan
                    break;
                case "suv":
                    baseFarePerKm = 15.0; // $15 per km for SUV
                    break;
                case "luxury":
                    baseFarePerKm = 20.0; // $20 per km for luxury
                    break;
                default:
                    baseFarePerKm = 10.0; // Default to sedan fare
            }
            double baseFare = distance * baseFarePerKm;

            // Tax calculation (10% of base fare)
            double taxRate = 10.0;
            double tax = baseFare * (taxRate / 100);

            // Total amount calculation
            double totalAmount = baseFare + tax;

            // Create and insert bill
            BillBean bill = new BillBean();
            bill.setBookingId(newBooking.getBookingId());
            bill.setBaseFare(baseFare);
            bill.setTax(tax);
            bill.setTotalAmount(totalAmount);
            billDAO.insertBill(bill);

            // Update driver availability
            driverDAO.updateDriverAvailability(driver.getDriverId(), false);

            // Set customer and bill details in the request for display
            request.setAttribute("customerId", customerId); // Use customerId
            request.setAttribute("customerName", customerName);
            request.setAttribute("carType", vehicleType);
            request.setAttribute("pickupLocation", currentLocation);
            request.setAttribute("destination", destination);
            request.setAttribute("bookingDate", bookingDate); // Date part only
            request.setAttribute("bookingTime", bookingTime); // Time part only
            request.setAttribute("driverId", driver.getDriverId());
            request.setAttribute("distance", distance);
            request.setAttribute("totalFare", totalAmount);

            // Set driver and bill details in the request for display
            request.setAttribute("driver", driver);
            request.setAttribute("booking", newBooking);
            request.setAttribute("bill", bill);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing your booking. Please try again.");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("booking_success.jsp").forward(request, response);
    }
}