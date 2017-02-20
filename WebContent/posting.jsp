<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/style.css" rel="stylesheet" type="text/css" >
<title>新規投稿画面</title>
</head>
<body>
<div class="posting-contents">

<div class="title">
<h2>新規投稿画面</h2>
</div>

<a href="./">ホーム</a>
<br /><br />

	<div class="error">
	<c:if test="${ not empty errorMessages }">
		<c:forEach items="${ errorMessages }" var="message">
			<c:out value="${ message }" /><br />
		</c:forEach>
		<c:remove var="errorMessages" scope="session"/>
	</c:if>
	</div>


<br />


<form action="./posting" method="post">

	<div class="subtitle" >タイトル(50文字以下)</div>
	<input name="title" id="title" value="${ posting.title }"  maxlength="50" /><br /><br />


	<div class="subtitle">投稿内容(1000文字以下)</div>
	<textarea  name="message" rows="20" cols="50"  class="tweet-box"><c:out value="${ posting.message }" /></textarea>
	<br /><br /><br />


	<div class="subtitle"><a>新規カテゴリーを入力もしくは既存カテゴリーから選択してください</a></div>
	<br />

	<div class="subtitle">新規カテゴリー(10文字以下)</div>
	<input name="category" id="category" value="${ posting.category }"  maxlength="10" />

	<div class="subtitle">既存カテゴリー</div>
		<select name="getCategory" class="mini-select">
			<option value="" >選択してください</option>
			<c:forEach items="${ categoryList }" var="category"><br />
				<option value="${ category.category }" <c:if test="${ category.category == reCategory }" > selected </c:if> >
				<c:out value="${ category.category }" /></option>
			</c:forEach>
		</select>



	<c:remove var="posting" scope="session"/>

	<br /><br />
	<input type="submit" class="c-btn" value="投稿する"><br />



</form>

<br /><br /><br /><br />
<div class="copyright">Copyright(c)Tanimoto Hiroshi</div>

</div>
</body>
</html>