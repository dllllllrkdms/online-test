<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험 | LMS</title>
</head>
<body>

	<!-- 강사 메뉴 -->
	<c:if test="${loginTeacher!=null}">
		<div><c:import url="/WEB-INF/view/inc/teacherMenu.jsp"></c:import> <!-- JSTL로 include하기 --></div>
	</c:if>
	
	<!-- 학생 메뉴 -->
	<c:if test="${loginStudent!=null}">
		<div><c:import url="/WEB-INF/view/inc/studentMenu.jsp"></c:import></div>
	</c:if>
	
	<h1>강사, 학생 - 시험 상세보기</h1>
	
	<div>${msg}</div>
	
	<c:if test="${loginTeacher!=null}">
		<a href="${pageContext.request.contextPath}/teacher/question/modifyTest?testNo=${test.testNo}">시험 관리</a>
	</c:if>
	
	<!-- 시험 제목 -->
	<table>
		<tr>
			<th>시험 제목</th>
			<td>${test.testTitle}</td>
		</tr>
		<tr>
			<th>시행 일시</th>
			<td>${test.testDate}</td>
		</tr>
	</table>

	<!-- 문제 & 보기 -->
	<c:forEach var="q" items="${questionList}">
		<table>
			<tr>
				<td>${q.questionIdx}.</td>
				<td>${q.questionTitle}</td>
			</tr>
			<c:forEach var="e" items="${exampleList}">
				<c:if test="${q.questionNo == e.questionNo}">
					<tr>
						<td>${e.exampleIdx}. ${e.exampleTitle}</td>
						<td>${e.exampleOx}</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:forEach>
</body>
</html>