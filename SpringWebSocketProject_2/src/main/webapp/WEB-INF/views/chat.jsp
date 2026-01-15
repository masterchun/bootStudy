<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://unpkg.com/vue-demi"></script>
<script src="https://unpkg.com/pinia@2/dist/pinia.iife.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3>1:1 채팅</h3> <%-- 실시간 상담 --%>
			<table class="table">
				<tbody>
					<tr>
						<td>
							<input type="text" v-model="store.userId" ref="userId" placeholder="아이디">
							<button @click="store.connect()">접속</button>
						</td>
					</tr>
					<tr>
						<td>
							<input type="text" v-model="store.receiver" ref="receiver" placeholder="상대방">
						</td>
					</tr>
					<tr>
						<td>
							<input type="text" v-model="store.msg" ref="msg" placeholder="보낼 메세지" size="50">
						</td>
					</tr>
					<tr>
						<td>
							<button @click="store.send()">전송</button>
						</td>
					</tr>
					<tr>
						<td>
							<ul>
								<li v-for="(m, index) in store.message" :key="index">
									{{ m.sender }} : {{ m.message }}
								</li>
							</ul>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<script src="/js/chatStore.js"></script>
	<script>
		const { createApp, onMounted, ref } = Vue
		const { createPinia } = Pinia
		
		const chatApp = createApp({
			setup() {
				const store = useChatStore()
				/*
				const userId = ref('')
				const receiver = ref('')
				const msg = ref('')
				*/
				/*const connect = () => {
					store.connect(store.userId)
				}
				
				const send = () => {
					store.sendPrivateMessage(store.receiver, store.msg)
					store.msg = ''
				}*/
				
				return {
					store
				}
			}
		})
		
		chatApp.use(createPinia())
		chatApp.mount(".container")
	</script>
</body>
</html>