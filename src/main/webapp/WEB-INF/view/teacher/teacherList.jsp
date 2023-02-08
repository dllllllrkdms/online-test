<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강사 목록 | 사원 | 온라인 시험</title>
</head>
<body>
	<!-- empMenu -->
	<div>
		<c:import url="/WEB-INF/view/inc/empMenu.jsp"></c:import> <!-- JSTL로 include하기 -->
	</div>
	<h1>사원 - 강사 출력</h1>
	
	<div>${searchMsg}</div>
	
	<!-- 검색 -->
	<form action="${pageContext.request.contextPath}/employee/teacher/teacherList" method="get">
		<input type="text" name="searchWord" value="${searchWord}">
		<button type="submit">검색</button>
	</form>
	
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
					<td><a href="${pageContext.request.contextPath}/employee/teacher/removeTeacher?teacherNo=${t.teacherNo}">삭제</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 페이징 -->
	<div>
		<a href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=1&searchWord=${searchWord}">처음으로</a>
		<a href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${startPage-1}&searchWord=${searchWord}">이전</a>
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
			<a href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
		</c:forEach>
		<a href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${endPage+1}&searchWord=${searchWord}">다음</a>
		<a href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${lastPage}&searchWord=${searchWord}">끝으로</a>
	</div>
</body>
</html>