<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<%-- Java入門 購入数入力画面 --%>
<!DOCTYPE html>
<head>
<title>購入画面</title>
<link href="<c:url value="/css/spring.css" />" rel="stylesheet" />
</head>
<body>
	<%@ include file="/WEB-INF/views/create/header.jsp"%>
	<div class="login_pane">
		<h1>ようこそショッピング風サイトへ</h1>

		<f:form action="Confirm" method="POST" modelAttribute="pr">
			<table class="table_list">
				<tbody>
					<tr>
						<th>商品ID</th>
						<th>商品名</th>
						<th>価格</th>
						<th>在庫数</th>
						<th>数量</th>
					</tr>
					<c:forEach var="data" items="${output}" varStatus="st">
						<tr>
							<!--商品ID  -->
							<td><c:out value="${data.item_id}" /></td>
							<!--商品名  -->
							<td><c:out value="${data.item_name}" /></td>
							<!--商品価格  -->
							<td><c:out value="${data.price}" /></td>
							<!--商品在庫  -->
							<td><c:out value="${data.quantity}" /></td>

							<c:if test="${data.quantity !=0}">
								<td><select class="list" name="${data.item_id}list">
										<c:forEach begin="1" end="${data.quantity}" step="1"
											varStatus="state">
											<option value="${state.count}">${state.count}</option>
										</c:forEach>
								</select></td>
								<!-- 購入ボタン -->
								<td class="button"><input class="common_button"
									type="submit" value="buy" name="${data.item_id}"></td>
							</c:if>
							<!-- 在庫なし -->
							<c:if test="${data.quantity==0}">
								<td></td>
								<td class="button">品切れ</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</f:form>



		<form action="login" method="POST">
			<input class="common_button" type="button" value="戻る">
		</form>
		<form action="history" method="POST">
			<input class="common_button" type="submit" value="購入履歴">
		</form>
	</div>
</body>
</html>