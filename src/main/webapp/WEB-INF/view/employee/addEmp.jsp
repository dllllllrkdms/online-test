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

	<link rel="canonical" href="https://demo-basic.adminkit.io/pages-sign-up.html" />

	<link href="${pageContext.request.contextPath}/resources/assets/static/css/app.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/custom/loginStyle.css">
	
<title>사원 등록 | LMS</title>
</head>
<body>
	
	<input type="hidden" value="${msg}" id="errorMsg">
	
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
									<h1 class="h2">사원 등록</h1>
								</div>
		
								<div class="card">
									<div class="card-body">
										<div class="m-sm-4">
											<form action="${pageContext.request.contextPath}/addEmp" method="post" id="form">
												<div class="mb-3">
													<label class="form-label" for="empName">이름</label>
													<input class="form-control form-control-lg" type="text" id="empName" name="empName" />
													<div class="msg"></div>
												</div>
												<div class="mb-3">
													<label class="form-label" for="empId">아이디</label>
													<input class="form-control form-control-lg" type="text" id="empId" name="empId" />
													<div class="msg"></div>
												</div>
												<div class="mb-3">
													<label class="form-label" for="empPw">비밀번호</label>
													<input class="form-control form-control-lg" type="password" id="empPw" name="empPw" />
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
	
		let idck = 0;
		$('#empId').blur(function(){
			idck = 0;
			let id = $(this).val();
			console.log(id);
			
			$.ajax({
				url: '/online-test/idck'
				, type: 'post'
				, data: {'id': id}
				, success: function(model){
					let msg = ' ' + id +'은/는 사용중인 아이디입니다.';
					if(model=='YES') { // 사용가능한 아이디
						msg = '';
						idck += 1;
					}
					$($('.msg')[1]).text(msg);
				}
			});
		});
		
		$('#submitBtn').click(function(){
			$('.msg').text('');
			console.log(idck);
			if($('#empName').val()==0){
				$($('.msg')[0]).text(' 이름을 입력해주세요.');
				$('#empName').focus();
				return;
			}
			if($('#empId').val()==0){
				$($('.msg')[1]).text(' 아이디를 입력해주세요.');
				$('#empId').focus();
				return;
			}
			if($('#empPw').val()==0){
				$($('.msg')[2]).text(' 비밀번호를 입력해주세요.');
				$('#empPw').focus();
				return;
			}
			if($('#empPw').val()!=$('#checkPw').val()){
				$($('.msg')[3]).text(' 비밀번호와 비밀번호 확인이 맞지 않습니다.');
				$('#empPw').focus();
				return;
			}
			if(idck != 1) {
				$('#empId').focus();
				return;
			}
			$('#form').submit();
		});
	</script>
	
</body>
</html>