<?php header('Access-Control-Allow-Origin: *'); ?>


<?php

if (isset($_GET['pageno'])) {
    $pageno = $_GET['pageno'];
} else {
    $pageno = 1;
}
$no_of_records_per_page = 4;
$offset = ($pageno-1) * $no_of_records_per_page;

$conn = new mysqli("localhost:3310", "root", "", "lab6_logs");
if ($conn->connect_error)
{
    die("Connection failed: " . $conn->connect_error);
}

$total_pages_sql = "SELECT COUNT(*) FROM lab6_logs.log_reports ";
$result = mysqli_query($conn, $total_pages_sql);
$total_rows = mysqli_fetch_array($result)[0];
$total_pages = ceil($total_rows / $no_of_records_per_page);

$query = "SELECT * FROM lab6_logs.log_reports LIMIT $offset, $no_of_records_per_page";
$result = mysqli_query($conn, $query);
$index=$offset;

echo $_SESSION['id'];
if(mysqli_num_rows($result)>0){
    echo "<table class='table' style='margin-left: 120px;'>";
    echo "<tr class='bold-row'>";
    echo "<th>LogId</th>";
    echo "<th>Type</th>";
    echo "<th>Severity</th>";
    echo "<th>Date</th>";
    echo "<th>User</th>";
    echo "<th>Message</th>";
    echo "</tr>";
    echo"<tbody>";
    while ($row = mysqli_fetch_array($result)){
        $index++;
        echo "<tr>";
        echo "<th>".$index."</th>";
        echo "<th>".$row['type']."</th>";
        echo "<th>".$row['severity']."</th>";
        echo "<th>".$row['date_of_log']."</th>";
        $queryUser = "SELECT username FROM lab6_logs.users WHERE id='".$row['user_id']."'";
        $userNameResult = mysqli_query($conn, $queryUser);
        $userName = mysqli_fetch_assoc($userNameResult);
        echo "<th>".$userName['username']."</th>";
        echo "<th>".$row['message']."</th>";
        echo "</tr>";
    }
    echo"</tbody>";

    echo "</table>";
}

$conn->close();
?>