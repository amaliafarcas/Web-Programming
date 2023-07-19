<%@ page import="com.example.examplejsp.model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Ama
  Date: 5/19/2023
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
</head>
<body>
<button onclick="location.href='profile.jsp'">Home</button>

<form action="SearchController" method="get">
    <input type="text" name="search" placeholder="Search..">
    <input type="submit" value="Search">
</form>

<table>
    <thead>
    <tr>
        <th>Profile Picture</th>
        <th>Username</th>
        <th>Name</th>
        <th>Email Address</th>
        <th>Address</th>
        <th>Home Town</th>
    </tr>
    </thead>
    <tbody>
    <%-- Retrieve the search results from the request attributes --%>
    <%
        List<User> searchResults = (List<User>) request.getSession().getAttribute("searchResults");
        if(searchResults != null && !searchResults.isEmpty()) {
            for (User profile : searchResults) {
                String profilePicture = profile.getProfilePicture();
                String username = profile.getUsername();
                String name = profile.getFullName();
                String email = profile.getEmail();
                String address = profile.getAddress();
                String homeTown = profile.getTown();

    %>
    <tr>
        <td><img src="profile_pictures/<%= profilePicture %>" alt="Profile Picture" width="50" height="50"></td>
        <td><%= username %></td>
        <td><%= name %></td>
        <td><%= email %></td>
        <td><%= address %></td>
        <td><%= homeTown %></td>
    </tr>
    <%-- End of loop --%>
    <% }} %>
    </tbody>
</table>
</body>
</html>
