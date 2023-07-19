<?php header('Access-Control-Allow-Origin: *'); ?>


<?php

if (isset($_GET['pageno'])) {
    $pageno = $_GET['pageno'];
} else {
    $pageno = 1;
}

$no_of_records_per_page = 2;
$offset = ($pageno - 1) * $no_of_records_per_page;

$conn = new mysqli("localhost:3310", "root", "", "lab6_logs");
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$userId = $_SESSION['id'];

$total_pages_sql = "SELECT COUNT(*) FROM lab6_logs.files WHERE user_id = '$userId'";
$result = mysqli_query($conn, $total_pages_sql);
$total_rows = mysqli_fetch_array($result)[0];
$total_pages = ceil($total_rows / $no_of_records_per_page);
$index = ($pageno - 1) * $no_of_records_per_page;

$query = "SELECT * FROM lab6_logs.files WHERE user_id = '$userId' LIMIT $offset, $no_of_records_per_page";
$result = mysqli_query($conn, $query);

if (mysqli_num_rows($result) > 0) {
    echo "<table class='table' style='margin-left: 120px'>";
    echo "<tr class='bold-row'>";
    echo "<th>FileId</th>";
    echo "<th>FileName</th>";
    echo "<th>FilePath</th>";
    echo "<th>Size</th>";
    echo "</tr>";
    while ($row = mysqli_fetch_array($result)) {
        $index++;
        echo "<tr>";
        echo "<th>" . $index . "</th>";
        echo "<th class='file-name'>" . $row['filename'] . "</th>";
        echo "<th>" . $row['filepath'] . "</th>";
        echo "<th>" . $row['size'] . "</th>";
        echo "</tr>";
    }
    echo "</table>";
}

$conn->close();
?>