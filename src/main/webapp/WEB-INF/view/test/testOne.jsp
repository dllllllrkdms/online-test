<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
	<meta name="author" content="AdminKit">
	<meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link rel="shortcut icon" href="img/icons/icon-48x48.png" />

	<link rel="canonical" href="https://demo-basic.adminkit.io/pages-sign-in.html" />

	<link href="${pageContext.request.contextPath}/resources/assets/static/css/app.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<link href="${pageContext.request.contextPath}/resources/assets/custom/mainStyle.css" rel="stylesheet">
	
<title>시험 | LMS</title>
</head>
<body>
	<input type="hidden" id="msg" value="${msg}">

	<div class="wrapper">
	
		<c:import url="/WEB-INF/view/inc/sideBar.jsp"></c:import> <!-- JSTL로 include하기 -->
	
		<div class="main">
			<c:import url="/WEB-INF/view/inc/navBar.jsp"></c:import>
			
			<main class="content">
				<div class="container-fluid p-0">
					
					<div class="col-md-8">
						<div class="row">
						
							<!-- 시험 제목 -->
							<table class="mb-3">
								<tr>
									<th>시험 제목</th>
									<td>${test.testTitle}</td>
								</tr>
								<tr>
									<th>시행 일시</th>
									<td>${test.testDate}</td>
								</tr>
								<tr>
									<th>총 문항 수</th>
									<td>${questionCount}</td>
								</tr>
							</table>
							
						</div>		
						<div class="row">
							<div class="col-md-6">
								<!-- 문제 & 보기 -->
								<c:forEach var="q" items="${questionList}">
									<c:if test="${q.questionIdx != 1 && q.questionIdx % 5 == 1 }">
										</div>
										
										<div class="col-md-6">
									</c:if>
									<table class="mb-3 table table-borderless ">
										<tr style="border-bottom: 1px solid gray">
											<th style="width: 10%">${q.questionIdx}.</th>
											<th>${q.questionTitle}</th>
										</tr>
										<c:forEach var="e" items="${exampleList}">
											<c:if test="${q.questionNo == e.questionNo}">
												<tr>
													<td>${e.exampleIdx}.</td>
													<td>${e.exampleTitle}</td>
												</tr>
											</c:if>		
										</c:forEach>
									</table>
								</c:forEach>
								
							</div>
						
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
	
	<script src="${pageContext.request.contextPath}/resources/assets/static/js/app.js"></script>
	
	<script>
		if($('#msg').val() != null && $('#msg').val() != ''){
			alert($('#msg').val());
		}
	</script>
		
	
</body>
</html>