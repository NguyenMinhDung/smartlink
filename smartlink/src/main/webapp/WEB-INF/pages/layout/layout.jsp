<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html lang="en">
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
	<link href="assets/css/jquery-ui.min.css" rel="stylesheet" />
	<link href="assets/css/jquery-ui.theme.min.css" rel="stylesheet" />
	<link href="assets/css/dataTables.bootstrap.min.css" rel="stylesheet" />
	<!--GOOGLE FONT -->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
	<!-- custom CSS here -->
	<link href="assets/css/style.css" rel="stylesheet" />
	
	<!--Core JavaScript file  -->
	<script src="assets/js/jquery-3.2.1.min.js"></script>
	<script src="assets/js/jquery-ui.min.js"></script>
	<!--bootstrap JavaScript file  -->
	<script src="assets/js/bootstrap.js"></script>
	<script src="assets/js/jquery.dataTables.min.js"></script>
	<script src="assets/js/dataTables.bootstrap.min.js"></script>
	<script src="assets/js/moment.min.js"></script>
	<script src="assets/js/datetime.js"></script>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3">
				<tiles:insertAttribute name="menu" />
			</div>
			<!-- /.col -->
			<div class="col-md-9">
				<tiles:insertAttribute name="body" />
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->

	<!-- /.col -->
	<tiles:insertAttribute name="footer" />
</body>
</html>
