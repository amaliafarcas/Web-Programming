<?php
session_start();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete Log</title>
    <link rel="stylesheet" type="text/css" href="../style/main.css">
</head>

<body>
<div class="container-main container-login">
    <div class="wrap-main wrap-login">

        <span class="title-main">
                My Logs
        </span>

        <?php include '../php/getMyLogs.php'; ?>


        <div class="pagination">
            <button class='pages'><a href="?pageno=1">First</a></button>&emsp;
            <button class="pages <?php if ($pageno <= 1) {
                echo 'disabled';
            } ?>">
                <a href="<?php if ($pageno <= 1) {
                    echo '#';
                } else {
                    echo "?pageno=" . ($pageno - 1);
                } ?>">Prev</a>
            </button>&emsp;
            <button class="pages <?php if ($pageno >= $total_pages) {
                echo 'disabled';
            } ?>">
                <a href="<?php if ($pageno >= $total_pages) {
                    echo '#';
                } else {
                    echo "?pageno=" . ($pageno + 1);
                } ?>">Next</a>
            </button>&emsp;
            <button class='pages'><a href="?pageno=<?php echo $total_pages; ?>">Last</a></button>&emsp;
        </div>


        <div class="pagination">
            <div class="homepage-button" style="margin-left: 70px; margin-top: 10px">
                <button class="button" type="button">
                    <a href="homepageMain.php">Homepage</a>
                </button>
            </div>

        <div class="wrap-button">
            <input class ="button" type="button" onclick="location.href='addPage.html'" value="Add Log">
        </div>

        </div>

    </div>
</div>

</body>
</html>
