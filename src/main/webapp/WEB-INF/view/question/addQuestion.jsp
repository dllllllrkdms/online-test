<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제 추가 | LMS</title>
</head>
<body>
	<div><c:import url="/WEB-INF/view/inc/teacherMenu.jsp"></c:import></div>
	
	<h1>강사 - 문제 추가</h1>
	
	<div>${msg}</div>
	
	<div>${test.testTitle}</div>
	<div>일시 : ${test.testDate}</div>
	<div id="table"></div>
	<table>
		<c:forEach var="m" items="+'model'+">
			
		</c:forEach>
	</table>
	
	<!-- 문제 추가 -->
	<form action="${pageContext.request.contextPath}/teacher/question/addQuestion" method="post">
		<input type="hidden" name="testNo" value="${testNo}">
		<table border="1">
			<tr>
				<th>문제 번호</th>
				<td><input type="number" name="questionIdx" value="${question.questionIdx}"></td>
			</tr>
			<tr>
				<th>문제 내용</th>
				<td><textarea cols="5" rows="50" name="questionTitle">${qustion.questionTitle}</textarea></td>
			</tr>
		</table>
		<button type="submit"> 추가 </button>
	</form>

</body>
</html>