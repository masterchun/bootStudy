const {defineStore} = Pinia

/*
		1. App 생성 : createApp({})
		2. Pinia 등록
		3. Store 생성
		4. 컴포넌트(자바에서는 클래스) store에 저장된 데이터 사용
		5. 필요시에는 state를 변경
		   *** staet가 변경되면 HTML에 자동 반영
		   
		=> Vue App 생성 => Vuex를 프레임워크화
			const app = createApp(App)
			const pinia = createPinia()
			app.use(pinia)
			app.mount("#app")
		=> store 생성
			export const useRecipeStore = defineStore('recipe', {
				state: () => ({변수:data()})
				actions: {
					=> axios 연동 => 데이터값 변경
				}
			})
			const recipeStore = useRecipeStore()
			
		=> Action 실행
			onMount(() => {})
			
		=> <div v-for="vo in recipeStore.list"
		
		사용자 요청
			|
		Component
			|
		Store Actions
			|
		state 변경
			|
		View (HTML)
*/

const useRecipeStore = defineStore('recipeList', {
	state: () => ({
		list: [],
		curpage: 1,
		totalpage: 0,
		startPage: 0,
		endPage: 0
	}), // data(){} => 지역 변수 / state => 전역
	// state 관리 프로그램 (데이터 관리 프로그램) => React / Vue / Ajax
	actions: {
		async recipeListData () {
			const res = await axios.get(
				'http://localhost:9090/recipe/list_vue/', {
					params: {
						page: this.curpage
					}
				})
				console.log(res.data)
				this.list = res.data.list
				this.curpage = res.data.curpage
				this.totalpage = res.data.totalpage
				this.startPage = res.data.startPage
				this.endPage = res.data.endPage
		},
		
		pageChange (page) {
			this.curpage = page
			this.recipeListData()
		},
		
	}
})




















