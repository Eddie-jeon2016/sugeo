<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="/WEB-INF/jsp/front/include/head.jsp"/>
<script type="text/javascript">

	function goLA(){
		document.laform.action = '/front/join/ee.do';
		document.laform.submit();
	}

</script>
<form name="laform" method="post">
</form>
<body class="bg_white">
	<div id="wrap">
		<div class="loginBox">
			<button title="뒤로" onclick="toMain();"> <img src="/images/common/btn_back.png" alt="뒤로" height="20"></button>
			
			<div class="login_top">
				<h1><img src="/images/common/bg_join1.png" alt="img" height="82"></h1>
				<h3 class="mgt10"><img src="/images/common/bg_join_title.png" alt="img" height="20"></h3>
				<p>고객님의 수거짱 회원가입을 위하여 <strong class="txt_gray3">아이디/비밀번호</strong>를 입력해주세요. </p>
			</div>
			
			<div class="join_input">
				<ul>
					<li><input title="아이디 입력" type="text" placeholder="아이디 입력"> <span>영문/숫자 6~12자</span></li>
					<li><input title="비밀번호 입력" type="password" placeholder="비밀번호 입력"> <span>영문/숫자/특수문자 조합으로 8~15자, 대소문자 구분</span></li>
                    <li><input title="비밀번호 확인" type="password" placeholder="비밀번호 확인"> <span>비밀번호 재확인</span></li>
				</ul>
			</div>
            
            <div class="btn_wrap col2">
            	<button title="이전" href="javascript:history.back(-2);" class="btn"> 이전 </button>
                <button title="다음" class="btn green" onclick="goLA();"> 다음 </button>
            </div>
		</div>
 	</div><!-- wrap (E) -->
     
</body>
</html>