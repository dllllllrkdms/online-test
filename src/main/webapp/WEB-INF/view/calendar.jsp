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
	
	
	

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.css">

<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/locales-all.js"></script>
	
	

<title>Insert title here</title>
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
								
									<div id='calendar-container'>
								    	<div id='calendar'></div>
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
	  $.ajax({
		  	async : false
	   		 , type:"get"
	   		 , url:"/online-test/testCalendar"
	   		 , dataType:"json"
	   		 , success(model){
	   			 	let list = model;
	   			 	
	   				var calendarEl = $('#calendar');
	   				
	   				let eventsList = list.map(function(item){
	   					return {
	   						title : item.testTitle
	   						, start : item.testDate
	   					}			
	   				});
	   				console.log(eventsList);
	   				var calendar = new FullCalendar.Calendar(calendarEl, {
		  				// 해더에 표시할 툴바
		  			    headerToolbar: {
		  			    	left: 'prev,next today',
		  			        center: 'title',
		  			        right: 'dayGridMonth,listWeek'
		  			      }
	   					, initialView: 'dayGridMonth' // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
		  			    , navLinks: true // 날짜를 선택하면 Day 캘린더나 Week 캘린더로 링크
		  			    , nowIndicator: true // 현재 시간 마크

		  			    , dayMaxEvents: true // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
		  			    , locale: 'ko' // 한국어 설정
		  			    , events: eventsList

			   		 });
	   				calendar.render();
	   		 }	
	 		
	   	});
	</script>
</body>
</html>

