<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value="/css/spring.css" />" rel="stylesheet" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Admin Setting</title>
</head>
<body>
	<h1>configure</h1>
	<div class="login_pane">
		<h1>ユーザー情報変更</h1>
		<p class="fuchsia">項目を入力してください。</p>
		<p class="fuchsia">未入力の項目がある場合エラーになります。</p>
		<f:form action="${contextPath}/new/edit/${output.id}/update" method="POST" modelAttribute="output">
<%-- 		<f:form action="${output.id}/update" method="POST" modelAttribute="output"> --%>
			<table class="table_list">
				<tbody>
					<tr>
						<th>id</th>
						<td>${output.id}</td>

					</tr>
					<tr>
						<th>パスワード</th>
						<td><f:input type="password" path="password" size="18"
								maxlength="20" value="${output.password}" /></td>
								<f:hidden path="password" />

					</tr>
					<tr>
						<th>お名前</th>
						<td><f:input path="name" size="18" maxlength="5"
								value="${output.name}" /></td>
<%-- 							<f:input type="hidden" path="name" id="name" /> --%>
						<%-- <td>${output.name}</td> --%>
					</tr>
					<tr>
						<th>年齢</th>
						<td><f:input path="age" size="18" maxlength="5"
								value="${output.age}" /></td>
<%-- 						<f:input type="hidden" path="age" id="age" /> --%>
						<%-- <td>${output.age}</td> --%>
					</tr>
				</tbody>
			</table>
			<input class="common_button" type="submit" value="update">
		</f:form>
	</div>
</body>
</html>