<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- Java入門 購入確認画面 --%>
<!DOCTYPE html>
<head>
<title>購入確認</title>
<link href="<c:url value="/css/spring.css" />" rel="stylesheet" />
</head>
<body>
	<%@ include file="/WEB-INF/views/create/header.jsp"%>
	<div class="login_pane">
		<h1>購入確認</h1>
		<p>つぎの商品を購入しますか？</p>
		<table class="table_list">
			<tbody>
				<tr>
					<th>商品ID</th>
					<th>商品名</th>
					<th>価格</th>
					<th>在庫数</th>
					<th>数量</th>
				</tr>
				<c:forEach var="data" items="${input}" varStatus="st">
					<tr>
						<!--商品ID  -->
						<td>${data.item_id}</td>
						<!--商品名  -->
						<td>${data.item_name}</td>
						<!--価格 -->
						<td>${data.price}</td>
						<!--在庫数 -->
						<td>${data.quantity}</td>
						<!--数量  -->
						<td>${quantity}</td>
						<!--購入処理を行えるボタンの実装  -->
						<td class="button">
							<form action="Result" method="POST">
								<input class="common_button" type="submit" value="confirm"
									name="data.item_id">

								<input type="hidden" name="item_id" value="${data.item_id}">
								<input type="hidden" name="item_quantity"
									value="<%=request.getAttribute("quantity")%>">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>