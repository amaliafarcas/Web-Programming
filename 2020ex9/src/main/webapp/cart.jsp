<%@ page import="java.util.List" %>
<%@ page import="com.example.examplejsp.model.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cart</title>
</head>
<body>

<%
    String message = request.getParameter("message");
    if(message!=null){
        if(message.equals("editSuccess")) {
            out.println("Profile updated successfully");
        }
    }

    String user = request.getSession().getAttribute("username").toString();
    if (user != null) {
        int index = 1;
%>
<h1>Cart</h1>

<table>
    <thead>
    <tr>
        <th>Index</th>
        <th>Product Code</th>
        <th>Quantity</th>
    </tr>
    </thead>
    <tbody>
    <%-- Retrieve the search results from the request attributes --%>
    <%
        List<Order> searchResults = (List<Order>) request.getSession().getAttribute("cart");
        if(searchResults != null && !searchResults.isEmpty()) {
            for (Order product : searchResults) {
                int productCode = product.getProductId();
                int quantity = product.getQuantity();

    %>
    <tr>
        <td><%= index %></td>
        <td><%= productCode %></td>
        <td><%= quantity %></td>
        <td></td>
    </tr>
    <%-- End of loop --%>
    <%
                index++;}} %>
    </tbody>
</table>

<%
    } else {
        // Handle the case when user object is not found in the session
        out.println("User not found.");
    }
%>


<button onclick="location.href='profile.jsp'">Back</button>

<form action="ProcessOrderController" method="post">
    <button type="submit" name="action" value="cancel">Cancel</button>
    <button type="submit" name="action" value="order">Place Order</button>
</form>

</body>
</html>
