<?php header('Access-Control-Allow-Origin: *'); ?>


<?php


$conn = new mysqli("localhost:3310", "root", "", "lab6_logs");
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$userId = $_SESSION['id'];


$query = "SELECT * FROM lab6_logs.content";
$result = mysqli_query($conn, $query);
$index=0;

if (mysqli_num_rows($result) > 0) {
    echo "<table class='table' style='margin-left: 180px'>";
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