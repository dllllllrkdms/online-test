<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	<!-- 시험회차 (table : test) 관리(CRUD)
		개별 시험회차를 클릭 -> 그 회차의 문제리스트 출력(문제CRUD)  -->
	<a href="${pageContext.request.contextPath}/teacher/testList">시험관리</a>
	
	<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
	<a href="${pageContext.request.contextPath}/teacher/modifyTeacherPw">비밀번호수정</a>
</div>
