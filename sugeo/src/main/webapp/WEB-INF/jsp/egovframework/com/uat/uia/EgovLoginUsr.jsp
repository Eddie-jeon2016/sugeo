<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovLoginUsr.jsp
  * @Description : Login 인증 화면
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.03    박지욱          최초 생성
  *   2011.09.25    서준식          사용자 관리 패키지가 미포함 되었을때에 회원가입 오류 메시지 표시
  *   2011.10.27    서준식          사용자 입력 탭 순서 변경
  *  @author 공통서비스 개발팀 박지욱
  *  @since 2009.03.03
  *  @version 1.0
  *  @see
  *
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>
<!DOCTYPE HTML>
<html lang="ko">
 <head>
    <meta charset="utf-8" />
    <!-- 브라우저별 최상의 렌더링 방식 -->
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">

    <meta name="keywords" content="2018 이통사와 함께하는 개인정보보호캠페인" />
    <meta name="Publisher" content="(주)포뎁스">
    <meta name="Copyright" content="이통사">

    <title>2018 이통사와 함께하는 개인정보보호캠페인</title>

    <script type="text/javascript" language="javascript" src="/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" language="javascript" src="/js/jquery.bpopup.min.js"></script>
    <script type="text/javascript" language="javascript" src="/js/common.js"></script>

   <link rel="stylesheet" type="text/css" href="/css/mng/front-base.css" media="all">
   <!-- <link rel="shortcut icon" type="image/x-icon" href="/images/common/favicon.ico" /> -->
    <link rel="icon" href="/images/common/favicon/cropped-safety-favicon-32x32.png" sizes="32x32" />
    <link rel="icon" href="/images/common/favicon/cropped-safety-favicon-192x192.png" sizes="192x192" />
    <link rel="apple-touch-icon-precomposed" href="/images/common/favicon/cropped-safety-favicon-180x180.png" />
    <meta name="msapplication-TileImage" content="/images/common/favicon/cropped-safety-favicon-270x270.png" />
    <!--[if lt IE 9]>
        <script type="text/javascript" src="../js/html5shiv.min.js"></script>
    <![endif]-->
<script type="text/javaScript" language="javascript">
<c:set var="msg" value="${param.message}"/>

    <c:if test="${msg != null || msg != ''}">
    <c:if test="${msg eq 'ERROR0001' || msg eq 'ERROR0000'}">
		alert('아이디/비밀번호가 일치하지 않습니다.\n다시 입력해주세요');   
		location.href='/uat/uia/egovLoginUsr.do';
    </c:if>
    </c:if>



function actionLogin() {

    if (document.loginForm.id.value =="") {
        alert("아이디를 입력해주세요");
    } else if (document.loginForm.password.value =="") {
        alert("비밀번호를 입력해주세요");
    } else {
        document.loginForm.action="<c:url value='/uat/uia/actionLogin.do'/>";
        //document.loginForm.j_username.value = document.loginForm.userSe.value + document.loginForm.username.value;
        //document.loginForm.action="<c:url value='/j_spring_security_check'/>";
        document.loginForm.submit();
    }
}



</script>
</head>
<body> 
<form name="loginForm" action="/logAction.do" method="post"> 
    <div id="loginwrap"> <!-- loginwrap (s)--> 
        <div class="lgninner"> 
            <h1><img src="/images/common/logo_1.png" alt="OPA 캠페인"><span>관리자 로그인</span></h1> 
            <input type="hidden" name="message" value="${message}">
            <ul> 
                <li><label for="uidinput">아이디</label><input type="text" name=id title="아이디" id="uidinput" value="" 
                                                            tabindex=1></li> 
                <li><label for="upwinput">비밀번호</label><input type="password" name="password" title="비밀번호" id="upwinput" 
                                                             maxlength="50" value="" tabindex="5" 
                                                             onKeyDown="javascript:if (event.keyCode == 13) { actionLogin(); }" 
                                                             tabindex=2></li> 
                <li class="btn"><a href="javascript:actionLogin()">로그인</a></li> 
            </ul> 
        </div> 
    </div>  <!-- loginwrap (e)--> 
    <input name="userSe" type="hidden" value="GNR"/> 
    <input name="j_username" type="hidden"/> 
</form> 
<style type="text/css"> 
    * { 
        padding: 0; 
        margin: 0; 
        text-decoration: none; 
        font-family: "맑은 고딕", "돋움", Arial 
    } 
    input { 
        font-family: inherit; 
        font-size: inherit; 
        color: #000; 
        display: inline-block; 
        zoom: 1; 
        *display: inline; 
        _display: inline; 
    } 
    input[type="checkbox"], input[type="radio"] { 
        margin: 0 3px 0 0 !important; 
        width: 14px; 
        height: 14px; 
        vertical-align: middle; 
    } 
    ul, ol, li, dl, dt, dd { 
        list-style: none; 
        vertical-align: top; 
    } 
    #loginwrap { 
        position: relative; 
        margin-top: 14% 
    } 
    .lgninner { 
        width: 450px; 
        margin: 0 auto; 
        padding: 25px 30px; 
        border: 5px solid #dedede; 
    } 
    .lgninner h1 { 
        text-align: center; 
        margin-bottom: 14px; 
        border-bottom: 1px dashed #ededed; 
        padding-bottom: 16px; 
    } 
    .lgninner h1 img { 
        margin-right: 10px 
    } 
    .lgninner h1 span { 
        vertical-align: top; 
        font-size: 25px; 
    } 
    .lgninner ul { 
        margin-top: 25px 
    } 
    .lgninner ul li { 
        margin-bottom: 10px; 
    } 
    .lgninner ul li input { 
        width: 98%; 
        height: 40px; 
        line-height: 40px; 
        padding: 0 0 0 5px; 
    } 
    .lgninner ul li label { 
        position: absolute; 
        left: -10000px; 
        top: auto; 
        height: 1px; 
        overflow: hidden 
    } 
    .lgninner ul .btn { 
        margin-top: 25px 
    } 
    .lgninner ul .btn a { 
        padding: 11px 20px 15px 20px; 
        background-color: #555; 
        display: block; 
        border-radius: 5px; 
        text-align: center; 
        color: #fff; 
        font-size: 18px; 
        font-weight: 600; 
    } 
</style> 
</body> 
</html>


