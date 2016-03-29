<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="javascript:void(0);">Spring Boot Security Customize</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li>
					<a href="${pageContext.request.contextPath}/messages">Message</a>
				</li>
				<sec:authorize access="hasAnyAuthority('USER_READ','ROLE_READ','AUTH_READ')">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
							Administration
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<sec:authorize access="hasAuthority('USER_READ')">
								<li>
									<a href="${pageContext.request.contextPath}/admin/users">User</a>
								</li>
							</sec:authorize>
							<sec:authorize access="hasAuthority('ROLE_READ')">
								<li>
									<a href="${pageContext.request.contextPath}/admin/roles">Role</a>
								</li>
							</sec:authorize>
							<sec:authorize access="hasAuthority('AUTH_READ')">
								<li>
									<a href="${pageContext.request.contextPath}/admin/auths">Auth</a>
								</li>
							</sec:authorize>
						</ul>
					</li>
				</sec:authorize>
			</ul>
			<sec:authorize access="isAuthenticated()">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a href="javascript:void(0);">
							<sec:authentication property="principal.username" />
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/logout">Logout</a>
					</li>
				</ul>
			</sec:authorize>
		</div>
	</div>
</nav>