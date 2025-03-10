<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Car Management</title>
    <link rel="stylesheet" href="css/home.css">
</head>
<body>
    <section>
        <div class="login">
            <h2>Welcome to Mega City Cab Reservation!</h2>

            <div class="login-container">
                <h1>Car Management</h1>

                <a href="add_car.jsp">Add New Car</a><br>

                <!-- List all cars -->
                <table border="1">
                    <tr>
                        <th>Car ID</th>
                        <th>Car Type</th>
                        <th>Base Fare</th>
                        <th>Per Km Rate</th>
                        <th>Actions</th>
                    </tr>
                    <c:forEach var="car" items="${carList}">
                        <tr>
                            <td>${car.carId}</td>
                            <td>${car.carType}</td>
                            <td>${car.baseFare}</td>
                            <td>${car.perKmRate}</td>
                            <td>
                                <a href="/Car?action=edit&carId=${car.carId}">Edit</a>
                                <a href="/Car?action=delete&carId=${car.carId}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </section>
</body>
</html>