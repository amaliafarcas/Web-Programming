<%--
  Created by IntelliJ IDEA.
  User: Ama
  Date: 5/19/2023
  Time: 5:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String message = request.getParameter("message");
    if(message!=null){
        if(message.equals("incorrectCredentials")) {
            out.println("Invalid credentials");
        }
        else if(message.equals("successRegister")){
            out.println("Registered successfully");
        }
    }
%>

<form action="LoginController" method="post">
    Enter username : <input type="text" name="username"> <BR>
    Enter password : <input type="password" name="password"> <BR>
    <input type="submit" value="Login"/>
</form>
<button onclick="location.href='register.jsp'">Register</button>
</body>
</html>
