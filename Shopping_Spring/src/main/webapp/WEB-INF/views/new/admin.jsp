<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link href="<c:url value="/css/spring.css" />" rel="stylesheet" />
</head>
<body>
	<h1>Welcome to Admin Page</h1>
	<div class="login_pane">

<%--	<f:form action="Confirm" method="POST" modelAttribute="pr"> --%>
			<table class="table_list">
				<tbody>
					<tr>
						<th>id</th>
						<th>name</th>
						<th>age</th>
					</tr>
					<c:forEach var="data" items="${output}" varStatus="st">
						<tr>
							<!--商品ID  -->
							<td><a href="${contextPath}/new/edit/${data.id}" name="${data.id}" value="edit"><c:out value="${data.id}" /></a></td>
<%-- 							<td><a href="${contextPath}/new/edit" name="${data.id}" value="edit"><c:out value="${data.id}" /></a></td> --%>
							<!--pass  -->
							<td><c:out value="${data.name}" /></td>
							<!--age -->
							<td><c:out value="${data.age}" /></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
<%-- 		</f:form> --%>
</body>
</html>