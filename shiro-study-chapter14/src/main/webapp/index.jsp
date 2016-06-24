<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
	<shiro:guest>
欢迎游客访问，<a href="${pageContext.request.contextPath}/login.jsp">点击登录</a>
		<br />
	</shiro:guest>

	<shiro:user>
	欢迎[<shiro:principal />]登录，<a
			href="${pageContext.request.contextPath}/logout">点击退出</a>
		<br />
	</shiro:user>
</body>
</html>