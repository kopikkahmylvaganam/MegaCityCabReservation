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
            int bookingId = newBooking.getBookingId(); // Ensure Booking ID is retrieved

            // Calculate the bill dynamically
            double baseFarePerKm;

            switch (vehicleType.toLowerCase()) {
                case "sedan":
                    baseFarePerKm = 10.0;
                    break;
                case "suv":
                    baseFarePerKm = 15.0;
                    break;
                case "luxury":
                    baseFarePerKm = 20.0;
                    break;
                default:
                    baseFarePerKm = 10.0;
                    break;
            }

            double baseFare = distance * baseFarePerKm;
            double taxRate = 10.0;
            double tax = baseFare * (taxRate / 100);
            double totalAmount = baseFare + tax;


            // Create and insert bill
            BillBean bill = new BillBean();
            bill.setBookingId(bookingId);
            bill.setBaseFare(baseFare);
            bill.setTax(tax);
            bill.setTotalAmount(totalAmount);
            billDAO.insertBill(bill);

            // Update driver availability
            driverDAO.updateDriverAvailability(driver.getDriverId(), false);

            // Set booking details
            request.setAttribute("bookingId", bookingId);
            request.setAttribute("customerId", customerId);
            request.setAttribute("customerName", customerName);
            request.setAttribute("carType", vehicleType);
            request.setAttribute("pickupLocation", currentLocation);
            request.setAttribute("destination", destination);
            request.setAttribute("bookingDate", bookingDate);
            request.setAttribute("bookingTime", bookingTime);
            request.setAttribute("driverId", driver.getDriverId());
            request.setAttribute("distance", distance);

            // Bill details
            request.setAttribute("baseFare", baseFare);
            request.setAttribute("tax", tax);
            request.setAttribute("totalFare", totalAmount);

            request.getRequestDispatcher("booking_success.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing your booking. Please try again.");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
        }
    }
}
