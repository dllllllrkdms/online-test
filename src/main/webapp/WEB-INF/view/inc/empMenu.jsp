<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="sidebar-nav">
	<li class="sidebar-item">
		<a class="sidebar-link" href="">
	        <i class="align-middle me-2" data-feather="calendar"></i> <span class="align-middle">캘린더</span>
	    </a>
	</li>


	<li class="sidebar-header">
		관리자
	</li>
	
	<li class="sidebar-item">
		<a class="sidebar-link" href="${pageContext.request.contextPath}/employee/empList">
	    	<i class="align-middle" data-feather="user"></i> <span class="align-middle">사원 관리</span>
	    </a>
	</li>
	
	<li class="sidebar-item">
		<a class="sidebar-link" href="${pageContext.request.contextPath}/employee/teacher/teacherList">
	    	<i class="align-middle" data-feather="user"></i> <span class="align-middle">강사 관리</span>
	    </a>
	</li>
	
	<li class="sidebar-item">
		<a class="sidebar-link" href="${pageContext.request.contextPath}/employee/student/studentList">
	    	<i class="align-middle" data-feather="user"></i> <span class="align-middle">학생 관리</span>
	    </a>
	</li>

</ul>