<html>

<body>

    Welcome <?php echo $_POST["username"]; ?><br>
    Your mail address is: <?php echo $_POST["email"]; ?>
    Welcomexx <?php echo $_POST["password"]; ?><br>
    Your full name isxx: <?php echo $_POST["fullname"]; ?>
    Your passwordxx: <?php echo $_POST["password"]; ?>

    <?php

    $con = new mysqli("localhost", "root", "", "lab6_logs");

    if (!$con) {
        die('Could not connect: ' . mysqli_error());
    }

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $username = mysqli_real_escape_string($con, $_POST["username"]);
        $password = mysqli_real_escape_string($con, $_POST["password"]);
        $fullname = mysqli_real_escape_string($con, $_POST["fullname"]);
        $age = mysqli_real_escape_string($con, $_POST["age"]);
        $role = mysqli_real_escape_string($con, $_POST["role"]);
        $mail = mysqli_real_escape_string($con, $_POST["email"]);

        echo $_POST["username"];

        $sql = "INSERT INTO users (username, password, full_name, age, role, email) VALUES ( '$username', '$password', '$fullname','$age', '$role',  '$mail')";

        if (!mysqli_query($con, $sql)) {
            printf("%d Row inserted.\n", mysqli_affected_rows($con));
        }
        mysqli_close($con);
    }
    ?>
</body>

</html>