<?php

session_start();


if ($_SERVER["REQUEST_METHOD"] == "POST") {

    // connect to the database
    $conn = new mysqli("localhost:3310", "root", "", "lab6_logs");
    if ($conn->connect_error)
    {
        die("Connection failed: " . $conn->connect_error);
    }
    //echo "Connected successfully";
    $userId = $_SESSION["id"];

    $logId = $_POST["logid"];

    $query = "DELETE FROM lab6_logs.log_reports WHERE id = '$logId'";
    $queryResult = $conn->query($query);

    if ($queryResult) {
        $_SESSION['delete_message'] = "Log deleted successfully.";
    } else {
        $_SESSION['delete_message'] = "Log could not be deleted.";
    }

    // close the database connection
    $conn->close();
}

return header("location: ../pages/myLogsPage.php");

?>