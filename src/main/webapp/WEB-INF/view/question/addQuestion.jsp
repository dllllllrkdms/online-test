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
	
<title>문제 추가 | LMS</title>
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
				
									<!-- 시험 제목 -->
									<div><strong>시험 제목</strong> : ${test.testTitle}</div>
									<div><strong>시행 일시</strong> : ${test.testDate}</div>
									
									<div class="col-12 mt-3">
										<!-- 문제 추가 -->
										<form action="${pageContext.request.contextPath}/teacher/test/addQuestion" method="post">
											
											<input type="hidden" name="testNo" value="${testNo}">
											<table class="table">
												<tr>
													<th>번호</th>
													<td><input class="form-control" type="number" name="questionIdx" value="${questionCount+1}" readonly="readonly"></td>
												</tr>
												<tr>
													<th>문제 내용</th>
													<td><textarea class="form-control" cols="50" rows="5" name="questionTitle"></textarea></td>
												</tr>
											</table>
											
											<table class="table">
												<c:forEach var="e" begin="0" end="4">
													<tr>
														<td style="width: 20%">
															<input class="form-control" type="number" name="exampleList[${e}].exampleIdx" value="${e+1}" readonly="readonly">
														</td>
														<td><input class="form-control" type="text" name="exampleList[${e}].exampleTitle"></td>
														<td>
															<input class="exampleO form-check-input" type="radio" name="exampleList[${e}].exampleOx" value="정답">정답
															<input class="exampleX form-check-input" type="radio" name="exampleList[${e}].exampleOx" value="오답">오답
														</td>
													</tr>
												</c:forEach>
											</table>
											<button type="submit">추가</button>
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

	
	<script>
		$('.exampleO').click(function(){
			let checkIndex = $('.exampleO').index(this);
			console.log(checkIndex+'클릭인덱스');
			$('.exampleX').each(function(index, item){
				
				if( index != checkIndex){
					console.log(index+'오답 인덱스');
					$(item).attr('checked', true);
				}
			});	
		});
	</script>

</body>
</html>