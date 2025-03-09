<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking Success</title>
<link rel="stylesheet" href="css/styless.css"> <!-- Link to your CSS file -->
</head>
<body>
    <section>
        <div class="success" id="success">
            <h2>Booking Successful!</h2>
            <br><br>
            <div class="success-container">
                <h1>Thank you for booking with Mega City Cab!</h1>
                <p>Your booking details:</p>
                <ul>
                    <li><strong>Customer ID:</strong> ${param.customerId}</li>
                    <li><strong>Customer Name:</strong> ${param.customerName}</li>
                    <li><strong>Car Type:</strong> ${param.carType}</li>
                    <li><strong>Pickup Location:</strong> ${param.pickupLocation}</li>
                    <li><strong>Destination:</strong> ${param.destination}</li>
                    <li><strong>Booking Date:</strong> ${param.bookingDate}</li>
                    <li><strong>Booking Time:</strong> ${param.bookingTime}</li>
                    <li><strong>Assigned Driver ID:</strong> ${param.driverId}</li>
                    <li><strong>Distance:</strong> ${param.distance} km</li>
                    <li><strong>Total Fare:</strong> $${param.totalFare}</li>
                </ul>
                <p><a href="customer_dashboard.jsp">Go back to Dashboard</a></p>
            </div>
        </div>
    </section>
</body>
</html>