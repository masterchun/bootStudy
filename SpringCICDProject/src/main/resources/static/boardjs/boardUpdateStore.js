const {defineStore} = Pinia

const useBoardUpdateStore = defineStore('board_update', {
	state: () => ({
		name: '',
		subject: '',
		content: '',
		pwd: '',
		msg: '',
		no: 0,
		vo: {}
	}),
	
	actions: {
		async boardUpdate({nameRef, subRef, contRef, pwdRef}) {

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
			
			const res = await axios.put('http://localhost:8080/board/update_ok_vue/', {
				no: this.no,
				name: this.name,
				subject: this.subject,
				content: this.content,
				pwd: this.pwd
			})
			
			if(res.data.msg == 'yes') {
				location.href = "/board/detail?no=" + this.no
			} else {
				alert("비밀번호가 틀립니다")
				this.pwd = ''
				pwdRef.focus()
			}
		},
		
		async boardUpdateData(no) {
			this.no = no
			const res = await axios.get('http://localhost:8080/board/update_vue/', {
				params: {
					no: no
				}
			})
			
			this.vo = res.data
			this.name = res.data.name
			this.subject = res.data.subject
			this.content = res.data.content
		}
	}
})