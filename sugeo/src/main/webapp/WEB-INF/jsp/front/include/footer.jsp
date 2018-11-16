<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript">
   
	    $(document).ready(function(){ 
	
	        $(".personal").click(function(){ 
	
	            $(".popup_wrap").css("display", "block");
	            $(".mask").css("display", "block"); 
	        }); 
	        $(".b-close, .mask").click(function(){ 
	            $(".popup_wrap").css("display", "none"); 
	            $(".mask").css("display", "none"); 
	        }); 
	    });     
    
    
    	function go_popup(index){
            $('#popup'+index).bPopup();
        }

    </script>
    <!-- FOOTER (S) -->
    <div class="footer">
        <div class="ftinner">
            <div class="adress">
                <p>서울특별시 서초구 서초대로 350, 305호(서초동, 동아빌라트) 개인정보보호협회 캠페인운영샤무국</p>
                <a href="javascript:; title="개인정보처리방침" class="personal"> 
                &#91;개인정보처리방침 &#93;
                </a>            
                <p>[E-mail:safetykeeperwebmaster@gmail.com]</p>
                <p>Copyright 2018 OPA All Rights Reserved.</p>
            </div>  

            <ul class="shortCut">
                <li class="fir">
                    <label for="famsite">관련기관</label>
                    <select id="famsite" onchange="window.open(value,'_blank');" focus-none="">
                        <option selected="">관련기관</option>
                    </select>
                </li>
            </ul>
        </div>
    </div>
    <!-- //FOOTER (E) -->
    
     <!--  180913 탑버튼 (S) -->
    <div id="cmTopScroll" class="cmTopScroll abled">
        <a href="javascript:;" class="top"><img src="/images/common/btn_top.png" alt="탑버튼"></a>
    </div>
       <script>
         $( window ).scroll( function() {
           if ( $( this ).scrollTop() > 200 ) {
             $( '.top' ).show();
           } else {
             $( '.top' ).fadeOut();
           }
         } );

         $( '.top' ).click( function() {
           $( 'html, body' ).animate( { scrollTop : 0 }, 400 );
          return;
         } );
    </script>
    <!--  //180913 탑버튼 (E) -->
       
        
        
    <!-- 180913 개인정보처리방침 (S) -->
    <div class="popup_wrap" style="display:none; z-index: 9999; opacity: 1;">
            <div class="pop-head">
                <h2>개인정보처리방침</h2>
                <span class="b-close" style="float:right;"><img src="/images/common/btn_popCls.png" alt="팝업 닫기"></span>
            </div>
            <div class="pop-cont">
                <div class="privacyWrap">

                	<div class="popCont">
                		<c:import url="/front/content/privacyViewer.do?contentId=CONTENT_0000211"/>
                    </div>
                </div>
            </div>
            <div class="inq-btnb1">
                <a class="b-close popc-close" href="javascript:;"><span>확인</span></a>
            </div>
     </div>
     <div class="mask" style="display:none; background-color: rgb(0, 0, 0); position: fixed; top: 0px; right: 0px; bottom: 0px; left: 0px; opacity: 0.7; z-index: 9998; cursor: pointer;"></div>
     
     
     