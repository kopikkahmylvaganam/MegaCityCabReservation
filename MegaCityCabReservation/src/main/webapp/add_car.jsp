<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Car</title>
    <link rel="stylesheet" href="css/home.css">
</head>
<body>
    <section>
        <div class="login" id="login">
            <h2>Welcome to MegaCityCab!</h2>

            <div class="login-container">
                <h1>Add New Car</h1>

                <!-- Display error message if the addition failed -->
                <% if (request.getAttribute("errorMessage") != null) { %>
                    <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
                <% } %>

                <!-- Form to handle car addition -->
                <form method="POST" action="/Car">
                <input type="hidden" name="action" value="add">
                    <label for="car_type">Car Type:</label>
                    <input type="text" id="car_type" name="car_type" placeholder="Enter car type" required><br>
                    
                    <label for="vehicle_number">Vehicle Number:</label>
                    <input type="text" id="vehicle_number" name="vehicle_number" placeholder="Enter vehicle number" required><br>

                    <label for="model">Model:</label>
                    <input type="text" id="model" name="model" placeholder="Enter car model" required><br>
                    

                    <label for="base_fare">Base Fare:</label>
                    <input type="number" id="base_fare" name="base_fare" value="50.00" step="10" placeholder="Enter base fare" required><br><br>

                    <label for="per_km_rate">Per Kilometer Rate:</label>
                    <input type="number" id="per_km_rate" name="per_km_rate" step="20" placeholder="Enter per km rate" required><br><br>

                    <input type="submit" id="add_car" name="add_car" value="Add Car">
                </form>

                <p><a href="car.jsp">Back to Car Management</a></p>
            </div>
        </div>
    </section>
</body>
</html>