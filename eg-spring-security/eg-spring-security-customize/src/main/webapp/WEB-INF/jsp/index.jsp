<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="/WEB-INF/jsp/layout/head.jsp" />
<body>
	<div class="container">
		<c:import url="/WEB-INF/jsp/layout/nav.jsp" />
		<div class="container">
			<p>This is a customized spring security application.</p>
			<ul>
				<li>Spring</li>
				<li>Spring Boot</li>
				<li>Spring Security</li>
			</ul>
		</div>
	</div>
</body>
</html>