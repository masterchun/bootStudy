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
                            <span>상세페이지</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->
    <section class="product-details spad">
        <div class="container">
            <div class="row" id="board_detail">
            	<table class="table">
            		<tbody>
            			<tr>
            				<td width="20%" class="text-center success">번호</td>
            				<td width="30%" class="text-center">{{ store.vo.no }}</td>
            				<td width="20%" class="text-center success">작성일</td>
            				<td width="30%" class="text-center">{{ store.vo.dbday }}</td>
            			</tr>
            			<tr>
            				<td width="20%" class="text-center success">이름</td>
            				<td width="30%" class="text-center">{{ store.vo.name }}</td>
            				<td width="20%" class="text-center success">조회수</td>
            				<td width="30%" class="text-center">{{ store.vo.hit }}</td>
            			</tr>
            			<tr>
            				<td width="20%" class="text-center success">제목</td>
            				<td colspan="3" class="text-center">{{ store.vo.subject }}</td>
            			</tr>
            			<tr>
            				<td colspan="4" class="text-left" valign="top" height="200">
            					<pre style="white-space: pre-wrap;border: none;background-color: white;">{{ store.vo.content }}</pre>
            				</td>
            			</tr>
            			<tr>
            				<td colspan="4" class="text-right">
            					<a :href="'/board/update?no='+store.vo.no" class="btn btn-xs btn-warning">수정</a>
            					<a :href="'/board/delete?no='+store.vo.no" class="btn btn-xs btn-info">삭제</a>
            					<a href="/board/list" class="btn btn-xs btn-success">목록</a>
            				</td>
            			</tr>
            		</tbody>
            	</table>
            </div>
        </div>
    </section>
<script src="/boardjs/boardListStore.js"></script>
<script>
	const {createApp, onMounted} = Vue
	const {createPinia} = Pinia
	
	const app = createApp({
		setup() {
			const store = useBoardListStore()
			
			const params = new URLSearchParams(location.search)
			const no = params.get('no')
			
			onMounted(() => {
				store.boardDetail(no)
			})
			
			return {
				store
			}
		}
	})
	
	app.use(createPinia())
	app.mount("#board_detail")
</script>
</body>
</html>