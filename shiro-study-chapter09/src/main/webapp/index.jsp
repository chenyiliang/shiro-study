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

	<shiro:authenticated>
	用户[<shiro:principal />]已身份验证通过<br />
	</shiro:authenticated>

	<shiro:notAuthenticated>
	未身份验证（包括记住我）<br />
	</shiro:notAuthenticated>

	<shiro:hasRole name="admin">
	用户[<shiro:principal />]拥有角色admin<br />
	</shiro:hasRole>

	<shiro:hasAnyRoles name="admin,user">
	用户[<shiro:principal />]拥有角色admin或user<br />
	</shiro:hasAnyRoles>

	<shiro:lacksRole name="abc">
	用户[<shiro:principal />]没有角色abc<br />
	</shiro:lacksRole>

	<shiro:hasPermission name="user:create">
	用户[<shiro:principal />]拥有权限user:create<br />
	</shiro:hasPermission>

	<shiro:lacksPermission name="org:create">
	用户[<shiro:principal />]没有权限org:create<br />
	</shiro:lacksPermission>
</body>
</html>