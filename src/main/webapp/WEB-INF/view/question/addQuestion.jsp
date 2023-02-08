<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제 추가 | 온라인 시험</title>
</head>
<body>
	<!-- 강사 메뉴 -->
	<c:if test="${loginTeacher!=null}">
		<!-- empMenu -->
		<div>
			<c:import url="/WEB-INF/view/inc/teacherMenu.jsp"></c:import> <!-- JSTL로 include하기 -->
		</div>
	</c:if>
	
	<h1>강사 - 문제, 보기 추가</h1>
	
	<div>${msg}</div>
	
	<form action="${pageContext.request.contextPath}/teacher/question/addQuestion" method="post">
		<input type="hidden" name="testNo" value="${testNo}">
		<table border="1">
			<tr>
				<th>문제 번호</th>
				<td><input type="number" name="questionIdx" value="${question.questionIdx}"></td>
			</tr>
			<tr>
				<th>문제</th>
				<td><input type="text" name="questionTitle" value="${question.questionTitle}"></td>
			</tr>
		</table>
		<table>
			<c:forEach var="n" begin="0" end="4" step="1">
				<tr>
					<td>
						<input type="number" name="exampleList[${n}].exampleIdx" value="${n+1}" readonly="readonly">
						<input type="text" name="exampleList[${n}].exampleTitle">
						<input type="checkbox" name="exampleList[${n}].exampleOx" value="정답">정답
						<input type="checkbox" name="exampleList[${n}].exampleOx" value="오답">오답
					</td>
				</tr>
			</c:forEach>
		</table>
		<button type="submit"> 추가 </button>
	</form>
</body>
</html>