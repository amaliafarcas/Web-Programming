<?php
header('Access-Control-Allow-Origin: *');

var_dump($_GET);
// Check if all required parameters are provided
if (!isset($_GET["type"]) || !isset($_GET["severity"]) || !isset($_GET["date"]) || !isset($_GET["userId"]) || !isset($_GET["message"])) {
  http_response_code(400);
  echo "Error: Missing parameter(s)";
  exit();
}

// Connect to the database
$conn = new mysqli("localhost", "root", "", "lab6_logs");
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

// Prepare and execute the query
$type = $_GET["type"];
$severity = $_GET["severity"];
$date = $_GET["date"];
$userId = $_GET["userId"];
$message = $_GET["message"];

$query = "INSERT INTO lab6_logs.log_reports(type, severity, date_of_log, user_id, message) VALUES (?, ?, ?, ?, ?)";
$statement = $conn->prepare($query);
$statement->bind_param("sssss", $type, $severity, $date, $userId, $message);
$statement->execute();

if ($statement->affected_rows === 0) {
  http_response_code(404);
  echo "Error: Failed to add log";
} else {
  http_response_code(200);
  echo "Log added successfully";
}

// Close the connection
$conn->close();
?>
