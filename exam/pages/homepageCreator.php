<?php
session_start();

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Homepage</title>
    <link rel="stylesheet" type="text/css" href="../style/main.css">
</head>

<body>
<div class="container-main container-login">
    <div class="wrap-main wrap-login">

        <span class="title-main">
            All Content
        </span>

        <?php include '../php/getContent.php'; ?>

        <div class="wrap-button">
            <input class ="button" type="button" onclick="location.href='addBulk.html'" value="Add Content">
        </div>

    </div>
</div>

</body>
</html>
