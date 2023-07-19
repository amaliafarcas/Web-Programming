<?php

header('Access-Control-Allow-Origin: *');

// connect to the database
$conn = new mysqli("localhost", "root", "", "lab6_logs");
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$pageSize = $_GET["pageSize"];
$userId = $_GET["userId"];

$query = "SELECT COUNT(*) FROM lab6_logs.log_reports WHERE user_id='$userId'";
$statement = $conn->prepare($query);
$statement->execute();
$queryResult = $statement->get_result();
$count = $queryResult->fetch_array()[0];

$pages = ceil($count / $pageSize);

echo json_encode($pages);

$conn->close();


?>
