<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div>
	<a href="#" class="list-group-item active">Menu </a>
	<ul class="list-group">
		<li class="list-group-item"><a href="links">Links</a></li>
		<li class="list-group-item"><a href="tracker">Tracker</a></li>
		
		<sec:authorize access="hasRole('ADMIN')">
			<li class="list-group-item"><a href="users">Add user</a></li>
		</sec:authorize>
	</ul>
</div>