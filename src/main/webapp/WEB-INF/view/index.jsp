<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>online-test</title>
</head>
<body>
	<h1>index</h1>
	<div>${msg}</div>
	<!-- 사원 메뉴 -->
	<c:if test="${loginEmp!=null}">
		<!-- empMenu -->
		<div>
			<c:import url="/WEB-INF/view/inc/empMenu.jsp"></c:import> <!-- JSTL로 include하기 -->
		</div>
	</c:if>
	<!-- 강사 메뉴 -->
	<c:if test="${loginTeacher!=null}">
		<!-- empMenu -->
		<div>
			<c:import url="/WEB-INF/view/inc/teacherMenu.jsp"></c:import> <!-- JSTL로 include하기 -->
		</div>
	</c:if>
	
	<!-- 학생 메뉴 -->
	<c:if test="${loginStudent!=null}">
		<!-- empMenu -->
		<div>
			<c:import url="/WEB-INF/view/inc/studentMenu.jsp"></c:import> <!-- JSTL로 include하기 -->
		</div>
	</c:if>
	
	<!-- 로그인 전 -->
	<c:if test="${loginEmp==null && loginTeacher==null && loginStudent==null}">
		<div>
			<a href="${pageContext.request.contextPath}/loginEmp">사원 로그인</a>
			<a href="${pageContext.request.contextPath}/loginTeacher">강사 로그인</a>
			<a href="${pageContext.request.contextPath}/loginStudent">학생 로그인</a>
		</div>
	</c:if>
</body>
</html>