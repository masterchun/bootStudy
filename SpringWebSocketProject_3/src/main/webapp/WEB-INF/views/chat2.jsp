<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
  margin-top: 50px;
}
.row {
  margin: 0px auto;
  width: 900px;
}
h3 {
   text-align: center
}
.chat-body{
   height: 300px;
   overflow-y:auto 
}
.my-msg{
  text-align: right;
  color:#337ab7
}
</style>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
<script src="https://unpkg.com/vue@3.3.4/dist/vue.global.js"></script>
<script src="https://unpkg.com/vue-demi"></script>
<script src="https://unpkg.com/pinia@2.1.7/dist/pinia.iife.prod.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript">
const LOGIN_USER='${sessionScope.userid}'
</script>
<style type="text/css">
/* ===== 접속자 영역 ===== */
.user-panel {
  height: 100vh;
  border-right: 1px solid #ddd;
  background: #fff;
}

/* ===== 채팅 영역 ===== */
.chat-panel {
  height: 80vh;
  display: flex;
  flex-direction: column;
}

/* ===== 메시지 영역 ===== */
.chat-body {
  flex: 1;
  overflow-y: auto;
  padding: 15px;
  background: #b2c7da; /* 카카오톡 배경색 */
}

/* ===== 말풍선 공통 ===== */
.bubble {
  max-width: 60%;
  padding: 10px;
  border-radius: 10px;
  margin-bottom: 10px;
  position: relative;
  clear: both;
}

/* ===== 상대방 말풍선 ===== */
.bubble.left {
  background: #fff;
  float: left;
}

/* ===== 내 말풍선 ===== */
.bubble.right {
  background: #ffeb33;
  float: right;
}

/* ===== 시간 ===== */
.time {
  font-size: 11px;
  color: #666;
  margin-top: 3px;
  text-align: right;
}

/* ===== 입력 영역 ===== */
.chat-footer {
  padding: 10px;
  background: #eee;
}
</style>
</head>

<body>

<div class="container" id="app">
  <div class="row">

    <!-- ================= 접속자 목록 ================= -->
    <div class="col-sm-3 user-panel">
      <h4 class="text-center">접속자</h4>
      <ul class="list-group">
        <li class="list-group-item"
            v-for="u in users"
            :key="u"
            @click="store.changeRoom(u)"
            style="cursor:pointer">
          {{ u }}
        </li>
        <li class="list-group-item" @click="store.changeRoom('public')"
            style="cursor: pointer;">전체 채팅</li>
      </ul>
    </div>

    <!-- ================= 채팅 ================= -->
    <div class="col-sm-9 chat-panel">

      <!-- 헤더 -->
      <div class="panel-heading">
        <b>
          {{ store.currentRoom === 'public'
              ? '전체 채팅'
              : '1:1 채팅 - ' + store.currentRoom }}
        </b>
      </div>

      <!-- 메시지 -->
      <div class="chat-body">
        <div v-for="m in messages">

          <!-- 상대방 -->
          <div v-if="m.sender !== store.loginUser"
               class="bubble left">
            <b>{{ m.sender }}</b><br>
            {{ m.message }}
          </div>

          <!-- 나 -->
          <div v-else
               class="bubble right">
            {{ m.message }}
            
          </div>

        </div>
      </div>

      <!-- 입력 -->
      <div class="chat-footer">
        <div class="input-group">
          <input type="text"
                 class="form-control"
                 v-model="msg"
                 @keyup.enter="store.send()"
                 placeholder="메시지 입력">
          <span class="input-group-btn">
            <button class="btn btn-warning"
                    @click="store.send()">전송</button>
          </span>
        </div>
      </div>

    </div>
  </div>
</div>
<script src="/js/chatStore.js"></script>
  <script>
    const {createPinia} = Pinia
    const {ref,createApp,onMounted} = Vue
    const app=createApp({
    	setup(){
    		const store=useChatStore()
    		onMounted(()=>{
    			store.loginUser=LOGIN_USER
    			store.connect()
    		})
    		
    		return {
    			store
    		}
    	}
    })
    app.use(createPinia())
    app.mount("#app")
  </script>

</body>
</html>