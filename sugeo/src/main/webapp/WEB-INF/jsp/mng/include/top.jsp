<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

        <header id="header"> <!-- header (s)-->

            <div id="hd_top"> <!-- hd_top (s)-->
                <div class="hd_topwrap">
                    <h1><a href="/mng/main.do" title="홈으로"><img src="/images/common/logo_1.png" alt="이통사와 함께하는 개인정보캠페인"></a></h1>
                    <div style="position: absolute;top: 18px;left: 800px;" ><a class="ltbtn whbtn" href="http://kafa.ac/index.do" title="사용자사이트">사용자 사이트</a></div>
                    <!-- 170310 추가 -->
                  	<!--   <div class="logoutbtn"><a href="/uat/uia/actionLogout.do" title="로그아웃">로그아웃</a></div> -->
                    <!-- // 170310 추가 -->
                </div>
             </div> <!-- hd_top (e)-->

             <div id="gnbwrp"> <!-- hd_bottom (s)-->
                <nav id="gnb"> <!-- gnb (s)-->
                      <ul class="gnbtab">
                        <li>
                            <a href="/mng/content/contentList.do" class="gnbbt" title="사이트 관리">${reqUrl}사이트 관리</a>
                              <ul class="gnb_depth">
                                <li><a href="/mng/content/contentList.do" title="콘텐츠 관리">콘텐츠 관리</a></li>
                                <li><a href="/mng/pop/popupList.do" title="팝업 관리">팝업 관리</a></li>
                              </ul>
                        </li>
                        <li>
                            <a href="/mng/bbs/BbsManageList.do" title="게시판 관리"  class="gnbbt">게시판 관리</a>
                              <ul class="gnb_depth">
                                <li><a href="/mng/bbs/BbsManageList.do" title="게시판 관리">게시판 관리</a></li>
                              </ul>
                        </li>
                        <li>
                            <a target="_blank" href="https://www.google-analytics.com" title="통계 관리"  class="gnbbt">통계 관리</a>
                              <ul class="gnb_depth">
                                <li><a target="_blank" href="https://www.google-analytics.com" title="통계 관리">통계 관리</a></li>
                              </ul>
                        </li>
                        <li>
                            <a href="/mng/auth/selectAuthorUserList.do" title="권한 관리"  class="gnbbt">권한 관리</a>
                              <ul class="gnb_depth">
                                <li><a href="/mng/auth/selectAuthorUserList.do" title="관리자 권한 관리">관리자 권한 관리</a></li>
                                <li><a href="/mng/auth/selectAccessUserList.do" title="관리자 접근 이력 관리">관리자 접근 이력 관리</a></li>
                              </ul>
                        </li>

                      </ul>

                    </nav> <!-- gnb (e)-->
                </div> <!-- hd_bottom (e)-->




        </header> <!-- header (e)-->    
    
