<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン画面</title>
</head>
<body>

<h3>ログイン画面</h3>


	<c:if test="${ not empty errorMessages }">
		<c:forEach items="${ errorMessages }" var="message">
			<c:out value="${ message }" />
		</c:forEach>
		<c:remove var="errorMessages" scope="session"/>
	</c:if>


<br /><br />



<form action="./login" method="post">

	<label for="loginId">ログインIDを入力してください</label><br />
	<input name="loginId" id="loginId" /><br />

	<label for="password">パスワードを入力してください</label><br />
	<input name="password" type="password" id="password" /><br /><br />

	<input type="submit"  value="送信" /><br />

	<a href="./home">ホームへ戻る</a>

</form>



</body>
</html>