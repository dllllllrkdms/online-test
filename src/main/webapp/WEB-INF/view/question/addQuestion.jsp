<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제 추가 | LMS</title>
</head>
<body>
	<!-- 강사 메뉴 -->
	<c:if test="${loginTeacher!=null}">
		<!-- empMenu -->
		<div>
			<c:import url="/WEB-INF/view/inc/teacherMenu.jsp"></c:import> <!-- JSTL로 include하기 -->
		</div>
	</c:if>
	
	<h1>강사 - 문제 추가</h1>
	
	<div>${msg}</div>
	
	<div>${test.testTitle}</div>
	<div>일시 : ${test.testDate}</div>
	<div id="table"></div>
	<table>
		<c:forEach var="m" items="+'model'+">
			
		</c:forEach>
	</table>
	
	<!-- 문제 추가 -->
	<form action="${pageContext.request.contextPath}/teacher/question/addQuestion" method="post">
		<input type="hidden" name="testNo" value="${testNo}">
		<table border="1">
			<tr>
				<th>문제 번호</th>
				<td><input type="number" name="questionIdx" value="${question.questionIdx}"></td>
			</tr>
			<tr>
				<th>문제</th>
				<td><input type="text" name="questionTitle" value="${question.questionTitle}"></td>
			</tr>
		</table>
		<button type="submit"> 추가 </button>
	</form>
	
	<!-- 보기 추가 -->
	<form>
		
	</form>
	
	<script>
		$.ajax({
			url: '/online-test/questionList';
			, type: 'get'
			, data : {'testNo': testNo}
			, success(function(model){
				let html = '';
				$('#id').html();
			})
		});
	</script>
</body>
</html>