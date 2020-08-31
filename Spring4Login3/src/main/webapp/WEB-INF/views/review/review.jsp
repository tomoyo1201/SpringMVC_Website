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
<title>レビュー画面</title>
</head>

<div class="login_pane">
	<p>
		総合評価 <span id="span1"></span>
	</p>

	<form name="form1">
		<input type="checkbox" name="color1" value="★" checked> ★ <input
			type="checkbox" name="color1" value="★★"> ★★ <input
			type="checkbox" name="color1" value="★★★"> ★★★
	</form>

	<input type="button" value="星選択" class="common_button"
		onclick="clickBtn1()" /> <br />
	<hr style="border: 0; border-top: 1px solid blue;">
	<f:form action="${contextPath}/PostReview" method="POST"
		modelAttribute="output">
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
		<p>写真を追加</p>
		<input type="file" name="file" class="myImage" id="myImage" accept="image/*">
		<br />
		<img id="preview"
			class="myImage" width="200" height="200">
		<script>
				$('#myImage').on('change', function(e) {
					var reader = new FileReader();
					reader.onload = function(e) {
						$("#preview").attr('src', e.target.result);
					}
					reader.readAsDataURL(e.target.files[0]);
				})
			</script>
		<script>
				function clickBtn1() {
					const arr1 = [];
					const color1 = document.form1.color1;

					for (let i = 0; i < color1.length; i++) {
						if (color1[i].checked) { //(color1[i].checked === true)と同じ
							arr1.push(color1[i].value);
						}
					}
					document.getElementById("span1").textContent = arr1;
				}
			</script>


		<hr style="border: 0; border-top: 1px solid blue;">
		<p>Add a headline</p>
		<input type="text" class="headline" name="headline" id="headline">
		<p>Write Your Review</p>
		<textarea name="contents" rows="10" class="contents" id="contents">What did you like or dislike?</textarea>

		<td class="button"><input type="submit" value="submit"
			class="common_button" /></td>
		<br />
	</f:form>




	<h2>Spring MVC file upload example</h2>
<%-- 		                <form method="POST" action="upload" enctype="multipart/form-data">
                    <input type="file" name="file">
                    <label class="label-info">Destination:</label>
                    <input type="text" value="/tmp" name="destination" id="destination">
                    <button type="submit" class="btn btn-primary">upload</button>
                </form> --%>

</div>
</body>
</html>