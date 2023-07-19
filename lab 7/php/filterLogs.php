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
$page = $_GET["page"];


$offset = ($page - 1) * $pageSize;
$index = $offset;

if ($type == "")
  $query = "SELECT * FROM lab6_logs.log_reports WHERE severity = '$severity' LIMIT ?, ?";
elseif ($severity == "")
  $query = "SELECT * FROM lab6_logs.log_reports WHERE type = '$type' LIMIT ?, ?";
else
  $query = "SELECT * FROM lab6_logs.log_reports WHERE type = '$type' AND severity = '$severity' LIMIT ?, ?";

$statement = $conn->prepare($query);
$statement->bind_param("ss", $offset, $pageSize);
$statement->execute();
$queryResult = $statement->get_result();
$logs = array();

while ($row = mysqli_fetch_array($queryResult)) {
  $log = array();
  $index++;
  array_push($log, $index);
  array_push($log, $row["type"]);
  array_push($log, $row["severity"]);
  array_push($log, $row["date_of_log"]);

  $queryUser = "SELECT username FROM lab6_logs.users WHERE id='".$row['user_id']."'";
  $userNameResult = mysqli_query($conn, $queryUser);
  $userName = mysqli_fetch_assoc($userNameResult);

  array_push($log, $userName['username']);
  array_push($log, $row["message"]);

  array_push($logs, $log);
}

echo json_encode($logs);

$conn->close();

?>
