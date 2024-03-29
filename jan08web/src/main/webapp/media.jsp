<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>미디어쿼리연습</title>
<style type="text/css">
/* 모든기기공통 CSS */
body {
	background-color: orange;
}

/* desktop 규격 */
@media screen and (min-width: 1024px) {
	body {
		background-color: lightblue;
	}
}

/* tablet 규격 */
@media screen and (max-width: 1023px) {
	body {
		background-color: pink;
	}
}

/* mobile 규격 */
@media screen and (max-width: 540px) {
	body {
		background-color: navy;
	}
}
</style>
<script type="text/javascript">
//console.log(window.innerWidth);
//console.log(window.innerHeight);

//자바, 서블릿, 스프링, 기업프로젝트


window.onresize = function(event){
	document.getElementById('size').textContent = window.innerWidth + " x " + window.innerHeight;
}
</script>
</head>
<body>
	<h1 id="size">너비 : </h1>
</body>
</html>