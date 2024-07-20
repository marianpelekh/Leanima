<?php
header("Content-Type: application/json");
header("Access-Control-Allow-Origin: *"); // Дозволити всі домени, або обмежте до конкретного домену

// Налаштування бази даних
include("db_config.php");

// Обробка запитів
$action = isset($_GET['action']) ? $_GET['action'] : '';

if ($action == 'getItems') {
    $sql = "SELECT * FROM goods";
    $result = $conn->query($sql);

    $items = array();
    while($row = $result->fetch_assoc()) {
        $items[] = $row;
    }

    echo json_encode($items);
}

$conn->close();
?>
