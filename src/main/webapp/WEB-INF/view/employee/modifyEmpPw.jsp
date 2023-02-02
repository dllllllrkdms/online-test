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
		<c:import url="/WEB-INF/view/inc/empMenu.jsp"></c:import> <!-- JSTL로 include하기 -->
	</div>
	<h1>사원 - 비밀번호 수정</h1>
	<form action="${pageContext.request.contextPath}/employee/modifyEmpPw" method="post">
		<table border="1">
			<tr>
				<th>현재 비밀번호</th>
				<td><input type="password" name="oldPw"></td>
			</tr>
			<tr>
				<th>새 비밀번호</th>
				<td><input type="password" name="newPw"></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th> <!-- 자바스크립트 확인 -->
				<td><input type="password" name="checkPw"></td>
			</tr>
		</table>
		<button type="submit">수정</button>
	</form>
</body>
</html>