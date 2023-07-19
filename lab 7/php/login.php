<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Credentials: true");
header("Access-Control-Allow-Methods: PUT, GET, POST, DELETE");
header("Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept");
header("Content-Type: application/json; charset=UTF-8");
$db_host = 'localhost';
$db_username = 'root';
$db_password = '';
$db_name = 'lab6_logs';
$mysqli = new mysqli($db_host, $db_username, $db_password,$db_name);

if ($mysqli->connect_error) {
  die('Error : ('. $mysqli->connect_errno .') '. $mysqli->connect_error);
}


$postdata = file_get_contents("php://input");
$request = json_decode($postdata);
if(isset($postdata) && !empty($postdata))
{
  $pwd = mysqli_real_escape_string($mysqli, trim($request->password));
  $username = mysqli_real_escape_string($mysqli, trim($request->username));
  $sql = "SELECT * FROM users where username='$username' and password='$pwd'";
  if($result = mysqli_query($mysqli,$sql))
  {
    $rows = array();
    while($row = mysqli_fetch_assoc($result))
    {
      $rows[] = $row;
    }
    echo json_encode($rows);
  }
  else
  {
    http_response_code(404);
  }
}
?>
