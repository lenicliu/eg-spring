<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="/WEB-INF/jsp/layout/head.jsp" />
<body>
	<div class="container">
		<c:import url="/WEB-INF/jsp/layout/nav.jsp" />
		<sec:authorize access="hasAuthority('AUTH_READ')">
			<h1>Auth Manager</h1>
			<form class="form-inline" action="${pageContext.request.contextPath}/admin/auths" method="GET">
				<div class="form-group">
					<label class="sr-only" for="inputKeyword">Keyword</label>
					<input type="text" class="form-control" id="inputKeyword" placeholder="Keyword" name="keyword" value="${keyword}" />
				</div>
				<button type="submit" class="btn btn-sm btn-primary">Search</button>
			</form>
			<table class="table">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${auths}" var="auth">
						<tr>
							<td>${auth.id}</td>
							<td>${auth.name}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</sec:authorize>
	</div>
</body>
</html>