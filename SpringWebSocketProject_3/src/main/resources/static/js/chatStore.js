const { defineStore } = Pinia
const { nextTick } = Vue
const useChatStore = defineStore('chat', {
	state: () => ({
		stomp: null, // Stomp 객체 
		users: [], // 접속자 명단 
		messages: [], // 메세지 목록
		currentRoom: 'public', // public / private
		loginUser: '',
		chatBodyEl: null
	}),
	actions: {
		changeRoom(room) {
			this.currentRoom = room;
			this.messages = []
		},
		connect() {
			const socket = new SockJS('/chat-ws') // 웹 소켓 연동 
			this.stomp = Stomp.over(socket)

			// 서버 연결 
			this.stomp.connect({}, () => {
				// 접속자 데이터 읽기
				this.stomp.subscribe('/topic/users', msg => {
					console.log(msg.body)
					this.users = JSON.parse(msg.body)
						.filter(u => u !== this.loginUser)
					// 본인은 제외
				})

				// 전체 채팅
				this.stomp.subscribe('/topic/chat', msg => {
					this.messages.push(JSON.parse(msg.body))
				})
				// 1:1 
				this.stomp.subscribe('/user/queue/chat', msg => {
					this.messages.push(JSON.parse(msg.body))
					this.scrollToBottom()
				})
				// 
				this.stomp.subscribe('/user/queue/force-disconnect', () => {
					alert("중복 로그인으로 로그아웃되었습니다")
					location.href = '/logout'
				})
			})

		},
		scrollToBottom() {
			nextTick()
			if (this.chatBodyEl) {
				this.chatBodyEl.scrollTop = this.chatBodyEl.scrollHeight
			}
		},
		// 전체 채팅 
		sendPublic(message) {
			this.stomp.send('/app/chat/public', {},
				JSON.stringify({ message })
			)
		},
		// 1:1 채팅 
		sendPrivate(to, message) {
			this.stomp.send('/app/chat/private', {},
				JSON.stringify({
					receiver: to,
					message: message
				})
			)
		},
		send() {
			if (this.msg === '') return
			if (this.currentRoom === 'public') {
				this.sendPublic(this.msg)
			}
			else {
				this.sendPrivate(this.currentRoom, this.msg)
			}
			this.msg = ''
		}
	}
})