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
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

	<link href="${pageContext.request.contextPath}/resources/assets/static/css/app.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<link href="${pageContext.request.contextPath}/resources/assets/custom/mainStyle.css" rel="stylesheet">
	
	
	

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.css">

<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/locales-all.js"></script>
<style>

    .fc-daygrid-day.fc-day.fc-day-sat a, .fc-col-header-cell.fc-day.fc-day-sat a{ color:#0000FF; }     /* 토요일 */
    .fc-daygrid-day.fc-day.fc-day-sun a, .fc-col-header-cell.fc-day.fc-day-sun a{ color:#FF0000; }    /* 일요일 */

    .fc-daygrid-day-number, .fc-col-header-cell-cushion{color: black;}
    .fc-daygrid-day-number:hover, .fc-col-header-cell-cushion:hover{color: black; text-decoration: none;}

    
</style>
<title>Home | LMS</title>
</head>
<body>
	<input type="hidden" value="${msg}" id="msg">
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
	
	<!-- 이벤트 클릭 시 상세보기 모달 영역 -->
	<div class="modal" id="calendarModal" role="dialog">
	    <div class="modal-dialog">
	    
	      <div class="modal-content">
	        <div class="modal-header">
		        <h4 style="font-weight: 700" class="modal-title"></h4>
	          
	        </div>
	        <div class="modal-body">
	      	</div>
	      </div>
	    </div>
	</div>
	
	
	<script src="${pageContext.request.contextPath}/resources/assets/static/js/app.js"></script>
	
	<!-- 메시지 스크립트 -->
	<script>
		if($('#msg').val().length != 0){
			alert($('#msg').val());
		}
	</script>
	
	<script>
		$(document).on("click","#paperBtn",function(){
			let testNo = $(this).val();
			$.ajax({
		   		 type:"get"
		   		 , url:"/online-test/student/paperCheck"
		   		 , data: {testNo: testNo}
		   		 , success: function(model){
		   			 console.log(model);
		   			 if(model != null && model != ''){ // null : 응시 가능, not null : 응시 불가능 
		   				alert('이미 응시한 시험입니다.');
		   			 } else if(model == null || model == ''){
		   				 location.href='/online-test/student/test/paper?testNo='+testNo;
		   			 }
		   		 }
		   		 , error: function(){
		   			 alert('시스템 에러. 다시 시도해주세요.');
		   		 }
			});	
		});
	</script>

	<!-- full calendar 스크립트 -->
	<script>
	$(document).ready(function(){
		
		$(function(){
			let request = $.ajax({
		   		 type:"get"
		   		 , url:"/online-test/testCalendar"
		   		 , dataType:"json" 
			});	
			
			request.done(function (data) {
				let list = [];
                $(data).each(function(index, item){
                	let e = {title: item.testTitle, start: item.testDate, id: item.testNo}
                	list.push(e);
                });
                console.log(list);

                var calendarEl = document.getElementById('calendar');

                var calendar = new FullCalendar.Calendar(calendarEl, {
                	height: 700 // 크기 설정
                    , initialView: 'dayGridMonth' // 초기 로드될때 보일 뷰 설정
                    , headerToolbar: { // 헤더 툴바
                    	left: ''
                        , center: 'title'
                    }
	                , eventClick: function(info) {
	                	// 오늘날짜 
	                	let today = new Date();   
	                	let todayDate = moment(today).format('YYYY-MM-DD');
	                	
	                	let itemDate = moment(info.event.start).format('YYYY-MM-DD'); // date format 설정
	                	
	                	let html = itemDate+'<br><a href="${pageContext.request.contextPath}/${path}/test/testOne?testNo='+info.event.id+'" class="btn btn-sm btn-primary mt-3">상세보기</a>';
	                	if(todayDate == itemDate){
	                		html += '<br><button type="button" value="'+info.event.id+'" class="btn btn-sm btn-primary mt-3" id="paperBtn">응시하기</button>';
	                	}
	                	$(".modal-title").html(info.event.title);
	                	$('.modal-body').html(html);
	                	$('#calendarModal').modal('show'); // 모달창 열기
	                  }
	                , dayMaxEvents: true // 이벤트가 오버되면 높이 제한
                	, locale: 'ko' // 한국어 설정
                    , events: list // 출력할 이벤트 
                });

                calendar.render();
            });

            request.fail(function( jqXHR, textStatus ) {
                alert( "Request failed: " + textStatus );
            });
        });

    });
  	</script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

