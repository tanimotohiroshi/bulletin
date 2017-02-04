<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿画面</title>
</head>
<body>
<h2>新規投稿画面</h2>

<form action="./posting" method="post">






	<label for="title" >タイトル(50文字以下)</label><br />
	<input name="title" id="title" maxlength="50" /><br /><br />

	<label for="message">投稿内容(1000文字以下)</label><br />
	<textarea name="message" rows="20" cols="50" class="tweet-box" maxlength="1000"></textarea><br /><br />

	<label for="category">カテゴリー(10文字以下)</label><br />
	<input name="category" id="category" maxlength="10" /><br /><br />


	<input type="submit" value="コメントする">



</form>



</body>
</html>