<%--
  Created by IntelliJ IDEA.
  User: Ama
  Date: 5/18/2023
  Time: 8:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<%
    String message = request.getParameter("message");
    if(message!=null){
        if(message.equals("errorRegister")) {
            out.println("Invalid fields");
        }
    }
%>
<form action="RegisterController" method="post" enctype="multipart/form-data">
    Enter username : <input type="text" name="username"> <BR>
    Enter password : <input type="password" name="password"> <BR>
    Enter name : <input type="text" name="name"> <BR>
    Enter email : <input type="text" name="email"> <BR>
    Enter address : <input type="text" name="address"> <BR>
    Enter hometown : <input type="text" name="hometown"> <BR>
    <label for="profilePicture">Profile Picture:</label>
    <input type="file" name="profilePicture" id="profilePicture" accept="image/*">
    <input type="submit" value="Register"/>
</form>
</body>
</html>
