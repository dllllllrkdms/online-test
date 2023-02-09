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
		<div>
			<c:import url="/WEB-INF/view/inc/teacherMenu.jsp"></c:import> <!-- JSTL로 include하기 -->
		</div>
	</c:if>
	
	<!-- 학생 메뉴 -->
	<c:if test="${loginStudent!=null}">
		<div><c:import url="/WEB-INF/view/inc/studentMenu.jsp"></c:import></div>
	</c:if>
	
	<h1>강사, 학생 - 시험 상세보기</h1>
	
	<div>${msg}</div>
	
	<c:if test="${loginTeacher!=null}">
		<a href="${pageContext.request.contextPath}/teacher/question/addQuestion?testNo=${test.testNo}">문제 추가</a>
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
				<td>
					<c:if test="${loginTeacher!=null}">
						<a href="${pageContext.request.contextPath}/teacher/question/modifyQuestion?questionNo=${q.questionNo}">문제 수정</a>
						<a href="${pageContext.request.contextPath}/teacher/question/removeQuestion?questinoNo=${q.questionNo}">문제 삭제</a>
					</c:if>
				</td>
			</tr>
			<c:if test="${loginTeacher!=null}">
				<a href="${pageContext.request.contextPath}/teacher/example/addExample?questionNo=${q.questionNo}">보기 추가</a>
			</c:if>
			<c:forEach var="e" items="${exampleList}">
				<c:if test="${q.questionNo == e.questionNo}">
					<tr>
						<td>${e.exampleIdx}. ${e.exampleTitle}</td>
						<td>${e.exampleOx}</td>
						<td>
							<c:if test="${loginTeacher!=null}">
								<a href="${pageContext.request.contextPath}/teacher/example/modifyExample?exampleNo=${e.exampleNo}">보기 수정</a>
								<a href="${pageContext.request.contextPath}/teacher/example/removeExample?exampleNo=${e.exampleNo}">보기 삭제</a>
							</c:if>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:forEach>
	
</body>
</html>