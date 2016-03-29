<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Boot Security Simple</title>
<link rel="stylesheet" href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="/webjars/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<script type="text/javascript" src="/webjars/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>Spring Boot Security Simple</h2>
		<div class="row">
			<div class="col-md-12">
				Hi&nbsp;
				<sec:authentication property="principal.username" />
				,&nbsp;
				<a href="logout">Logout</a>
			</div>
			<sec:authorize access="hasAuthority('WRITE')">
				<div class="col-md-12">
					<a href="input" class="btn btn-sm btn-success">Create</a>
				</div>
			</sec:authorize>
			<sec:authorize access="hasAuthority('READ')">
				<div class="col-md-12">
					<table class="table">
						<thead>
							<tr>
								<th>Message</th>
								<th>Created</th>
								<th>--</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${messages}" var="message">
								<tr>
									<td>${message.message}</td>
									<td>${message.created}</td>
									<td>
										<a href="view?id=${message.id}" class="btn btn-sm btn-primary">View</a>
										<sec:authorize access="hasAuthority('WRITE')">
											<a href="input?id=${message.id}" class="btn btn-sm btn-default">Edit</a>
											<a href="delete?id=${message.id}" class="btn btn-sm btn-danger" onclick="return confirm('Really Delete?');">Delete</a>
										</sec:authorize>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</sec:authorize>
		</div>
	</div>
</body>
</html>