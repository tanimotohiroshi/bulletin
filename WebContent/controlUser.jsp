<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/style.css" rel="stylesheet" type="text/css" >

<title>ユーザー管理画面</title>


<script type="text/javascript">

	function disp(){
		if ( window.confirm('ユーザーの権限を復活します。よろしいですか？')) {
			return true;

		} else {
			return false;
		}
	}

	function stopDisp(){
		if ( window.confirm('ユーザーの権限を停止します。よろしいですか？')) {
			return true;

		} else {
			return false;
		}
	}

	function deleteDisp(){
		if ( window.confirm('ユーザーを削除します。よろしいですか？')) {
			return true;

		} else {
			return false;
		}
	}
</script>

</head>
<body>
<div class="main-contents">

<div class="title">
<h3>ユーザー管理画面</h3>
</div>

	<a href="./">ホーム</a><br /><br />

	<a href="./signUp">ユーザー新規登録</a>
<br /><br />


	<div class="error">
	<c:if test="${ not empty urlErrorMessages }">
		<c:forEach items="${ urlErrorMessages }" var="message">
			<c:out value="${ message }" />
		</c:forEach>
		<c:remove var="urlErrorMessages" scope="session"/>
	</c:if>
	</div>

	<table border="3" align="center">
	<tr>
	<th>ログインID</th>
	<th>名前</th>
	<th>支店名</th>
	<th>役職名</th>
	<th>ユーザー編集</th>
	<th>復活・停止</th>
	<th>削除</th>
	</tr>

		<c:forEach items="${ userList }" var="user">
		<br />
			<tr>
			<th><c:out value="${ user.loginId }" /></th>
			<th><c:out value="${ user.name }" /></th>
			<th><c:out value="${ user.branchName }" /></th>
			<th><c:out value="${ user.departmentName }" /></th>

			<th><form action="./editUser" method="get">
			<input type="submit" class="btn" value="ユーザー編集" />
			<input type="hidden" name="id" value="${ user.id }" >
			</form>
			</th>

			<th><c:if test="${ user.id != loginUser.id }" >

			<form action="./controlUser" method="post" >

			<c:if test="${ user.isStopped == 1}" >
				<input type="hidden" name="stopId" value="0" >
				<input type="hidden" name="id" value="${ user.id }" >
				<input type="submit" class="btn" value="復活" onClick="return disp();" />
			</c:if>

			<c:if test="${ user.isStopped == 0 }" >
				<input type="hidden" name="permitId" value="1" >
				<input type="hidden" name="id" value="${ user.id }" >
				<input type="submit" class="red-btn" value="停止" onClick="return stopDisp();" />
			</c:if>

			</form>
			</c:if>
			</th>

			<th>
			<c:if test="${ user.id != loginUser.id }" >
			<form action="./controlUser" method="post" >
				<input type="hidden" name="id" value="${ user.id }" />
				<input type="hidden" name="delete" value="delete" />
				<input type="submit" class="red-btn" value="削除" onClick="return deleteDisp();" />

			</form>

			</c:if>
			</th>
			</tr>
		</c:forEach>
	</table>


<br /><br />


<br /><br /><br /><br />
<div class="copyright">Copyright(c)Tanimoto Hiroshi</div>

</div>
</body>
</html>