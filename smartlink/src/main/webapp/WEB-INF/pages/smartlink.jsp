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

<form:form id="frm-add-link" action="links" method="post"
	modelAttribute="userLink">
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
				<label for="metadata">Metadata:</label> <input type="text"
					class="form-control" id="metadata" name="metadata"
					required="required"
					value="<s:escapeBody htmlEscape="true">${metadata}</s:escapeBody>">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label for="channel">Email:</label> <input type="text"
					class="form-control" id="email" name="email" required="required"
					value="<s:escapeBody htmlEscape="true">${email}</s:escapeBody>">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label for="channel">Kênh:</label> <input type="text"
					class="form-control" id="channel" name="channel"
					required="required"
					value="<s:escapeBody htmlEscape="true">${channel}</s:escapeBody>">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label for="link">Link dự kiến :</label> <input type="text"
					class="form-control" id="link" name="link"
					value="<s:escapeBody htmlEscape="true">${link}</s:escapeBody>"
					disabled="disabled"> <input type="hidden" id="hdLink"
					value="<s:escapeBody htmlEscape="true">${link}</s:escapeBody>" />
			</div>
		</div>
	</div>

	<button id="btn-add-link" type="submit" class="btn btn-primary">Thêm
		link</button>
</form:form>

<script>
	$(document).ready(function() {
		$('#btn-add-link').click(function() {
			$("#frm-add-link").validate();
			$('#btn-add-link').prop("disabled", true);
			$('#frm-add-link').submit();
		});
	});
</script>
