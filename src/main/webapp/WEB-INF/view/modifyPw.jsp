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
	<link href="${pageContext.request.contextPath}/resources/assets/custom/loginStyle.css" rel="stylesheet">
	
<title>비밀번호 변경 | LMS</title>
</head>
<body>
	<input type="hidden" id="errorMsg" value="${msg}">
	
	
	<div class="wrapper">
	
		<c:import url="/WEB-INF/view/inc/sideBar.jsp"></c:import> <!-- JSTL로 include하기 -->
		
		<div class="main">
			<c:import url="/WEB-INF/view/inc/navBar.jsp"></c:import>
			
			<main class="d-flex w-100">
				<div class="container d-flex flex-column">
					<div class="row vh-100">
						<div class="col-sm-10 col-md-8 col-lg-6 mx-auto d-table h-100">
							<div class="d-table-cell align-middle">
		
								<div class="text-center mt-4">
									<h1 class="h2">비밀번호 변경</h1>
								</div>
								
								<div class="card">
									<div class="card-body">
										<div class="m-sm-4">
											<form action="${pageContext.request.contextPath}/${path}/modifyPw" method="post" id="form">
												<div class="mb-3">
													<label class="form-label" for="oldPw">현재 비밀번호</label>
													<input class="form-control form-control-lg" type="password" id="oldPw" name="oldPw" />
													<div class="msg"></div>
												</div>
												<div class="mb-3">
													<label class="form-label" for="newPw">새 비밀번호</label>
													<input class="form-control form-control-lg" type="password" id="newPw" name="newPw" />
													<div class="msg"></div>
												</div>
												<div class="mb-3">
													<label class="form-label" for="checkPw">비밀번호 확인</label>
													<input class="form-control form-control-lg" type="password" id="checkPw" />
													<div class="msg"></div>
												</div>
												<div class="text-center mt-3">
													<button type="button" id="submitBtn" class="btn btn-lg btn-primary">등록</button>
												</div>
											</form>
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
		if($('#errorMsg').val() != null && $('#errorMsg').val() != '') {
			alert($('#errorMsg').val());
		}
		
		$('#submitBtn').click(function(){
			$('.msg').text('');
			if($('#oldPw').val()==0){
				$($('.msg')[0]).text(' 현재 비밀번호를 입력해주세요.');
				$('#oldPw').focus();
				return;
			}
			if($('#newPw').val()==0){
				$($('.msg')[1]).text(' 새 비밀번호를 입력해주세요.');
				$('#newPw').focus();
				return;
			}
			if($('#newPw').val()!=$('#checkPw').val()){
				$($('.msg')[2]).text(' 비밀번호와 비밀번호 확인이 맞지 않습니다.');
				$('#newPw').focus();
				return;
			}
			$('#form').submit();
		});
	</script>
	
		

</body>
</html>