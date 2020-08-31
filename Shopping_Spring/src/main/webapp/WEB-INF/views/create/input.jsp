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
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<%@ include file="/WEB-INF/views/create/header.jsp"%>
	<%--  	<form action="<s:url value="/user/contact_search"/>">

 --%>

	<form method="post" action="Search">
		<table class="table">
			<tr>
				<td colspan="5">商品ID<input type="text" name="key_itemId"
					maxlength="100" style="width: 55%;" value=""></td>
			</tr>
			<tr>
				<td colspan="5">商品名<input type="text" name="key_itemName"
					maxlength="100" style="width: 55%;" value="">例）メロン
				</td>
			</tr>
			<td class="button"><input class="common_button" type="submit"
				value="Search" name=""></td>
			</form>
		</table>
		<div class="login_pane">
			<h1>ようこそショッピング風サイトへ</h1>

<%-- 			<f:form action="Confirm" method="POST" modelAttribute="pr"> --%>
			<f:form action="${contextPath}/Confirm" method="POST" modelAttribute="pr">
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
								<td><a href="${contextPath}/new/detail/${data.item_id}" name="${data.item_id}" value="detail"><c:out value="${data.item_id}" /></a></td>
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
									<td class="button" onclick="return confirm('カートに入れてもいいですか?')"><input class="common_button"
										type="submit" value="buy" name="${data.item_id}" ></td>
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
			<br /> <a href="${contextPath}/viewemp/1">1</a> <a
				href="${contextPath}/viewemp/2">2</a> <a
				href="${contextPath}/viewemp/3">3</a>


			<form action="login" method="POST">
				<input class="common_button" type="button" value="戻る">
			</form>
			<form action="${contextPath}/history" method="POST">
				<input class="common_button" type="submit" value="購入履歴">
			</form>
 			<form action="${contextPath}/check" method="POST">
				<input class="right_button" type="submit" value="カートを確認">
			</form>
		</div>
</body>

	<script>
		/* 未入力の項目があるとエラーメッセージを表示 */
/* 		function confirm() { */

		/* 		  alert("カートに入れますか？") */


	</script>
</html>