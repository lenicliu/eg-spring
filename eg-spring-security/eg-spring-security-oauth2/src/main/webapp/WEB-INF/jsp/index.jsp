<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Boot Security OAuth2</title>
<link rel="stylesheet" href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="/webjars/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<script type="text/javascript" src="/webjars/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>Spring Boot Security OAuth2</h2>
		<div class="row">
			<div class="col-md-12">
				<ul>
					<sec:authorize access="!isAuthenticated()">
						<li><a href="signin/github">Login with Github</a></li>
						<li><a href="signin/google">Login with Google</a></li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<li><a href="home">Home</a></li>
						<li><a href="logout">Logout</a></li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>