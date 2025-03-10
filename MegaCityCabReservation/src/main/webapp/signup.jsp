<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign up</title>
<link rel="stylesheet" href="css/home.css">
</head>
<body>
<section>
    <div class="login" id="login">
        <h2>Welcome to Mega City Cab Reservation!</h2>
        
        <div class="login-container">
        <h1> Customer Signup</h1>
        
        <!-- Display error message if registration fails -->
        <% if (request.getAttribute("errorMessage") != null) { %>
            <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
        <% } %>

        <form method="POST" action="Signup">
            <!-- Customer Registration Number (Auto-generated in the backend, optional in form) -->
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" placeholder="Enter your username" required><br>
            
            <label for="nic">NIC:</label>
            <input type="text" id="nic" name="nic" placeholder="Enter your NIC" required><br>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" placeholder="Enter your address" required><br>

            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" placeholder="Enter your phone number" required><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required><br>

            <input type="submit" id="register" name="register" value="Register">
        </form>
        <p>Already have an account? <a href="login.jsp">Login Here</a></p>
        
        </div>
        <br> 
    </div>
</section>
</body>
</html>