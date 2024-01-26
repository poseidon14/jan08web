<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link href="../css/admin.css" rel="stylesheet"/>
<link href="../css/member.css" rel="stylesheet"/>
<script type="text/javascript" src="../js/menu.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
</head>
<body>
	<div class="wrap">
		<div class="menu">
			<nav>
				<ul>
					<li onclick="url('./members')"><i class="xi-users"></i> 회원 관리</li>
					<li onclick="url('./board')"><i class="xi-document"></i> 게시글 관리</li>
					<li onclick="url('./comments')"><i class="xi-forum-o"></i> 댓글 관리</li>
					<li onclick="url('./info')"><i class="xi-lock-o"></i> poseidon님</li>
					<li></li>
					<li></li>
				</ul>
			</nav>
		</div>
		<div class="main">
			<article>
				<h2>회원관리</h2>
				<table>
					<thead>
					<tr>
						<th>번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>가입일</th>
						<th>등급</th>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="row">
						<tr class="row${row.mgrade }">
							<td class="d1">${row.mno }</td>
							<td class="title">${row.mid }</td>
							<td class="d1">${row.mname }</td>
							<td class="d2">${row.mdate }</td>
							<td class="d1">
								<select>
									<optgroup label="정지">
										<option <c:if test="${row.mgrade eq 0}">selected="selected"</c:if> >0 강퇴</option>
										<option <c:if test="${row.mgrade eq 1}">selected="selected"</c:if> >1 탈퇴</option>
										<option <c:if test="${row.mgrade eq 2}">selected="selected"</c:if> >2 정지</option>
									</optgroup>
									<optgroup label="정상">
										<option <c:if test="${row.mgrade eq 5}">selected="selected"</c:if> >5 평민</option>
									</optgroup>
									<optgroup label="관리자">								
										<option <c:if test="${row.mgrade eq 9}">selected="selected"</c:if> >9 관리자</option>
									</optgroup>
								</select>
							${row.mgrade }
							
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				페이징 필요
			</article>
		</div>
	</div>
</body>
</html>