<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답지 추가 | LMS</title>
</head>
<body>

	<!-- 학생 메뉴 -->
	<c:if test="${loginStudent!=null}">
		<div><c:import url="/WEB-INF/view/inc/studentMenu.jsp"></c:import></div>
	</c:if>
	
	<h1>학생 - 시험 응시</h1>
	
	<div>${msg}</div>
	
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
	<form action="${pageContext.request.contextPath}/student/test/paper" method="post">
		<!-- 문제 & 객관식 보기 -->
		<c:forEach var="q" items="${questionList}" varStatus="m">
			<table>
				<tr>
					<td>${q.questionIdx}.</td>
					<td>${q.questionTitle}</td>
				</tr>
				<c:forEach var="e" items="${exampleList}" varStatus="n">
					<c:if test="${q.questionNo == e.questionNo}">
						<tr>
							<td>
								<input type="hidden" name="paperList[${m.index}].questionNo" value="${e.questionNo}">
								<input type="radio" name="paperList[${m.index}].answer" value="${e.exampleIdx}" id="answer${n.index}">
								<label for="answer${n.index}">${e.exampleIdx}. ${e.exampleTitle}</label>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</c:forEach>
		<button type="submit">제출하기</button>
	</form>
	
</body>
</html>