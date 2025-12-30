<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://unpkg.com/vue-demi"></script>
<script src="https://unpkg.com/pinia@2/dist/pinia.iife.js"></script>
<script src="/recipejs/recipeDetailStore.js"></script>
<style type="text/css">
.container {
	margin-top: 50px;
}
.row {
	margin: 0px auto;
	width: 960px;
}
p {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
.nav-link {
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
		<table class="table">
					<tr>
						<td class="text-center" colspan="3">
							<img :src="detail.poster" style="width:500px;height:400px;">
						</td>
					</tr>
					<tr>
						<td colspan="3" class="text-center"><h3>{{detail.title}}</h3></td>
					</tr>
					<tr>
						<td colspan="3">{{detail.content}}</td>
					</tr>
					<tr>
						<td class="text-center"><img src="/images/a1.png"></td>
						<td class="text-center"><img src="/images/a2.png"></td>
						<td class="text-center"><img src="/images/a3.png"></td>
					</tr>
					<tr>
						<td class="text-center">{{detail.info1}}</td>
						<td class="text-center">{{detail.info2}}</td>
						<td class="text-center">{{detail.info3}}</td>
					</tr>
				</table>
				<table class="table">
					<tr>
						<td colspan="2"><b>[조리 순서]</b></td>
					</tr>
					<!-- <tr v-for="(detail, index) in store.">
						<td class="text-left" width=80%>[[${data}]]</td>
						<td class="text-right" width=20%>
							<img th:src="${nList[status.index]}" class="img-rounded" style="width:100px;height:100px;">
						</td>-->
						<%--  
							status
							status.index => ArrayList의 index
							status.count => ArrayList의 총개수
							status.last => ArrayList의 저장된 마지막 값
							status.first => 첫번째 값
							status.even => 짝수 값
							status.odd => 홀수 값
							
							=> StringTokenizer
							=> ${#strings.split(문자열, '구분자')}
						 --%>
					<!-- </tr> -->
				</table>
				<table class="table">
					<tr>
						<td width=20% class="text-left" rowspan="2">
							<img :src="detail.chef_poster" class="img-circle" style="width:100px;height:100px;">
						</td>
						<td width=80%>{{detail.chef}}</td>
					</tr>
					<tr>
						<td width=80%>{{detail.chef_profile}}</td>
					</tr>
				</table>
		</div>
	</div>
<script>
	const detailApp = Vue.createApp({
		setup() {
			const store = useRecipeDetailStore()
			const params = new URLSearchParams(location.search)
			const no = params.get('no')
			console.log("no=" + no)
			
			Vue.onMounted(() => {
				store.recipeDetailData(no)
			})
			
			return store
		}
	})
	detailApp.use(Pinia.createPinia())
	detailApp.mount(".container")
</script>
</body>
</html>