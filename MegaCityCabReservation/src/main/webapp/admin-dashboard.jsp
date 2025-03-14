<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.DriverBean, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="css/home.css">
</head>
<body>
   <section>
   <div class="login" id="login">
   
    <h1>Driver Details</h1>

    <%-- Display error message if any --%>
    <% 
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
    %>
        <p style="color: red;"><%= errorMessage %></p>
    <%
    }
    %>

    <table border="1">
        <thead>
            <tr>
                <th>Driver ID</th>
                <th>Username</th>
                <th>NIC</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Licence Number</th>
                <th>Email</th>
                <th>Car Type</th>
                <th>Availability</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
            Object driversObj = request.getAttribute("drivers");
            if (driversObj instanceof List) {
                List<?> driversList = (List<?>) driversObj;
                for (Object driverObj : driversList) {
                    if (driverObj instanceof DriverBean) {
                        DriverBean driver = (DriverBean) driverObj;
            %>
                <tr>
                    <td><%= driver.getDriverId() %></td>
                    <td><%= driver.getUsername() %></td>
                    <td><%= driver.getNic() %></td>
                    <td><%= driver.getAddress() %></td>
                    <td><%= driver.getPhone() %></td>
                    <td><%= driver.getLicenceNumber() %></td>
                    <td><%= driver.getEmail() %></td>
                    <td><%= driver.getCarType() %></td>
                    <td><%= driver.isAvailability() ? "Available" : "Unavailable" %></td>
                    <td>
                        <a href="UpdateDriver?id=<%= driver.getDriverId() %>">Update</a>
                        <a href="DeleteDriver?id=<%= driver.getDriverId() %>" onclick="return confirm('Are you sure you want to delete this driver?')">Delete</a>
                    </td>
                </tr>
            <%
                    }
                }
            }
            %>
        </tbody>
    </table>

    <br>
    <a href="add-driver.jsp">Add New Driver</a>
    </div>
    </section>
</body>
</html>