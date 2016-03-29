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
				<c:if test="${role.id == null}">Create</c:if>
				<c:if test="${role.id != null}">Update</c:if>
			</c:if>
			<c:if test="${readonly == 'readonly'}">
			View
			</c:if>
			Role
		</h1>
		<form id="input-form" method="POST" action="${pageContext.request.contextPath}/admin/roles/submit">
			<input type="hidden" name="id" value="${role.id}" />
			<div class="form-group">
				<label for="inputName">Name</label>
				<input type="text" class="form-control" name="name" value="${role.name}" id="inputName" required="required" ${readonly} />
			</div>
			<div class="form-group">
				<label>Authorities</label>
				<div class="checkbox">
					<c:forEach items="${auths}" var="auth">
						<label>
							<input id="auth_${auth.id}" name="auth_ids" type="checkbox" value="${auth.id}" ${disabled} />${auth.name}
						</label>
					</c:forEach>
				</div>
			</div>
			<c:if test="${readonly != 'readonly'}">
				<button type="submit" class="btn btn-primary">Submit</button>
			</c:if>
			<a href="${pageContext.request.contextPath}/admin/roles" class="btn btn-default">Back</a>
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
			<c:forEach items="${auth_ids}" var="auth_id">
			if ($('#auth_${auth_id}')) {
				$('#auth_${auth_id}').attr('checked', 'checked');
			}
			</c:forEach>
		});
	</script>
</body>
</html>