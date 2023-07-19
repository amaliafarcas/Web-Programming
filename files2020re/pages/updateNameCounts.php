<?php
session_start();

// Check if the request is a POST request
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Get the JSON data from the request body
    $data = json_decode(file_get_contents('php://input'), true);

    // Update the session variable with the new name counts
    $_SESSION['nameCounts'] = $data;

    // Send a response to indicate the successful update
    http_response_code(200);
    echo 'Name counts updated successfully.';
} else {
    // Send an error response for non-POST requests
    http_response_code(400);
    echo 'Invalid request.';
}
