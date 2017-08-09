<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Windreams">
<title>Songoku</title>
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
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand" href="home"><strong>Songoku</strong></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">${user} <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="logout">Logout</a></li>
                        </ul>
                    </li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3">
				<div>
					<a href="#" class="list-group-item active">Menu </a>
					<ul class="list-group">
						<li class="list-group-item"><a href="links">Links</a></li>
						<li class="list-group-item"><a href="tracker">Tracker</a></li>
					</ul>
				</div>
				<!-- /.div -->
			</div>
			<!-- /.col -->
			<div class="col-md-9">
				<div>
					<ol class="breadcrumb">
						<li class="active">Home</li>
					</ol>
				</div>
				<!-- /.div -->
				
				<div class="row">
					<div class="col-md-12">
						<table id="tracker-table" class="table table-striped table-bordered" 
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Id</th>
									<th>Country Code</th>
									<th>Visit</th>
									<th>Clicked</th>
									<th>Created Date</th>
								</tr>
							</thead>
					    </table>
					</div>
				</div>
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->

	<!-- /.col -->
	<div class="col-md-12 end-box ">&copy; 2017 | &nbsp; All Rights Reserved</div>
	<!-- /.col -->
	<!--Footer end -->
	<!--Core JavaScript file  -->
	<script src="assets/js/jquery-3.2.1.min.js"></script>
	<script src="assets/js/jquery-ui.min.js"></script>
	<!--bootstrap JavaScript file  -->
	<script src="assets/js/bootstrap.js"></script>
	<script src="assets/js/jquery.dataTables.min.js"></script>
	<script src="assets/js/dataTables.bootstrap.min.js"></script>
	<script src="assets/js/moment.min.js"></script>
	<script src="assets/js/datetime.js"></script>
	
	<script>
		$(document).ready(function() {
		    $('#tracker-table').DataTable({
			    searching: false,
			    ajax: {
			        url: "trackers",
			        dataSrc: ""
			    },
			    columns: [
					{ data: "smartlinkTrackerId" },
					{ data: "countryCode" },
					{ data: "visit" },
					{ data: "clicked" },
					{ data: "trackerDate" }
				]
			});
		} );
	</script>
</body>
</html>
