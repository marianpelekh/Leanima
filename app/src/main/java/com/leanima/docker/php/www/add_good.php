<?php
require 'db_config.php';

$conn = getDB();

$type = $_POST['type'];
$name = $_POST['name'];
$description = $_POST['description'];
$price = $_POST['price'];
$discount = $_POST['discount'];
$imageUrl = $_POST['imageUrl'];
$size = $_POST['size'];

$sql = "INSERT INTO goods (type, name, description, price, discount, imageUrl, size) VALUES ('$type', '$name', '$description', $price, $discount, '$imageUrl', '$size')";

if ($conn->query($sql) === TRUE) {
    echo json_encode(["success" => true]);
} else {
    echo json_encode(["success" => false, "error" => $conn->error]);
}

$conn->close();
?>
