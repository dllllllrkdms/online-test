<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보기 추가 | LMS</title>
</head>
<body>
	<!-- 강사 메뉴 -->
	<c:if test="${loginTeacher!=null}">
		<div>
			<c:import url="/WEB-INF/view/inc/teacherMenu.jsp"></c:import> <!-- JSTL로 include하기 -->
		</div>
	</c:if>
	
	<h1>강사 - 보기 추가</h1>
	
	<div>${msg}</div>
	
	<form action="${pageContext.request.contextPath}/teacher/example/addExample" method="post">
		<table>
			<tr>
				<td>
					<input type="hidden" name="questionNo" value="${questionNo}">
					<input type="number" name="exampleIdx"> <!-- readonly로 바꿀것 -->
					<input type="text" name="exampleTitle">
					<input type="radio" name="exampleOx" value="정답">정답
					<input type="radio" name="exampleOx" value="오답">오답
				</td>
			</tr>
		</table>
		<button type="submit">추가</button>
	</form>
</body>
</html>