<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
	
	<style type="text/css">
		.container {
			margin-top: 30px;
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
	<div class="container" style="margin-top: 10px">
		<div class="row text-right">
			<c:if test="${sessionScope.id == null }">
				<a href="/member/login" class="btn btn-sm btn-info">로그인</a>
			</c:if>
			<c:if test="${sessionScope.id != null }">
				<a href="member/logout" class="btn btn-sm btn-danger">로그아웃</a>
			</c:if>
		</div>
	</div>
	<div class="container" id="list_app">
		<div class="row">
		<div class="col-md-3" v-for="(vo, index) in store.list" :key="vo.no">
	    <div class="thumbnail">
	      <a :href="'/recipe/detail?no='+vo.no">
	        <img :src="vo.poster" :title="vo.title" style="width: 240px;height: 220px;">
	        <div class="caption">
	          <p>{{ vo.chef }}</p>
	        </div>
	      </a>
	    </div>
	  </div>
		</div>
		<div class="row text-center" style="margin-top: 10px">
			<ul class="pagination">
				<li v-if="store.startPage > 1"><a class="nav-link" @click="store.movePage(store.startPage - 1)">&laquo;</a></li>
				<li v-for="i in store.range" :class="i===store.curpage?'active':''"><a class="nav-link" @click="store.movePage(i)">{{ i }}</a></li>
				<li v-if="store.endPage < store.totalpage"><a class="nav-link" @click="store.movePage(store.endPage + 1)">&raquo;</a></li>
			</ul>
		</div>
	</div>
	<script src="/js/axios.js"></script>
	<script src="/js/recipeStore.js"></script>
		<script src="/js/memberStore.js"></script>
	<script>
		const {createApp, onMounted} = Vue
		const {createPinia} = Pinia
		
		const recipeApp = createApp({
			setup() {
				const store = useRecipeStore()
				
				onMounted(() => {
					store.recipeListData()
				})
				
				return {
					store
				}
			}
		})
		
		recipeApp.use(createPinia())
		recipeApp.mount("#list_app")
	
		
		const logApp = createApp({
			
		})
		
		logApp.use(createPinia())
		logApp.mount('#login_app')
	</script>
</body>
</html>



























