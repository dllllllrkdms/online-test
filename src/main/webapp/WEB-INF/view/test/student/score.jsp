<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 학생 메뉴 -->
	<c:if test="${loginStudent!=null}">
		<div><c:import url="/WEB-INF/view/inc/studentMenu.jsp"></c:import></div>
	</c:if>
	
	<h1> 학생 - 내 점수 보기 </h1>
	${testNo}
	${score}
	
</body>
</html>