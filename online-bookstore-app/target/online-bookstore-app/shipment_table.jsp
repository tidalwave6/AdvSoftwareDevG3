<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

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
                
                <form method="post">
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
                            
                            <%
                                try
                                {
                                    Class.forName("com.mysql.jdbc.Driver");
                                    String url="jdbc:mysql://localhost:3306/bookstoredb";
                                    String username="root";
                                    String password="root";
                                    String query="select * from shipments";
                                    Connection conn=DriverManager.getConnection(url, username, password);
                                    Statement stmt=conn.createStatement();
                                    ResultSet rs=stmt.executeQuery(query);
                                    while(rs.next())
                                    {
                                %>
                            <tbody>
                                <tr>
                                    <td><%=rs.getInt("ShipmentID")%></td>
                                    <td><%=rs.getInt("ShipmentID")%></td>
                                    <td><%=rs.getString("ShipmentDate")%></td>
                                    <td><%=rs.getString("ShipmentProgress")%></td>
                                    <td><a href="#" class="button">Edit</a></td>
                                    <td><a href="#" class="button">Cancel</a></td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                            <%
                                rs.close();
                                stmt.close();
                                conn.close();
                           }
                           catch(Exception e)
                           {
                                e.printStackTrace();
                           }
                           %>
                         </form>
            </section>
        </main>
    </body>
</html>