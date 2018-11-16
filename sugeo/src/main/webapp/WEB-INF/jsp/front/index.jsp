<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
.mainPopup {
	max-height: 600px;
	max-width: 400px;
}
</style>
<script type="text/javascript">
$(document).ready(function() {

	<c:forEach items="${popups}" var="popup" varStatus="status">
		if(getCookieData('popupCookie_${popup.popupId }') != 'noPop'){
			var img ="/cmm/fms/getImage.do?atchFileId=${popup.fileId}&fileSn=0";		
			var url = urlCheck('${popup.url}');
			 openPopup('${popup.popupId }','<img src='+img+' alt="이미지" width="100%" height="100%"/>', url,'${popup.popupName}');	
		}
	</c:forEach>
});

function selectBoardView(bbsid, id){
	
	document.listForm.selectId.value = id;
	document.listForm.selectbbsId.value = bbsid;
	document.listForm.cmd.value = "view";
	document.listForm.action = "<c:out value='/front/bbs/BbsMain.do?menuNo=1000001'/>";
	document.listForm.submit();
}


function getRand() {
    randNumber = Math.floor(Math.random() * randList.length);
    randResult.push(randList[randNumber]);
    randList.splice(randNumber, 1);
}

function scheToggle(idx){
	$('.tbltr').hide();
	$('.tbltr'+idx).show();

	$('#tabtab1').removeClass();
	$('#tabtab2').removeClass();
	$('#tabtab'+idx).attr('class','on');
}
function urlCheck(url) {
	if(url.indexOf("http://") > -1){
		return url;
	} else {
		return "http://"+url;
	}

}
function openPopup(id, data, url,name){

	$("#mainPopup_"+id).show();
	$("#url_"+id).prop("href", url);
	$("#popup_display_"+id).html(data);
	//$("#popup_name_"+id).html(name);
	
}

function closePopup(id){
	$("#mainPopup_"+id).remove();
}

function setCookieData(opt){
	opt = (opt) || {};
	var name = (opt.name) || "";
	var value = (opt.value) || "";
	var expires = (opt.expires) || null;
	var path = (opt.path) || null;
	var domain = (opt.domain) || null;
	var secure = (opt.secure) || null;

	var today = new Date();
	today.setTime( today.getTime() );

	if (expires){
		expires = expires * 1000 * 60 * 60 * 24;
	}
	var expires_date = new Date( today.getTime() + (expires) );

	document.cookie = name + "=" +escape( value ) +
	( ( expires ) ? ";expires=" + expires_date.toGMTString() : "" ) +
	( ( path ) ? ";path=" + path : "" ) +
	( ( domain ) ? ";domain=" + domain : "" ) +
	( ( secure ) ? ";secure" : "" );

}
function getCookieData(name) {
	var nameOfCookie = name + "=";
	var x = 0;
	while (x <= document.cookie.length) {
		var y = (x + nameOfCookie.length);
		if (document.cookie.substring(x, y) == nameOfCookie) {
			if ((endOfCookie=document.cookie.indexOf(";", y)) == -1) {
				endOfCookie = document.cookie.length;
			}
			return unescape(document.cookie.substring(y, endOfCookie));
		}
		x = document.cookie.indexOf(" ", x) + 1;
		if (x == 0) {
			break;
		}
	}
	return "";
}
function removeCookieData(opt) {
	var $this = this;

	opt = (opt) || {};
	var name = (opt.name) || "";
	var path = (opt.path) || null;
	var domain = (opt.domain) || null;

	if ( $this.getCookieData( name ) ) {
		document.cookie = name + "=" +
	//( ( path ) ? ";path=" + path : "") +
	//( ( domain ) ? ";domain=" + domain : "" ) +
		";expires=Thu, 01-Jan-1970 00:00:01 GMT";
	}
}
function setCookieAndClosePopup(id, expireValue){
	this.setPopupExpireCookie(id, expireValue);
	$("#mainPopup_"+id).remove();
}
function setPopupExpireCookie(id, expireValue){
	this.setCookieData({
        name : 'popupCookie_'+id,
        value : 'noPop',
        expires : expireValue,
        path : "/"
	});
}
function goProMovieList() {
	document.moveFrm.action = '/front/movie/searchMovie.do';
	document.moveFrm.submit();
}
function goBbs() {
	document.moveFrm.action = '/front/bbs/BbsMain.do?menuNo=1000001';
	document.moveFrm.submit();
}
function goMasterClass() {
	document.moveFrm.action = '/front/content/contentViewer.do?contentId=CONTENT_0000181';
	document.moveFrm.submit();
}
</script>

		 <!-- CONTAINER (S) -->
    <div class="wrap">
    			<!-- 170314 메인 팝업 /   #main_wrp 안에 위치함(s) -->
       	<div id="mainPopupWrp">
       		<c:forEach var="popup" items="${popups }">
	            <div class="mainPopup" id="mainPopup_${popup.popupId }" style="display: none;">
	                <div class="innercont" >
	                	<c:if test="${popup.url ne '' }">
		                	<a id="url_${popup.popupId }" target="_blank">
		                		<div id ="popup_name_${popup.popupId }"  align="center">
		                		
		                		</div>
			                    <div id="popup_display_${popup.popupId }">

			                    </div>
		                    </a>
	                    </c:if>
	                    <div id="popup_display_${popup.popupId }">

	                    </div>
	                    <div class="closebtn">
	                  
	                    	<c:if test="${popup.closeType eq 'W'}">
		                        <span>일주일동안 보이지 않기 <input type="checkbox" title="일주일동안 보이지 않기" onclick="javascript:setCookieAndClosePopup('${popup.popupId}', 7);"></span>                    	
	                    	</c:if>
	                    	<c:if test="${popup.closeType eq 'T'}">
		                        <span>하루동안 보이지 않기 <input type="checkbox" title="하루동안 보이지 않기" onclick="javascript:setCookieAndClosePopup('${popup.popupId}', 1);"></span>                    	
	                    	</c:if>
	                    	<c:if test="${popup.closeType eq 'F'}">
		                        <span>다시 보지 않기 <input type="checkbox" title="다시보지 않기" onclick="javascript:setCookieAndClosePopup('${popup.popupId}', 365);"></span>                    	
	                    	</c:if>
	                        <span>닫기 <input type="checkbox" onclick="closePopup('${popup.popupId}'); return false;" title="닫기"></span>
	                    </div>
	                </div>
	            </div>
            </c:forEach>
         </div>
            <!-- 170314 메인 팝업 /   #main_wrp 안에 위치함(e) -->

<!-- 3depth 오른쪽 슬라이더 (E) -->
        <!-- 메인 비주얼 (S) 이미지:1400이하,640이하 -->
       <!--  <div class="visual">
            <ul class="visual_box">
            <li class="vb_1">
              <img src="images/main/bg_0.png" alt="메인비주얼">
              <img src="images/main/bg_1.png" alt="메인비주얼">
              <img src="images/main/bg_2.png" alt="메인비주얼">
              <img src="images/main/bg_3.png" alt="메인비주얼">
            </li>
            <li class="vb_2">
              <img src="images/main/bg_0.png" alt="메인비주얼">
              <img src="images/main/bg_1.png" alt="메인비주얼">
              <img src="images/main/bg_2.png" alt="메인비주얼">
              <img src="images/main/bg_3.png" alt="메인비주얼">
            </li>
            </ul>
        </div> -->
             <!-- 180904 수정 (S) -->
        <div class="visual">
            <!-- 180904 수정 (S) -->
            <div class="visuin"><img src="images/main/bg_txt.png" alt="메인비주얼"></div> 
            <%-- <div class="visuin"><img src="/cmm/fms/getImage.do?atchFileId=${mainImg}&fileSn=0" alt="메인비주얼"></div> --%>
            <!-- //180904 수정 (E) -->
        </div>
        
        <!-- //메인 비주얼 (E) -->

        <!-- 2DEPTH (S) -->
        <div class="wrap secon">
            <div class="inner">
            <ul class="adlist">
              <li class="listin1">
                <a href="/front/content/contentViewer.do?contentId=CONTENT_0000001" title="바로가기"><img src="images/main/2d_1.png" alt="개인정보환상호흡"></a>
                <div class="hovback hb1" style="display:none;">
                    <a class="hovinner" href="/front/content/contentViewer.do?contentId=CONTENT_0000001">
                    <p class="hovtit">캠페인 소개</p>
                    <span><img src="images/main/2dhover.png" alt="바로가기이미지" ></span>
                    </a>
                    </a>
                </div>
              </li>
              <li class="listin2">
                <a href="/front/video/videoList.do" title="바로가기"><img src="images/main/2d_2.png" alt="이동전화가입시"></a>
                <div class="hovback hb2" style="display:none;">
                    <a class="hovinner" href="/front/video/videoList.do">
                      <p class="hovtit">동영상 소개</p>
                      <span><img src="images/main/2dhover.png" alt="바로가기이미지"></span>
                    </a>
                </div>
              </li>
              <li class="listin3">
                <a href="/front/content/contentViewer.do?contentId=CONTENT_0000171" title="바로가기"><img src="images/main/2d_3.png" alt="불법텔레마케팅"></a>
                <div class="hovback hb3" style="display:none;">
                  <a class="hovinner" href="/front/content/contentViewer.do?contentId=CONTENT_0000171">
                    <p class="hovtit">카드뉴스 소개</p>
                    <span><img src="images/main/2dhover.png" alt="바로가기이미지"></span>
                  </a>
                </div>
              </li>
              <li class="listin4">
                <a href=" /front/content/contentViewer.do?contentId=CONTENT_0000181" title="바로가기"><img src="images/main/2d_4.png" alt="일상폰반사"></a>
                <div class="hovback hb4" style="display:none;">
                  <a class="hovinner" href=" /front/content/contentViewer.do?contentId=CONTENT_0000181">
                    <p class="hovtit">웹툰 소개</p>
                    <span><img src="images/main/2dhover.png" alt="바로가기이미지"></span>
                  </a>
                </div>
              </li>
            </ul>
            </div>
        </div>
        <!-- //2DEPTH (E) -->

        <!-- 3DEPTH (S) -->
        <div class="wrap third">
            <div class="inner clfix">
              <div class="thleft">
                    <h2>관련글</h2>
                    <a class="more" title="더보기" href="/front/bbs/BbsMain.do?menuNo=1000001">
                        <img src="images/main/arw.png" alt="더보기"><span>더보기</span>
                    </a>
                    <div class="wrtbox">
                        <c:forEach var="relatedContent" items="${relatedContents}">
                    		 <div class="wrtbx bx1">
                            	<div class="bximg">
                            		<c:set var="imgSrc" value=""/>
			                			<c:choose>
			                    			<c:when test="${relatedContent.thumbAtchFileId!='0'&& relatedContent.thumbAtchFileId!='' && relatedContent.thumbAtchFileId!=null}">
			                        			<c:set var="imgSrc" value="/cmm/fms/getImage.do?atchFileId=${relatedContent.thumbAtchFileId}&fileSn=0"/>
			                    			</c:when>			                 
			                    			<c:otherwise>
			                        			<c:set var="imgSrc" value="/images/sub/blank2.gif"/>
			                   				 </c:otherwise>
			                			</c:choose>
                               	 	<a alt="바로가기" onClick="javascript:selectBoardView('${relatedContent.bbsId}', '${relatedContent.nttId}'); return false;"><img src="${imgSrc}"></a>
                            	</div>  
                            	 <div class="bxtxt">
                                	<h3><a onClick="javascript:selectBoardView('${relatedContent.bbsId}', '${relatedContent.nttId}'); return false;" title="바로가기" class="dotdot">${relatedContent.title}</a></h3>
                               		 <div class="txtbox dotdot">${relatedContent.content}</div>
                          		  </div>
                        	</div>
                    	</c:forEach>
                        
                       <!--  <div class="wrtbx bx2">
                            <div class="bximg">
                                <a href="javascript:;" alt="바로가기"><img src="images/main/wrt_2.png" alt="관련글"></a>
                            </div>  
                            <div class="bxtxt">
                                <h3><a href="javascript:;" title="바로가기" class="dotdot">개인정보 안전하게 지키는 꿀팁개인정보 안전하게 지키는 꿀팁개인정보 안전하게 지키는 꿀팁</a></h3>
                                <p class="dotdot">한국인터넷진흥원(KISA)이 준비한 '2017 인터넷 내정보 지킴이 캠페인' 카드뉴스! 접근 권한을 확인하면 소중한 개인정보를 보호할 수 있어요!한국인터넷진흥원(KISA)이 준비한 '2017 인터넷 내정보 지킴이 캠페인' 카드뉴스! 접근 권한을 확인하면 소중한 개인정보를 보호할 수 있어요!</p>
                            </div>
                        </div> -->
                    </div>
              </div>
              <div class="thright">
                    <ul class="thrslide">
                    	<c:forEach var="faq" items="${faqList}">
                    		<li class="slidelist">
                    			<a href="javascript:;" title="바로가기">
                    				 <div class="faq faq_q">
                    				 	 <strong>FAQ</strong>
                    				 	 	<span class="cate"> &#91; ${faq.typeName} &#93;</span>
                    				 	 		<p class="txt">${faq.quest}</p>
                    				 </div>
                    				  <div class="faq faq_a">
                    				  	<strong>FAQ</strong>
                               				<span class="cate"> &#91; 답변 &#93;</span>
                               					 <p class="txt">${faq.answer}</p>
                               		  </div>
                               	</a>
                            </li>
                    	</c:forEach>
                        <!-- <li class="slidelist">
                          <a href="javascript:;" title="바로가기">
                              <div class="faq faq_q">
                                <strong>FAQ</strong>
                                <span class="cate"> &#91; 개인정보 &#93;</span>
                                <p class="txt">070이나 기계음으로 전화가 오면 불법이라고 하는데,확실하나요?070이나 기계음으로 전화가 오면 불법이라고 하는데,확실하나요?070이나 기계음으로 전화가 오면 불법이라고 하는데,확실하나요?</p>
                              </div>
                              <div class="faq faq_a">
                                <strong>FAQ</strong>
                                <span class="cate"> &#91; 답변 &#93;</span>
                                <p class="txt">답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변답변</p>
                              </div>
                          </a>
                        </li> -->
                    </ul>
              </div>
            </div>
        </div>
        <!-- //3DEPTH (E) -->

        <!-- 4DEPTH (S) -->
        <div class="wrap four">
            <div class="inner friner">
                <h2>언론보도자료</h2>
                    <a class="more" title="더보기" href="/front/pressContent/pressContentList.do">
                        <img src="images/main/arw.png" alt="더보기"><span>더보기</span>
                    </a>
                  <div class="tbl_wrp news"><!--tbl_wrp (s)언론보도자료 리스트 5개-->
                      <table class="tbl_lst">
                          <caption>공지사항</caption>
                          <colgroup>
                              <col style="width: 70%;" class="rmv_75">
                              <col style="width: 15%;" class="rmv_35">
                              <col style="width: 15%;" class="attach">
                          </colgroup>
                          <tbody>
                          	<c:forEach var="pressContent" items="${pressContentList}">
                          		<tr>
                                  <td class="subjectwo">
                                       <c:choose>
	                                   		<c:when test="${fn:indexOf(pressContent.url, 'http://') == -1 && fn:indexOf(pressContent.url, 'https://') == -1}">
	                                    		<a target="_blank" href="http://${pressContent.url}" title="바로가기" >${pressContent.title}</a>                                    		
	                                   		</c:when>
	                                   		<c:otherwise>
	                                         	<a target="_blank" href="${pressContent.url}" title="바로가기" >${pressContent.title}</a>
	                                   		</c:otherwise>
	                                   	</c:choose>
                                  </td>
                                  <td class="author">${pressContent.pressName }</td>
                                  <td class="day"> ${fn:substring(pressContent.regDate,0,10)}</td>
                               </tr>
                          	</c:forEach>
                          
                              <!-- <tr>
                                  <td class="subjectwo">
                                      <a href="javascript:;" title="바로가기">방통위, 이통사와 함께하는 개인정보보호 캠페인 실시</a>
                                  </td>
                                  <td class="author">보안뉴스</td>
                                  <td class="day">2018-08-08</td>
                              </tr> -->
                                                          
                          </tbody>
                      </table>
                  </div><!--tbl_wrp (e)-->
                </div>
            </div><!-- //4DEPTH (E) -->

        </div><!-- CONTAINER (E) -->


<form id="listForm" name="listForm" method="post" action="">
<input type="hidden" name="cmd" />
<input type="hidden" name="selectId" value="" />
<input type="hidden" name="selectbbsId" value="" />
</form>
<form id="moveFrm" name="moveFrm" method="post">
	<input type="hidden" name="mvNumber">
</form>
<!-- 메인비주얼 슬라이더 (S) -->
<script type="text/javascript"> 
    $('.visual_box').slick({
      dots: true,
      infinite: true,
      speed: 200,
      autoplay:true,
      slidesToShow: 1
  });
</script>
<!-- 메인비주얼 슬라이더 (E) -->

<!-- 마우스오버 (S) -->
<script type="text/javascript"> 
    jQuery(function(){

        $(".listin1").mouseover(
          function () {
                $('.hb1').show();
          }
        );
        $(".listin1").mouseleave(
            function () {
                $('.hb1').hide();
            }
        );

        $(".listin2").mouseover(
            function () {
                $('.hb2').show();
            }
        );
        $(".listin2").mouseleave(
            function () {
                $('.hb2').hide();
            }
        );

        $(".listin3").mouseover(
            function () {
                $('.hb3').show();
            }
        );
        $(".listin3").mouseleave(
            function () {
                $('.hb3').hide();
            }
        );

        $(".listin4").mouseover(
            function () {
                $('.hb4').show();
            }
        );
        $(".listin4").mouseleave(
            function () {
                $('.hb4').hide();
            }
        );
  });
</script>
<!-- //마우스오버 (E) -->

<!-- 3depth 오른쪽 슬라이더 (S) -->
<script type="text/javascript"> 
    $('.thrslide').slick({
      infinite: true,
      speed: 300,
      autoplay:true,
      slidesToShow: 1
    });
</script>