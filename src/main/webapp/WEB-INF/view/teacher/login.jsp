<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 | 강사 | 온라인 시험 프로젝트</title>
</head>
<body>
	<!-- 로그인 전 -->
	<c:if test="${loginTeacher==null}">
		<h1>강사 - 로그인</h1>
		<div>${msg}</div>
		<div>${errorMsg}</div>
		<form action="${pageContext.request.contextPath}/loginTeacher" method="post">
			<table border="1">
				<tr>
					<th>아이디</th>
					<td><input type="text" name="teacherId"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="teacherPw"></td>
				</tr>
			</table>
			<button type="submit">로그인</button>
		</form>
		<a href="${pageContext.request.contextPath}/addTeacher">회원가입하기</a>
	</c:if>

</body>
</html>