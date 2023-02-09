<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보기 수정 | LMS</title>
</head>
<body>
	<!-- 강사 메뉴 -->
	<c:if test="${loginTeacher!=null}">
		<div>
			<c:import url="/WEB-INF/view/inc/teacherMenu.jsp"></c:import> <!-- JSTL로 include하기 -->
		</div>
	</c:if>
	
	<h1>강사 - 보기 수정</h1>
	
	<div>${msg}</div>
	
	<form action="${pageContext.request.contextPath}/teacher/example/modifyExample" method="post">
		<table>
			<tr>
				<td>
					<input type="hidden" name="questionNo" value="${example.questionNo}">
					<input type="hidden" name="exampleNo" value="${example.exampleNo}">
					<input type="number" name="exampleIdx" value="${example.exampleIdx}">
					<input type="text" name="exampleTitle" value="${example.exampleTitle}">
					<c:if test="${example.exampleOx == '정답'}">
						<input type="radio" name="exampleOx" value="정답" checked="checked">정답
						<input type="radio" name="exampleOx" value="오답">오답
					</c:if>
					<c:if test="${example.exampleOx == '오답'}">
						<input type="radio" name="exampleOx" value="정답">정답
						<input type="radio" name="exampleOx" value="오답" checked="checked">오답
					</c:if>
				</td>
			</tr>
		</table>
		<button type="submit">추가</button>
	</form>
</body>
</html>