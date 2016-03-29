<html> 
	<head> 
		<title>welcome jboot!</title> 
	</head> 
	<body> 
		<h1>welcome jboot!</h1> 
		<table border="1">
			<tr>
				<td>姓名</td>
				<td>电话</td>
			</tr>
			<#list contactList as contact> 
				<tr>
					<td>${contact.name}</td>
					<td>${contact.mobile}</td>
				</tr>
			</#list> 
		</table>
	</body> 
</html> 