<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- total_menu (S) -->
<div class="total_menu" style="display: none;">
    <div class="txt">
        <button type="button">닫기</button>
      
        <div class="level">
            <img src="/images/common/bg_lv0.png" alt="로그인해주세요."> <!-- 등급따라 bg_lv1.png ~ bg_lv5.png 이미지 -->
            
            <h1 style="display:block">로그인 해주세요.</h1> 
            
            <h1 class="mgt3" style="display:none"><!-- 로그인 시 보여지는 h1태그 --> 
                <span>김미림님</span> 환영합니다.
                <i><strong>13,300</strong>P</i>
            </h1> 
        </div>
        <p class="btn_wrap"  style="display:">
            <a title="로그인" class="btn_small" href="/front/login.do">로그인</a> 
            <a title="회원가입" class="btn_small" href="/front/join/st.do">회원가입</a>
        </p>
        <p class="btn_wrap" style="display:none"> <!-- 로그인 시 보여지는 버튼 -->
            <a title="마이 페이지" class="btn_small" href="00_mypage.html">마이 페이지</a> 
            <a title="로그아웃" class="btn_small" href="/front/logout.do">로그아웃</a>
        </p>
    </div>
    
    <div class="gnb">
        <ul>
            <li class="depth"><a title="수거짱 소개" href="01_info.html">수거짱 소개</a></li>
            
            <li class="depth bN"><a title="수거짱" href="02_1sub.html">수거짱</a>
                <ul class="subDepth">
                    <li><a title="이렇게 수거해요" href="02_1sub.html">이렇게 수거해요</a></li>
                    <li><a title="수거품목 소개" href="02_2sub.html">수거품목 소개</a></li>
                    <li><a title="수거품 시세 정보" href="02_3sub.html">수거품 시세 정보</a></li>
                    <li><a title="방문수거" href="00_index.html">방문수거</a></li>
                    <li><a title="수거의뢰" href="02_5sub.html">수거의뢰</a></li>
                    <li><a title="예약조회 및 취소" href="00_mypage.html">예약조회 및 취소</a></li>
                </ul>
            </li>
            
            <li class="depth"><a title="포인트몰" href="03_pointM.html">포인트몰</a></li>
            
            <li class="depth"><a title="난 수거짱(마이 페이지)" href="00_mypage.html">난 수거짱(마이 페이지)</a></li>
            
            <li class="depth bN"><a title="고객센터" href="04_1sub.html">고객센터</a>
                <ul class="subDepth">
                    <li><a title="고객후기" href="04_1sub.html">고객후기</a></li>
                    <li><a title="이벤트" href="04_2sub.html">이벤트</a></li>
                    <li><a title="공지사항" href="04_3sub.html">공지사항</a></li>
                    <li><a title="FAQ" href="04_4sub.html">FAQ</a></li>
                    <li><a title="Q&A" href="04_5sub.html">Q&A</a></li>
                    <li><a title="제휴문의" href="04_6sub.html">제휴문의</a></li>
                </ul>
            </li>
        </ul>
    </div> 
    
    <p class="cp_txt">Copyright © 2018 수거짱 All rights reserved.</p>
     
</div> 
<!-- total_menu (E) -->