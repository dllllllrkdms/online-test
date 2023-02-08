<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제 수정 | 온라인 시험</title>
</head>
<body>
	<!-- 강사 메뉴 -->
	<c:if test="${loginTeacher!=null}">
		<!-- empMenu -->
		<div>
			<c:import url="/WEB-INF/view/inc/teacherMenu.jsp"></c:import> <!-- JSTL로 include하기 -->
		</div>
	</c:if>
	
	<h1>강사 - 문제, 보기 수정</h1>
	
	<div>${msg}</div>
	
	<form action="${pageContext.request.contextPath}/teacher/question/modifyQuestion" method="post">
		<input type="hidden" name="questionNo" value="${question.questionNo}">
		<input type="hidden" name="testNo" value="${question.testNo}">
		<table border="1">
			<tr>
				<th>문제 번호</th>
				<td><input type="text" name="questionIdx" value="${question.questionIdx}"></td>
			</tr>
			<tr>
				<th>문제</th>
				<td><input type="text" name="questionTitle" value="${question.questionTitle}"></td>
			</tr>
		</table>
		<table>
			<c:forEach var="e" items="${exampleList}" varStatus="s">
				<tr>
					<td>
						<input type="hidden" name="exampleList[${s.index}].exampleNo" value="${e.exampleNo}">
						<input type="number" name="exampleList[${s.index}].exampleIdx" value="${e.exampleIdx}" readonly="readonly">
						<input type="text" name="exampleList[${s.index}].exampleTitle" value="${e.exampleTitle}">
						<c:if test="${e.exampleOx == '정답'}">
							<input type="radio" name="exampleList[${s.index}].exampleOx" value="정답" checked="checked">정답
							<input type="radio" name="exampleList[${s.index}].exampleOx" value="오답">오답
						</c:if>
						<c:if test="${e.exampleOx == '오답'}">
							<input type="radio" name="exampleList[${s.index}].exampleOx" value="정답">정답
							<input type="radio" name="exampleList[${s.index}].exampleOx" value="오답" checked="checked">오답
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		<button type="submit"> 수정 </button>
	</form>
</body>
</html>