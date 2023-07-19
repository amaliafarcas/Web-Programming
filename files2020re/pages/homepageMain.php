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
            My files
        </span>

        <?php include '../php/getFiles.php'; ?>

        <div class="pagination">
            <button class="pages <?php if($pageno <= 1){ echo 'disabled'; } ?>">
                <a href="<?php if($pageno <= 1){ echo '#'; } else { echo "?pageno=".($pageno - 1); } ?>">Prev</a>
            </button>&emsp;
            <button class="pages <?php if($pageno >= $total_pages){ echo 'disabled'; } ?>">
                <a href="<?php if($pageno >= $total_pages){ echo '#'; } else { echo "?pageno=".($pageno + 1); } ?>">Next</a>
            </button>&emsp;
        </div>

        <div class="wrap-button">
            <input class ="button" type="button" onclick="location.href='addBulk.html'" value="Add Files">
        </div>

        <div id="most-frequent-name"></div> <!-- Placeholder element to display the most frequent name -->

    </div>
</div>

<script>
    // Function to compute the most frequent name
    function computeMostFrequentName() {
        const fileNameElements = document.querySelectorAll('.file-name'); // Assuming you add the class "file-name" to the column containing file names

        let nameCounts = <?php echo json_encode($_SESSION['nameCounts']); ?>;

        const array = nameCounts === null ? {} : { ...nameCounts };

        fileNameElements.forEach((element) => {
            const name = element.innerHTML.trim();
            if (array[name]) {
                array[name]++;
            } else {
                array[name] = 1;
            }
            console.log(name);
            console.log(array[name]);
        });

        let mostFrequentName = null;
        let highestCount = 0;

        Object.keys(array).forEach((name) => {
            const count = array[name];
            if (count > highestCount) {
                mostFrequentName = name;
                highestCount = count;
            }
        });

        const mostFrequentNameElement = document.getElementById('most-frequent-name');
        mostFrequentNameElement.textContent = `Most frequent name: ${mostFrequentName}`;

        // Store the updated nameCounts in the session
        fetch('updateNameCounts.php', {
            method: 'POST',
            body: JSON.stringify(array)
        })
            .then(() => console.log('Name counts updated successfully.'))
            .catch((error) => console.error('Error updating name counts:', error));
    }

    // Call the function to compute the most frequent name
    computeMostFrequentName();
</script>

</body>
</html>
