<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム画面</title>
</head>
<body>

	<h3>ホーム画面</h3>

<div class="header">

	<c:if test="${ empty loginUser }">
		<a href="login">ログインする</a>
	</c:if>
	<c:if test="${ not empty loginUser }">
		<c:out value="${ loginUser.name }" /><br />
		<a href="home">ホーム</a>
		<a href="./posting">新規投稿画面へ</a> <br /> <br />
		<a href="logout">ログアウト</a>
	</c:if>

</div>


	<div class="edit">
		<a href="./signUp">新規登録</a> <a href="./editUser">ユーザー管理</a><br /> <br />
		<br /> <br />

	</div>

	<div class="posting-form" >



		<div class="postings">
			<c:forEach items="${ postings }" var="postings">
				<c:out value="${ postings.title }" /><br />
				<c:out value="${ postings.message }" /><br />
				<c:out value="${ postings.name }" /><br />
				<c:out value="${ postings.category }" /><br />
				<fmt:formatDate value="${ postings.updateDate }" pattern="yyyy/MM/dd HH:mm:ss" /><br /><br />

			<%-- <div class="comment-form">
				<c:forEach items="${ comment } var="comment">
					<c:out value="">コメント</c:out>
				</c:forEach>
				</div>
			--%>
				<font color="red">
					<c:if test="${ not empty errorMessages }">
						<c:forEach items="${ errorMessages }" var="messages" >
							<c:out value="${ messages }" /><br /><br />
						</c:forEach>
						<c:remove var="errorMessages" scope="session"/>
					</c:if>
				</font>
				<form action="./comment" method="post">コメントする(500文字まで)<br />
					<textarea name="comment" rows="10" cols="60" class="tweet-box"></textarea><br />
					<input type="hidden" name="postingId" value="${ postings.id }" >
					<input type="submit" value="コメントする">
				</form>
			</c:forEach>
		</div>
	</div>










	<div class="copyright">copyright(c) tanimotohiroshi</div>


</body>
</html>