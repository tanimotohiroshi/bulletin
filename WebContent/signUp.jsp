<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/style.css" rel="stylesheet" type="text/css" >
<title>ユーザー新規登録画面</title>
</head>
<body>



<div class="posting-contents">


<div class="title">
<h2>ユーザー新規登録画面</h2>
</div>

	<br />
	<a href="controlUser">ユーザー管理画面へ</a>

	<br /><br />

	<div class="error">
	<c:if test="${ not empty errorMessages }">
		<c:forEach items="${ errorMessages }" var="message">
			<c:out value="${ message }" /><br />
		</c:forEach>
		<c:remove var="errorMessages" scope="session"/>
	<br />
	</c:if>
	</div>


	<form action="./signUp" method="post">

		<div class="name">ログインID</div>
		<input name="loginId" id="loginId" maxlength="20"
		value="${ editUser.loginId }" /><br /><br />

		<div class="name">パスワード</div>
		<input name="password1" id="password" type="password" maxlength="255"  /><br /><br />

		<div class="name">パスワード(確認用)</div>
		<input name="password2" id="password" type="password" maxlength="255"  /><br /><br />


		<div class="name">名前</div>
		<input name="name" id="name" maxlength="10" value="${ editUser.name }" /><br /><br />


		<div class="name">支店</div>
		<select name="branchId" class="signUp-select">
			<c:forEach items="${ branchList }" var="branch">
				<option value="${ branch.id }" <c:if test="${ branch.id == editUser.branchId }"> selected </c:if>>
				<c:out value="${ branch.name }" /></option>
			</c:forEach>
		</select><br />



		<div class="name" >役職</div>
		<select name="departmentId" class="signUp-select">
			<c:forEach items="${ departmentList }" var="department"><br />
				<option value="${ department.id }" <c:if test="${ department.id == editUser.departmentId }"> selected </c:if>>
				<c:out value="${ department.name }" /></option>
			</c:forEach>
		</select>
		<br />
		<br />

		<input type="submit" class="c-btn" value="登録" /> <br />


	</form>
<br /><br /><br /><br />
<div class="copyright">Copyright(c)Tanimoto Hiroshi</div>

</div>
</body>
</html>