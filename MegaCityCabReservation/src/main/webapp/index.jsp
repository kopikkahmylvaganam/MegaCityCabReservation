<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/home.css">
    <!-- FontAwesome -->
    <!-- <script src="https://kit.fontawesome.com/804a29e5b5.js" crossorigin="anonymous"></script> -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    
</head>
<body>
    <!-- navbarb section start here -->
    <nav class="navbar navbar-expand-lg fixed-top">
        <div class="container">
            <a class="navbar-brand" href="index.jsp"><h2>The Mega City <span>CAB</span></h2></a>
    
            <button class="navbar-toggler bg-light" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
    
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="#home">Home</a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="#about">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#service">Service</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="menu.php">Menu</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="reservation.php">Reservation</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#contact">Contact</a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">
                            <i class="fa-regular fa-user"></i>
                        </a>
                    </li>
                    
                    
                    
                    
                </ul>
    
                <!-- <form class="d-flex">
                    <div class="input-group">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <div class="input-group-append">
                            <button class="btn btn-outline-light" type="submit">
                                <i class="fas fa-search"></i> 
                            </button>
                        </div>
                    </div>
                </form> -->
            </div>
        </div>
    </nav>
    
    
    <!-- navbar finish here -->
    <!-- Home section  start-->
    <div class="home" id="home" style="position: relative;">
    <img src="img/top-view-food-banquet.jpg" alt="" style="width: 100%; height:100vh; object-fit: cover; position: relative;">
<div style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; background-image: linear-gradient(rgba(0,0,0,0.5),rgba(0,0,0,0.5));"></div>


    <div class="home-content" style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 1; text-align: center; color: #fff;">
        <h2>Welcome to The Mega City Cab   </h2>
        <p>We are offering our service in many places in Sri Lanka. 
            We have many branches like Colombo, Kandy, Batticaloa, Galle, and Ampara.
        </p>
    </div>
</div>
    <!-- home section finish here -->

    <!-- promotions -->
    <section id="promotions">
        <div class="promotion-section">
            <div class="promotion-container">
                <h2>Special Offers</h2>
                <div class="offers">
                    <div class="offer">
                        <img src="img/biriyani.jpg" alt="Food Offer 1">
                        <h3>Biriyani</h3>
                        <h4>only Rs1000</h4>
                        <p>Get 25% off on your favorite dish!</p>
                        
                    </div>
                    <div class="offer">
                        <img src="img/pasta.jpg" alt="Food Offer 2">
                        <h3>Pasta</h3>
                        <h4>only Rs1000</h4>
                        <p>Enjoy a 25% discount on selected menu items!</p>
                        
                    </div>
                    <div class="offer">
                        <img src="img/burger.jpeg" alt="Food Offer 3">
                        <h3>Burger combo</h3>
                        <h4>only Rs1200</h4>
                        <p>Save 25% on our special combos!</p>
                        
                    </div>
                </div>
            </div>
        </div>
        
    </section>
    <!-- promotions end -->
     <!-- about -->
     <div class="about" id="about">
        <div class="flex-row1">
            <div class="img-container">
                <figure class="img-gallery">
                    <img src="img/rs.jpg" alt="Description">
                </figure>
            </div>
            <div class="about-uss">
                <h4>About Us</h4>
                <p>This extraordinary, elegant space has brought a diverse array of events to life. A separate entrance and the flexibility to be partitioned into four sections makes the Oak Room even more special. In addition to weddings, the Oak Room is ideal for engagements, homecoming receptions, Poruwa ceremonies, MICE events, exhibitions, and corporate functions and dinners.</p>
            </div>
        </div>
    </div>
    

    <!-- about end -->



     <!-- Service section -->
     <section class="service" id="service">
    <h2>Our Services and Facilities</h2>
    <p>Our aim is to make your day special with our best Service and Facilities</p>
    <h2>Services</h2>
    <div class="service-items">
        <div class="service-item">
            <i class="fa-solid fa-credit-card"></i>
            <h3>Online Payment</h3>
            <p>We accept online payment with all types of cards</p>
        </div>

        <div class="service-item">
            <i class="fa-solid fa-shield"></i>
            <h3>Safety First</h3>
            <p>Your safety is our first priority</p>
        </div>

        <div class="service-item">
            <i class="fa-solid fa-clock"></i>
            <h3>24 Hours Service</h3>
            <p>We are available 24 hours.</p>
        </div>
    </div>
</section>

    <!-- service section end -->
    <!-- facilities start -->

    <section class="facilities" id="facilities">
        <h2>Facilities</h2>
        
        <div class="facility-items">
            <div class="facility-item">
                
                <h3>Parking</h3>
                <img src="img/parking.jpg" alt="">
                <p>We are offering one of the safe and best parking facility.</p>
                <p>More than 100 vehicles able to park at the time.</p>
                
            </div>

            <div class="facility-item">
                
                <h3>Swimming Pool</h3>
                <img src="img/swim.jpg" alt="">
                
                
                <p>We have a stunning and large swiimming pool.</p> 
                <p> We offer a variety of aquatic escapes for guests of all ages to enjoy during their stay.</p>
                
            </div>

            <div class="facility-item">
                
                <h3>Wedding hall</h3>
                <img src="img/wedhall.jpg" alt="">
                <p>We offer a big and luxury hall. You can celebrate you special occations here</p>
                <p>1000 people can gather at the time.Make your day more special with us.</p>
                
            </div>


            <div class="facility-item">
                
                <h3>Bar</h3>
                <img src="img/cocktails.jpg" alt="">
                <p>We are offering one of wonderfull bar</p>
                <p>You can enjoy your drinks,cocktails,fresh juises and many more</p>
                <p>More than 100 people can sit at the time.</p>
                
            </div>
        </div>
    </section>

    <!-- enquiry -->

    <section id="contact">
        <h2>Contact Us</h2>
        <div class="container" >
            
            <div class="left-side">
                <h4>we are here to get your Enquiries and feedbacks so feel free to contact</h4>
                <img src="img/contact.jpg" alt="Description of the image">
                
            </div>
            <div class="right-side">
                <div class="feedback-container">
                    <!-- <h2>Feedback / Enquiry Form</h2> -->
                    <form id="feedbackForm" action="index2.php" method="POST">
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" required><br>
        
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required><br>
        
                    <label for="message">Message:</label>
                    <textarea id="message" name="message" rows="5" required></textarea><br><br>
                        
                    <input type="submit" class="special-submit" id="submit" name="submit" value="Submit" onclick="sendmessage">
                    </form>
                </div>
            </div>
        </div>
    </section>
    <!-- enquiry end -->

   

    <!-- footer -->
    <section>
        <div class="footer-container">
            <div class="footer-column">
                <h3 class="logo-text">The Mega City <span> CAB</span> </h3>
                <p>The mega city cab is one of the best cab service in Srilanka</p>
                <ul class="social-icons">
                    <li><a href="#"><i class="fab fa-facebook"></i></a></li>
                    <li><a href="#"><i class="fab fa-twitter"></i></a></li>
                    <li><a href="#"><i class="fab fa-instagram"></i></a></li>
                    <li><a href="#"><i class="fab fa-linkedin"></i></a></li>

                </ul>
                
            </div>
            <div class="footer-column">
                <h3>Quick links</h3>
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
                    <li><i class="fa-solid fa-location-dot"></i> 123, main street, Batticaloa</li>
                    <li><i class="fas fa-envelope"></i> themegacitycab@gmail.com</li>
                </ul>
            </div>

        </div>
        <div class="footer_bottom">&copy; All rights reserved from The Mega City Cab since-2020</div>

    </section>
    <!-- footer end -->
    

    <!-- Bootstrap JS (with Popper) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>