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
<script src="/recipejs/recipeStore.js"></script>
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
			<div class="col-md-3" v-for="vo in store.list">
			    <div class="thumbnail">
			      <a :href="'http://localhost:9090/recipe/detail?no='+vo.no">
			        <img :src="vo.poster" :title="vo.name" style="width:250px;height:170px;">
			        <div class="caption">
			          <p>{{vo.chef}}</p>
			        </div>
			      </a>
			    </div>
			  </div>
		</div>
		<div class="row text-center" style="margin-top: 10px;">
			<ul class="pagination">
				<li v-if="store.startPage > 1">
					<a class="nav-link" @click="store.pageChange(store.startPage - 1)">
						&laquo;
					</a>
				</li>
				
				<li v-for="i in range(store.startPage, store.endPage)" :class="i === store.curpage?'active':''">
					<a class="nav-link" @click="store.pageChange(i)">
						{{i}}
					</a>
				</li>
				
				<li v-if="store.endPage < store.totalpage">
					<a class="nav-link" @click="store.pageChange(store.endPage + 1)">
						&raquo;
					</a>
				</li>
			</ul>
		</div>
	</div>
<script>
	const app = Vue.createApp({
		setup() {
			const store = useRecipeStore()
			
			Vue.onMounted(() => {
				store.recipeListData()
			})
			
			const range = (start, end) => {
				let arr = []
				let len = end - start
				
				for (let i=0;i<=len;i++) {
					arr[i] = start
					start++
				}
				
				return arr
			}
			
			return {
				store,
				range
			}
		}
	})
	app.use(Pinia.createPinia())
	app.mount(".container")
</script>
</body>
</html>