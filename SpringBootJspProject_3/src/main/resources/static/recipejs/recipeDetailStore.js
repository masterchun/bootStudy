const {defineStore} = Pinia

const useRecipeDetailStore = defineStore('recipeDetail', {
	state: () => ({
		detail: {}
	}),
	
	actions: {
		async recipeDetailData(no) {
			const res = await axios.get('http://localhost:9090/recipe/detail_vue/', {
				params: {
					no: no
				}
			})
			console.log(res.data)
			this.detail = res.data
		}
	}
})