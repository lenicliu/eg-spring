<html> 
	<head> 
		<title>welcome jboot message!</title>
		<style type="text/css">
			table{width:600px;border:1px solid black;}
			td{font-size:18px;}
			th{font-size:14px;}
		</style>
	</head> 
	<body style="text-align:center;">
		<h1>welcome jboot message!</h1>
		<#list messages as message>
			<table border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<th align="left">(#${message.id})${message.created?string("yyyy-MM-dd HH:mm:ss")}[<a href="${request.contextPath}/delete?id=${message.id}">删除</a>]</th>
					<th align="right">${message.author}(<a href="mailto:${message.email}">${message.email}</a>)</th>
				</tr>
				<tr>
					<td colspan="2">${message.content}</td>
				</tr>
			</table>
		</#list>
		<form action="${request.contextPath}/submit" method="post">
		<table border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<th><input id="author" name="author" placeholder="昵称" style="width:100%"/></th>
				<th><input name="email" placeholder="邮箱" style="width:100%"/></th>
			</tr>
			<tr>
				<td colspan="2"><textarea name="content" style="width:100%;height:200px;" placeholder="内容……"></textarea></td>
			</tr>
			<tr>
				<td align="right"><input type="submit" value="留言"/>&nbsp;&nbsp;</td>
				<td align="left">&nbsp;&nbsp;<input type="reset" value="清空"/></td>
			</tr>
		</table>
		</form>
		<script type="text/javascript">
			document.getElementById('author').focus();
		</script>
	</body>
</html>