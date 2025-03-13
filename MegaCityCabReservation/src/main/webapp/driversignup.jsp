<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Driver Signup</title>
    <link rel="stylesheet" href="css/home.css">
</head>
<body>
    <section>
        <div class="login" id="login">
            <h2>Welcome to Mega City Cab Reservation!</h2>
            
            <div class="login-container">
                <h1>Driver Signup</h1>
                
                <!-- Form to handle driver registration -->
                <form method="POST" action="/DriverSignup">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" placeholder="Enter your username" required><br>

                    <label for="nic">NIC:</label>
                    <input type="text" id="nic" name="nic" placeholder="Enter your NIC" required><br>

                    <label for="address">Address:</label>
                    <input type="text" id="address" name="address" placeholder="Enter your address" required><br>

                    <label for="phone">Phone:</label>
                    <input type="tel" id="phone" name="phone" placeholder="Enter your phone number" pattern="[0-9]{10}" required><br>

                    <label for="licencenumber">Licence Number:</label>
                    <input type="text" id="licencenumber" name="licencenumber" placeholder="Enter your licence number" required><br>

                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" placeholder="Enter your email" required><br>

                    <!-- Car Type Dropdown -->
                    <label for="carType">Car Type:</label>
                    <select id="carType" name="carType" required>
                        <option value="">Select Car Type</option>
                        <option value="sedan">Sedan</option>
                        <option value="suv">SUV</option>
                        <option value="luxury">Luxury</option>
                    </select><br>

                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" placeholder="Enter your password" required><br>

                    <input type="submit" id="register" name="register" value="Register">
                </form>
                <p>Already have an account? <a href="login.jsp">Login Here</a></p>
            </div>
        </div>
    </section>
</body>
</html>