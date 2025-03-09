<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book a Cab</title>
<link rel="stylesheet" href="css/styless.css"> <!-- Link to your CSS file -->
</head>
<body>
    <section>
        <div class="booking" id="booking">
            <h2>Book Your Cab Now!</h2>
            <br><br>
            <div class="booking-container">
                <h1>Book a Cab</h1>
                
                <!-- Display error message if booking fails -->
                <% if (request.getAttribute("errorMessage") != null) { %>
                    <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
                <% } %>

                <!-- Form action updated to call the BookingServlet -->
                <form method="POST" action="BookingServlet">
                    <label for="customerName">Customer Name:</label>
                    <input type="text" id="customerName" name="customerName" placeholder="Enter your name" required><br>

                    <label for="pickupLocation">Pickup Location:</label>
                    <input type="text" id="pickupLocation" name="pickupLocation" placeholder="Enter pickup location" required><br>

                    <label for="destination">Destination:</label>
                    <input type="text" id="destination" name="destination" placeholder="Enter destination" required><br>

                    <label for="carType">Choose Car Type:</label>
                    <select id="carType" name="carType" required>
                        <option value="Sedan">Sedan</option>
                        <option value="SUV">SUV</option>
                        <option value="Luxury">Luxury</option>
                    </select><br>

                    <label for="bookingDate">Booking Date:</label>
                    <input type="date" id="bookingDate" name="bookingDate" required><br>

                    <label for="bookingTime">Booking Time:</label>
                    <input type="time" id="bookingTime" name="bookingTime" required><br><br>
            
                    <input type="submit" id="book" name="book" value="Book Now">
                </form>
                <p>Go back to <a href="customer_dashboard.jsp">Dashboard</a></p>
            </div>
        </div>
    </section>
</body>
</html>