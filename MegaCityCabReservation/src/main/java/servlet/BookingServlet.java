package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.BookingBean;
import dao.BookingDao;

@WebServlet("/booking")  // URL pattern to map this servlet
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve booking details from the form
        String customerName = request.getParameter("customerName");
        String pickupLocation = request.getParameter("pickupLocation");
        String destination = request.getParameter("destination");
        String carType = request.getParameter("carType");
        String bookingDate = request.getParameter("bookingDate");
        String bookingTime = request.getParameter("bookingTime");

        // Basic validation for required fields
        if (customerName == null || customerName.isEmpty() ||
            pickupLocation == null || pickupLocation.isEmpty() ||
            destination == null || destination.isEmpty() ||
            carType == null || carType.isEmpty() ||
            bookingDate == null || bookingDate.isEmpty() ||
            bookingTime == null || bookingTime.isEmpty()) {

            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }

        // Convert booking date and time strings to SQL types
        try {
            // Convert String to java.time.LocalDate and java.time.LocalTime
            LocalDate localDate = LocalDate.parse(bookingDate);  // Format: YYYY-MM-DD
            LocalTime localTime = LocalTime.parse(bookingTime);  // Format: HH:MM

            // Create BookingBean object and set data
            BookingBean booking = new BookingBean();
            booking.setCustomerName(customerName);
            booking.setPickupLocation(pickupLocation);
            booking.setDestination(destination);
            booking.setCarType(carType);
            booking.setBookingDate(localDate);  // Set LocalDate
            booking.setBookingTime(localTime);  // Set LocalTime

            // Retrieve customer ID from session
            HttpSession session = request.getSession();
            Integer customerId = (Integer) session.getAttribute("customerId");
            if (customerId != null) {
                booking.setCustomerId(customerId);
            } else {
                throw new ServletException("Customer ID not found in session.");
            }

            // Save booking details using the correct method from BookingDao
            BookingDao bookingDao = new BookingDao();
            boolean isBooked = bookingDao.addBooking(booking); // Using addBooking instead of saveBooking

            if (isBooked) {
                // Successfully booked, redirect to success page
                response.sendRedirect("booking_success.jsp");  
            } else {
                // Booking failed, forward to booking page with error message
                request.setAttribute("errorMessage", "Booking failed. Please try again.");
                request.getRequestDispatcher("booking.jsp").forward(request, response);
            }
        } catch (IllegalArgumentException e) {
            // Catching parsing issues like wrong date/time format
            request.setAttribute("errorMessage", "Invalid date or time format. Please follow the correct format.");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
        } catch (Exception e) {
            // Catching all other exceptions
            e.printStackTrace();
            request.setAttribute("errorMessage", "An unexpected error occurred. Please try again.");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
        }
    }
}