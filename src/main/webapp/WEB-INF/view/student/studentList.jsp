<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- empMenu -->
	<div>
		<c:import url="/WEB-INF/view/employee/inc/empMenu.jsp"></c:import> <!-- JSTL로 include하기 -->
	</div>
	<h1>사원 - 학생 출력</h1>
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>아이디</th>
				<th>이름</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="s" items="${list}">
				<tr>
					<td>${s.studentNo}</td>
					<td>${s.studentId}</td>
					<td>${s.studentName}</td>
					<td><a href="${pageContext.request.contextPath}/student/removeStudent?studentNo=${s.studentNo}">삭제</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<a href="${pageContext.request.contextPath}/student/studentList?currentPage=${currentPage-1}">이전</a>
		<a href="${pageContext.request.contextPath}/student/studentList?currentPage=${currentPage+1}">다음</a>
	</div>
</body>
</html>