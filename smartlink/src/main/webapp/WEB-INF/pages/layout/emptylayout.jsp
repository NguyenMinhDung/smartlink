<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="Windreams">
	<title><tiles:getAsString name="title" /></title>
	<!-- Bootstrap core CSS -->
	<link href="assets/css/bootstrap.css" rel="stylesheet">
	<!-- Fontawesome core CSS -->
	<link href="assets/css/font-awesome.min.css" rel="stylesheet" />
	<!--GOOGLE FONT -->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans'
		rel='stylesheet' type='text/css'>
	<!-- custom CSS here -->
	<link href="assets/css/style.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<tiles:insertAttribute name="body" />
	</div>

	<!--Core JavaScript file  -->
	<script src="assets/js/jquery-1.10.2.js"></script>
	<!--bootstrap JavaScript file  -->
	<script src="assets/js/bootstrap.js"></script>
</body>
</html>