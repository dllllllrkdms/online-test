<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		<nav id="sidebar" class="sidebar js-sidebar">
			<div class="sidebar-content js-simplebar">
				<a class="sidebar-brand" href="${pageContext.request.contextPath}/index"><span class="align-middle">GOODEE</span></a>
				
				
				<c:if test="${loginEmp==null && loginTeacher==null && loginStudent==null}">
					<div class="sidebar-cta">
						<div style="margin: 1.75rem; padding: 1.5rem;">
							<div class="d-grid">
								<a href="${pageContext.request.contextPath}/loginStudent" class="btn btn-lg btn-primary">로그인</a>
							</div>
						</div>
					</div>
				</c:if>
				
				
				<c:if test="${loginEmp != null}">
					<c:import url="/WEB-INF/view/inc/empMenu.jsp"></c:import>
				</c:if>
				
				<c:if test="${loginTeacher != null}">
					<c:import url="/WEB-INF/view/inc/teacherMenu.jsp"></c:import>
				</c:if>
				
				<c:if test="${loginStudent != null}">
					<c:import url="/WEB-INF/view/inc/studentMenu.jsp"></c:import>
				</c:if>
			
			</div>
		</nav>