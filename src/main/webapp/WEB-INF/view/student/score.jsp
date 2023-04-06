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
	
<title>성적 | LMS</title>
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
					
						
						<div class="col-12">
							<div class="card">
								<div class="card-header">
									<h5 class="card-title mb-0">성적</h5>
								</div>
								
								<div class="card-body">
									<canvas id="myChart" style="width:100%;max-width:700px"></canvas>
								</div>
							</div>
						</div>
						
				</div>
			</main>
		</div>
	</div>
		
	<script src="${pageContext.request.contextPath}/resources/assets/static/js/app.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
	
	<!-- ajax로 모델 가져오기 -->
	<script>
		let xModel = [];
		let yModel = [];
		$.ajax({
			async : false // 동기처리
			, url : '/online-test/student/score'
			, type : 'post'
			, success : function(model){
				for(let attr in model){ 
					xModel.push(model[attr].testTitle);
					yModel.push(model[attr].score);
				}
			}
		});
		console.log(xModel);
		console.log(yModel);
	</script>
	
	<!-- 차트 그리기 스크립트 -->
	<script>

		new Chart("myChart", {
		  type: "line",
		  data: {
		    labels: xModel,
		    datasets: [{
		      borderColor: "rgba(0,0,0,0.1)",
		      data: yModel
		    }]
		  },
		  options:{
			  legend: {display: false},
			    scales: {
			      yAxes: [{ticks: {min: 0, max: 100}}],
			    }
		  }
		});


	</script>	
</body>					
</html>