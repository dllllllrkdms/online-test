<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<ul class="sidebar-nav">
	<li class="sidebar-item">
		<a class="sidebar-link" href="${pageContext.request.contextPath}/student/calendar">
	        <i class="align-middle me-2" data-feather="calendar"></i> <span class="align-middle">캘린더</span>
	    </a>
	</li>

	<li class="sidebar-header">
		성적
	</li>
	<li class="sidebar-item">
		<a class="sidebar-link" href="${pageContext.request.contextPath}/student/score">
	    	<i class="align-middle me-2" data-feather="trending-up"></i> <span class="align-middle">성적 확인</span>
	    </a>
	</li>
	<li class="sidebar-item">
		<a class="sidebar-link" href="${pageContext.request.contextPath}/student/test/myTestList">
	    	<i class="align-middle me-2" data-feather="list"></i> <span class="align-middle">점수 확인</span>
	    </a>
	</li>
	
	<li class="sidebar-header">
		시험
	</li>
	<li class="sidebar-item">
		<a class="sidebar-link" href="${pageContext.request.contextPath}/student/test/pastTestList">
	    	<i class="align-middle me-2" data-feather="list"></i> <span class="align-middle">지난 시험 보기</span>
	    </a>
	</li>
</ul>
