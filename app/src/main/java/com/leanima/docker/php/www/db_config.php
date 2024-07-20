<?php
define('DB_SERVER', 'localhost:3306');
define('DB_USERNAME', 'marianpelekh');
define('DB_PASSWORD', 'mH04122005Op');
define('DB_DATABASE', 'leanima_db');

function getDB() {
    $conn = new mysqli(DB_SERVER, DB_USERNAME, DB_PASSWORD, DB_DATABASE);
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
    return $conn;
}
?>
