<?php header('Access-Control-Allow-Origin: *'); ?>


<?php

if (isset($_GET['pageno'])) {
    $pageno = $_GET['pageno'];
} else {
    $pageno = 1;
}
$conn = new mysqli("localhost:3310", "root", "", "lab6_logs");
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}


$query = "SELECT COUNT(*) FROM lab6_logs.content ";
$result = mysqli_query($conn, $query);
$total_pages = mysqli_fetch_array($result)[0];

if($pageno>$total_pages){
    $pageno=1;
}


$no_of_records_per_page = 1;
$offset = ($pageno - 1) * $no_of_records_per_page;

$userId = $_SESSION['id'];


$query = "SELECT * FROM lab6_logs.content LIMIT $offset, $no_of_records_per_page";
$result = mysqli_query($conn, $query);
$index=0;

if (mysqli_num_rows($result) > 0) {
    echo "<table id='mytable' class='table'  style='margin-left: 180px'>";
    echo "<tr class='bold-row'>";
    echo "<th>Index</th>";
    echo "<th>Date</th>";
    echo "<th>Title</th>";
    echo "<th>Description</th>";
    echo "<th>URL</th>";
    echo "<th>User</th>";
    echo "</tr>";
    while ($row = mysqli_fetch_array($result)) {
        $index++;
        echo "<tr>";
        echo "<th>" . $index . "</th>";
        echo "<th>" . $row['date'] . "</th>";
        echo "<th>" . $row['title'] . "</th>";
        echo "<th>" . $row['description'] . "</th>";
        echo "<th>" . $row['url'] . "</th>";
        $queryUser = "SELECT username FROM lab6_logs.users WHERE id='".$row['user_id']."'";
        $userNameResult = mysqli_query($conn, $queryUser);
        $userName = mysqli_fetch_assoc($userNameResult);
        echo "<th>".$userName['username']."</th>";
        echo "</tr>";
    }
    echo "</table>";
}

$conn->close();
?>