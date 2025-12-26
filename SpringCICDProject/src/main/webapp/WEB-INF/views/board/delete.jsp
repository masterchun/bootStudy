<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="/img/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>게시판</h2>
                        <div class="breadcrumb__option">
                            <span>삭제하기</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->
    <section class="product-details spad">
        <div class="container">
            <div class="row" id="board_delete" style="margin: 0px auto;">
            	<table class="table">
            		<tbody>
            			<tr>
            				<td class="text-center">
            					비밀번호 : <input type="password" ref="pwdRef" v-model="store.pwd" size="20" class="input-sm">
            				</td>
            			</tr>
            			<tr>
            				<td class="text-center">
            					<button class="btn-sm btn-danger" @click="store.boardDelete(no, pwdRef)">삭제</button>
            					<button class="btn-sm btn-danger" onclick="javascript:history.back()">취소</button>
            				</td>
            			</tr>
            		</tbody>
            	</table>
            </div>
        </div>
    </section>
<script src="/boardjs/boardDeleteStore.js"></script>
<script>
	const {createApp, ref} = Vue
	const {createPinia} = Pinia
	
	const app = createApp({
		setup() {
			const store = useBoardDeleteStore()
			const pwdRef = ref('')
			const params = new URLSearchParams(location.search)
			const no = params.get('no')
			
			return {
				no,
				pwdRef,
				store
			}
		}
	})
	
	app.use(createPinia())
	app.mount("#board_delete")
</script>
</body>
</html>