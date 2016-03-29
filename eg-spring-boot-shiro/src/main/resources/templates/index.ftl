<html> 
	<head> 
		<title>welcome jboot shiro!</title>
	</head> 
	<body>
		<h1>welcome jboot shiro!</h1>
		<ul>
			<#if Session["org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY"]?exists>
				<li>Hi,${Session["org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY"]}</li>
			<#else>
				<li><a href="${request.contextPath}/login">login</a></li>
			</#if>
			<li><a href="${request.contextPath}/home">profile</a></li>
			<li><a href="${request.contextPath}/admin">administrator</a></li>
			<#if Session["org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY"]?exists>
				<li><a href="${request.contextPath}/logout">logout</a></li>
			</#if>
		</ul>
	</body>
</html>