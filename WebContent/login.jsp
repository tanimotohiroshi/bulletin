<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ログイン画面</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css" >

</head>
<body>
<div class="main-contents">

<div class="title">
<h3>ログイン画面</h3>
</div>

	<div class="error">
	<c:if test="${ not empty errorMessages }">
		<c:forEach items="${ errorMessages }" var="message">
			<c:out value="${ message }" />
		</c:forEach>
		<c:remove var="errorMessages" scope="session"/>
	</c:if>
	</div>

<br /><br />



	<form action="./login" method="post">

		<div class="subtitle">ログインIDを入力してください</div>
		<input name="loginId" id="loginId" /><br />
	<br />
		<div class="subtitle">パスワードを入力してください</div>
		<input name="password" type="password" id="password" /><br /><br />

		<input type="submit" class="c-btn"  value="ログインする" /><br />

	</form>



<br /><br /><br /><br />
<div class="copyright">Copyright(c)Tanimoto Hiroshi</div>

</div>

</body>
</html>