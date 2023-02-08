<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험 목록 | 온라인 시험</title>
</head>
<body>
	<!-- 강사 메뉴 -->
	<c:if test="${loginTeacher!=null}">
		<!-- empMenu -->
		<div>
			<c:import url="/WEB-INF/view/inc/teacherMenu.jsp"></c:import> <!-- JSTL로 include하기 -->
		</div>
	</c:if>
	
	<h1>강사 - 시험 출력</h1>
	
	<div>${msg}</div>
	<div>${searchMsg}</div>
	
	<c:if test="${loginTeacher != null}">
		<div>
			<a href="${pageContext.request.contextPath}/teacher/test/addTest">추가</a>
		</div>
	</c:if>
	
	<!-- 검색 -->
	<form action="${pageContext.request.contextPath}/teacher/test/testList" method="get">
		<input type="text" name="searchWord" value="${searchWord}">
		<button type="submit">검색</button>
	</form>
	
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>날짜</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="t" items="${list}">
				<tr>
					<td>${t.testNo}</td>
					<td>
						<a href="${pageContext.request.contextPath}/teacher/test/testOne?testNo=${t.testNo}">${t.testTitle}</a>	
					</td>
					<td>${t.testDate}</td>
					<td>
						<!-- 시험 날짜가 지나지 않았으면 수정/삭제 가능 -->
						<a href="${pageContext.request.contextPath}/teacher/test/modifyTest?testNo=${t.testNo}">수정</a>
						<a href="${pageContext.request.contextPath}/teacher/test/removeTest?testNo=${t.testNo}">삭제</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- a 태그는 * 표시가 되지 않음 -->
	<!-- 페이징 -->
	<div>
		<a href="${pageContext.request.contextPath}/teacher/test/testList?currentPage=1&searchWord=${searchWord}">처음으로</a>
		<a href="${pageContext.request.contextPath}/teacher/test/testList?currentPage=${startPage-1}&searchWord=${searchWord}">이전</a>
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
			<a href="${pageContext.request.contextPath}/teacher/test/testList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
		</c:forEach>
		<a href="${pageContext.request.contextPath}/teacher/test/testList?currentPage=${endPage+1}&searchWord=${searchWord}">다음</a>
		<a href="${pageContext.request.contextPath}/teacher/test/testList?currentPage=${lastPage}&searchWord=${searchWord}">끝으로</a>
	</div>
	
</body>
</html>