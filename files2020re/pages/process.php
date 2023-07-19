<?php
// Assuming you have already established a database connection
// and $pdo is your PDO object

// Check if form data is submitted
/*if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Get the book data from the form
    $names = $_POST['name'];
    $authors = $_POST['author'];
    $pages = $_POST['pages'];

    // Prepare the insert statement
    $sql = "INSERT INTO bookswith (name, author, pages) VALUES (?, ?, ?)";
    $stmt = $pdo->prepare($sql);

    // Execute the insert queries in a loop
    for ($i = 0; $i < count($names); $i++) {
        $name = $names[$i];
        $author = $authors[$i];
        $page = $pages[$i];

        $stmt->execute([$name, $author, $page]);
    }*/

    echo "Bulk insert completed successfully!";

?>
