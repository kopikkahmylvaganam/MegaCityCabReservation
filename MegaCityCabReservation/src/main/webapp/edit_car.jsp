<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Car</title>
    <link rel="stylesheet" href="css/home.css"> <!-- Link to your CSS file -->
</head>
<body>
    <section>
        <div class="login" id="login">
            <h2>Welcome to MegaCityCab!</h2>

            <div class="login-container">
                <h1>Edit Car</h1>

                <!-- Check if car object is available -->
                <c:choose>
                    <c:when test="${not empty car}">
                        <!-- Form to handle car editing -->
                        <form method="POST" action="/Car">
                           <input type="hidden" name="action" value="update">
                            <input type="hidden" name="car_id" value="${car.carId}">

                            <label for="car_type">Car Type:</label>
                            <input type="text" id="car_type" name="car_type" value="${car.carType}" required><br>
                            
                            <label for="vehicle_number">Vehicle Number:</label>
                            <input type="text" id="vehicle_number" name="vehicle_number" value="${car.vehicleNumber}" required><br>

                            <label for="model">Model:</label>
                            <input type="text" id="model" name="model" value="${car.model}" required><br>
                            

                            <label for="base_fare">Base Fare:</label>
                            <input type="number" id="base_fare" name="base_fare" value="${car.baseFare}" step="0.01" required><br>

                            <label for="per_km_rate">Per Kilometer Rate:</label>
                            <input type="number" id="per_km_rate" name="per_km_rate" value="${car.perKmRate}" step="0.01" required><br>

                            <input type="submit" id="update_car" name="update_car" value="Update Car">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <p>Car details not found.</p>
                    </c:otherwise>
                </c:choose>

                <p><a href="car.jsp">Back to Car Management</a></p>
            </div>
        </div>
    </section>
</body>
</html>
