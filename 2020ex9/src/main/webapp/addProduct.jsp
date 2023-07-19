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
<form action="AddProductsController" method="post">
    <div id="productInputs">
        <div class="product-input">
            <label for="productName">Name:</label>
            <input type="text" name="productName[]" required>

            <label for="productDescription">Description:</label>
            <input type="text" name="productDescription[]" required>
        </div>
    </div>

    <!-- Allow users to add more book inputs if needed -->
    <div id="additionalProduct"></div>
    <button type="button" onclick="addProductInput()">Add Product</button>

    <button type="submit">Submit</button>
</form>

<script>
    function addProductInput() {
        var bookInputsDiv = document.getElementById("productInputs");
        var additionalBooksDiv = document.getElementById("additionalProduct");

        // Clone the book input container
        var clonedBookInput = bookInputsDiv.firstElementChild.cloneNode(true);

        // Clear the input values of the cloned container
        var clonedInputs = clonedBookInput.getElementsByTagName("product-input");
        for (var i = 0; i < clonedInputs.length; i++) {
            clonedInputs[i].value = "";
        }

        // Append the cloned container to the additional books section
        additionalBooksDiv.appendChild(clonedBookInput);
    }
</script>
</body>
</html>
