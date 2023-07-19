<?php

header('Access-Control-Allow-Origin: *');

// connect to the database
$conn = new mysqli("localhost", "root", "", "lab6_logs");
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$logId = $_GET["logId"];

$query = "DELETE FROM lab6_logs.log_reports WHERE id=?";
$statement = $conn->prepare($query);
$statement->bind_param("i", $logId);
$statement->execute();

if($statement->affected_rows === 0){
  http_response_code(404);
}

$conn->close();

?>
