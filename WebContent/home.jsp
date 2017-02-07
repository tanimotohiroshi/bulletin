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

	<font color="red">
		<c:if test="${ not empty controlErrorMessages }">
			<c:out value="${ controlErrorMessages }" /><br /><br />
			<c:remove var="controlErrorMessages" scope="session"/>
		</c:if>
	</font>


	<c:if test="${ empty loginUser }">
		<a href="login">ログインする</a>
	</c:if>
	<c:if test="${ not empty loginUser }">
		<c:out value="${ loginUser.name }" /><br />
		<a href="./controlUser">ユーザー管理画面へ</a> <br /> <br />
		<a href="home">ホーム</a><br />
		<a href="logout">ログアウト</a>
	</c:if>
</div>

<br /><br /><br />


	<div class="posting-form" >



		<div class="postings">
			<c:forEach items="${ postings }" var="posting">
				<c:out value="${ posting.title }" /><br />
				<c:out value="${ posting.message }" /><br />
				<c:out value="${ posting.name }" /><br />
				<c:out value="${ posting.category }" /><br />
				<fmt:formatDate value="${ posting.updateDate }" pattern="yyyy/MM/dd HH:mm:ss" /><br /><br />

				<div class="comment-form">

				<c:forEach items="${ comments }" var="comment" >

				<c:if test="${ posting.id == comment.postingId }" >
					<c:out value="${ comment.name }" />
					<c:out value="${ comment.message }" />
					<fmt:formatDate value="${ comment.updateDate }" pattern="yyyy/MM/dd HH:mm:ss" /><br /><br />
				</c:if>
				</c:forEach>
				</div>

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
					<input type="hidden" name="postingId" value="${ posting.id }" >
					<input type="submit" value="コメントする">
				</form>
			</c:forEach>
		</div>
	</div>








<br /><br /><br />

	<div class="copyright">copyright(c) tanimotohiroshi</div>


</body>
</html>