<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="/WEB-INF/jsp/layout/head.jsp" />
<body>
	<div class="container">
		<c:import url="/WEB-INF/jsp/layout/nav.jsp" />
		<h1>
			<c:if test="${readonly != 'readonly'}">
				<c:if test="${message.id == null}">Create</c:if>
				<c:if test="${message.id != null}">Update</c:if>
			</c:if>
			<c:if test="${readonly == 'readonly'}">
			View
			</c:if>
			Message
		</h1>
		<form id="input-form" method="POST" action="${pageContext.request.contextPath}/messages/submit">
			<input type="hidden" name="id" value="${message.id}" />
			<div class="form-group">
				<label for="inputMessage">Message</label>
				<input type="text" class="form-control" name="content" value="${message.content}" id="inputMessage" required="required" ${readonly} />
			</div>
			<c:if test="${readonly == 'readonly'}">
				<div class="form-group">
					<label for="inputUsername">Username</label>
					<input type="text" class="form-control" name="content" value="${message.user.username}" id="inputUsername" required="required" ${readonly} />
				</div>
			</c:if>
			<c:if test="${readonly != 'readonly'}">
				<button type="submit" class="btn btn-primary">Submit</button>
			</c:if>
			<a href="${pageContext.request.contextPath}/messages" class="btn btn-default">Back</a>
		</form>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#input-form').submit(function() {
				if ('${readonly}' == 'readonly') {
					return false;
				}
				return true;
			});
		});
	</script>
</body>
</html>