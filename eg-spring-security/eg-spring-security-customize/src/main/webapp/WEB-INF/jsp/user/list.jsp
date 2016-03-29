<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="/WEB-INF/jsp/layout/head.jsp" />
<body>
	<div class="container">
		<c:import url="/WEB-INF/jsp/layout/nav.jsp" />
		<sec:authorize access="hasAuthority('USER_READ')">
			<h1>User Manager</h1>
			<form class="form-inline" action="${pageContext.request.contextPath}/admin/users" method="GET">
				<div class="form-group">
					<label class="sr-only" for="inputKeyword">Keyword</label>
					<input type="text" class="form-control" id="inputKeyword" placeholder="Keyword" name="keyword" value="${keyword}" />
				</div>
				<button type="submit" class="btn btn-sm btn-primary">Search</button>
				<sec:authorize access="hasAuthority('USER_WRITE')">
					<a href="${pageContext.request.contextPath}/admin/users/input" class="btn btn-sm btn-success">Create</a>
				</sec:authorize>
			</form>
			<table class="table">
				<thead>
					<tr>
						<th>Id</th>
						<th>Username</th>
						<th>--</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.id}</td>
							<td>${user.username}</td>
							<td>
								<a href="${pageContext.request.contextPath}/admin/users/view?id=${user.id}" class="btn btn-sm btn-primary">View</a>
								<sec:authorize access="hasAuthority('USER_WRITE')">
									<a href="${pageContext.request.contextPath}/admin/users/input?id=${user.id}" class="btn btn-sm btn-default">Edit</a>
									<a href="${pageContext.request.contextPath}/admin/users/delete?id=${user.id}" class="btn btn-sm btn-danger" onclick="return confirm('Really Delete?');">Delete</a>
								</sec:authorize>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</sec:authorize>
	</div>
</body>
</html>