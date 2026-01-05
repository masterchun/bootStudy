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
	<style type="text/css">
		.container {
			margin-top: 50px;
		}
		.row {
		margin: 0px auto;
		width: 300px;
		}
	</style>
</head>
<body>
	<div class="container" id="login_app">
		<h3 class="text-center">로그인</h3>
		<div class="row">
			<table class="table">
				<tbody>
					<tr>
						<th width="20%">ID</th>
						<td width="80%">
							<input type="text" size="20" ref="idRef" v-model="store.id" class="input-sm">
						</td>
					</tr>
					<tr>
						<th width="20%">PW</th>
						<td width="80%">
							<input type="password" size="20" ref="pwdRef" v-model="store.pwd" class="input-sm">
						</td>
					</tr>
					<tr>
						<td class="text-center" colspan="2">
							<button type="button" class="btn-sm btn-info" @click="store.login(idRef, pwdRef)">로그인</button>
							<button type="button" class="btn-sm btn-danger" onclick="javascript:history.back()">취소</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<script src="/js/axios.js"></script>
	<script src="/js/memberStore.js"></script>
	<script>
		const {createApp, onMounted, ref} = Vue
		const {createPinia} = Pinia
		
		const logApp = createApp({
			
			setup() {
				const store = useMemberStore()
				const idRef = ref(null)
				const pwdRef = ref(null)
				
				return {
					store,
					idRef,
					pwdRef
				}
			}
		})
		
		logApp.use(createPinia())
		logApp.mount("#login_app")
	</script>
</body>
</html>