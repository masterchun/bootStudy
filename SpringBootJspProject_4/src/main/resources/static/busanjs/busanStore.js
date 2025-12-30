const {defineStore} = Pinia

const usebusanStore = defineStore('busan', {
	state: () => ({
		list: [],
		curpage: 1,
		totalpage: 0,
		startPage: 0,
		endPage: 0,
		type: 1,
		detail: {}
	}),
	
	actions: {
		async busanListData(type) {
			this.type = type
			const res = await axios.get('http://localhost:9090/busan/list_vue/', {
				params: {
					page: this.curpage,
					type: this.type,
				}
			})
			console.log(res.data)
			this.list = res.data.list
			this.curpage = res.data.curpage
			this.totalpage = res.data.totalpage
			this.startPage = res.data.startPage
			this.endPage = res.data.endPage
			this.type = res.data.type
		},
		
		pageChange(page) {
			this.curpage = page
			this.busanListData(this.type)
		},
		
		range(start, end) {
			let arr = []
			let len = end - start
			
			for(let i=0;i<=len;i++) {
				arr.push(start)
				start++
			}
			
			return arr
		}
	}
})
