<%--
  Created by IntelliJ IDEA.
  User: Ama
  Date: 5/19/2023
  Time: 12:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Edit Profile</title>
</head>
<body>
<button onclick="location.href='profile.jsp'">Home</button>

<h1>Edit Profile</h1>

<%
  String message = request.getParameter("message");
  if(message!=null){
    if(message.equals("incorrectFields")) {
      out.println("Invalid fields");
    }
  }

  // Retrieve the user object from the session
  com.example.examplejsp.model.User user = (com.example.examplejsp.model.User) request.getSession().getAttribute("user");

  // Retrieve user profile data
  String username = user.getUsername();
  String fullName = user.getFullName();
  String email = user.getEmail();
  String address = user.getAddress();
  String town = user.getTown();
  String profilePicture = user.getProfilePicture();
%>

<form action="UpdateProfileController" method="post" enctype="multipart/form-data">
  <label>Username:</label>
  <input type="text" name="username" value="<%= username %>" readonly><br>

  <label>Full Name:</label>
  <input type="text" name="fullName" value="<%= fullName %>"><br>

  <label>Email Address:</label>
  <input type="email" name="email" value="<%= email %>"><br>

  <label>Address:</label>
  <input type="text" name="address" value="<%= address %>"><br>

  <label>Home Town:</label>
  <input type="text" name="town" value="<%= town %>"><br>

  <label>Profile Picture:</label>
  <img src="profile_pictures/<%= profilePicture %>" alt="Profile Picture" width="100" height="100"><br>
  <input type="file" name="profilePicture"><br>

  <input type="submit" value="Update Profile">
</form>

</body>
</html>

