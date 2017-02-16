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



	<c:if test="${ not empty loginUser }">
		<c:out value="${ loginUser.name }" /><br />
		<a href="home">ホーム</a>			<a href="logout">ログアウト</a>
		<c:if test="${ loginUser.id ==1 }">
		<a href="controlUser">ユーザー管理画面へ</a>
		</c:if> <br /><br />
		<a href="posting">新規投稿</a><br /><br />
	</c:if>
</div>

<br /><br />


		<font color="red">
			<c:if test="${ not empty errorMessages }">
				<c:forEach items="${ errorMessages }" var="messages" >
					<c:out value="${ messages }" /><br /><br />
				</c:forEach>
				<c:remove var="errorMessages" scope="session"/>
			</c:if>
		</font>


<br />



 <div class="category-updateDate">
 	<form action="./home" method="get">
 	<a>カテゴリーと日付による指定</a><br />

 		<label for="category">カテゴリー</label><br />
		<select name="category"  >
		<option value=""  >選択してください</option>
			<c:forEach items="${ postings }" var="posting"><br />
				<option value="${ posting.category }"  ><c:out value="${ posting.category }" /></option>
			</c:forEach>
		</select>


		<br /><br />
		<label for="date"> 投稿日時 </label><br />
			<input type="date" name="startDate" value="${ date1 }" />
			 ～ <input type="date" name="endDate" value="${ date2 }"/><br />
		<input type="submit" value="検索" />
	</form>
</div>
<br /><br />



	<div class="posting-form" >

		<div class="postings">

			<c:forEach items="${ postings }" var="posting">
				<c:out value="${ posting.title }" /><br />
				<pre><c:out value="${ posting.message }" /></pre><br />
				<c:out value="${ posting.name }" /><br />
				<c:out value="${ posting.category }" /><br />
				<fmt:formatDate value="${ posting.insertDate }" pattern="yyyy/MM/dd HH:mm:ss" /><br />



					<form action="./home" method="get">

					<input type="hidden" name="deletePosting" value="${ posting.id }" />

					<c:choose>
						<c:when test="${ loginUser.departmentId == 2}" >
						<input type="submit" value="投稿を削除する" /></c:when>
						<c:when test="${ loginUser.id == posting.userId }" >
						<input type="submit" value="投稿を削除する" /></c:when>
						<c:when test="${ loginUser.departmentId == 3 && loginUser.branchId == posting.branchId }" >
						<input type="submit" value="投稿を削除する" /></c:when>
					</c:choose>

					</form>

				<br /><br />


	 			<div class="comment-form">

				<c:forEach items="${ comments }" var="comment" >

				<c:if test="${ posting.id == comment.postingId }" >
					<c:out value="${ comment.name }" />
					<pre><c:out value="${ comment.message }" /></pre>
					<fmt:formatDate value="${ comment.insertDate }" pattern="yyyy/MM/dd HH:mm:ss" /><br />

						<form action="./home" method="get">
						<input type="hidden" name="deleteComment" value="${ comment.id }" />

						<c:choose>
							<c:when test="${ loginUser.departmentId == 2}" >
							<input type="submit" value="コメントを削除する" /></c:when>
							<c:when test="${ loginUser.id == posting.userId }" >
							<input type="submit" value="コメントを削除する" /></c:when>
							<c:when test="${ loginUser.departmentId == 3 && loginUser.branchId == comment.branchId }" >
							<input type="submit" value="コメントを削除する" /></c:when>
						</c:choose>

						</form>

				</c:if>
				</c:forEach>
				</div>

				<form action="./comment" method="post">コメントする(500文字まで)<br />
					<textarea name="comment" rows="10" cols="50" class="tweet-box"></textarea><br />
					<input type="hidden" name="postingId" value="${ posting.id }" >
					<input type="submit" value="コメントする">
				</form>
				<br />
			</c:forEach>

		</div>
	</div>








<br /><br /><br />

	<div class="copyright">copyright(c) tanimotohiroshi</div>


</body>
</html>