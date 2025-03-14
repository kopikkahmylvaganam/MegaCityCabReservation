<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book a Cab</title>
    <link rel="stylesheet" href="css/home.css"> <!-- Link to your CSS file -->
</head>
<body>
    <section>
        <div class="login" id="login">
        <h2>Welcome to Mega City Cab Reservation!</h2>
        
        <div class="login-container">
        <h1>Book a cab now</h1>
                
                <!-- Display error message if booking fails -->
                <c:if test="${not empty errorMessage}">
                    <p style="color: red;">${errorMessage}</p>
                </c:if>

                <!-- Updated form action to match your annotation -->
                <form method="POST" action="${pageContext.request.contextPath}/booking">
                    <input type="hidden" name="userId" value="${sessionScope.userId}">
                    
                    <label for="customerName">Customer Name:</label>
                    <input type="text" id="customerName" name="customerName" placeholder="Enter your name" required><br>

                    <label for="telephone">Telephone:</label>
                    <input type="text" id="telephone" name="telephone" placeholder="Enter your telephone number" required><br>

                    <label for="currentLocation">Current Location:</label>
                    <input type="text" id="currentLocation" name="currentLocation" placeholder="Enter pickup location" required><br>

                    <label for="destination">Destination:</label>
                    <input type="text" id="destination" name="destination" placeholder="Enter destination" required><br>

                    <label for="distance">Distance (in km):</label>
                    <input type="number" id="distance" name="distance" step="0.1" placeholder="Enter distance" required><br>

                    <label for="vehicleType">Choose Car Type:</label>
                    <select id="vehicleType" name="vehicleType" required>
                        <option value="">Select Car Type</option>
                        <option value="sedan">Sedan</option>
                        <option value="suv">SUV</option>
                        <option value="luxury">Luxury</option>
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