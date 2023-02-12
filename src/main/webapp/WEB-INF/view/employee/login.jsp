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
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/custom/loginStyle.css">
	
<title>로그인 | 사원 | LMS</title>
</head>
<body>

	<input type="hidden" value="${msg}" id="errorMsg">
	
	<main class="d-flex w-100">
		<div class="container d-flex flex-column">
			<div class="row vh-100">
				<div class="col-sm-10 col-md-8 col-lg-6 mx-auto d-table h-100">
					<div class="d-table-cell align-middle">

						<div class="text-center mt-4">
							<h1 class="h2">GOODEE 온라인 시험</h1>
							<p class="lead">
							</p>
						</div>
						
						
						
						<div class="card"> 
							<div class="card-body">
								<div class="m-sm-4">
									<div class="text-center mb-3 userSe">
										<a class="btn btn-lg active" href="${pageContext.request.contextPath}/loginEmp" rel="employee"><strong>[사원]</strong></a>
										<a class="btn btn-lg" href="${pageContext.request.contextPath}/loginStudent" rel="student">[학생]</a>
										<a class="btn btn-lg" href="${pageContext.request.contextPath}/loginTeacher" rel="teacher">[강사]</a>
									</div>
									<form action="${pageContext.request.contextPath}/loginEmp" method="post" id="form">
										<div class="mb-3">
											<input class="form-control form-control-lg" type="text" id="empId" name="empId" placeholder="아이디">
										</div>
										<div class="mb-3">
											<input class="form-control form-control-lg" type="password" id="empPw" name="empPw" placeholder="비밀번호">
										</div>
										<div class="">
											<div class="msg">${msg}</div>
										</div>
										<div class="text-center mt-3">
											<button type="button" class="btn btn-lg btn-primary">로그인</button>
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

	<script src="${pageContext.request.contextPath}/resources/assets/static/js/app.js"></script>
	
	
	<script>
		if($('#errorMsg').val() != null && $('#errorMsg').val() != '') {
			alert($('#errorMsg').val());
		}
	
		$('#submitBtn').click(function(){
			if($('#empId').val()==0){
				$('.msg').text(' 아이디를 입력해주세요.');
				$('#empId').focus();
				return;
			}
			if($('#empPw').val()==0){
				$('.msg').text(' 비밀번호를 입력해주세요.');
				$('#empPw').focus();
				return;
			}
			$('#form').submit();
		});
	</script>

	
</body>
</html>