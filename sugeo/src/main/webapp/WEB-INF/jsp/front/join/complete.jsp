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
				<h1><img src="/images/common/bg_join2.png" alt="img" height="140"></h1>
				<h3 class="mgt10"><img src="/images/common/bg_join2_title.png" alt="img" height="20"></h3>
				<p class="f_14">수거짱 회원가입이  <strong>완료</strong> 되었습니다. </p>
			</div>
            
            <p class="btn_wrap mgt15"><button title="메인 바로가기" onclick="toMain();" class="btn gray mgt15 f_wB" style="width:55%">메인 바로가기</button></p> 
            
		</div>
 	</div><!-- wrap (E) -->
</body>
</html>