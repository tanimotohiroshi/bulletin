<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿画面</title>
</head>
<body>
<h2>新規投稿画面</h2>

<form action="./posting" method="post">

	<font color="red">
	<c:if test="${ not empty errorMessages }">
		<c:forEach items="${ errorMessages }" var="message">
			<c:out value="${ message }" /><br />
		</c:forEach>
		<c:remove var="errorMessages" scope="session"/>
	</c:if>
	</font>

<a href="./">ホームへ戻る</a>

<br /><br />


	<label for="title" >タイトル(50文字以下)</label><br />
	<input name="title" id="title" value="${ posting.title }"  maxlength="50" /><br /><br />


	<label for="message">投稿内容(1000文字以下)</label><br />
	<textarea  name="message" rows="20" cols="50"  class="tweet-box"><c:out value="${ posting.message }" /></textarea>
	<br /><br />

	<label for="category">カテゴリー(10文字以下)</label><br />
	<input name="category" id="category" value="${ posting.category }"  maxlength="10" /><br /><br />
	<c:remove var="posting" scope="session"/>


	<input type="submit" value="投稿する"><br />



</form>


<br /><br /><br /><br />
<div class="copyright">Copyright(c)Tanimoto Hiroshi</div>
</body>
</html>