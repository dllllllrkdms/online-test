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
	<h1>사원 - 추가</h1>
	<div>${errorMsg}</div>
	<form action="${pageContext.request.contextPath}/employee/addEmp" method="post">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="empId"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="empPw"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="empName"></td>
			</tr>
		</table>
		<button type="submit">가입</button>
	</form>
</body>
</html>