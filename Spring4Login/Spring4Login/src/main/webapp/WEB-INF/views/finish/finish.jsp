<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>登録完了</title>
<link href="<c:url value="/css/spring.css" />" rel="stylesheet" />
</head>
<body>
	<div class="login_pane">
		<h1>${output.msg}</h1>

 		<table class="table_list">
			<tbody>
				<tr>
					<th>id</th>
					<td>${output.id}</td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td>${output.password}</td>
				</tr>
 				<tr>
					<th>お名前</th>
					<td>${output.name}</td>
				</tr>
				<tr>
					<th>ご年齢</th>
					<td>${output.age}</td>
				</tr>

			</tbody>
		</table>
		<form action="/schoo">
			<input class="common_button" type="submit" value="戻る">
		</form>
	</div>
</body>
</html>