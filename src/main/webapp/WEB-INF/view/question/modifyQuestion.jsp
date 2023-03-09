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
	
<title>문제 수정 | LMS</title>
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
									
									<div class="col-12 mt-3">
										<!-- 문제 수정 -->
										<form id="addForm">
											<input type="hidden" name="questionNo" value="${question.questionNo}">
											<input type="hidden" name="testNo" value="${question.testNo}">
											<table class="table">
												<tr>
													<th>번호</th>
													<td><input class="form-control" type="number" name="questionIdx" value="${question.questionIdx}" readonly="readonly"></td>
												</tr>
												<tr>
													<th>문제 내용</th>
													<td><textarea class="form-control" cols="50" rows="5" name="questionTitle" id="questionTitle" >${question.questionTitle}</textarea></td>
												</tr>
											</table>
											<div class="mb-3"><button type="button" id="addBtn" class="btn btn-primary">보기 추가</button></div>
											<table class="table" id="exampleTable">
												<c:forEach var="e" items="${exampleList}" varStatus="s">
													<tr class="exampleRow">
														<td>
															<input class="exampleTitle form-control" type="text" value="${e.exampleTitle}">
														</td>
														<td>
															<c:if test="${e.exampleOx == '정답'}">
																<input type="checkbox" class="exampleO form-check-input" value="정답" checked="checked">&nbsp;정답
															</c:if>
															<c:if test="${e.exampleOx == '오답'}">
																<input type="checkbox" class="exampleO form-check-input" value="정답">&nbsp;정답
															</c:if>
														</td>
														<td style="width: 10%;">
															<button type="button" class="removeBtn btn btn-secondary">x</button>
														</td>
													</tr>
												</c:forEach>
											</table>
											<div class="text-center">
												<button type="button" id="submitBtn" class="btn btn-primary">등록</button>
											</div>
										</form>
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

	<!-- 보기 추가 스크립트 -->
	<script>
	 	$('#addBtn').on("click", function(){
			let html = '<tr class="exampleRow">';
			html += '';
			html += '<td><input class="exampleTitle form-control" type="text"></td>';
			html += '<td>';
			html += '<input type="checkbox" class="exampleO form-check-input" value="정답">&nbsp;정답';
			html += '</td>';
			html += '<td><button type="button" class="removeBtn btn btn-secondary">x</button></td>';
			html += '</tr>';
			$('#exampleTable').append(html);
		});
	</script>
	
	<!-- 보기 삭제 스크립트 -->
	<script>
		$(document).on('click', '.removeBtn', function() {
			var idx = $('.removeBtn').index(this);
			$('.exampleRow').eq(idx).remove();
			console.log(idx);
		});
	</script>
	
	<!-- 유효성 검사 스크립트 -->
	<script>
		$('#submitBtn').click(function(){
			let exampleRowCnt = $('#exampleTable tr').length;
			
			
			if($('#questionTitle').val().length == 0){
				alert('문제를 입력해주세요.');
				$('#questionTitle').focus();
				return;
			}
			
			if($('#questionTitle').val().length == 0){
				alert('문제를 입력해주세요.');
				$('#questionTitle').focus();
				return;
			}
			
			$('.exampleTitle').each(function(index, item){
				if($(this).val().length == 0){
					alert('보기를 입력해주세요.');
					$(this).focus();
					return;
				}
			});
			
			if($('.exampleO:checked').length == 0){
				alert('정답을 하나 이상 선택해주세요');
				return;
			}
			
			let exampleRow = 0;
			$('.exampleTitle').each(function(index, item){
				let name = 'exampleList['+exampleRow+'].exampleTitle';
				$($('.exampleTitle')[index]).attr('name', name);
				exampleRow++;
			});
			
			exampleRow = 0;
			$('.exampleO').each(function(index, item){
				let name = 'exampleList['+exampleRow+'].exampleOx';
				$($('.exampleO')[index]).attr('name', name);
				exampleRow++;
			});
			
			$('#addForm').serialize();
			$('#addForm').attr('method', 'POST');
			$('#addForm').attr('action', '/online-test/teacher/test/modifyQuestion');
			$('#addForm').submit();
		});
	</script>

</body>
</html>