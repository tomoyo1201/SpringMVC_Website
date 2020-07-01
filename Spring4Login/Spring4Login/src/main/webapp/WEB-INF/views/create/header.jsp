<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value="/css/spring.css" />" rel="stylesheet" />

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- Java入門 ヘッダー画面 --%>
<div align="right">
	<%-- ログイン済みの場合はIDを表示 --%>

	<f:form method="POST" action="create" modelAttribute="create">
		<h3>
			ようこそ
			<c:out value="${sessionScope.user_db.id}" />
			<%-- % request.getAttribute("user_db"); %> --%>
			さん！
		</h3>
		<%-- 	 --%>
		<%
			//T パラメータ名「submit」で履歴およびログアウトを判定する
		%>
		<a href="${contextPath}/create/history">購入履歴</form>
		<a href="<c:url value='/logout' />">ログアウト</a>
	</f:form>

</div>