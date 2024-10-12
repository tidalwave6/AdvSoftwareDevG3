<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link rel="stylesheet" type="text/css" href="css/specific.css">
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

    <title>Shipment Tracking - Bookstore</title>
</head>
<body>
    <jsp:include page="nav-header.jsp"/>
    <main>
        <section class="form-section animated">
            <a href="order.jsp" class="back-arrow">
                <img src="images/back-arrow.png" alt="Back" />
            </a>

            <h1>Shipment Tracking</h1>
            <p>Order Number: 100001</p>
            <p>Expected Delivery time : 3 Days</p>

            <ul>
                <li>
                    <div class="gap"></div>
                    <div class="progress one">
                        <i class="uil uil-check"></i>
                    </div>
                    <p>Shipping Soon</p>
                </li>
                <li>
                    <div class="gap"></div>
                    <div class="progress two">
                        <i class="uil uil-check"></i>
                    </div>
                    <p>Shipped</p>
                </li>
                <li>
                    <div class="gap"></div>
                    <div class="progress three">
                        <i class="uil uil-check"></i>
                    </div>
                    <p>On the way</p>
                </li>
                <li>
                    <div class="gap"></div>
                    <div class="progress four">
                        <i class="uil uil-check"></i>
                    </div>
                    <p>Out for delivery</p>
                </li>
                <li>
                    <div class="gap"></div>
                    <div class="progress five">
                        <i class="uil uil-check"></i>
                    </div>
                    <p>Delivered</p>
                </li>
            </ul>
    
        </section>

    </main>

   <script>
        const one = document.querySelector(".one");
        const two = document.querySelector(".two");
        const three = document.querySelector(".three");

        one.classList.add("active");
        two.classList.add("active");
        three.classList.add("active");
   </script>
</body>
</html>