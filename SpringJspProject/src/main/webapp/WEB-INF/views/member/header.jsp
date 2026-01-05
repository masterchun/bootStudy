<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	const SESSION_ID = '${sessionScope.id}'
</script>
</head>
<body>
	<div class="container" style="margin-top: 10px" id="login_app">
		<div class="row text-right">
			ID : <input type="text" ref="idRef" size="15" class="input-sm">
			&nbsp; PW : <input type="password" ref="pwdRef" size="15" class="input-sm">
			&nbsp;<button type="button" class="btn-sm btn-primary">로그인</button>
		</div>
		<div class="row text-right">
			&nbsp;<button type="button" class="btn-sm btn-danger">로그아웃</button>
		</div>
	</div>
</body>
</html>