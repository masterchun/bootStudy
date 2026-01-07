<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%-- Procedure --%>
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

p {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<c:forEach var="vo" items="${list }">
				<div class="col-md-3">
					<div class="thumbnail">
						<a href="/detail?fno=${vo.fno }"> <img src="${vo.poster }" alt="Lights"
							style="width: 240px; height: 150px;">
							<div class="caption">
								<p>${vo.name }</p>
							</div>
						</a>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="row text-center" style="margin-top: 20px">
			<a href="/list?page=${curpage > 1 ? curpage - 1 : curpage }" class="btn btn-sm btn-primary">이전</a>
			${curpage } page / ${totalpage } pages
			<a href="/list?page=${curpage < totalpage ? curpage + 1 : curpage }" class="btn btn-sm btn-primary">다음</a>
		</div>
	</div>
</body>
</html>