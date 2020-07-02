<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
<head>
<title>Java入門</title>
<link href="<c:url value="/css/spring.css" />" rel="stylesheet" />
</head>
<body>

	<f:form modelAttribute="insert" action="./finish" method="POST">
		<div class="login_pane">
			<h1>新規登録</h1>
			<p>${insert.msg}</p>
			<p class="fuchsia">idは5文字まで。</p>
			<p class="fuchsia">未入力の項目がある場合エラーになります。</p>
			<table class="table_form">
				<tbody>
					<tr>
						<th>id</th>
						<td><f:input path="id" /></td>

					</tr>

					<tr>
						<th>パスワード</th>
						<td><f:input path="password" /></td>

					</tr>
 					<tr>
						<th>お名前</th>
						<td><f:input path="name" /></td>
					</tr>
					<tr>
						<th>年齢</th>
						<td><f:input path="age" type="number" value=""/></td>
					</tr>


				</tbody>
			</table>
			<input class="common_button" type="submit" value="新規登録"
				onclick="return confirm();">

		</div>
	</f:form>
<script>
		/* 未入力の項目があるとエラーメッセージを表示 */
		function confirm(){
		var id = document.getElementById('id').value;
		var password = document.getElementById('password').value;
		var name = document.getElementById('name').value;
		var age = document.getElementById('age').value;
		  if (id ==''||password == ''||name==""||age ==""){
			  //Idかパスワードが入力されていません
			  alert('未入力の項目があります。');
			  	return false;
		  }else

			  }


		  return true;
