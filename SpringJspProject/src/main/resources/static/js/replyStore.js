const initailState = () => ({
	reply_list: [],
	cno: 0,
	type: 2,
	msg: '',
	upReplyNo: null,
	sessionId: '',
	updateMsg: {}
})

const useReplyStore = defineStore('reply', {
	state: initailState,
	
	actions: {
		toggleUpdate(no, msg) {
			this.upReplyNo = this.upReplyNo === no ? 0 : no
			this.updateMsg[no] = msg
			
		},
		
		async replyListData(cno) {
			this.cno = cno
			
			const {data} = await api.get('/reply/list_vue/', {
				params: {
					cno: this.cno,
					type: this.type
				}
			})
			
			this.reply_list = data.list
		},
		
		async replyInsert() {
			if(!this.msg.trim()) {
				return
			}
			
			const {data} = await api.post('/reply/insert_vue/', {
				cno: this.cno,
				type: this.type,
				msg: this.msg
			})
			
			this.reply_list = data.list
			this.msg = ''
		},
		
		async replyUpdate(no) {
			const {data} = await api.put('/reply/update_vue/', {
				no: no,
				cno: this.cno,
				type: this.type,
				msg: this.updateMsg[no]
			})
			
			this.reply_list = data.list
			this.upReplyNo = null
		},
		
		async replyDelete(no) {
			const {data} = await api.delete('/reply/delete_vue/', {
				params: {
					no: no,
					cno: this.cno,
					type: this.type
				}
			})
			
			this.reply_list = data.list
		}
	}
})



























