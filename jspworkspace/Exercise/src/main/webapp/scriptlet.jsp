<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Scripting Tag</title>
</head>
<body>
	<% java.util.Date date = new java.util.Date();//util은 import쓰지않고 불러오는 것
		out.print("Today : " + date);
	%>
</body>
</html>