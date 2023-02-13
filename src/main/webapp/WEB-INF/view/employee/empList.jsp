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
	
<title>사원 관리 | LMS</title>
</head>
<body>
	<input type="hidden" id="msg" value="${msg}">
	
	
	<div class="wrapper">
	
		<c:import url="/WEB-INF/view/inc/sideBar.jsp"></c:import> <!-- JSTL로 include하기 -->
		
		<div class="main">
			<c:import url="/WEB-INF/view/inc/navBar.jsp"></c:import>
		
			<main class="content">
				<div class="container-fluid p-0">
				
					<div class="row">
						<div class="col-12 d-flex">
						
							<div class="card flex-fill">
								<div class="card-header">
									<a href="${pageContext.request.contextPath}/employee/addEmp" class="btn btn-secondary">+ 사원 등록</a>
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-sm-12 col-md-6">
											<select id="rowPerPage" class="form-select form-select-sm mb-3">
												<option>5개씩</option>
												<option>10개씩</option>
												<option>20개씩</option>
											</select>
										</div>
										
										<div class="col-sm-12 col-md-6">
											<!-- 검색 -->
											<div class="search">
												<form action="${pageContext.request.contextPath}/employee/empList" method="get" id="searchForm">
													<label>
														<input type="text" name="searchWord" class="form-control form-control-sm" value="${searchWord}" placeholder="이름을 검색해주세요.">
													</label>
													<button type="button" class="btn btn-sm btn-primary" id="searchBtn">검색</button>
												</form>
											</div>
											
										</div>
									</div>
									
									
									<table class="table table-hover my-0">
										<thead>
											<tr>
												<th>번호</th>
												<th>아이디</th>
												<th>이름</th>
												<td>&nbsp;</td>
											</tr>
										</thead>
										
										<!-- emp List -->
										<tbody id="list">
											<c:forEach var="e" items="${list}">
												<tr>
													<td>${e.empNo}</td>
													<td>${e.empId}</td>
													<td>${e.empName}</td>
													<td><a rel="${e.empName}" class="remove btn btn-sm btn-primary" href="${pageContext.request.contextPath}/employee/removeEmp?empNo=${e.empNo}">탈퇴</a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									
							
									<!-- 페이징 -->
									<div class="text-center mt-4">
										<div class="pagination">
											<div class="mb-4">
												<div class="btn-group me-2" role="group" aria-label="First group">
													<a class="btn" href="${pageContext.request.contextPath}/employee/empList?currentPage=1&searchWord=${searchWord}">처음으로</a>
													<a class="btn" href="${pageContext.request.contextPath}/employee/empList?currentPage=${startPage-1}&searchWord=${searchWord}">이전</a>
													<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
														<c:if test="${currentPage == i}">
															<a class="btn active" href="${pageContext.request.contextPath}/employee/empList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
														</c:if>
														<c:if test="${currentPage != i}">
															<a class="btn" href="${pageContext.request.contextPath}/employee/empList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
														</c:if>
														
													</c:forEach>
													<a class="btn" href="${pageContext.request.contextPath}/employee/empList?currentPage=${endPage+1}&searchWord=${searchWord}">다음</a>
													<a class="btn" href="${pageContext.request.contextPath}/employee/empList?currentPage=${lastPage}&searchWord=${searchWord}">끝으로</a>
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

	
	<div>${searchMsg}</div>
	
	
	
</body>
</html>