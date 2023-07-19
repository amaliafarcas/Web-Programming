<%@ page import="com.example.examplejsp.model.File" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.examplejsp.model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>My files</title>
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
<h1>Products</h1>

<form action="SearchController" method="get">
  <input type="text" name="search" placeholder="Search..">
  <input type="submit" value="Search">
</form>

<table>
  <thead>
  <tr>
    <th>Index</th>
    <th>Code</th>
    <th>Name</th>
    <th>Description</th>
    <th>Shop Product</th>
  </tr>
  </thead>
  <tbody>
  <%-- Retrieve the search results from the request attributes --%>
  <%
    List<Product> searchResults = (List<Product>) request.getSession().getAttribute("products");
    if(searchResults != null && !searchResults.isEmpty()) {
      for (Product product : searchResults) {
        int code = product.getId();
        String name = product.getName();
        String description = product.getDescription();

  %>
  <tr>
    <td><%= index %></td>
    <td><%= code %></td>
    <td><%= name %></td>
    <td><%= description %></td>
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


<button onclick="location.href='addProduct.jsp'">Add Product</button>

<form action="AddToCartController" method="post">
  <input type="number" name="code" placeholder="Product Code">
  <input type="number" name="quantity" placeholder="Quantity">
  <input type="submit" value="Add To Cart">
</form>

<form action="GetOrdersController" method="get">
  <button type="submit">Order History</button>
</form>


</body>
</html>
