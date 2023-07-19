
<?php

//CREATE TABLE lab6_logs.files (
//  id INT AUTO_INCREMENT PRIMARY KEY,
//  user_id INT REFERENCES lab6_logs.users(id),
//  filename VARCHAR(255) NOT NULL,
//  filepath VARCHAR(255) NOT NULL,
//  size INT NOT NULL
//);
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

    $query = "SELECT id, username, password, role FROM lab6_logs.users where username = '$username' LIMIT 1";
    $queryResult = $conn->query($query);
    if($queryResult)
    {
        $row = mysqli_fetch_row($queryResult);
        $userId= $row[0];
        $databaseUsername = $row[1];
        $databasePassword= $row[2];
        $databaseRole = $row[3];
    }
    if($username == $databaseUsername && $password == $databasePassword)
    {
        // if the credentials are correct, set the session variables
        $_SESSION["username"] = $username;
        $_SESSION["id"] = $userId;


        //echo $_SESSION['id'];
        // redirect the user to the homepage
        echo $databaseRole;
        if($databaseRole == 'creator')
            header("Location: ../pages/homepageCreator.php");
        else{
            header("Location: ../pages/homepageReader.php");
        }
    }
    else
    {
        echo "Incorrect credentials";
    }

    // close the database connection
    $conn->close();
}

?>
