<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="sidebar-nav">
	<li class="sidebar-item">
		<a class="sidebar-link" href="">
	        <i class="align-middle" data-feather="sliders"></i> <span class="align-middle">캘린더</span>
	    </a>
	</li>
	
	<li class="sidebar-header">
		시험
	</li>
	
	<li class="sidebar-item">
		<a class="sidebar-link" href="${pageContext.request.contextPath}/teacher/test/testList">
	    	<i class="align-middle" data-feather="user"></i> <span class="align-middle">시험 관리</span>
	    </a>
	</li>
	
	<li class="sidebar-header">
		학생
	</li>
	
	<li class="sidebar-item">
		<a class="sidebar-link" href="${pageContext.request.contextPath}/">
	    	<i class="align-middle" data-feather="user"></i> <span class="align-middle">성적 확인</span>
	    </a>
	</li>
	
	<li class="sidebar-item">
		<a class="sidebar-link" href="${pageContext.request.contextPath}/">
	    	<i class="align-middle" data-feather="user"></i> <span class="align-middle">통계</span>
	    </a>
	</li>
	
</ul>

