<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Success</title>
    <link rel="stylesheet" href="css/home.css">
</head>
<body>
    <section>
        <div class="login">
            <h2>Booking Successful!</h2>
            <div class="login-container">
                <h1>Thank you for booking with Mega City Cab!</h1>
                <p>Your booking details:</p>
                <ul>
                    <li><strong>Booking ID:</strong> ${requestScope.bookingId}</li>
                    <li><strong>Customer ID:</strong> ${requestScope.customerId}</li>
                    <li><strong>Customer Name:</strong> ${requestScope.customerName}</li>
                    <li><strong>Car Type:</strong> ${requestScope.carType}</li>
                    <li><strong>Pickup Location:</strong> ${requestScope.pickupLocation}</li>
                    <li><strong>Destination:</strong> ${requestScope.destination}</li>
                    <li><strong>Booking Date:</strong> ${requestScope.bookingDate}</li>
                    <li><strong>Booking Time:</strong> ${requestScope.bookingTime}</li>
                    <li><strong>Assigned Driver ID:</strong> ${requestScope.driverId}</li>
                    <li><strong>Distance:</strong> ${requestScope.distance} km</li>
                </ul>

                <h2>Bill Details</h2>
                <ul>
                    <li><strong>Base Fare:</strong> $${requestScope.baseFare}</li>
                    <li><strong>Tax (10%):</strong> $${requestScope.tax}</li>
                    <li><strong>Total Amount:</strong> $${requestScope.totalFare}</li>
                </ul>

                <p><a href="customer_dashboard.jsp">Go back to Dashboard</a></p>
            </div>
        </div>
    </section>
</body>
</html>
