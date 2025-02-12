<%@page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*, javax.servlet.*, javax.servlet.http.*"%>

<jsp:useBean id="user" scope="session" class="bean.UserBean" />

<%
request.setCharacterEncoding("utf-8");
String fileName = (String) request.getAttribute("fileName");
user.setName(fileName != null ? fileName : "miss");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン成功！</title>
<link href="myPageCss.css" rel="stylesheet" type="text/css">
</head>
<body>
	<p>ログイン成功！</p>
	<p>
		こんにちは <strong><%=user.getName()%> さん!</strong>
	</p>
	<br />

	<%
	for (int i = 0; i < user.getFileNamesSize(); i++) {
	%>
	<form action="/AnkiApplication/FileEditerController" method="post">
		<figcaption><%=user.getFileName(i)%></figcaption>
		<button type="submit">
			<img src="images/file.jpg" alt="Image <%=i + 1%>" name="" width="100"
				height="100">
		</button>

		<input type="hidden" name="fileName" value="<%=user.getFileName(i)%>">
		<input type="hidden" name="userName" value="<%=user.getName()%>">

	</form>
	<%
	}
	%>

	<form action="/AnkiApplication/MyPageController" method="post">
		<label for="create_imageName">作成する画像名:</label> <input type="text"
			id="imageName" name="create_fileName" placeholder="画像名を入力">
		<button type="submit" name="action" value="create">新しいファイルの作成</button>
	</form>
	<form action="/AnkiApplication/MyPageController" method="post">
		<label for="remove_imageName">削除する画像名:</label> <input type="text"
			id="imageName" name="remove_fileName" placeholder="画像名を入力">
		<button type="submit" name="action" value="remove">ファイルの削除</button>
	</form>

</body>
</html>
