<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지 - 댓글관리</title>
<link href="../css/admin.css?ver=0.19" rel="stylesheet"/>
<link href="../css/member.css" rel="stylesheet"/>
<script type="text/javascript" src="../js/menu.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
  var data = google.visualization.arrayToDataTable([
    ['ip', '접속수'],<c:forEach items="${list1 }" var="row">
    ['${row.iip}', ${row.count}],</c:forEach>
  ]);

  var options = {
    title: '접속 ip 비율'
  };

  var chart = new google.visualization.PieChart(document.getElementById('piechart'));

  chart.draw(data, options);
}
</script>
</head>
<body>
	<div class="wrap">
		<!-- menu -->
		<%@ include file="menu.jsp" %>
		<div class="main">
			<article>
				<h2>IP관리</h2>
<!-- 				0. 중복없이 ip리스트 뽑기는?
				1. 최다 접속 ip 5개 출력 - ip, 접속수
				2. 그래프 그리기 - 구글차트 - ip당 접속 건수 - 10개? -->
				<hr>
				<h2>가장 많이 접속한 IP</h2>
				<div style="height: 300px; width: 100%;">
				<table style="width: 500px; float: left">
					<c:forEach items="${list1 }" var="row">
					<tr>
						<td>${row.iip }</td>
						<td>${row.count }</td>
					</tr>
					</c:forEach>
				</table>
				<!-- 그래프로 그리기 -->
				<div id="piechart" style="width: 500px; height: 300px; float: right;"></div>
				</div>
				<hr>
				<h2>가장 최근에 접속한 IP</h2>
				<table>
					<c:forEach items="${list2 }" var="row">
					<tr>
						<td>${row.iip }</td>
						<td>${row.idate }</td>
					</tr>
					</c:forEach>
				</table>
				<hr>
				<div class="nav-lists">
					<ul class="nav-lists-group">
						<li class="nav-lists-item" onclick="url('./ip?del=1')"><i class="xi-close-circle-o"></i> 보임</li>					
						<li class="nav-lists-item" onclick="url('./ip?del=0')"><i class="xi-close-circle-o"></i> 숨김</li>					
					</ul>
					<div class="search">
						<input type="text" id="search">
						<button id="searchBtn">검색</button>
					</div>
					<button onclick="location.href='./ip'">초기화</button>
				</div>
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>IP</th>
							<th>날짜</th>
							<th>URL</th>
							<th>기타</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="row">
							<tr class="row">
								<td class="d1">${row.ino }</td>
								<td class="d1"><a href="./ip?ip=${row.iip }">${row.iip }</a></td>
								<td class="d2">${row.idate }</td>
								<td class="d1">${row.iurl }</td>
								<td class="title">${row.idata }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</article>
		</div>
	</div>
</body>
</html>