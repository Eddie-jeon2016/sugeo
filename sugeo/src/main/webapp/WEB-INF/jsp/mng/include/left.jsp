<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>





<%--</style>--%>
<form name="bbsMove" id="bbsMove" method="post">
	<input type="hidden" name="bbsId"> <input type="hidden"
		name="pcId">

</form>
<h1>
	<a href="/mng/main.do" title="홈으로"><img
		src="/images/common/logo_1.png" alt="이통사와 함께하는 개인정보캠페인"></a>
</h1>
<div class="gnbwrp">
	<!-- gnbwrp (s)-->
	<nav id="gnb">
		<!-- gnb (s)-->
		<ul class="gnbtab">
			<li><a href="/mng/event/eventList.do" class="gnbbt"
				title="이벤트 관리">이벤트 관리</a>
				<ul class="twodep"
					<c:if test="${fn:indexOf(reqUrl,'/mng/event/') != -1 || fn:indexOf(reqUrl,'/mng/notice/') != -1 || fn:indexOf(reqUrl,'/mng/main') != -1}">style="display:block"</c:if>>
					<!--181113 수정  (S)-->
					<%-- <li><a href="/mng/event/eventList.do" title="이벤트 등록 및 관리"
						<c:if test="${fn:indexOf(reqUrl,'/mng/event/') != -1 || fn:indexOf(reqUrl,'/mng/main') != -1 }">class="on"</c:if>>이벤트
							등록 및 관리</a></li> --%>
					<!--181113 수정  (E)-->
					<li><a
						href="/mng/notice/BbsDetailList.do?BbsId=8&menuNo=1000008"
						title="이벤트 공지"
						<c:if test="${fn:indexOf(reqUrl,'/mng/notice/') != -1 || fn:indexOf(reqUrl,'/mng/main') != -1}">class="on"</c:if>>이벤트
							공지</a></li>
				</ul></li>

			<li><a href="/mng/bbs/pressContent/pressContentList.do"
				class="gnbbt" title="게시판 관리">게시판 관리</a>
				<ul class="twodep"
					<c:if test="${fn:indexOf(reqUrl,'/mng/bbs/') != -1}">style="display:block"</c:if>>
					<li><a href="/mng/bbs/pressContent/pressContentList.do"
						title="언론보도자료 관리"
						<c:if test="${fn:indexOf(reqUrl,'/mng/bbs/pressContent/') != -1}">class="on"</c:if>>언론보도자료
							관리</a></li>
					<li><a href="/mng/bbs/videoMng/videoMngList.do" title="동영상 관리"
						<c:if test="${fn:indexOf(reqUrl,'/mng/bbs/videoMng/') != -1}">class="on"</c:if>>동영상
							관리</a></li>
					<li><a
						href="/mng/bbs/relatedContent/BbsDetailList.do?BbsId=1&menuNo=1000001"
						title="관련글 관리"
						<c:if test="${fn:indexOf(reqUrl,'/mng/bbs/relatedContent/') != -1}">class="on"</c:if>>관련글
							관리</a></li>
					<li><a href="/mng/bbs/faqMng/faqMngList.do" title="FAQ 관리"
						<c:if test="${fn:indexOf(reqUrl,'/mng/bbs/faqMng/') != -1}">class="on"</c:if>>FAQ
							관리</a></li>
					<%-- <li><a href="/mng/bbs/BbsManageList.do" title="게시판 관리" <c:if test="${fn:indexOf(reqUrl,'/mng/bbs/BbsManageList') != -1}">class="on"</c:if>>게시판 관리</a></li> --%>
				</ul></li>
			<li><a href="/mng/banner/bannerInsertView.do" class="gnbbt"
				title="홈페이지 관리">홈페이지 관리</a>
				<ul class="twodep"
					<c:if test="${fn:indexOf(reqUrl,'/mng/content/') != -1 || fn:indexOf(reqUrl,'/mng/pop/') != -1 || fn:indexOf(reqUrl,'/mng/banner/') != -1}">style="display:block"</c:if>>
					<li><a href="/mng/banner/bannerInsertView.do" title="메인관리"
						<c:if test="${fn:indexOf(reqUrl,'/mng/banner/') != -1}">class="on"</c:if>>메인 관리</a></li>
					<li><a href="/mng/pop/popupList.do" title="팝업 관리"
						<c:if test="${fn:indexOf(reqUrl,'/mng/pop/') != -1}">class="on"</c:if>>팝업 관리</a></li>
					<li><a href="/mng/content/contentList.do" title="콘텐츠 관리"
						<c:if test="${fn:indexOf(reqUrl,'/mng/content/') != -1 || fn:indexOf(reqUrl,'/mng/bbs/BbsMng') != -1}">class="on"</c:if>>콘텐츠 관리</a></li>
				</ul>
			</li>
			<li><a href="/mng/auth/selectAuthorUserList.do" class="gnbbt"
				title="시스템 관리">시스템 관리</a>
				<ul class="twodep"
					<c:if test="${fn:indexOf(reqUrl,'/mng/auth/') != -1}">style="display:block"</c:if>>
					<li><a href="/mng/auth/selectAuthorUserList.do" title="관리자 관리"
						<c:if test="${fn:indexOf(reqUrl,'/mng/auth/selectAuthorUserList') != -1 || fn:indexOf(reqUrl,'/mng/main') != -1}">class="on"</c:if>>관리자
							관리</a></li>
					<li><a href="/mng/auth/selectAccessUserList.do" title="접속 로그"
						<c:if test="${fn:indexOf(reqUrl,'/mng/auth/selectAccessUserList') != -1}">class="on"</c:if>>접속
							로그</a></li>
					<li><a href="/mng/auth/workList/selectWorkLogList.do"
						title="작업 로그"
						<c:if test="${fn:indexOf(reqUrl,'/mng/auth/workList') != -1}">class="on"</c:if>>작업
							로그</a></li>
				</ul></li>

		</ul>

	</nav>
	<!-- gnb (e)-->
</div>
<!-- gnbwrp (e)-->