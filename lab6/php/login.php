
<?php

session_start();

$username = $password = "";
$databaseUsername = $databasePassword = "";
$userId = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {

    // connect to the database
    $conn = new mysqli("localhost:3310", "root", "", "lab6_logs");
    if ($conn->connect_error)
    {
        die("Connection failed: " . $conn->connect_error);
    }

    // check if the user credentials are in the database
    $username = $_POST["username"];
    $password = $_POST["password"];

    $query = "SELECT id, username, password FROM lab6_logs.users where username = '$username' LIMIT 1";
    $queryResult = $conn->query($query);
    if($queryResult)
    {
        $row = mysqli_fetch_row($queryResult);
        $userId= $row[0];
        $databaseUsername = $row[1];
        $databasePassword= $row[2];
    }
    if($username == $databaseUsername && $password == $databasePassword)
    {
        // if the credentials are correct, set the session variables
        $_SESSION["username"] = $username;
        $_SESSION["id"] = $userId;

        // redirect the user to the homepage
        header("Location: ../pages/homepageMain.php");
    }
    else
    {
        echo "Incorrect credentials";
    }

    // close the database connection
    $conn->close();
}

?>
