<%@page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*, javax.servlet.*, javax.servlet.http.*"%>

 <jsp:useBean id="userbean" scope="session" class="bean.UserBean"/>
 <jsp:useBean id="fileofdata" scope="session" class="bean.FileOfData"/>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p><%=userbean.getName() %>の<%=fileofdata.getFileName() %></p>
<h3><%=fileofdata.getQuestionById(i) %></h3>
</body>
</html>