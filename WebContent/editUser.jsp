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
<div class="main-contents">

<h3>ユーザー編集画面</h3>


<a href="controlUser">ユーザー管理画面へ</a>

<font color="red">
	<c:if test="${ not empty editErrorMessages }">
	<br />
		<c:forEach items="${ editErrorMessages }" var="message"><br />
			<c:out value="${ message }" />
		</c:forEach>
		<c:remove var="errorMessages" scope="session"/>
	</c:if>
</font>



<br /><br />

	<form action="./editUser" method="post">

		<input type="hidden" name="id" value="${ editUserReading.id }" />

		<label for="loginId">ログインID</label>
		<input name="loginId" id="loginId" maxlength="20"
		value="${ editUserReading.loginId }" /><br />

		<label for="password">パスワード</label>
		<input name="password1" id="password" type="password" maxlength="255"
		value="${ password2 }"/><br />

		<label for="password">パスワード(確認用)</label>
		<input name="password2" id="password" type="password" maxlength="255"
		value="${ password3 }" /><br />

		<label for="name">名前</label>
		<input name="name" id="name" maxlength="10"
		value="${ editUserReading.name }"/><br />


		<label for="branchId">支店名</label>
		<select name="branchId"  >
			<c:forEach items="${ branchList }" var="branch"><br />
				<option value="${ branch.id }" <c:if test="${ branch.id == editUserReading.branchId }" > selected </c:if>>
				<c:out value="${ branch.name }" /></option>
			</c:forEach>
		</select>



		<label for="departmentId">役職</label>
		<select name="departmentId">
			<c:forEach items="${ departmentList }" var="department"><br />
				<option value="${ department.id }" <c:if test="${ department.id == editUserReading.departmentId }" > selected </c:if> >
				<c:out value="${ department.name }" /></option>
			</c:forEach>
		</select>
		<br />
		<br />

		<input type="submit" value="登録" />
	</form>

		<br />
		<br />



<br /><br /><br /><br />
<div class="copyright">Copyright(c)Tanimoto Hiroshi</div>

</div>
</body>
</html>