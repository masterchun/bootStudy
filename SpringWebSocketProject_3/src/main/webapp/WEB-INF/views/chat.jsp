<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://unpkg.com/vue-demi"></script>
<script src="https://unpkg.com/pinia@2/dist/pinia.iife.js"></script>
<script>
const LOGIN_USER = '${sessionScope.userid}'
</script>
<style type="text/css">
.container {
	margin-top: 50px;
}

.row {
	margin: 0px auto;
	width: 900px;
}

h3 {
	text-align: center;
}

.chat-body {
	height: 450px;
	overflow-y: auto;
}

.my-msg {
	text-align: right;
	color: #337ab7;
}
</style>
</head>
<body>
	<div class="container" id="app">
		<div class="row">
			<div class="col-sm-3">
				<div class="panel panel-primary">
					<div class="panel-heading text-center">
						<b>접속자 목록</b>
					</div>
					<ul class="list-group">
						<li class="list-group-item" v-for="(u, index) in store.users"
						@click="store.changeRoom(u)" style="cusor: pointer" :key="index">{{ u }}</li>
						<li class="list-group-item" @click="store.changeRoom('public')" style="cursor: pointer">전체 채팅</li>
					</ul>
				</div>
			</div>
			<div class="col-sm-9">
				<div class="panel panel-primary">
					<div class="panel-heading text-center">
						<b>{{ store.currentRoom === 'public' ? '전체 채팅' : '일대일 채팅 - ' + store.currentRoom }}</b>
					</div>
					<div class="pane-body chat-body">
						<div v-for="(m, index) in store.messages" :key="index">
							<div :class="{'my-msg' : m.sender == store.loginUser }">
								<b>{{ m.sender }}</b> : {{ m.message }}
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<div class="input-grooup">
							<input type="text" class="form-control" v-model="store.msg" @keyup.enter="store.send()" placeholder="메세지 입력">
							<span class="input-groub-btn">
								<button class="btn btn-primary" @click="store.send()">전송</button>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/chatStore.js"></script>
	<script>
	  const { createApp, onMounted, ref } = Vue
	  const { createPinia } = Pinia
	  
	  const app = createApp({
		  setup() {
			  const store = useChatStore()
			  const chatBody = ref(null)
			  
			  onMounted(() => {
				  store.loginUser = LOGIN_USER
				  store.chatBodyEl = chatBody.value
				  store.connect()
			  })
			  
			  return {
				  store,
				  chatBody
			  }
		  }
	  })
	  
	  app.use(createPinia())
	  app.mount('#app')
	</script>
</body>
</html>