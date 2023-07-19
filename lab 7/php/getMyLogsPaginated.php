<?php

header('Access-Control-Allow-Origin: *');

//session_start();

// connect to the database
$conn = new mysqli("localhost", "root", "", "lab6_logs");
if ($conn->connect_error)
{
  die("Connection failed: " . $conn->connect_error);
}

//$userId = $_SESSION["id"];
//
$pageSize = $_GET["pageSize"];
$page = $_GET["page"];
$userId = $_GET["userId"];
$offset = ($page - 1) * $pageSize;
$index = $offset;

$query = "SELECT * FROM lab6_logs.log_reports WHERE user_id='$userId' LIMIT ?, ?";
$statement = $conn->prepare($query);
$statement->bind_param("ss", $offset, $pageSize);
$statement->execute();
$queryResult = $statement->get_result();


$logs = array();
//while ($row = mysqli_fetch_array($queryResult)) {

while($row = $queryResult->fetch_assoc()) {
  $log = array();
  $index++;
  array_push($log, $index);
  array_push($log, $row["type"]);
  array_push($log, $row["severity"]);
  array_push($log, $row["date_of_log"]);
  array_push($log, $row["message"]);
  array_push($log, $row["id"]);
  array_push($logs, $log);
}

echo json_encode($logs);

$conn->close();

?>
