<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理画面</title>
</head>
<body>

<h3>ユーザー管理画面</h3>

	<a href="./signUp">ユーザー新規登録</a>
<br /><br /><br />


	<div class="usersList">
		<c:forEach items="${ usersList }" var="user">
			<c:out value="${ user.loginId }" />   <c:out value="${ user.name }" />
			<form action="./editUser" method="get">
			<input type="submit" value="ユーザー編集" />
			<input type="hidden" name="id" value="${ user.id }" >
			</form>

				<form action="./controlUser" method="post" >
				<c:if test="${ user.isStopped == 1}" >
				<input type="hidden" name="stopId" value="0" >
				<input type="hidden" name="id" value="${ user.id }" >
				<input type="submit" value="停止" />
				</c:if>
				<c:if test="${ user.isStopped == 0 }" >
				<input type="hidden" name="permitId" value="1" >
				<input type="hidden" name="id" value="${ user.id }" >
				<input type="submit" value="復活" >
				</c:if>
				</form>


			<br /><br />
		</c:forEach>
	</div>




<br /><br />
	<a href="./home">ホーム画面へ</a>

<br /><br /><br /><br />
<div class="copyright">Copyright(c)Tanimoto Hiroshi</div>

</body>
</html>