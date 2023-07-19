<%@ page import="com.example.examplejsp.model.File" %>
<%@ page import="java.util.List" %>
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

  com.example.examplejsp.model.User user = (com.example.examplejsp.model.User) request.getSession().getAttribute("user");
  if (user != null) {
    int currentPage = (int) request.getSession().getAttribute("currentPage");
    int recordsPerPage = (int) request.getSession().getAttribute("recordsPerPage");
    int index = (currentPage-1)*recordsPerPage + 1;
%>
<h1>Files</h1>

<table>
  <thead>
  <tr>
    <th>Index</th>
    <th>Filename</th>
    <th>Filepath</th>
    <th>Size</th>
  </tr>
  </thead>
  <tbody>
  <%-- Retrieve the search results from the request attributes --%>
  <%
    List<File> searchResults = (List<File>) request.getSession().getAttribute("currentPageResults");
    if(searchResults != null && !searchResults.isEmpty()) {
      for (File files : searchResults) {
        String filename = files.getFilename();
        String filepath = files.getFilepath();
        int size = files.getSize();

  %>
  <tr>
    <td><%= index %></td>
    <td class='file-name'><%= filename %></td>
    <td><%= filepath %></td>
    <td><%= size %></td>
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

<form action="GetFilesController" method="get">
    <input type="submit" name="step" value="prev">
</form>

<form action="GetFilesController" method="get">
  <input type="submit" name="step" value="next">
</form>

<button onclick="location.href='addBulk.jsp'">Add Files</button>

<div id="most-frequent-name"></div>

<script>
  // Function to compute the most frequent name
  function computeMostFrequentName() {
    const fileNameElements = document.querySelectorAll('.file-name'); // Assuming you add the class "file-name" to the column containing file names

    let nameCounts = <%= session.getAttribute("viewedFiles") %>;

    const array = nameCounts === null ? {} : { ...nameCounts };

    fileNameElements.forEach((element) => {
      const name = element.innerHTML.trim();
      if (array[name]) {
        array[name]++;
      } else {
        array[name] = 1;
      }
      console.log(name);
      console.log(array[name]);
    });

    let mostFrequentName = null;
    let highestCount = 0;

    Object.keys(array).forEach((name) => {
      const count = array[name];
      if (count > highestCount) {
        mostFrequentName = name;
        highestCount = count;
      }
    });

    const mostFrequentNameElement = document.getElementById('most-frequent-name');
    console.log(mostFrequentName);

    mostFrequentNameElement.textContent = `Most frequent name: ` + mostFrequentName;

    // Store the array in the session using AJAX
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "UpdateSession", true);
    xhr.setRequestHeader("Content-Type", "application/json");

    const jsonData = JSON.stringify(array);
    xhr.send(jsonData);
  }

  // Call the function to compute the most frequent name
  computeMostFrequentName();
</script>


</body>
</html>
