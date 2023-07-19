<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Credentials: true");
header("Access-Control-Allow-Methods: PUT, GET, POST, DELETE");
header("Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept");
header("Content-Type: application/json; charset=UTF-8");
// connect to the database
$conn = new mysqli("localhost", "root", "", "lab6_logs");
if ($conn->connect_error)
{
  die("Connection failed: " . $conn->connect_error);
}

$type = $_GET["type"];
$severity = $_GET["severity"];
$pageSize = $_GET["size"];

if ($type == "")
  $query = "SELECT COUNT(*) FROM lab6_logs.log_reports WHERE severity = '$severity'";
elseif ($severity == "")
  $query = "SELECT COUNT(*) FROM lab6_logs.log_reports WHERE type = '$type'";
else
  $query = "SELECT COUNT(*) FROM lab6_logs.log_reports WHERE type = '$type' AND severity = '$severity'";

$statement = $conn->prepare($query);

$statement->execute();
$queryResult = $statement->get_result();
$count = $queryResult->fetch_array()[0];

$pages = ceil($count / $pageSize);

echo json_encode($pages);

$conn->close();


?>
