<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강사 등록 | 온라인 시험 프로젝트</title>
</head>
<body>
	<div>
		<c:import url="/WEB-INF/view/inc/teacherMenu.jsp"></c:import> 
	</div>
	<h1>강사 - 추가</h1>
	<div>${errorMsg}</div>
	<form action="${pageContext.request.contextPath}/addTeacher" method="post">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="teacherId"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="teacherPw"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="teacherName"></td>
			</tr>
		</table>
		<button type="submit">가입</button>
	</form>
</body>
</html>