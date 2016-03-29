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
				<c:if test="${user.id == null}">Create</c:if>
				<c:if test="${user.id != null}">Update</c:if>
			</c:if>
			<c:if test="${readonly == 'readonly'}">
			View
			</c:if>
			User
		</h1>
		<form id="input-form" method="POST" action="${pageContext.request.contextPath}/admin/users/submit">
			<input type="hidden" name="id" value="${user.id}" />
			<div class="form-group">
				<label for="inputUsername">Username</label>
				<input type="text" class="form-control" name="username" value="${user.username}" id="inputUsername" required="required" ${readonly} />
			</div>
			<div class="form-group">
				<label for="inputPassword">Password</label>
				<input type="password" class="form-control" name="password" value="${user.password}" id="inputPassword" required="required" ${readonly} />
			</div>
			<c:if test="${readonly != 'readonly'}">
				<div class="form-group">
					<label for="inputPassword2">Password Again</label>
					<input type="password" class="form-control" value="${user.password}" id="inputPassword2" required="required" />
				</div>
			</c:if>
			<div class="form-group">
				<label>Roles</label>
				<div class="checkbox">
					<c:forEach items="${roles}" var="role">
						<label>
							<input id="role_${role.id}" name="role_ids" type="checkbox" value="${role.id}" ${disabled} />${role.name}
						</label>
					</c:forEach>
				</div>
			</div>
			<c:if test="${readonly != 'readonly'}">
				<button type="submit" class="btn btn-primary">Submit</button>
			</c:if>
			<a href="${pageContext.request.contextPath}/admin/users" class="btn btn-default">Back</a>
		</form>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#input-form').submit(function() {
				if ('${readonly}' == 'readonly') {
					return false;
				}
				var v = $('#inputPassword').val() == $('#inputPassword2').val();
				if (!v) {
					alert('Password must be equals');
				}
				return v;
			});
			<c:forEach items="${role_ids}" var="role_id">
			if ($('#role_${role_id}')) {
				$('#role_${role_id}').attr('checked', 'checked');
			}
			</c:forEach>
		});
	</script>
</body>
</html>