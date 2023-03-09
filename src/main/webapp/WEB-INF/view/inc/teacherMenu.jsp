<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="sidebar-nav">
	<li class="sidebar-item">
		<a class="sidebar-link" href="${pageContext.request.contextPath}/teacher/calendar"">
	       <i class="align-middle me-2" data-feather="calendar"></i> <span class="align-middle">캘린더</span>
	    </a>
	</li>
	
	<li class="sidebar-header">
		내 시험
	</li>
	<li class="sidebar-item">
		<a class="sidebar-link" href="${pageContext.request.contextPath}/teacher/test/testList">
	    	<i class="align-middle me-2" data-feather="list"></i><span class="align-middle">시험 관리</span>
	    </a>
	</li>
	<li class="sidebar-item">
		<a class="sidebar-link" href="${pageContext.request.contextPath}/teacher/test/pastTestList">
	    	<i class="align-middle me-2" data-feather="list"></i><span class="align-middle">지난 시험 보기</span>
	    </a>
	</li>
	
	<li class="sidebar-item">
		<a class="sidebar-link" href="${pageContext.request.contextPath}/teacher/test/avg">
	    	<i class="align-middle me-2" data-feather="trending-up"></i><span class="align-middle">성적 확인</span>
	    </a>
	</li>
	
</ul>

