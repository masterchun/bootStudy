<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Procedure / Trigger / Function (JOIN) --%>
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
	width: 960px;
	margin: 0px auto;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tbody>
					<tr>
						<td class="text-center" width="30%" rowspan="9">
							<img src="${vo.poster }" style="width: 100%">
						</td>
						<td colspan="2":>
							<h3>${vo.name }&nbsp;<span style="color: red">${vo.score }</span></h3>
						</td>
					</tr>
					<tr>
						<td width="10%">주소</td>
						<td width="60%">${vo.address }</td>
					</tr>
					<tr>
						<td width="10%">전화</td>
						<td width="60%">${vo.phone }</td>
					</tr>
					<tr>
						<td width="10%">음식 종류</td>
						<td width="60%">${vo.type }</td>
					</tr>
					<tr>
						<td width="10%">가격대</td>
						<td width="60%">${vo.price }</td>
					</tr>
					<tr>
						<td width="10%">영업 시간</td>
						<td width="60%">${vo.time }</td>
					</tr>
					<tr>
						<td width="10%">주차</td>
						<td width="60%">${vo.parking }</td>
					</tr>
					<tr>
						<td width="10%">테마</td>
						<td width="60%">${vo.theme }</td>
					</tr>
				</tbody>
			</table>
			<table class="table">
				<tbody>
					<tr>
						<td>${vo.content }</td>
					</tr>
					<tr>
						<td class="text-right">
							<button type="button" class="btn-sm btn-primary" onclick="javascript:history.back()">목록</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>