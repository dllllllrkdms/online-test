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
	
<title>내 시험 | LMS</title>
</head>
<body>
	<input type="hidden" id="msg" value="${msg}">
	
	
	<div class="wrapper">
	
		<c:import url="/WEB-INF/view/inc/sideBar.jsp"></c:import> <!-- JSTL로 include하기 -->
	
		<div class="main">
			<c:import url="/WEB-INF/view/inc/navBar.jsp"></c:import>
			
			<main class="content">
				<div class="container-fluid p-0">
				
					<div class="mb-3">
						<h1 class="h3 d-inline align-middle">마이 페이지</h1>
					</div>
					
					<div class="row">
						<div class="col-md-4 col-xl-3">
							<div class="card mb-3">
								<div class="card-header">
									<h5 class="card-title mb-0">Profile Details</h5>
								</div>
								<div class="card-body text-center">
									<h5 class="card-title mb-0">Christina Mason</h5>
									<div class="text-muted mb-2">Lead Developer</div>

									<div>
										<a class="btn btn-primary btn-sm" href="#">Follow</a>
										<a class="btn btn-primary btn-sm" href="#"><span data-feather="message-square"></span> Message</a>
									</div>
								</div>
								<hr class="my-0" />
								<div class="card-body">
									<h5 class="h6 card-title">Skills</h5>
									<a href="#" class="badge bg-primary me-1 my-1">HTML</a>
									<a href="#" class="badge bg-primary me-1 my-1">JavaScript</a>
									<a href="#" class="badge bg-primary me-1 my-1">Sass</a>
									<a href="#" class="badge bg-primary me-1 my-1">Angular</a>
									<a href="#" class="badge bg-primary me-1 my-1">Vue</a>
									<a href="#" class="badge bg-primary me-1 my-1">React</a>
									<a href="#" class="badge bg-primary me-1 my-1">Redux</a>
									<a href="#" class="badge bg-primary me-1 my-1">UI</a>
									<a href="#" class="badge bg-primary me-1 my-1">UX</a>
								</div>
								<hr class="my-0" />
								<div class="card-body">
									<h5 class="h6 card-title">About</h5>
									<ul class="list-unstyled mb-0">
										<li class="mb-1"><span data-feather="home" class="feather-sm me-1"></span> Lives in <a href="#">San Francisco, SA</a></li>

										<li class="mb-1"><span data-feather="briefcase" class="feather-sm me-1"></span> Works at <a href="#">GitHub</a></li>
										<li class="mb-1"><span data-feather="map-pin" class="feather-sm me-1"></span> From <a href="#">Boston</a></li>
									</ul>
								</div>
								<hr class="my-0" />
								<div class="card-body">
									<h5 class="h6 card-title">Elsewhere</h5>
									<ul class="list-unstyled mb-0">
										<li class="mb-1"><a href="#">staciehall.co</a></li>
										<li class="mb-1"><a href="#">Twitter</a></li>
										<li class="mb-1"><a href="#">Facebook</a></li>
										<li class="mb-1"><a href="#">Instagram</a></li>
										<li class="mb-1"><a href="#">LinkedIn</a></li>
									</ul>
								</div>
							</div>
						</div>
						
						<div class="col-md-8 col-xl-9">
							<div class="card">
								<div class="card-header">
									<h5 class="card-title mb-0">성적</h5>
								</div>
								
								<div class="card-body h-100">
									<table class="table table-hover my-0 text-center">
										<thead>
											<tr>
												<th>제목</th>
												<th>점수</th>
												<th>&nbsp;</th>
											</tr>
										</thead>
										<!-- test List -->
										<tbody id="list">
											<c:forEach var="t" items="${testList}">
												<tr>
													<td>${t.testTitle}</td>
													<td>${t.score}</td>
													<td><a href="${pageContext.request.contextPath}/student/answer?testNo=${t.testNo}">답안확인</a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									
									<!-- 페이징 -->
									<div class="text-center mt-4">
										<div class="pagination">
											<div class="mb-4">
												<div class="btn-group me-2">
													<c:if test="${page.prev}">
														<a class="btn" href="${pageContext.request.contextPath}/student/test/myTestList?currentPage=1&searchWord=${page.map.searchWord}">처음으로</a>
														<a class="btn" href="${pageContext.request.contextPath}/student/test/myTestList?currentPage=${page.startPage-1}&searchWord=${page.map.searchWord}">이전</a>
													</c:if>
													<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}" step="1">
														<c:if test="${page.currentPage == i}">
															<a class="btn active" href="${pageContext.request.contextPath}/student/test/myTestList?currentPage=${i}&searchWord=${page.map.searchWord}">${i}</a>
														</c:if>
														<c:if test="${page.currentPage != i}">
															<a class="btn" href="${pageContext.request.contextPath}/student/test/myTestList?currentPage=${i}&searchWord=${page.map.searchWord}">${i}</a>
														</c:if>
													</c:forEach>
													<c:if test="${page.next}">
														<a class="btn" href="${pageContext.request.contextPath}/student/test/myTestList?currentPage=${page.endPage+1}&searchWord=${page.map.searchWord}">다음</a>
														<a class="btn" href="${pageContext.request.contextPath}/student/test/myTestList?currentPage=${page.lastPage}&searchWord=${page.map.searchWord}">끝으로</a>
													</c:if>
												</div>												
											</div>
										</div>
									</div>
									
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