<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<script>
	$(document).ready(function() {
		$('#tracker-table').DataTable({
			searching : false,
			ajax : {
				url : "trackers",
				dataSrc : ""
			},
			columns : [ {
				data : "smartlinkTrackerId"
			}, {
				data : "countryCode"
			}, {
				data : "visit"
			}, {
				data : "clicked"
			}, {
				data : "trackerDate"
			} ]
		});
	});
</script>
