<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
		<div id="header" class="header">
        	<div class="top"> 
        		 <a title="메인화면 바로가기" href="00_index.html" class="logo"><img src="images/common/logo.png" alt="수거짱 로고"> </a> 
        		 
 				<button type="button" class="btn_menu" onClick="openMobileMenu();">전체메뉴</button>
          	</div> 
         	<div class="notic rolling_banner"> 
				<ul>
					<li><a title="공지사항바로보기" href="04_3sub_view.html">두껍아, 두껍아, 헌 옷 줄게 새 옷 다오~  </a><span>08-22</span></li>
					<li><a title="공지사항바로보기" href="04_3sub_view.html">가전제품 바로 판매하기 안내입니다.</a><span>08-22</span></li>
				</ul> 
			</div>
       
       		<div class="top_my">
       			<ul>
					<li class="lv_icon lv5">레벨아이콘</li><!-- 등급따라 lv1~lv5 클래스 -->
					<li class="my_point">
						<p class="pointR"><strong class="f_wM">밀림의왕자</strong>님의 포인트</p>
						<p class="pointC"><strong>133,500</strong>P</p>
					</li>
					<li class="btn_fl_tel"><a title="문의사항" href="javascript:void(0);" ></a></li>
				</ul>
			</div>
       
       		<div class="menu">
       			<ul>
					<li><a title="방문수거" href="#" class="on">방문수거</a></li>
					<li><a title="포인트몰" href="03_pointM.html">포인트몰</a></li>
					<li><a title="고객후기" href="04_1sub.html">고객후기</a></li>
					<li><a title="공지사항" href="04_3sub.html">공지사항</a></li>
				</ul>
			</div>
        </div> <!-- header (E) -->
<!--   <!-- HEADER (S) --> -->
<!--       <div class="header"> -->
<!--         <div class="hdtop">hdtop(s) -->
<!--           <div class="topinner"> -->
<!--             <ul class="outlink"> -->
<!--               <li><a href="javascript:;" title="페이스북 바로가기"><img src="/images/common/link_fb.png" alt="페이스북"></a></li> -->
<!--               <li><img src="/images/common/linkbar.png" alt="분리 바"></li> -->
<!--               <li class="sclink"><a href="https://www.notm.or.kr/" title="개인정보보호 자율감시센터 바로가기" target="_blank">개인정보보호 자율감시센터</a></li> -->
<!--             </ul> -->
<!--           </div> -->
<!--         </div>hdtop(e) -->
<!--         <div class="hdbtm">hdbtm(s) -->
<!--           <div class="bG"></div> -->
<!--           <div class="btminner clfix"> -->
<!--             <h2 class="blind">주메뉴</h2> -->
<!--             <h1> -->
<!--               <a href="/index.do" title="홈"><img src="/images/common/logo_1.png" alt="로고"></a> -->
<!--             </h1> -->
<!--             <nav id="gnb">#gnb(s) -->
<!--               <ul class="gnb"> -->
<%-- 	              <c:forEach var="result" items="${menues }"> --%>
<!-- 	              	<li class="gnbbg"> -->
<%-- 	              		<a href="${result.url }" class="gnbbt" title="${result.title }">${result.title}</a> --%>
<!-- 	              			<ul class="gnb_depth"> -->
<%-- 	                    		<c:forEach var="result1" items="${result.subMenues }" varStatus="status"> --%>
<%-- 			                        <li><a href="${result1.url }" title="${result1.title }">${result1.title }</a></li> --%>
<%-- 			                    </c:forEach> --%>
<!-- 	                  		</ul> -->
<!-- 	              	</li> -->
<%-- 	              </c:forEach> --%>
<!--               </ul> -->
<!--             </nav>#gnb(e) -->
<!--             <div class="gnb_bt">gnb_bt(s) -->
<!--               <a class="button"> -->
<!--                 <span class="mu"></span> -->
<!--               </a> -->
<!--             </div>gnb_bt(e) -->
<!--           </div> -->
<!--         </div> -->
<!--       </div>hdbtm(e) -->
      <!-- //HEADER (E) -->