<%@ page import="com.example.examplejsp.model.File" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.examplejsp.model.Question" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Questions</title>
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
<h1>Questions</h1>

<table>
  <thead>
  <tr>
    <th>Index</th>
    <th>Code</th>
    <th>Text</th>

  </tr>
  </thead>
  <tbody>
  <%-- Retrieve the search results from the request attributes --%>
  <%
    List<Question> searchResults = (List<Question>) request.getSession().getAttribute("questions");
    if(searchResults != null && !searchResults.isEmpty()) {
      for (Question files : searchResults) {
        int code = files.getId();
        String text = files.getText();

  %>
  <tr>
    <td><%= index %></td>
    <td><%= code %></td>
    <td><%= text %></td>
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

<button onclick="location.href='addBulk.jsp'">Make quiz</button>

</body>
</html>
