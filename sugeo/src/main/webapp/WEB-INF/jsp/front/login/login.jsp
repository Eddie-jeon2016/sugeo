<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="/WEB-INF/jsp/front/include/head.jsp"/>
<body class="bg_white">
	<div id="wrap"> 
		<div class="loginBox">
			<button title="뒤로" onclick="toMain();"> <img src="/images/common/btn_back.png" alt="뒤로" height="20"></button>
			
			<div class="login_top">
				<h1><img src="/images/common/bg_lv5.png" alt="img" height="82"></h1>
				<h3 class="mgt10"><img src="/images/common/bg_login_title.png" alt="img" height="20"></h3>
				<p>두껍아 두껍아 헌 옷 줄께 새 옷 다오~!<br>
회원가입시 수거짱의 서비스를 더 편리하게 이용하실 수 있습니다. </p>
			</div>
			
			<div class="login_input">
				<ul>
					<li><input title="아이디 입력" type="text" placeholder="아이디 입력"></li>
					<li><input title="비밀번호 입력" type="password" placeholder="비밀번호 입력"></li>
					
					<li><button title="로그인" href="#" class="btn gray mgt15 f_wB">로그인</button></li>
					<li class="txt_r"><a title="아이디/비밀번호 찾기" href="#" class="f_wB txt_green f_13">아이디/비밀번호 찾기</a></li>
					
					<li class="naver"><button title="네이버 ID로 로그인" href="#" class="btn  green"><span></span> 네이버 ID로 로그인</button></li>
					<li class="txt_r f_13 txt_green">처음오셨나요? <a title="가입하기" href="/front/join/st.do" class="f_wB txt_green txt_under">가입하기</a></li>
				</ul>
			</div>
		</div>
 	</div><!-- wrap (E) -->
     
</body>
</html>
