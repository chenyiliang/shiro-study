<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>没有权限</title>
<style type="text/css">
.error {
	color: red;
}
</style>
</head>
<body>
	<div class="error">您没有权限[${exception.message }]</div>
</body>
</html>