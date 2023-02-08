<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험 추가 | 강사 | 온라인 시험</title>
</head>
<body>
	<!-- 강사 메뉴 -->
	<c:if test="${loginTeacher!=null}">
		<!-- empMenu -->
		<div>
			<c:import url="/WEB-INF/view/inc/teacherMenu.jsp"></c:import> <!-- JSTL로 include하기 -->
		</div>
	</c:if>
	
	<h1>강사 - 시험 추가</h1>
	
	<div>${searchMsg}</div>
	
	<form action="${pageContext.request.contextPath}/teacher/test/addTest" method="post">
		<table border="1">
			<tr>
				<th>시험 제목</th>
				<td><input type="text" name="testTitle" value="${test.testTitle}"></td>
			</tr>
			<tr>
				<!-- 오늘 이전의 날짜가 들어가면 안됨 -->
				<th>시행 일시</th>
				<td><input type="date" name="testDate" min="${minDate}" value="${test.testDate}"></td>
			</tr>
		</table>
		<button type="submit"> 추가 </button>
	</form>
</body>
</html>