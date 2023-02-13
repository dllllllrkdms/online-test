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

<title>강사 관리 | LMS</title>
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
								<div class="card-body">
									<div class="row">
										<div class="col-sm-12 col-md-6">
											<select id="rowPerPage" class="form-select form-select-sm mb-3">
												<c:forEach var="r" begin="5" end="15" step="5">
													<c:if test="${map.rowPerPage == r}">
														<option value="${r}" selected="selected">${r}개씩</option>
													</c:if>
													<c:if test="${map.rowPerPage != r}">
														<option value="${r}">${r}개씩</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										
										<div class="col-sm-12 col-md-6">
											<!-- 검색 -->
											<form action="${pageContext.request.contextPath}/employee/teacher/teacherList" method="get" id="searchForm">
												<input type="hidden" name="rowPerPage">
												<label>
													<input type="text" name="searchWord" class="form-control form-control-sm" value="${searchWord}" placeholder="이름을 검색해주세요.">
												</label>
												<button type="button" class="btn btn-sm btn-primary" id="searchBtn">검색</button>
											</form>
											
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
										
										<!-- teacher List -->
										<tbody id="list">
											<c:forEach var="t" items="${map.list}">
												<tr>
													<td>${t.teacherNo}</td>
													<td>${t.teacherId}</td>
													<td>${t.teacherName}</td>
													<td><a rel="${t.teacherName}" class="remove btn btn-sm btn-primary" href="${pageContext.request.contextPath}/employee/teacher/removeTeacher?teacherNo=${t.teacherNo}">탈퇴</a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									
							
									<!-- 페이징 -->
									<div class="text-center mt-4">
										<div class="pagination">
											<div class="mb-4">
												<div class="btn-group me-2" role="group">
													<a class="btn" href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=1&searchWord=${map.searchWord}&rowPerPage=${map.rowPerPage}">처음으로</a>
													<a class="btn" href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${map.startPage-1}&searchWord=${map.searchWord}&rowPerPage=${map.rowPerPage}">이전</a>
													<c:forEach var="i" begin="${map.startPage}" end="${map.endPage}" step="1">
														<c:if test="${map.currentPage == i}">
															<a class="btn active" href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${i}&searchWord=${map.searchWord}&rowPerPage=${map.rowPerPage}">${i}</a>
														</c:if>
														<c:if test="${map.currentPage != i}">
															<a class="btn" href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${i}&searchWord=${map.searchWord}&rowPerPage=${map.rowPerPage}">${i}</a>
														</c:if>
													</c:forEach>
													<a class="btn" href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${map.endPage+1}&searchWord=${map.searchWord}&rowPerPage=${map.rowPerPage}">다음</a>
													<a class="btn" href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${map.lastPage}&searchWord=${map.searchWord}&rowPerPage=${map.rowPerPage}">끝으로</a>
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
	

	<script>
		$('#rowPerPage').change(function(){
			$('input[name="rowPerPage"]').val($(this).val());
			$('#searchForm').submit();
		});
		$('#searchBtn').click(function(){
			$('input[name="rowPerPage"]').val($('#rowPerPage').val());
			$('#searchForm').submit();
		});
	</script>
	
	
	
</body>
</html>