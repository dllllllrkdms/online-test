<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 | 학생 | 온라인 시험 프로젝트</title>
</head>
<body>
	<div>
		<c:import url="/WEB-INF/view/inc/studentMenu.jsp"></c:import> 
	</div>
	<h1>학생 - 추가</h1>
	<div>${errorMsg}</div>
	<form action="${pageContext.request.contextPath}/addStudent" method="post">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="studentId"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="studentPw"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="studentName"></td>
			</tr>
		</table>
		<button type="submit">가입</button>
	</form>
</body>
</html>