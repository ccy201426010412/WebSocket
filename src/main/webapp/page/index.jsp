<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="/SpringDemo/css/index.css">
</head>
<body>

<form  name="ff"  action="/WebSocket/home/login" method="post" >
    用户名：<input name="username"  /><br/>
    <input type="submit"  />
</form>
</body>
</html>