<?php
require 'db_config.php';

$conn = getDB();

$sql = "SELECT * FROM goods";
$result = $conn->query($sql);

$goods = array();

if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $goods[] = $row;
    }
}

echo json_encode($goods);

$conn->close();
?>
