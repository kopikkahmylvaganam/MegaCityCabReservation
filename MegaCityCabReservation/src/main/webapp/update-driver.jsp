<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.DriverBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Driver</title>
</head>
<body>
    <h1>Update Driver</h1>

    <%-- Display error message if any --%>
    <% 
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
    %>
        <p style="color: red;"><%= errorMessage %></p>
    <%
    }
    %>

    <% 
    DriverBean driver = (DriverBean) request.getAttribute("driver");
    if (driver != null) {
    %>
    <form method="POST" action="UpdateDriver">
        <input type="hidden" name="driverId" value="<%= driver.getDriverId() %>">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="<%= driver.getUsername() %>" required><br>
        <label for="nic">NIC:</label>
        <input type="text" id="nic" name="nic" value="<%= driver.getNic() %>" required><br>
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="<%= driver.getAddress() %>" required><br>
        <label for="phone">Phone:</label>
        <input type="tel" id="phone" name="phone" value="<%= driver.getPhone() %>" pattern="[0-9]{10}" required><br>
        <label for="licenceNumber">Licence Number:</label>
        <input type="text" id="licenceNumber" name="licenceNumber" value="<%= driver.getLicenceNumber() %>" required><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= driver.getEmail() %>" required><br>
        <label for="carType">Car Type:</label>
        <select id="carType" name="carType" required>
            <option value="sedan" <%= driver.getCarType().equals("sedan") ? "selected" : "" %>>Sedan</option>
            <option value="suv" <%= driver.getCarType().equals("suv") ? "selected" : "" %>>SUV</option>
            <option value="luxury" <%= driver.getCarType().equals("luxury") ? "selected" : "" %>>Luxury</option>
        </select><br>
        <label for="availability">Availability:</label>
        <select id="availability" name="availability" required>
            <option value="true" <%= driver.isAvailability() ? "selected" : "" %>>Available</option>
            <option value="false" <%= !driver.isAvailability() ? "selected" : "" %>>Unavailable</option>
        </select><br>
        <input type="submit" value="Update">
    </form>
    <%
    } else {
    %>
        <p style="color: red;">Driver details not found.</p>
    <%
    }
    %>
</body>
</html>