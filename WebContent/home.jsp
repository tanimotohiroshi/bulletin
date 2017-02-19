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

<script type="text/javascript">

	function disp(){
		if ( window.confirm('投稿を削除します。よろしいですか？')) {
			return true;

		} else {
			return false;
		}
	}

	function commentDisp(){
		if ( window.confirm('コメントを削除します。よろしいですか？')) {
			return true;

		} else {
			return false;
		}
	}
</script>

<link href="./css/style.css" rel="stylesheet" type="text/css" >

</head>
<body>

<div class="main-contents">

	<h1>ホーム</h1>

<div class="header">

	<font color="red">
		<c:if test="${ not empty controlErrorMessages }">
			<c:out value="${ controlErrorMessages }" /><br /><br />
			<c:remove var="controlErrorMessages" scope="session"/>
		</c:if>
	</font>



	<c:if test="${ not empty loginUser }">
		<a>ログインユーザー名　　</a>
		<c:out value="${ loginUser.name }" /><br /><br />
		<a href="./">ホーム</a>			  <a href="posting">新規投稿</a>
		<c:if test="${ loginUser.departmentId == 1 }">
		<a href="controlUser">ユーザー管理画面へ</a>
		</c:if> <br /><br />
		<a href="logout">ログアウト</a>
		<br /><br />
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
 	<form action="./" method="get">
 	<a>カテゴリーと日付による指定</a><br />


		<label for="category">カテゴリー(未選択の場合は全てのカテゴリーを選択)</label><br >
		<select name="getCategory">
			<option value="" >選択してください(未選択の場合は全選択)</option>
			<c:forEach items="${ categoryList }" var="category"><br />
				<option value="${ category.category }" <c:if test="${ category.category == reCategory }" > selected </c:if> >
				<c:out value="${ category.category }" /></option>
			</c:forEach>
		</select>



		<br /><br />
		<label for="date"> 投稿日時 </label><br />
			<input type="date" name="startDate" value="${ date1 }" />
			 ～ <input type="date" name="endDate" value="${ date2 }"/><br /><br />
		<input type="submit" value="検索" />
	</form>
</div>
<br /><br />





		<div class="postings">


			<c:forEach items="${ postings }" var="posting">
			<br /><br />
			<div class="comment-title">投稿</div>
			<div class="posting-box" >
				<a>タイトル</a><br />
				<c:out value="${ posting.title }" /><br /><br />
				<a>投稿内容</a><br />
				<div class="border">
				<pre><c:out value="${ posting.message }" /></pre><br /><br />
				</div>
				<a>投稿者　　</a>
				<c:out value="${ posting.name }" /><br />
				<a>カテゴリー　　</a>
				<c:out value="${ posting.category }" /><br />
				<fmt:formatDate value="${ posting.insertDate }" pattern="yyyy/MM/dd HH:mm:ss" /><br />



					<form action="./" method="post">

					<input type="hidden" name="deletePosting" value="${ posting.id }" />

					<c:choose>
						<c:when test="${ loginUser.departmentId == 2}" >

						<input type="submit" value="投稿を削除する" onClick="return disp();" /></c:when>
						<c:when test="${ loginUser.id == posting.userId }" >
						<input type="submit" value="投稿を削除する" onClick="return disp();" /></c:when>
						<c:when test="${ loginUser.departmentId == 3 && loginUser.branchId == posting.branchId }" >
						<input type="submit" value="投稿を削除する" onClick="return disp();" /></c:when>
					</c:choose>

					</form>

				<br /><br />


				<div class="line"></div>
				<br />

				<div class="comment-title">コメント</div>
				<br />


	 			<div class="comment-form">

				<c:forEach items="${ comments }" var="comment" >

				<c:if test="${ posting.id == comment.postingId }" >
					<a>名前　　</a>
					<c:out value="${ comment.name }" />
					<pre><c:out value="${ comment.message }" /></pre>
					<fmt:formatDate value="${ comment.insertDate }" pattern="yyyy/MM/dd HH:mm:ss" />


						<form action="./" method="post">
						<input type="hidden" name="deleteComment" value="${ comment.id }" />

						<c:choose>
							<c:when test="${ loginUser.departmentId == 2}" >
							<input type="submit" value="コメントを削除する" onClick="return commentDisp();" /></c:when>
							<c:when test="${ loginUser.id == comment.userId }" >
							<input type="submit" value="コメントを削除する" onClick="return commentDisp();" /></c:when>
							<c:when test="${ loginUser.departmentId == 3 && loginUser.branchId == comment.branchId }" >
							<input type="submit" value="コメントを削除する" onClick="return commentDisp();" /></c:when>
						</c:choose>

						</form>

				</c:if>
				</c:forEach>
				</div>

				<br /><br />

				<form action="./comment" method="post">コメントする(500文字まで)<br />
					<textarea name="comment" rows="5" cols="10" class="tweet-box"></textarea><br />
					<input type="hidden" name="postingId" value="${ posting.id }" >
					<input type="submit" value="コメントする">
				</form>
				<br />
				</div>
			</c:forEach>



	</div>

<br />
<a href="./">ホーム</a>
<br /><br />

	<div class="copyright">copyright(c) tanimotohiroshi</div>

</div>

</body>
</html>