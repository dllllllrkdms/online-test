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
	
<title>회원가입 | 강사 | LMS</title>
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
						</div>

						<div class="card">
							<div class="card-body">
								<div class="m-sm-4">
									<div class="text-center mb-3 userSe">
										<a class="btn btn-lg" href="${pageContext.request.contextPath}/addStudent" rel="student">[학생]</a>
										<a class="btn btn-lg active" href="${pageContext.request.contextPath}/addTeacher" rel="teacher">[강사]</a>
									</div>
									<form action="${pageContext.request.contextPath}/addTeacher" method="post" id="form">
										<div class="mb-3">
											<label class="form-label" for="teacherName">이름</label>
											<input class="form-control form-control-lg" type="text" id="teacherName" name="teacherName" />
											<div class="msg"></div>
										</div>
										<div class="mb-3">
											<label class="form-label" for="teacherId">아이디</label>
											<input class="form-control form-control-lg" type="text" id="teacherId" name="teacherId" />
											<div class="msg"></div>
										</div>
										<div class="mb-3">
											<label class="form-label" for="teacherPw">비밀번호</label>
											<input class="form-control form-control-lg" type="password" id="teacherPw" name="teacherPw" />
											<div class="msg"></div>
										</div>
										<div class="mb-3">
											<label class="form-label" for="checkPw">비밀번호 확인</label>
											<input class="form-control form-control-lg" type="password" id="checkPw" />
											<div class="msg"></div>
										</div>
										<div class="text-center mt-3">
											<button type="button" id="submitBtn" class="btn btn-lg btn-primary">회원가입</button>
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
	
		let idck = 0;
		$('#teacherId').blur(function(){
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
			console.log(idck);
			if($('#teacherName').val()==0){
				$($('.msg')[0]).text(' 이름을 입력해주세요.');
				$('#teacherName').focus();
				return;
			}
			if($('#teacherId').val()==0){
				$($('.msg')[1]).text(' 아이디를 입력해주세요.');
				$('#teacherId').focus();
				return;
			}
			if($('#teacherPw').val()==0){
				$($('.msg')[2]).text(' 비밀번호를 입력해주세요.');
				$('#teacherPw').focus();
				return;
			}
			if($('#teacherPw').val()!=$('#checkPw').val()){
				$($('.msg')[3]).text(' 비밀번호와 비밀번호 확인이 맞지 않습니다.');
				$('#teacherPw').focus();
				return;
			}
			if(idck != 1) {
				$('#teacherId').focus();
				return;
			}
			$('#form').submit();
		});
	</script>
	
</body>
</html>