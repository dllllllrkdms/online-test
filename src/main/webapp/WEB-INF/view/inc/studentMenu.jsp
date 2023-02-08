<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	<!-- 지나간 시험 목록 + 점수 (table : test ) 리스트 + 점수 - 점수확인 버튼 - 제출답안지 확인 (table : paper)  
		오늘 날짜 시험 리스트는 응시 버튼 - 시험지 출력 (table : question + example) - 답안지 제출 (table : paper)
		 
	-->
	<a href="${pageContext.request.contextPath}/student/test/testList">시험 확인</a>
	
	<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
	<a href="${pageContext.request.contextPath}/student/modifyPw">비밀번호수정</a>
		<a href="${pageContext.request.contextPath}/student/removeStudent?studentNo=${loginStudent.studentNo}">회원 탈퇴</a> <!-- no 필수 값 -->
</div>