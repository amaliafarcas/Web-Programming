<%--
  Created by IntelliJ IDEA.
  User: Ama
  Date: 6/18/2023
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="AddFilesController" method="post">
    <div id="fileInputs">
        <div class="file-input">
            <label for="filename">Filename:</label>
            <input type="text" name="filename[]" required>

            <label for="filepath">Filepath:</label>
            <input type="text" name="filepath[]" required>

            <label for="size">Size:</label>
            <input type="number" name="size[]" required>
        </div>
    </div>

    <!-- Allow users to add more book inputs if needed -->
    <div id="additionalFile"></div>
    <button type="button" onclick="addFileInput()">Add File</button>

    <button type="submit">Submit</button>
</form>

<script>
    function addFileInput() {
        var bookInputsDiv = document.getElementById("fileInputs");
        var additionalBooksDiv = document.getElementById("additionalFile");

        // Clone the book input container
        var clonedBookInput = bookInputsDiv.firstElementChild.cloneNode(true);

        // Clear the input values of the cloned container
        var clonedInputs = clonedBookInput.getElementsByTagName("input");
        for (var i = 0; i < clonedInputs.length; i++) {
            clonedInputs[i].value = "";
        }

        // Append the cloned container to the additional books section
        additionalBooksDiv.appendChild(clonedBookInput);
    }
</script>
</body>
</html>
