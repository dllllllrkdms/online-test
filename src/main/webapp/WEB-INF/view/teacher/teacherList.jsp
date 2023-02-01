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
	<h1>사원 - 강사 출력</h1>
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
			<c:forEach var="t" items="${list}">
				<tr>
					<td>${t.teacherNo}</td>
					<td>${t.teacherId}</td>
					<td>${t.teacherName}</td>
					<td><a href="${pageContext.request.contextPath}/teacher/removeTeacher?teacherNo=${t.teacherNo}">삭제</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<a href="${pageContext.request.contextPath}/teacher/teacherList?currentPage=${currentPage-1}">이전</a>
		<a href="${pageContext.request.contextPath}/teacher/teacherList?currentPage=${currentPage+1}">다음</a>
	</div>
</body>
</html>