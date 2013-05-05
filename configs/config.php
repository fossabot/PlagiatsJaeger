<?php

if ($_SERVER['SERVER_NAME'] == 'localhost') {
	$logData['host'] = 'localhost';
	$logData['user'] = 'root';
	$logData['pass'] = 'root';
	$logData['database'] = 'plagiatsjaeger';
	$logData['debug'] = true;
	require_once '../../mail.php';

	$root = 'http://localhost:8888/webseiten/plagiatsjaeger-app/htdocs/';

} else {
	require_once '../../database.php';
	require_once '../../mail.php';
	
	$logData['debug'] = true;
	
	$root = 'http://192.168.4.28/';
}

//DEBUG
if($logData['debug'] == true) {
	ini_set("display_errors", 1);
	error_reporting(1);
}
else {

	ini_set("display_errors", 0);
	error_reporting(0);
}
?>