<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<jsp:include page="/WEB-INF/jsp/mng/include/head.jsp" />
	<script type="text/javascript">
        function go_popup(index){
            $('#popup'+index).bPopup();
        }
    </script>
</head>
<body>
	<!-- skip navi -->
	<!-- <div id="skipnavigation">
		<ul>
			<li><a href="#cont_wrp">본문 바로가기</a></li>
			<li><a href="#gnb">주 메뉴 바로가기</a></li>
		</ul>
	</div> -->
	<!-- skip navi -->

	<div id="wrap" class="clfix">
		<!-- wrap (s)-->
		<header id="header"> <!-- header (s)-->
			<%-- <jsp:include page="/WEB-INF/jsp/mng/include/top.jsp" /> --%>
			<jsp:include page="/mng/left.do" />
		</header>
		
		
		<section id="cont_wrp" class="clfix"> <!-- main_wrp (s)-->

			<jsp:include page="/WEB-INF/jsp/mng/include/menuTopBar.jsp" />
			<!-- 			사이트매쉬 바디영역 -->
				<sitemesh:write	property="body" />
		</section>
<!-- 
		<footer id="footer"> footer (s)
		<div class="ftinner">
			170310 수정
            <p class="emlnone">
            	<span onclick="go_popup('1')">이메일무단수집거부</span>
            </p>
            // 170310 수정
			<address>
				
			</address>
			<p class="copy">Copyright (c)2017. All rights reserved.</p>

			<a href="00_index.html" title="홈으로" class="f-logo"><img
				src="/images/mng/common/f-logo.jpg" alt="KAFA 영화아카데미"></a>
		</div>

		</footer>
		footer (e)
		이메일무단수집거부 팝업 (s)
            <div class="inquiryBox" id="popup1">
                <div class="pop-head">
                    <h2>이메일주소 무단수집 거부 안내</h2>
                    <p><span class="b-close"><img src="/images/common/popupclose.png" alt="팝업 닫기"></span></p>
                </div>

                <div class="pop-cont">
                    <h3>이메일주소 무단수집 거부</h3>
                    <p>본 웹사이트에 게시된 이메일 주소가 전자우편수집 프로그램이나 그밖의 기술적장치를 이용하여 무단으로 수집되는 것을 거부하며, 이를 위반시 <strong>정보통신망 이용촉진 및 정보보호등에 관한 법률</strong>에 의해 처벌 받을 수 있습니다.</p>
                </div>
                <div class="inq-btnb">
                    <a class="b-close popc-close" href="#none"><span>닫기</span></a>
                </div>
            </div>
        이메일무단수집거부 팝업 (e) -->
        
   

	</div>

</body>
