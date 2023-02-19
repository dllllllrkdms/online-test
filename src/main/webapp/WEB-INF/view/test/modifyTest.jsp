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

<title>시험 수정 | LMS</title>
</head>
<body>
	<div class="wrapper">
	
		<c:import url="/WEB-INF/view/inc/sideBar.jsp"></c:import> <!-- JSTL로 include하기 -->
	
		<div class="main">
			<c:import url="/WEB-INF/view/inc/navBar.jsp"></c:import>
			
			<main class="content">
				<div class="container-fluid p-0">
					
					<div class="row">
						<div class="col-12 d-flex">
							
							<div class="card flex-fill">
								<div class="card-body">
	
										<table class="table">
											<tr>
												<th style="width: 20%">시험 제목</th>
												<td>${test.testTitle}</td>
											</tr>
											<tr>
												<th>시행 일시</th>
												<td>${test.testDate}</td>
											</tr>
										</table>
										
									<hr>
									<div class="mb-3"><a class="btn btn-primary" href="${pageContext.request.contextPath}/teacher/test/addQuestion?testNo=${test.testNo}">문제 추가</a></div>
							
									<table class="table">
										<tr>
											<th>번호</th>
											<th >문제 내용</th>
											<th style="width: 15%">&nbsp;</th>
										</tr>
							
										<c:forEach var="q" items="${questionList}">
									
											<tr>
												<td>${q.questionIdx}</td>
												<td>${q.questionTitle}</td>
												<td><a href="${pageContext.request.contextPath}/teacher/test/modifyQuestion?questionNo=${q.questionNo}" class="btn btn-sm btn-primary">관리</a></td>
											</tr>
												
										</c:forEach>
									</table>
								</div>
							</div>
							
						</div>
					</div>
				</div>
					
			</main>
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/resources/assets/static/js/app.js"></script>
	
</body>
</html>