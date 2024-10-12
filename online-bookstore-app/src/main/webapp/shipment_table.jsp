<%@ page import="com.g3app.model.Shipment" %>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <title>Shipping Table - Bookstore</title>
    </head>
    <body>
        <jsp:include page="staff-nav-header.jsp"/>

        <main>
            <section class="form-section animated">
                <h1 class="formTitle">Shipping Table</h1>
                
                        <table>
                            <thead>
                                <tr>
                                    <th>Shipment ID</th>
                                    <th>OrderLine ID</th>
                                    <th>Shipping Date</th>
                                    <th>Shipping Progress</th>
                                    <th>Shipping Status</th>
                                    <th></th>
                                </tr>
                            </thead>
                            
                    
                            <tbody>
                                
                                <%
                                Shipment sment = new Shipment();
                                ArrayList<Shipment> shipments = sment.getAllShipments();
                                if (shipments != null && !shipments.isEmpty()) {
                                    for(Shipment shipment : shipments){
                                %>
                                <tr>
                                    <td><%=shipment.getID()%></td>
                                    <td><%=shipment.getID()%></td>
                                    <td><%=shipment.getDate()%></td>
                                    <td><%=shipment.getStatus()%></td>
                                    <td><a href="#" class="button">Edit</a></td>
                                   
                                    <form action="DeleteShipmentServlet" method="get">           
                                        <td> <button name = "id" value="<%=shipment.getID()%>">Cancel</button></td>
                                    </form>

                                  
                                </tr>
                                <%
                                    }
                                }

                                else {
                        %>
                                    <tr>
                                        <td colspan="5">No shipment found.</td>
                                    </tr>
                        <%
                            }
                        %>
                            </tbody>
                        </table>           
            </section>
        </main>
    </body>
</html>