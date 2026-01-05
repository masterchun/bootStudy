<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  
  <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="https://unpkg.com/vue-demi"></script>
	<script src="https://unpkg.com/pinia@2/dist/pinia.iife.js"></script>
	
	<script>
		const SESSION_ID = '${sessionScope.id}'
	</script>
	
	<style type="text/css">
		.container {
			margin-top: 30px;
		}
		.row {
			margin: 0px auto;
			width: 960px;
		}
		p {
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
		}
		.nav-link {
			cursor: pointer;
		}
	</style>
</head>
<body>
	<div class="container" style="margin-top: 10px">
		<div class="row text-right">
			<c:if test="${sessionScope.id == null }">
				<a href="/member/login" class="btn btn-sm btn-info">로그인</a>
			</c:if>
			<c:if test="${sessionScope.id != null }">
				<a href="member/logout" class="btn btn-sm btn-danger">로그아웃</a>
			</c:if>
		</div>
	</div>
	<div class="container" id="recipe_detail">
		<div class="row">
			<table class="table">
				<tbody>
					<tr>
						<td class="text-center" colspan="3">
							<img :src="rStore.detail.vo.poster" style="width: 800px;height: 250px;">
						</td>
					</tr>
					<tr>
						<td class="text-center" colspan="3">
							<h3 class="text-center">{{ rStore.detail.vo.title }}</h3>
						</td>
					</tr>
					<tr>
						<td class="text-center" colspan="3">
							{{ rStore.detail.vo.content }}
						</td>
					</tr>
					<tr>
						<td class="text-center"><img src="/images/a1.png"></td>
						<td class="text-center"><img src="/images/a2.png"></td>
						<td class="text-center"><img src="/images/a3.png"></td>
					</tr>
					<tr>
						<td class="text-center">{{ rStore.detail.vo.info1 }}</td>
						<td class="text-center">{{ rStore.detail.vo.info2 }}</td>
						<td class="text-center">{{ rStore.detail.vo.info3 }}</td>
					</tr>
					<tr>
						<td class="text-right" colspan="3">
							<button type="button" class="btn-sm btn-danger">예약</button>
							<button type="button" class="btn-sm btn-danger" onclick="javascript:hitory.back()">목록</button>
						</td>
					</tr>
				</tbody>
			</table>
			<table class="table">
				<tbody>
					<tr>
						<td colspan="2">
							<h3>[ 조리 순서 ]</h3>
						</td>
					</tr>
					<tr v-for="(data, index) in rStore.detail.tList">
						<td class="text-left" width="85%">{{ data }}</td>
						<td class="text-right" width="15%"><img :src="rStore.detail.iList[index]" style="width: 180px;height: 180px;"></td>
					</tr>
				</tbody>
			</table>
			<table class="table">
				<tbody>
					<tr>
						<td colspan="2"><h3>[ 레시피 작성자 ]</h3></td>
					</tr>
					<tr>
						<td width="20%" rowspan="2" class="text-center">
							<img :src="rStore.detail.vo.chef_poster" style="width: 150px;height: 150px;" class="img-circle">
						</td>
						<td width="80%">{{ rStore.detail.vo.chef }}</td>
					</tr>
					<tr>
						<td width="80%">{{ rStore.detail.vo.chef_profile }}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row" id="recipe_reply" class="margin-top: 20px">
			<table class="table">
				<tbody>
					<tr>
						<td>
							<table class="table" v-for="(rvo, index) in store.reply_list" :key="index">
								<tbody>
									<tr>
										<td class="text-left">* {{ rvo.name }}&nbsp;({{ rvo.dbday }})</td>
										<td class="text-right">
											<button class="btn-xs btn-success" v-if="store.sessionId===rvo.id"
												@click="store.toggleUpdate(rvo.no, rvo.msg)">
												{{ store.upReplyNo === rvo.no ? '취소' : '수정' }}
											</button>
											<button class="btn-xs btn-danger" v-if="store.sessionId===rvo.id"
												@click="store.replyDelete(rvo.no)">삭제</button>
										</td>
									</tr>
									<tr>
										<td colspan="2" class="text-left">
											<pre style="white-space: pre-wrap;">{{ rvo.msg }}</pre>
										</td>
									</tr>
									<tr>
										<td colspan="2" class="text-left" v-if="store.upReplyNo === rvo.no">
											<textarea rows="5" cols="100" style="float: left;" v-model="store.msg"
												v-model="store.updateMsg[rvo.no]"></textarea>
											<button type="button" class="btn-success" 
												style="width: 100px;height: 103px;float: left;" 
												@click="store.replyUpdate(rvo.no)">
													댓글 수정
											</button>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
			<table class="table" v-if="store.sessionId">
				<tbody>
					<tr>
						<td>
							<textarea rows="5" cols="100" style="float: left;" v-model="store.msg"></textarea>
							<button type="button" class="btn-success" 
								style="width: 100px;height: 103px;float: left;" 
								@click="store.replyInsert()">
								댓글 쓰기
							</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<script src="/js/axios.js"></script>
	<script src="/js/recipeStore.js"></script>
	<script src="/js/replyStore.js"></script>
	<script>
		const {createApp, onMounted} = Vue
		const {createPinia} = Pinia
		
		const detailApp = createApp({
			setup() {
				const rStore = useRecipeStore()
				const store = useReplyStore()
				
				const params = new URLSearchParams(location.search)
				const no = params.get("no")
				
				onMounted(() => {
					rStore.recipeDetailData(no)
					store.sessionId = SESSION_ID
					store.replyListData(no)
				})
				
				return {
					rStore,
					store
				}
			}
		})
		
		detailApp.use(createPinia())
		detailApp.mount("#recipe_detail")
	</script>
</body>
</html>