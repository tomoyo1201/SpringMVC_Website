<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<%-- Java入門 購入完了画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>Java入門</title>
		<link href="<c:url value="/css/spring.css" />" rel="stylesheet" />
	</head>
	<body>
		<%@ include file="/WEB-INF/views/create/header.jsp"%>
		<h1>購入結果</h1>
		<p>購入しました。
		<img src="<c:url value="/resources/images/Thankyou.jpg"/>"  width="100" height="100" alt="image" /></p>
		<f:form action="create" method="post">
			<input class="common_button" type="submit" value="一覧に戻る">
		</f:form>
	</body>
</html>