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
			<div class="col-md-3" v-for="vo in list">
			    <div class="thumbnail">
			      <a :href="'http://localhost:9090/food/detail?fno='+vo.fno">
			        <img :src="vo.poster" :title="vo.address" style="width:250px;height:170px;">
			        <div class="caption">
			          <p>{{vo.name}}</p>
			        </div>
			      </a>
			    </div>
			  </div>
		</div>
		<div class="row text-center" style="margin-top: 20px;">
			<ul class="pagination">
				<li v-if="startPage > 1">
					<a class="nav-link" @click="pageChange(startPage - 1)">
						&laquo;
					</a>
				</li>
				
				<li v-for="i in range(startPage, endPage)"
					:class="i === curpage?'active':''">
					<a class="nav-link" @click="pageChange(i)">
						{{i}}
					</a>
				</li>
					
				<li v-if="endPage < totalpage">
					<a class="nav-link" @click="pageChange(endPage + 1)">
						&raquo;
					</a>
				</li>
			</ul>
		</div>
	</div>
<script>
	let foodApp = Vue.createApp({
		// Model => 데이터 관리 : 데이터가 변경이 되면 HTML에 바로 적용
		data() {
			return {
				list: [],
				curpage: 1,
				totalpage: 0,
				startPage: 0,
				endPage:0
			}
		},
		
		// ViewModel => 데이터를 변경하는 위치 (사용자 요청에 따라)
		// created() : Vue 객체 생성 완료
		// mounted() : HTML이 브라우저에 출력이 된 경우 : onload
		// $(function() {}) => 다른 Front와 연동
		// updated() : 데이터가 수정이 된 경우 : 버튼 / 값 입력 => v-model
		// unmounted() : 화면 변경 => vue 객체 소멸
		mounted() {
			this.dataRecv()
		},
		
		// 사용자 정의 함수
		methods: {
			async dataRecv() {
				await axios.get('http://localhost:9090/food/list_vue/', {
					params: {
						page: this.curpage
					}
				}).then(response => {
					console.log(response.data)
					this.list = response.data.list
					this.curpage = response.data.curpage
					this.totalpage = response.data.totalpage
					this.startPage = response.data.startPage
					this.endPage = response.data.endPage
				})
			},
			
			pageChange(page) {
				this.curpage = page
				this.dataRecv()
			},
			
			range(start, end) {
				let arr = []
				let len = end - start
				
				for(let i=0;i <= len; i++) {
					arr[i] = start
					start++
				}
				
				return arr
			},
		},
		
		// 목록 + 상세보기 / 예약 => component
		components: {
			
		},
		
		// 완성된 데이터 가지고 오기 : 1,000, 수량 계산
		computed: {
			
		}
	})
	foodApp.mount(".container")
</script>
</body>
</html>