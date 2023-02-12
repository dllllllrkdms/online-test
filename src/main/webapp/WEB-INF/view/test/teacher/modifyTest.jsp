<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험 수정 | LMS</title>
</head>
<body>
	<!-- 강사 메뉴 -->
	<div><c:import url="/WEB-INF/view/inc/teacherMenu.jsp"></c:import></div>
	
	<h1>강사 - 시험 수정</h1>
	
	<form action="${pageContext.request.contextPath}/teacher/test/modifyTest" method="post">
		<input type="hidden" name="testNo" value="${test.testNo}">
		<table>
			<tr>
				<th>시험 제목</th>
				<td><input type="text" name="testTitle" value="${test.testTitle}"></td>
			</tr>
			<tr>
				<th>시행 일시</th>
				<td><input type="date" name="testDate" value="${test.testDate}" readonly="readonly"></td>
			</tr>
		</table>
		<button type="submit"> 수정 </button>
	</form>
</body>
</html>