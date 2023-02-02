<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 | 사원 | 온라인 시험 프로젝트</title>
</head>
<body>
	<!-- 로그인 전 -->
	<c:if test="${loginEmp==null}">
		<h1>사원 - 로그인</h1>
		<form action="${pageContext.request.contextPath}/loginEmp" method="post">
			<table border="1">
				<tr>
					<th>아이디</th>
					<td><input type="text" name="empId"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="empPw"></td>
				</tr>
			</table>
			<button type="submit">로그인</button>
		</form>
	</c:if>
	
</body>
</html>