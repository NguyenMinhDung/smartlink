<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
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
<!--GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
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

				<form:form id="frm-add-link" action="links" method="post" modelAttribute="userLink">
					<c:if test="${success == 1}">
						<div class="alert alert-success">
							<p>Tạo link thành công.</p>
						</div>
					</c:if>
					<c:if test="${success == -1}">
						<div class="alert alert-success">
							<p>Tạo link không thành công.</p>
						</div>
					</c:if>
				
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="metadata">Metadata:</label>
	  							<input type="text" class="form-control" id="metadata" name="metadata" required="required"
	  								value="<s:escapeBody htmlEscape="true">${metadata}</s:escapeBody>" >
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="channel">Email:</label>
	  							<input type="text" class="form-control" id="email" name="email" required="required"
	  								value="<s:escapeBody htmlEscape="true">${email}</s:escapeBody>">
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="channel">Kênh:</label>
	  							<input type="text" class="form-control" id="channel" name="channel" required="required"
	  								value="<s:escapeBody htmlEscape="true">${channel}</s:escapeBody>">
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="link">Link dự kiến :</label>
	  							<input type="text" class="form-control" id="link" name="link" 
	  								value="<s:escapeBody htmlEscape="true">${link}</s:escapeBody>" disabled="disabled">
	  								
	  							<input type="hidden" id="hdLink" value="<s:escapeBody htmlEscape="true">${link}</s:escapeBody>"/>
							</div>
						</div>
					</div>
					
					<button id="btn-add-link" type="submit" class="btn btn-primary">Thêm link</button>
				</form:form>

				
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
	<script src="assets/js/jquery-1.10.2.js"></script>
	<!--bootstrap JavaScript file  -->
	<script src="assets/js/bootstrap.js"></script>
	
	<script>
		$(document).ready(function(){
			$('#btn-add-link').click(function(){
				$("#frm-add-link").validate();
				$('#btn-add-link').prop("disabled", true);
				$('#frm-add-link').submit();
			});
		});
	</script>
</body>
</html>
