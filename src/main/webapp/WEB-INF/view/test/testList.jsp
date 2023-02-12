<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<title>시험 일정 | LMS</title>
</head>
<body>
	<!-- 강사 메뉴 -->
	<c:if test="${loginTeacher!=null}">
		<div><c:import url="/WEB-INF/view/inc/teacherMenu.jsp"></c:import></div>
	</c:if>
	
	<!-- 학생 메뉴 -->
	<c:if test="${loginStudent!=null}">
		<div><c:import url="/WEB-INF/view/inc/studentMenu.jsp"></c:import></div>
	</c:if>
	
	<h1>강사, 학생 - 지난 시험 출력</h1>
	
	<div>${msg}</div>
	
	<!-- 강사 기능 : 시험 추가 -->
	<c:if test="${loginTeacher != null}">
		<div><a href="${pageContext.request.contextPath}/teacher/test/addTest">추가</a></div>
	</c:if>
	
	<!-- 검색 -->
	<form action="${pageContext.request.contextPath}/${path}/test/testList" method="get">
		<input type="text" name="searchWord" value="${searchWord}">
		<button type="submit">검색</button>
	</form>
	
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>시험일</th>
				<td>&nbsp;</td>
			</tr>
		</thead>
		<!-- test List -->
		<tbody id="list">
			<c:forEach var="t" items="${map.testList}">
				<tr>
					<td>${t.testNo}</td>
					<td><a href="${pageContext.request.contextPath}/${path}/test/testOne?testNo=${t.testNo}">${t.testTitle}</a></td>
					<td>${t.testDate}</td>
					<td>
						<c:if test="${loginTeacher != null}">
							<a href="${pageContext.request.contextPath}/teacher/test/modifyTest?testNo=${t.testNo}">수정</a>
						</c:if>
						
						<c:if test="${loginStudent!=null}">
							<a href="${pageContext.request.contextPath}/student/test/testOne?testNo=${t.testNo}">점수 확인</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 페이징 - ajax-->
	<div id="pagination">
		
		<a href="${pageContext.request.contextPath}/${path}/test/testList?currentPage=1&searchWord=${searchWord}">처음으로</a>
		<a href="${pageContext.request.contextPath}/${path}/test/testList?currentPage=${map.startPage-1}&searchWord=${searchWord}">이전</a>
		<c:forEach var="i" begin="${map.startPage}" end="${map.endPage}" step="1">
			<a href="${pageContext.request.contextPath}/${path}/test/testList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
		</c:forEach>
		<a href="${pageContext.request.contextPath}/${path}/test/testList?currentPage=${map.endPage+1}&searchWord=${searchWord}">다음</a>
		<a href="${pageContext.request.contextPath}/${path}/test/testList?currentPage=${map.lastPage}&searchWord=${searchWord}">끝으로</a>
		
	</div>
	
	

</body>
</html>