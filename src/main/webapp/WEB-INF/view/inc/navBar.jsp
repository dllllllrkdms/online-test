<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		
		<nav class="navbar navbar-expand navbar-light navbar-bg">
			<a class="sidebar-toggle js-sidebar-toggle"><i class="hamburger align-self-center"></i></a>

			<div class="navbar-collapse collapse">
				<ul class="navbar-nav navbar-align">
					
					<li class="nav-item dropdown">
						<a class="nav-icon dropdown-toggle d-inline-block d-sm-none" href="#" data-bs-toggle="dropdown">
               				<i class="align-middle" data-feather="settings"></i>
             				</a>
						
						<c:if test="${loginStudent != null}">
							<a class="nav-link dropdown-toggle d-none d-sm-inline-block" href="#" data-bs-toggle="dropdown">
                				<i class="align-middle me-2" data-feather="user"></i><span class="text-dark">${loginStudent.studentName}</span>
              				</a>
							<div class="dropdown-menu dropdown-menu-end">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/student/modifyPw"><i class="align-middle me-2" data-feather="lock"></i>비밀번호 변경</a>
								<a rel="${loginStudent.studentName}" class="dropdown-item" href="${pageContext.request.contextPath}/student/removeStudent?studentNo=${loginStudent.studentNo}"><i class="align-middle me-2" data-feather="user-x"></i>회원 탈퇴</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/logout"><i class="align-middle me-2" data-feather="log-out"></i>로그아웃</a>
							</div>
						</c:if>
						
						<c:if test="${loginEmp != null}">
							<a class="nav-link dropdown-toggle d-none d-sm-inline-block" href="#" data-bs-toggle="dropdown">
                				<i class="align-middle me-2" data-feather="user"></i><span class="text-dark">${loginEmp.empName}</span>
              				</a>
							<div class="dropdown-menu dropdown-menu-end">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/employee/modifyPw"><i class="align-middle me-2" data-feather="lock"></i>비밀번호 변경</a>
								<a rel="${loginEmp.empName}" class="dropdown-item" href="${pageContext.request.contextPath}/employee/removeEmp?studentNo=${loginEmp.empNo}"><i class="align-middle me-2" data-feather="user-x"></i>회원 탈퇴</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/logout"><i class="align-middle me-2" data-feather="log-out"></i>로그아웃</a>
							</div>
						</c:if>
						
						<c:if test="${loginTeacher != null}">
							<a class="nav-link dropdown-toggle d-none d-sm-inline-block" href="#" data-bs-toggle="dropdown">
                				<i class="align-middle me-2" data-feather="user"></i><span class="text-dark">${loginTeacher.teacherName}</span>
              				</a>
							<div class="dropdown-menu dropdown-menu-end">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/teacher/modifyPw"><i class="align-middle me-2" data-feather="lock"></i>비밀번호 변경</a>
								<a rel="${loginTeacher.teacherName}" class="dropdown-item" href="${pageContext.request.contextPath}/teacher/removeTeacher?teacherNo=${loginTeacher.teacherNo}"><i class="align-middle me-2" data-feather="user-x"></i>회원 탈퇴</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/logout"><i class="align-middle me-2" data-feather="log-out"></i>로그아웃</a>
							</div>
						</c:if>
						
						
					</li>
				</ul>
			</div>
		</nav>
	
	<script>
		$('.remove').click(function(){
			let result = confirm($(this).attr('rel')+'님, 탈퇴하시겠습니까?');
			if(result){
				return true;
			} else {
				return false;
			}
		});
	</script>
	
	