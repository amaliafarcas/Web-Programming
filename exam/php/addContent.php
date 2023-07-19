<?php
session_start();
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Connect to the database
    $conn = new mysqli("localhost:3310", "root", "", "lab6_logs");
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    $userId = $_SESSION["id"];
    $titles = $_POST["title"];
    $descriptions = $_POST["description"];
    $urls = $_POST["url"];

   // Prepare the insert statement
    $stmt = $conn->prepare("INSERT INTO lab6_logs.content (date, title, description, url, user_id) VALUES (?, ?, ?, ?, ?)");

    // Bind parameters
    $stmt->bind_param("sssss", $date, $title, $description, $url, $userId);

    // Execute the insert queries in a loop
    for ($i = 0; $i < count($titles); $i++) {
        $title = $titles[$i];
        $description = $descriptions[$i];
        $url = $urls[$i];
        $date = date("Y-m-d");
        echo($date);

        $stmt->execute();
    }

    // Close the statement and the database connection
    $stmt->close();
    $conn->close();
}

return header("location: ../pages/homepageCreator.php");
?>
