<%@page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*, javax.servlet.*, javax.servlet.http.*"%>

<jsp:useBean id="user" scope="session" class="bean.UserBean" />

<%
request.setCharacterEncoding("utf-8");
String userName = (String) request.getAttribute("userName");
user.setName(userName != null ? userName : "miss");

String fileName = (String) request.getAttribute("fileName");
user.setName(fileName != null ? fileName : "miss");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン成功！</title>
</head>
<body>


</body>
</html>
