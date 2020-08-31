<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<%-- Java入門 購入確認画面 --%>
<!DOCTYPE html>
<head>
<title>購入確認</title>
<link href="<c:url value="/css/spring.css" />" rel="stylesheet" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
</head>
<body>
	<%@ include file="/WEB-INF/views/create/header.jsp"%>
	<div class="login_pane">
		<h1>商品概要</h1>
		<f:form action="${contextPath}/Confirm1" method="POST"
			modelAttribute="pr">
			<table class="table_list">
				<tbody>
					<tr>
						<th>商品ID</th>
						<!--商品ID  -->
						<td>${output.itemId}</td>
					</tr>
					<tr>
						<th>商品名</th>
						<!--商品名  -->
						<td>${output.itemName}</td>
					</tr>
					<tr>
						<th>商品概要</th>
						<!--商品概要 -->
						<td>${output.detail}</td>
					</tr>
					<tr>
						<th>価格</th>
						<!--価格 -->
						<td>${output.price}</td>
					</tr>
					<tr>
						<th>（在庫）</th>
						<!--在庫数 -->
						<td>${output.quantity}</td>
					</tr>
					<tr>
						<th>数量</th>

						<c:if test="${output.quantity !=0}">

							<td><select class="list" name="${output.itemId}list">
									<c:forEach begin="1" end="${output.quantity}" step="1"
										varStatus="state">
										<option value="${state.count}">${state.count}</option>
									</c:forEach>
							</select></td>
							<!-- 購入ボタン -->

						</c:if>
						<!-- 在庫なし -->
						<c:if test="${output.quantity==0}">
							<td class="button">品切れ</td>
						</c:if>

					</tr>
				</tbody>
			</table>
			<td class="button"><input class="common_button" type="submit"
				value="buy" name="${output.itemId}"></td>
			<td class="button"><input class="big_button" type="submit"
				value="カートに入れて他の商品を購入する" name="${output.itemId}" ></td>
		</f:form>
		<f:form action="${contextPath}/Review" method="POST"
			modelAttribute="pr">
			<td class="button">
			<INPUT class="big_button" type="submit" value="レビューを書く"></td>
		</f:form>

		<FORM>
			<INPUT class="common_button" type="button" value="戻る"
				onClick="history.back()">
		</FORM>

	</div>

</body>
</html>