const {defineStore} = Pinia

const useBoardInsertStore = defineStore('board_insert', {
	state: () => ({
		name: '',
		subject: '',
		content: '',
		pwd: '',
		msg: ''
	}),
	
	actions: {
		async boardInsert({nameRef, subRef, contRef, pwdRef}) {
			if(this.name === '') {
				nameRef.focus()
				return
			}
			if(this.subject === '') {
				subRef.focus()
				return
			}
			if(this.content === '') {
				contRef.focus()
				return
			}
			if(this.pwd === '') {
				pwdRef.focus()
				return
			}
			
			const res = await axios.post('http://localhost:8080/board/insert_vue/', {
				name: this.name,
				subject: this.subject,
				content: this.content,
				pwd: this.pwd
			})
			
			if(res.data.msg == 'yes') {
				location.href = "/board/list"
			} else {
				alert("글쓰기 입력에 실패하셨습니다")
			}
		}
	}
})