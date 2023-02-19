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
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link rel="shortcut icon" href="img/icons/icon-48x48.png" />

	<link rel="canonical" href="https://demo-basic.adminkit.io/pages-sign-in.html" />

	<link href="${pageContext.request.contextPath}/resources/assets/static/css/app.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
	
	<link href="${pageContext.request.contextPath}/resources/assets/custom/mainStyle.css" rel="stylesheet">
	
	
<title>시험 목록 | LMS</title>
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
										<div class="mb-3">
										
											<!-- 강사 기능 : 시험 추가 -->
											<c:if test="${loginTeacher != null}">
												<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#addTest">시험 등록하기</button>
											</c:if>
										</div>
									</div>
									
									<!-- 시험 추가 모달 영역 -->
									<div class="modal" id="addTest" role="dialog">
									    <div class="modal-dialog">
									    
									      <!-- Modal content-->
									      <div class="modal-content">
									        <div class="modal-header">
										        <h4 style="font-weight: 700" class="modal-title">시험 등록</h4>
									          	<button type="button" class="close" data-dismiss="modal">×</button>
									          
									        </div>
									        <div class="modal-body">
									        
									        	<form action="${pageContext.request.contextPath}/teacher/test/addTest" method="post" id="addTestForm">
													<table class="table">
														<tr>
															<th style="vertical-align: middle;">시험 제목</th>
															<td>
																<input class="form-control" type="text" name="testTitle" value="${test.testTitle}">
																<div class="msg"></div>	
															</td>
														</tr>
												
														<tr>
															<!-- 내일날짜부터 -->
															<th style="vertical-align: middle;">시행 일시</th>
															<td>
																<input class="form-control" type="date" name="testDate" min="${minDate}" value="${test.testDate}">
																<div class="msg"></div>	
															</td>
														</tr>
													</table>
												</form>
												
										        </div>
										        <div class="modal-footer">
										        	<button type="button" id="addTestFormBtn" class="btn btn-primary">등록</button>
										          	<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
										        </div>
										        
									      </div>
									      
									    </div>
									</div>

								
								
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
											<form action="${pageContext.request.contextPath}/${path}/test/pastTestList" method="get" id="searchForm">
												<input type="hidden" name="rowPerPage">
												<label>
													<input type="text" name="searchWord" class="form-control form-control-sm" value="${map.searchWord}" placeholder="제목을 검색해주세요.">
												</label>
												<button type="button" class="btn btn-sm btn-primary" id="searchBtn">검색</button>
											</form>
											
										</div>
									</div>
									
									<table class="table table-hover my-0 text-center">
										<thead>
											<tr>
												<th>번호</th>
												<th>제목</th>
												<th class="d-none d-xl-table-cell">시험일</th>
												<td>&nbsp;</td>
											</tr>
										</thead>
										<!-- test List -->
										<tbody id="list">
											<c:forEach var="t" items="${map.testList}">
												<tr>
													<td>${t.testNo}</td>
													<td class="title"><a href="${pageContext.request.contextPath}/${path}/test/testOne?testNo=${t.testNo}">${t.testTitle}</a></td>
													<td class="d-none d-xl-table-cell">${t.testDate}</td>
													<td>
														<c:if test="${loginTeacher != null && todayDate < t.testDate}">
															<a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/teacher/test/modifyTest?testNo=${t.testNo}">관리</a>
														</c:if>
														
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
							
									<!-- 페이징 -->
									<div class="text-center mt-4">
										<div class="pagination">
											<div class="mb-4">
												<div class="btn-group me-2" role="group" aria-label="First group">
													<a class="btn" href="${pageContext.request.contextPath}/${path}/test/pastTestList?currentPage=1&searchWord=${map.searchWord}&rowPerPage=${map.rowPerPage}">처음으로</a>
													<a class="btn" href="${pageContext.request.contextPath}/${path}/test/pastTestList?currentPage=${map.startPage-1}&searchWord=${map.searchWord}&rowPerPage=${map.rowPerPage}">이전</a>
													<c:forEach var="i" begin="${map.startPage}" end="${map.endPage}" step="1">
														<c:if test="${map.currentPage == i}">
															<a class="btn active" href="${pageContext.request.contextPath}/${path}/test/pastTestList?currentPage=${i}&searchWord=${map.searchWord}&rowPerPage=${map.rowPerPage}">${i}</a>
														</c:if>
														<c:if test="${map.currentPage != i}">
															<a class="btn" href="${pageContext.request.contextPath}/${path}/test/pastTestList?currentPage=${i}&searchWord=${map.searchWord}&rowPerPage=${map.rowPerPage}">${i}</a>
														</c:if>
													</c:forEach>
													<a class="btn" href="${pageContext.request.contextPath}/${path}/test/pastTestList?currentPage=${map.endPage+1}&searchWord=${map.searchWord}&rowPerPage=${map.rowPerPage}">다음</a>
													<a class="btn" href="${pageContext.request.contextPath}/${path}/test/pastTestList?currentPage=${map.lastPage}&searchWord=${map.searchWord}&rowPerPage=${map.rowPerPage}">끝으로</a>
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
	
	
	<!-- 메시지 스크립트 -->
	<script>
		if($('#msg').val() != null && $('#msg').val() != ''){
			alert($('#msg').val());
		}
	</script>
	
	<!-- add Test form 스크립트 -->
	<script>
		$('#addTestFormBtn').click(function(){
			$($('.msg')[0]).text('');
			$($('.msg')[1]).text('');
			if($('input[name="testTitle"]').val().length == 0){
				$($('.msg')[0]).text(' 시행 일을 입력해주세요.');
				return;
			}
			if($('input[name="testDate"]').val().length == 0){
				$($('.msg')[1]).text(' 시행 일을 입력해주세요.');
				return;
			}
			$('#addTestForm').submit();
		});
	</script>
	
	
	<!-- search form 스크립트 -->
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
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>