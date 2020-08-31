<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Java入門 ユーザ画面 --%>
<!DOCTYPE html>
<html>
<head>
<title>Java入門</title>
<link href="<c:url value="/css/spring.css" />" rel="stylesheet" />
</head>
<body>
	<div class="login_pane">
		<h1>ログインに成功しました！</h1>
		<table class="table_list">
			<tbody>
				<tr>
					<th>id</th>
					<td>${output.id}</td>
				</tr>
				<tr>
					<th>名前</th>
					<td>${output.name}</td>
				</tr>
				<tr>
					<th>年齢</th>
					<td>${output.age}</td>
				</tr>
			</tbody>
		</table>
		<form action="/schoo">
			<input class="common_button" type="submit" value="戻る">
		</form>
		<form action="create" method="POST">
			<input class="common_button" type="submit" value="購入画面に進む">
		</form>

		<td><sec:authorize ifAllGranted="ROLE_ADMIN,ROLE_USER">
				<a href="${varBookEditUrl}/${book.bookId}">${varLinkEdit}</a>
			</sec:authorize></td>
		<c:if test="${output.role==1}">
			<form action="new/admin" method="POST">
				<input class="common_button" type="submit" value="ユーザー表示">
			</form>
		</c:if>
	</div>
</body>
</html>