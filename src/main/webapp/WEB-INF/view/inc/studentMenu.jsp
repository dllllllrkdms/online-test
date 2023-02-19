<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<ul class="sidebar-nav">
	<li class="sidebar-item">
		<a class="sidebar-link" href="${pageContext.request.contextPath}/student/calendar">
	        <i class="align-middle me-2" data-feather="calendar"></i> <span class="align-middle">캘린더</span>
	    </a>
	</li>
	<li class="sidebar-item">
		<a data-bs-target="#myPage" data-bs-toggle="collapse" class="sidebar-link">
			<i class="align-middle" data-feather="sliders"></i> <span class="align-middle">마이 페이지<i class="align-middle me-2" data-feather="chevron-down"></i></span>
		</a>
		<ul id="myPage" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
			<li class="sidebar-item"><a class="sidebar-link" href="${pageContext.request.contextPath}/student/test/myTestList"><i class="align-middle me-2" data-feather="arrow-right"></i>점수 확인</a></li>
			<li class="sidebar-item"><a class="sidebar-link" href="dashboard-ecommerce.html"><i class="align-middle me-2" data-feather="arrow-right"></i>성적 통계
					<span class="sidebar-badge badge bg-primary">Pro</span></a></li>
			<li class="sidebar-item"><a class="sidebar-link" href="dashboard-crypto.html">Crypto <span
						class="sidebar-badge badge bg-primary">Pro</span></a></li>
		</ul>
	</li>
	
	<li class="sidebar-header">
		시험
	</li>
	
	<li class="sidebar-item">
		<a class="sidebar-link" href="${pageContext.request.contextPath}/student/test/testList">
	    	<i class="align-middle" data-feather="user"></i> <span class="align-middle">지난 시험 보기</span>
	    </a>
	</li>
	
	<li class="sidebar-header">
		성적
	</li>
	
	<li class="sidebar-item">
		<a class="sidebar-link" href="${pageContext.request.contextPath}/">
	    	<i class="align-middle" data-feather="user"></i> <span class="align-middle">성적 확인</span>
	    </a>
	</li>

</ul>
