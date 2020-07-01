<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入履歴</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/create/header.jsp"%>
	<div class="login_pane">
		<h1>
			<c:out value="${sessionScope.user_db.id}" />
			さんの購入履歴
		</h1>
		<%-- リクエストスコープからBeanクラスの配列を取得（eclipseの警告が出ても今回は大丈夫です） --%>
		<%-- 		<% ArrayList<HistoryBean> historyList = (ArrayList<HistoryBean>)request.getAttribute("history"); %> --%>
		<table class="table_list">
			<tbody>
				<tr>
					<th>商品ID</th>
					<th>商品名</th>
					<th>購入数</th>
				</tr>
				<c:forEach var="data" items="${history}" varStatus="st">
					<tr>
						<!--商品ID  -->
						<td><c:out value="${data.item_id}" /></td>
						<!--商品名  -->
						<td><c:out value="${data.item_name}" /></td>
						<!--商品価格  -->
						<td><c:out value="${data.quantity}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>