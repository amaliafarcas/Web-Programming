<?php
session_start();


// connect to the database
$conn = new mysqli("localhost:3310", "root", "", "lab6_logs");
if ($conn->connect_error)
{
    die("Connection failed: " . $conn->connect_error);
}

$userId = $_SESSION["id"];

$type = $_GET["type"];
$severity = $_GET["severity"];

if ($type == "")
    $query = "SELECT * FROM lab6_logs.log_reports WHERE severity = '$severity'";
elseif ($severity == "")
    $query = "SELECT * FROM lab6_logs.log_reports WHERE type = '$type'";
else
    $query = "SELECT * FROM lab6_logs.log_reports WHERE type = '$type' AND severity = '$severity'";

$queryResult = $conn->query($query);

$logs = array();

while ($row = mysqli_fetch_array($queryResult)) {
    $log = array();
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