<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container {
	margin-top: 50px;
}

.row {
	margin: 0px auto;
	width: 400px;
}

h3 {
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3>메인페이지</h3>
			<table class="table">
				<tbody>
					<sec:authorize access="!isAuthenticated()">
						<tr>
							<td class="text-center"><a href="/login"
								class="btn btn-sm btn-info">로그인</a></td>
						</tr>
					<tr>
						<td class="text-center"><a href="/join"
							class="btn btn-sm btn-info">회원가입</a></td>
					</tr>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<tr>
							<td class="text-center"><strong> <sec:authentication
										property="name" />
							</strong> 님 환영합니다</td>
						</tr>
						<tr>
							<td class="text-center"><a href="/chat"
								class="btn btn-sm btn-info">채팅</a></td>
						</tr>
						<tr>
							<td class="text-center"><a href="/logout"
								class="btn btn-sm btn-danger">로그아웃</a></td>
						</tr>
						<sec:authorize access="hasRole('ADMIN')">
							<tr>
								<td class="text-center"><a href="#"
									class="btn btn-sm btn-danger">채팅 관리</a></td>
							</tr>
						</sec:authorize>
					</sec:authorize>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>