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
            Recent Content
        </span>

        <div class="wrap-button">
            <input class ="button" type="button" onclick="location.href='homepageReader.php'" value="Back">
        </div>

        <div id="content"></div>

    </div>
</div>

<script>

    setInterval(display(), 1000);

    function display(){
        <?php include '../php/getRecentContent.php'; ?>
        <?php $pageno + 1; ?>

        const table = document.getElementById("content");
        table.setAttribute('mytable');
    }

</script>

</body>
</html>
