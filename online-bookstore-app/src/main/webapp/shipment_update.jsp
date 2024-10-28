<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.g3app.model.Shipment" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <title>Shipment Update</title>
    </head>
    <body>
        <main>
            <section class="form-section animated">
        <%String shipmentID = request.getParameter("id");%>
        <% Shipment shipment = new Shipment(); %>
        <% shipment = shipment.getShipmentFromID(Integer.parseInt(shipmentID)); %>
        <h1 class="formTitle">Update Shipment Info</h1>
        <p>Shipment ID = <%= shipmentID %></p>
        
        <form action="shipment" method="post">
                    
                    <div>
                        <label for="date">Shipment Date</label>
                        <input type="date" name="date" value="<%= shipment.getDate() %>" required>
                    </div>

                    <div class="form-group">
                        <label for="progress">Shipment Progress</label>
                        <input type="text" name="progress" value="<%= shipment.getProgress() %>" required>
                    </div>

                    <div class="form-group">
                        <label for="status">Shipment Status</label>
                        <input type="text" name="status" value="<%= shipment.getStatus() %>" required>
                    </div>
                            
                    <div>
                        <label for="id"></label>
                        <input type="hidden" name="id" value="<%=shipment.getID() %>">
                    </div>
                    
                    <div>
                        <input type="hidden" name="action" value="update">
                        <button type="submit">Update</button>
                    </div>
                </form>
            </section>
        </main>
    </body>
</html>
