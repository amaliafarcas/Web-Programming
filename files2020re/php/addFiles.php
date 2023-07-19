<?php
session_start();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Connect to the database
    $conn = new mysqli("localhost:3310", "root", "", "lab6_logs");
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    $userId = $_SESSION["id"];
    $filenames = $_POST["filename"];
    $filepaths = $_POST["filepath"];
    $sizes = $_POST["size"];

    // Prepare the insert statement
    $stmt = $conn->prepare("INSERT INTO lab6_logs.files (user_id, filename, filepath, size) VALUES (?, ?, ?, ?)");

    // Bind parameters
    $stmt->bind_param("isss", $userId, $filename, $filepath, $size);

    // Execute the insert queries in a loop
    for ($i = 0; $i < count($filenames); $i++) {
        $filename = $filenames[$i];
        $filepath = $filepaths[$i];
        $size = $sizes[$i];

        $stmt->execute();
    }

    // Close the statement and the database connection
    $stmt->close();
    $conn->close();
}

return header("location: ../pages/homePageMain.php");
?>
