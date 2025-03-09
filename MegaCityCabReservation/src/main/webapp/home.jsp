<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>The Outer Clove Restaurant</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navbar Section -->
<nav class="navbar navbar-expand-lg fixed-top">
    <div class="container">
        <a class="navbar-brand" href="index2.jsp">The Outer Clove <span>Restaurant</span></a>
        <button class="navbar-toggler bg-light" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="#home">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="#about">About</a></li>
                <li class="nav-item"><a class="nav-link" href="#service">Service</a></li>
                <li class="nav-item"><a class="nav-link" href="#facilities">Facilities</a></li>
                <li class="nav-item"><a class="nav-link" href="#contact">Contact</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Home Section -->
<div class="home" id="home">
    <h2>Welcome to The Outer Clove Restaurant</h2>
    <p>We are offering our service in many places in Sri Lanka.</p>
</div>

<!-- About Section -->
<section id="about">
    <h2>About Us</h2>
    <p>This extraordinary, elegant space has brought a diverse array of events to life.</p>
</section>

<!-- Services Section -->
<section id="service">
    <h2>Our Services</h2>
    <div class="service-items">
        <div class="service-item">
            <h3>Dine-in</h3>
            <p>We have the dine-in option.</p>
        </div>
        <div class="service-item">
            <h3>Delivery</h3>
            <p>We offer free delivery.</p>
        </div>
        <div class="service-item">
            <h3>24 Hours Open</h3>
            <p>Our restaurant is open 24 hours.</p>
        </div>
    </div>
</section>

<!-- Facilities Section -->
<section id="facilities">
    <h2>Facilities</h2>
    <div class="facility-items">
        <div class="facility-item">
            <h3>Parking</h3>
            <p>Safe and spacious parking available.</p>
        </div>
        <div class="facility-item">
            <h3>Swimming Pool</h3>
            <p>Enjoy our stunning and large swimming pool.</p>
        </div>
        <div class="facility-item">
            <h3>Wedding Hall</h3>
            <p>Host your special occasions in our luxury hall.</p>
        </div>
        <div class="facility-item">
            <h3>Bar</h3>
            <p>Relax at our wonderful bar with a variety of drinks.</p>
        </div>
    </div>
</section>

<!-- Contact Section with Feedback Form -->
<section id="contact">
    <h2>Contact Us</h2>
    <form action="FeedbackServlet" method="POST">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>

        <label for="message">Message:</label>
        <textarea id="message" name="message" rows="5" required></textarea><br><br>

        <input type="submit" id="submit" name="submit" value="Submit">
    </form>
</section>

<!-- Footer -->
<section>
    <div class="footer-container">
        <div class="footer-column">
            <h3 class="logo-text">The Outer Clove <span>Restaurant</span></h3>
            <p>The Outer Clove Restaurant is one of the best restaurants in Sri Lanka.</p>
            <ul class="social-icons">
                <li><a href="#"><i class="fab fa-facebook"></i></a></li>
                <li><a href="#"><i class="fab fa-twitter"></i></a></li>
                <li><a href="#"><i class="fab fa-instagram"></i></a></li>
                <li><a href="#"><i class="fab fa-linkedin"></i></a></li>
            </ul>
        </div>

        <div class="footer-column">
            <h3>Quick Links</h3>
            <ul>
                <li><a href="#home">Home</a></li>
                <li><a href="#">Promotions</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#service">Service</a></li>
                <li><a href="#">Menu</a></li>
                <li><a href="#">Reservation</a></li>
            </ul>
        </div>

        <div class="footer-column">
            <h3>Contact Us</h3>
            <ul>
                <li><i class="fas fa-phone"></i> +94675546576</li>
                <li><i class="fa-solid fa-location-dot"></i> 123, Main Street, Batticaloa</li>
                <li><i class="fas fa-envelope"></i> theoutercloverest@gmail.com</li>
            </ul>
        </div>
    </div>
    <div class="footer_bottom">&copy; All rights reserved from The Outer Clove Restaurant since 2010</div>
</section>
</body>
</html>
