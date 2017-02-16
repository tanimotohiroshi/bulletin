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


<script type="text/javascript">

	function disp(){
		if ( window.confirm('よろしいですか')) {
			return true;

		} else {
			return false;
		}
	}
</script>

</head>
<body>

<h3>ユーザー管理画面</h3>

	<a href="./signUp">ユーザー新規登録</a><br /><br />

	<a href="./home">ホーム画面へ</a>
<br /><br /><br />

				<font color="red">
				<c:out value="${ urlErrorMessage }" />
				</font>

<br /><br />

	<div class="usersList">
		<c:forEach items="${ userList }" var="user">
			<c:out value="${ user.loginId }" />
			<c:out value="${ user.name }" />
			<c:out value="${ user.branchName }" />
			<c:out value="${ user.departmentName }" /><br /><br />
			<form action="./editUser" method="get">
			<input type="submit" value="ユーザー編集" />
			<input type="hidden" name="id" value="${ user.id }" >
			</form>

			<br>

			<form action="./controlUser" method="post" >

			<c:if test="${ user.isStopped == 1}" >
			<input type="hidden" name="stopId" value="0" >
			<input type="hidden" name="id" value="${ user.id }" >
			<input type="submit" value="復活" onClick="return disp();" />
			</c:if>

			<c:if test="${ user.isStopped == 0 }" >
			<input type="hidden" name="permitId" value="1" >
			<input type="hidden" name="id" value="${ user.id }" >
			<input type="submit" value="停止" onClick="return disp();" />
			</c:if>

			</form>

			<form action="./controlUser" method="post" >
			<input type="hidden" name="id" value="${ user.id }" />
			<input type="hidden" name="delete" value="delete" />
			<input type="submit" value="ユーザー削除" onClick="return disp();" />

			</form>

			<br /><br /><br />
		</c:forEach>
	</div>




<br /><br />


<br /><br /><br /><br />
<div class="copyright">Copyright(c)Tanimoto Hiroshi</div>

</body>
</html>