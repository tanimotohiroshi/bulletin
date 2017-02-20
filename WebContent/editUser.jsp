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
<title>ユーザー編集画面</title>
</head>
<body>
<div class="posting-contents">

<div class="title">
<h2>ユーザー編集画面</h2>
</div>

<a href="controlUser">ユーザー管理画面へ</a>


<div class="error">
	<c:if test="${ not empty editErrorMessages }">
	<br />
		<c:forEach items="${ editErrorMessages }" var="message"><br />
			<c:out value="${ message }" />
		</c:forEach>
		<c:remove var="errorMessages" scope="session"/>
	</c:if>
</div>



<br /><br />

	<form action="./editUser" method="post">

		<input type="hidden" name="id" value="${ editUserReading.id }" />

		<div class="subtitle">ログインID</div>
		<input name="loginId" id="loginId" maxlength="20"
		value="${ editUserReading.loginId }" /><br />

		<div class="subtitle">パスワード</div>
		<input name="password1" id="password" type="password" maxlength="255"
		value="${ password2 }"/><br />

		<div class="subtitle">パスワード(確認用)</div>
		<input name="password2" id="password" type="password" maxlength="255"
		value="${ password3 }" /><br />

		<div class="subtitle">名前</div>
		<input name="name" id="name" maxlength="10"
		value="${ editUserReading.name }"/><br />


		<div class="subtitle">支店</div>
		<select name="branchId" class="signUp-select" >
			<c:forEach items="${ branchList }" var="branch"><br />
				<option value="${ branch.id }" <c:if test="${ branch.id == editUserReading.branchId }" > selected </c:if>>
				<c:out value="${ branch.name }" /></option>
			</c:forEach>
		</select>



		<div class="subtitle">役職</div>
		<select name="departmentId" class="signUp-select">
			<c:forEach items="${ departmentList }" var="department"><br />
				<option value="${ department.id }" <c:if test="${ department.id == editUserReading.departmentId }" > selected </c:if> >
				<c:out value="${ department.name }" /></option>
			</c:forEach>
		</select>
		<br />
		<br />

		<input type="submit" class="c-btn" value="登録" />
	</form>

		<br />
		<br />



<br /><br /><br /><br />
<div class="copyright">Copyright(c)Tanimoto Hiroshi</div>

</div>
</body>
</html>