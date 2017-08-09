<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div>
	<ol class="breadcrumb">
		<li class="active">Home</li>
	</ol>
</div>
<!-- /.div -->

<form:form id="frm-add-link" action="users" method="post"
	modelAttribute="userLink">
	<c:if test="${success == 1}">
		<div class="alert alert-success">
			<p>Thêm user thành công.</p>
		</div>
	</c:if>
	<c:if test="${success == -1}">
		<div class="alert alert-success">
			<p>Thêm user không thành công.</p>
		</div>
	</c:if>
	<c:if test="${success == -2}">
		<div class="alert alert-success">
			<p>User đã tồn tại.</p>
		</div>
	</c:if>

	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label for="email">Email:</label> <input type="text"
					class="form-control" id="email" name="email"
					required="required"
					value="<s:escapeBody htmlEscape="true">${email}</s:escapeBody>">
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label for="network">Network:</label> <input type="text"
					class="form-control" id="network" name="network"
					required="required"
					value="<s:escapeBody htmlEscape="true">${network}</s:escapeBody>">
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label for="smartlink">Smartlink:</label> <input type="text"
					class="form-control" id="smartlink" name="smartlink"
					required="required"
					value="<s:escapeBody htmlEscape="true">${smartlink}</s:escapeBody>">
			</div>
		</div>
	</div>

	<button id="btn-add-link" type="submit" class="btn btn-primary">Thêm user</button>
</form:form>

<script>
	$(document).ready(function() {
		
	});
</script>
