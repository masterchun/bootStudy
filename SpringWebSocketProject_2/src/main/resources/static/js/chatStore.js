const { defineStore } = Pinia

const useChatStore = defineStore('chat', {
	state: () => ({
		stompClient: null,
		userId: '',
		message: [],
		msg: '',
		receiver: ''
	}),
	
	actions: {
		connect() {
			const socket = new SockJS('/ws-chat')
			this.stompClient = Stomp.over(socket)
			
			this.stompClient.connect({}, () => {
				this.stompClient.subscribe('/queue/private/' + this.userId, (msg) => {
					this.message.push(JSON.parse(msg.body))
				})
			})
		},
		
		send() {
			this.stompClient.send('/app/chat.private', {}, JSON.stringify({
				sender: this.userId,
				receiver: this.receiver,
				message: this.msg
			}))
		}
	}
})