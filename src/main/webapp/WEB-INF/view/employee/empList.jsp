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
	<h1>사원 - 목록</h1>
	<div><a href="${pageContext.request.contextPath}/employee/addEmp">사원등록</a></div>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>삭제</th>
		</tr>
		<c:forEach var="e" items="${list}">
			<tr>
				<td>${e.empNo}</td>
				<td>${e.empId}</td>
				<td>${e.empName}</td>
				<td><a href="${pageContext.request.contextPath}/employee/removeEmp?empNo=${e.empNo}">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<a href="${pageContext.request.contextPath}/employee/empList?currentPage=${currentPage-1}">이전</a>
		<a href="${pageContext.request.contextPath}/employee/empList?currentPage=${currentPage+1}">다음</a>
	</div>
</body>
</html>