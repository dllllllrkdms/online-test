<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험 | 온라인 시험</title>
</head>
<body>

	<!-- 강사 메뉴 -->
	<c:if test="${loginTeacher!=null}">
		<div>
			<c:import url="/WEB-INF/view/inc/teacherMenu.jsp"></c:import> <!-- JSTL로 include하기 -->
		</div>
	</c:if>
	
	<h1>강사 - 시험 상세보기</h1>
	
	<div>${msg}</div>
	
	<!-- 강사 기능 -->
	<c:if test="${loginTeacher!=null}">
		<div>
			<a href="${pageContext.request.contextPath}/teacher/test/modifyTest?testNo=${test.testNo}">수정</a>
			<a href="${pageContext.request.contextPath}/teacher/test/removeTest?testNo=${test.testNo}">삭제</a>
		</div>
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
	
	<!-- 강사 기능 :  문제 추가 -->
	<c:if test="${loginTeacher!=null}">
		<a href="${pageContext.request.contextPath}/teacher/question/addQuestion?testNo=${test.testNo}">문제 추가</a>
	</c:if>
	
	<!-- 문제 & 보기 -->
	<c:forEach var="q" items="${questionList}">
		<table>
			<tr>
				<td>
					${q.questionIdx}.
					<!-- 강사 기능 : 문제 수정 -->
					<a href="${pageContext.request.contextPath}/teacher/question/modifyQuestion?questionNo=${q.questionNo}">수정</a>
					<a href="${pageContext.request.contextPath}/teacher/question/removeQuestion?testNo=${q.testNo}&questionNo=${q.questionNo}">삭제</a>
				</td>
			</tr>
			<tr>
				<td>${q.questionTitle}</td>
			</tr>
			<c:forEach var="e" items="${exampleList}">
				<c:if test="${q.questionNo == e.questionNo}">
					<tr>
						<td>${e.exampleIdx}. ${e.exampleTitle}   ${e.exampleOx}</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:forEach>
	
</body>
</html>