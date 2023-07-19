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
                Log Reports
            </span>

        <?php include '../php/getLogs.php'; ?>

        <div class="pagination">
            <button class='pages'>
                <a href="?pageno=1">First</a>
            </button>&emsp;
            <button class="pages <?php if($pageno <= 1){ echo 'disabled'; } ?>">
                <a href="<?php if($pageno <= 1){ echo '#'; } else { echo "?pageno=".($pageno - 1); } ?>">Prev</a>
            </button>&emsp;
            <button class="pages <?php if($pageno >= $total_pages){ echo 'disabled'; } ?>">
                <a href="<?php if($pageno >= $total_pages){ echo '#'; } else { echo "?pageno=".($pageno + 1); } ?>">Next</a>
            </button>&emsp;
            <button class='pages'>
                <a href="?pageno=<?php echo $total_pages; ?>">Last</a>
            </button>&emsp;
        </div>

        <div class="wrap-button">
            <input class ="button" type="button" onclick="location.href='myLogsPage.php'" value="View Personal Logs">
        </div>

        <div class="wrap-button">
            <input class ="button" type="button" onclick="location.href='filterPage.html'" value="Filter Logs">
        </div>

    </div>
</div>
</body>
</html>