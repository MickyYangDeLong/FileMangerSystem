<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<style type="text/css">
body, td, th {
	color: #0000FF;
}

body {
	background-color: #99CC99;
	background-image: url(file:///C|/Users/hp/Pictures/,fcut74.png);
}

.STYLE1 {
	font-size: 36px;
	font-weight: bold;
}
</style>
</head>

<body>
	<div align="center" class="STYLE1">文件管理系统</div>
	<form method="post" id="form" action="login?method=login">
		<p align="center">
			input your name:<input type="text" name="username" />
		</p>
		${message}<br />
		<p align="center">
			your password:<input type="password" name="password" />
		</p>
		<br />
		<p align="center">
			<input type="submit" value="submit" /> <input type="reset" />
		</p>
	</form>
</body>
</html>
