<%@ page import="com.g3app.model.Shipment" %>

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
            <%String shipmentID = request.getParameter("id");%>
            <% Shipment shipment = new Shipment(); %>
            <% shipment = shipment.getShipmentFromID(Integer.parseInt(shipmentID)); %>

            <a href="order.jsp" class="back-arrow">
                <img src="images/back-arrow.png" alt="Back" />
            </a>

            <h1>Shipment Tracking</h1>
            <p>Shipment ID: <%=shipmentID%></p>
            <p>Expected Delivery time : 3 Days</p>

            <ul>
                <li>
                    <div class="gap"></div>
                    <div class="progress one">
                        <i class="uil uil-check"></i>
                    </div>
                    <p>In Storage</p>
                </li>
                <li>
                    <div class="gap"></div>
                    <div class="progress two">
                        <i class="uil uil-check"></i>
                    </div>
                    <p>Shipping Soon</p>
                </li>
                <li>
                    <div class="gap"></div>
                    <div class="progress three">
                        <i class="uil uil-check"></i>
                    </div>
                    <p>Shipped</p>
                </li>
                <li>
                    <div class="gap"></div>
                    <div class="progress four">
                        <i class="uil uil-check"></i>
                    </div>
                    <p>On The Way</p>
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
    
    <%  int check = 0;
        String progress = shipment.getProgress();
        switch(progress){
        case "In Storage":check =1 ;
            break;
        case "Shipping Soon":check= 2;
            break;
        case "Shipped":check = 3;
            break;
        case "On The Way":check = 4;
            break;
        case "Delivered":check = 5;
            break;
        default:check=0;
    } 
    
    %>

   <script>
       
        
        const one = document.querySelector(".one");
        one.classList.add("active");
        
        if('<%=check%>' >1){
            const two = document.querySelector(".two");
            two.classList.add("active");
        }
        
        if('<%=check%>' >2){
            const three = document.querySelector(".three");
            three.classList.add("active");
        }
        
        if('<%=check%>' >3){
            const four = document.querySelector(".four");
            four.classList.add("active");
        }
        
        if('<%=check%>' >4){
            const five = document.querySelector(".five");
            five.classList.add("active");
        }
        
        
        
   </script>
</body>
</html>