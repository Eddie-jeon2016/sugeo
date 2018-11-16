<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
  * @Class Name : BbsCafeManageList.jsp
  * @Description : 카페 게시판 목록 화면
  * @Modification Information
  * @
  * @  	수정일      			수정자           	 수정내용
  * @ ---------------        --------    	---------------------------
  * @ 2013.08.29     		조은태         		최초 생성
  *
  *  @author (주)거산디에스엔 조은태
  *  @since 2013.08.29
  *  @version 1.0
  *  @see
  *
  */
%>
<script>
history.pushState(null, null, location.href); 
window.onpopstate = function(event) {
		if(location.href.indexOf("#none") == -1) {
			document.tempForm.action = document.referrer;
			document.tempForm.submit();	
		}
	
}
</script>
<form id="tempForm" name="tempForm" method="post">
	<input type="hidden" name="searchCateId" value="${searchVO.searchCateId }">
	<input type="hidden" name="searchCondition" value="${searchVO.searchCondition }">
	<input type="hidden" name="searchKeyword" value="${searchVO.searchKeyword }">
	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }">
</form>
<form id="listForm" name="listForm" method="post">
<input type="hidden" name="cmd" />
<input type="hidden" name="selectId" value="" />
<input type="hidden" name="selectbbsId" value="" />
</form>
<form name="selectForm" method="post">
<input type="hidden" name="cmd" value="<c:out value='${searchVO.cmd }' />" />
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input type="hidden" name="searchCateId" value="${searchVO.searchCateId}"/>
<input type="hidden" name="nttId" value="${result.nttId }"/>
<input type="hidden" name="searchBbs_ty_code" value="${bbsManageVO.bbsTyCode }"/>
<input type="hidden" name="selectId" value="" />
<meta property="og:image" content="http://sbtv.kr/images/mediacenter/renew_usr/common/logo.gif">
<meta property="og:url" content="${domain }/front/bbs/BbsMain.do?menuNo=${searchVO.menuNo}&amp;cmd=view&amp;selectId=${result.nttId }&amp;selectbbsId=${ bbsManageVO.bbsId }">
<meta property="og:description" content="${result.title }">
<meta property="og:site_name" content="${domain }/front/bbs/BbsMain.do?menuNo=${searchVO.menuNo}" />

	<!-- 180904 수정 (S) -->
	<div class="subIn">

		<div class="readCnt">
			<div class="right clfix">
				<ul class="snsbox clfix">
					<li><a href="javascript:snsTo('facebook', '${domain }/front/bbs/BbsMain.do?menuNo=${searchVO.menuNo}&amp;cmd=view&amp;selectId=${result.nttId }&amp;selectbbsId=${ bbsManageVO.bbsId }', '<c:out value='${result.content}'/>');" title="페이스북" class="sn_linkf">
						<img src="/images/sub/facebook.gif" alt="페이스북"></a></li>
					<li><a href="javascript:snsTo('twitter', '${domain }/front/bbs/BbsMain.do?menuNo=${searchVO.menuNo}&amp;cmd=view&amp;selectId=${result.nttId }&amp;selectbbsId=${ bbsManageVO.bbsId }', '<c:out value='${result.title}'/>');" title="트위터" class="sn_facb">
						<img src="/images/sub/twitter.gif" alt="트위터"></a></li>
					<li><a href="javascript:snsTo('google', '${domain }/front/bbs/BbsMain.do?menuNo=${searchVO.menuNo}&amp;cmd=view&amp;selectId=${result.nttId }&amp;selectbbsId=${ bbsManageVO.bbsId }', '<c:out value='${result.title}'/>');" title="구글" class="sn_facb">
						<img src="/images/sub/google.gif" alt="구글"></a></li>
				</ul>
			</div>

			<div class="readPage">
				<div class="boardView">
					<div class="boradItem">
						<div class="heading no-line">
							<h2 class="title">
								<span class="tits dotdot">${result.title}</span>
							</h2>
							<p class="meta">
								<span class="regidate" title="등록일">등록일<i>${result.frstDateTime }</i></span>
								<span class="viewnum" title="조회수">조회수<i>${result.rdcnt }</i></span>
							</p>
						</div>
						<div class="content">
							<div class="readInner">	
								${result.content}
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="prnxt clfix">
				<div class="leftPrv">
					<c:if test="${empty prevBbs}">
						<a href="javascript:;"><img src="/images/sub/wprev.png" alt="이전글"></a>
						<span title="이전글">이전글<i class="prtit"> 이전글이 없습니다. </i></span>
					</c:if>
					<c:if test="${!empty prevBbs}">
						<tr>
							<a href="javascript:;" title="이전글"><img src="/images/sub/wprev.png" alt="이전글"></a>
							<span title="${prevBbs.title }">이전글 <i class="prtit">
							<a href="javascript:selectBoardView('${prevBbs.bbsId}', '${prevBbs.nttId}')" title="초성퀴즈 이벤트 경품 변동 안내"> 
							<c:choose>
								<c:when test="${fn:length(prevBbs.title)>30 }">
									<c:out value="${fn:substring(prevBbs.title,0,29)}" />...
	                        	</c:when>
								<c:otherwise>
									<c:out value="${prevBbs.title}" />
								</c:otherwise>
							</c:choose>
								</a>
								</i>
							</span>
						</tr>
					</c:if>
				</div>
				<div class="rightNxt">
					<c:if test="${empty nextBbs}">
						<span title="다음글"><i class="nxtit">
							<a href="javascript:;"> 다음글이 없습니다. </i>다음글</span><img src="/images/sub/wnext.png" alt="다음글"></a>
					</c:if>
					<c:if test="${!empty nextBbs}">
						<span title="다음글"><i class="nxtit">
						 <a href="javascript:selectBoardView('${nextBbs.bbsId}', '${nextBbs.nttId}')" title="${nextBbs.title }"> 
						 <c:choose>
								<c:when test="${fn:length(nextBbs.title)> 30}">
										<c:out value="${fn:substring(nextBbs.title,0,29)}" />...
                         		</c:when>
								<c:otherwise>
										<c:out value="${nextBbs.title }" />
								</c:otherwise>
						</c:choose>
							</a></i>다음글</span>
						<a href="javascript:;" title="다음글"><img src="/images/sub/wnext.png" alt="다음글"></a>
					</c:if>
				</div>
			</div>

			<div class="mprnxt">
				<table class="tablList">
					<colgroup>
						<col style="width: 18%"></col>
						<col style="width: *"></col>
					</colgroup>
					<tbody>
						<c:if test="${empty prevBbs}">
							<tr>
								<th></th>
								<td class="tl">이전글이 없습니다.</td>
							</tr>
						</c:if>
						<c:if test="${!empty prevBbs}">
							<tr>
								<th>이전글</th>
								<td class="tl">
									<a href="javascript:selectBoardView('${prevBbs.bbsId}', '${prevBbs.nttId}')"
										class="atg_go" title="${prevBbs.title }" class="mbtit dotdot">${prevBbs.title }</a>
								</td>
							</tr>
						</c:if>

						<c:if test="${empty nextBbs}">
							<tr>
								<th>다음글</th>
								<td class="tl">다음글이 없습니다.</td>
							</tr>
						</c:if>
						<c:if test="${!empty nextBbs}">
							<tr>
								<th>다음글</th>
								<td class="tl">
									<a href="javascript:selectBoardView('${nextBbs.bbsId}', '${nextBbs.nttId}')"
										class="atg_go" title="${nextBbs.title }" class="mbtit dotdot">${nextBbs.title }</a>
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>

			<div class="listBtn">
				<a href="javascript:listBack('${bbsManageVO.bbsId}');" title="목록">목록</a>
			</div>

		</div>
	</div>
	<!-- 180904 수정 (E) -->

	<%--      <div class="sub_cont"> <!-- sub_cont (s)-->


            <ul class="notice-sns">
            	<!-- 170404 추가 (s) -->
                <li><a href="#none" onclick="javascript:urlCopy('${domain }/front/bbs/BbsMain.do?menuNo=${searchVO.menuNo}&amp;cmd=view&amp;selectId=${result.nttId }&amp;selectbbsId=${ bbsManageVO.bbsId }');" title="url복사"><img src="../../images/sub/linkb.gif" alt="url복사"></a></li>
                <!-- 170404 추가 (e) -->
                <li><a href="javascript:snsTo("facebook", "${domain }/usr/bbs/BbsMain.do?cmd=view&amp;selectId=${result.nttId }", "<c:out value="${result.title}"/>");" title="카카오톡"><img src="../../images/sub/kkos.gif" alt="카카오톡"></a></li>
                <li><a href="javascript:snsTo('blog', '${domain }/front/bbs/BbsMain.do?menuNo=${searchVO.menuNo}&amp;cmd=view&amp;selectId=${result.nttId }&amp;selectbbsId=${ bbsManageVO.bbsId }', '<c:out value='${result.title}'/>');" title="네이버 블로그"><img src="../../images/sub/navb.gif" alt="네이버 블로그"></a></li>
                <li><a href="javascript:snsTo('facebook', '${domain }/front/bbs/BbsMain.do?menuNo=${searchVO.menuNo}&amp;cmd=view&amp;selectId=${result.nttId }&amp;selectbbsId=${ bbsManageVO.bbsId }', '<c:out value='${result.title}'/>');" title="페이스북"><img src="../../images/sub/facb.gif" alt="페이스북"></a></li>
            </ul>
            <div class="tbl_wrp">
                <table class="tbl_lst">
                    <caption>${ bbsManageVO.bbsName } 상세</caption>
                    <colgroup>
                        <col style="width: 15%;">
                        <col style="width: 85%;">
                    </colgroup>
                    <tbody>
                        <tr>
                            <td colspan="2">
                                <div class="dtltit">
                                    <p><span class="viColor">${ result.bbscateName }</span><br>
                                        ${result.title }
                                    </p>

                                    <ul class="info">
                                        <li>${result.nickname}</li>
                                        <li>${result.frstDateTime }</li>
                                        <li>조회수 : ${result.rdcnt }</li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        <c:if test="${result.atchFileId ne 0 && result.atchFileId ne null }">
                        <tr>
                            <th scope="row">첨부파일</th>
                            <td class="tl">
									<c:import url="/cmm/fms/selectFileListView.do" charEncoding="utf-8">
										<c:param name="param_atchFileId" value="${result.atchFileId}" />
									</c:import>
                            </td>
                        </tr>
                        </c:if>
                        <tr>
                            <td colspan="2" class="notic_cont">
                               ${result.content }
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">이전글 <span class="dtldirect">&#x25B2;</span></th>
                            <c:if test="${empty prevBbs }">
                            	<td class="tl">이전글이 없습니다.</td>
                            </c:if>
                            <c:if test="${not empty prevBbs }">
	                            <td class="tl"><a href="#none" id="prev" onclick="javascript:selectBoardView('${prevBbs.bbsId }', '${prevBbs.nttId }')" class="a-dtl">${prevBbs.title }</a></td>
	                        </c:if>
                        </tr>
                        <tr>
                            <th scope="row">다음글 <span class="dtldirect">&#x25BC;</span></th>
                            <c:if test="${empty nextBbs }">
                            	<td class="tl">다음글이 없습니다.</td>
                            </c:if>
                            <c:if test="${not empty nextBbs }">
                            	<td class="tl"><a href="#none" id="next" onclick="javascript:selectBoardView('${nextBbs.bbsId }', '${nextBbs.nttId }')" class="a-dtl"> ${nextBbs.title }</a></td>
                            </c:if>
                        </tr>

                    </tbody>
                </table>
            </div>
			<c:if test="${bbsManageVO.answerYn == 'Y'}" >
<!-- 				<div id="BbsCommentArea"> -->
					<c:import url="/front/bbs/cmt/BbsCommentList.do" charEncoding="utf-8" >
						<c:param name="nttId" value="${result.nttId}"/>
					</c:import>
<!-- 				</div> -->
			</c:if> --%>


           <%--  <div class="btnbox">
			<c:choose>
				<c:when test="${empty loginVO.uniqId}">
				</c:when>
				<c:otherwise>
					<c:if test="${loginVO.uniqId == result.ntcrId || loginVO.role == 'ROLE_ADMIN'}">
					<p class="right"><a href="javascript:updateBoardView('${ bbsManageVO.bbsId }','${result.nttId}');" class="btnwrp vibtn" title="수정">수정</a></p>
					<p class="right"><a href="javascript:deleteSubmit('${result.nttId}');" class="btnwrp vibtn" title="삭제">삭제</a></p>
					</c:if>
				</c:otherwise>
			</c:choose>            
                <p class="right"><a href="javascript:listBack('${bbsManageVO.bbsId}');" class="btnwrp vibtn" title="목록">목록</a></p>
            </div>
 --%>



        </div> <!-- sub_cont (e)--> 
</form>

								<%-- <c:param name="bbs_id" value="${searchVO.bid}"/> --%>
