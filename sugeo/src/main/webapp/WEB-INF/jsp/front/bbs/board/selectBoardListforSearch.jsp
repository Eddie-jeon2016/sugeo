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
<h2 class="sub_tit">${title}</h2>
<form id="listForm" name="listForm" method="post">
<input type="hidden" name="cmd" />
<input type="hidden" name="selectId" value="" />
<input type="hidden" name="selectbbsId" value="" />
<input type="hidden" name="searchCateId" value="${searchVO.searchCateId}"/>
<input type="hidden" name="searchUseYn" value="Y">
<script type="text/javascript">
history.pushState(null, null, location.href);
window.onpopstate = function(event) {
	if(location.href.indexOf("#none") == -1) {
		location.replace(document.referrer);
	}

}
$(document).ready(function(){

	$('.content').dotdotdot();
});
</script>
<form id="tempForm2" name="tempForm2" method="post">
	<input type="hidden" name="searchCondition" value="${searchVO.searchCondition }">
	<input type="hidden" name="searchKeyword" value="${searchVO.searchKeyword }">
	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }">
</form>

        <div class="sub_cont"> <!-- sub_cont (s)-->


            <div class="totalSch">
                <ul class="enter-basic">
                    <li class="txtype"><label class="lbhidden">통합검색 입력창</label><input type="text" name="searchKeyword" value="${searchVO.searchKeyword}" placeholder="검색어를 입력해주세요" onKeyDown="if(event.keyCode==13){javascript:fnSearch1();}"></li>
                    <li class="btn"><a href="javascript:fnSearch1();"><span>검색<img src="../../images/sub/srch.png" alt="검색"></span></a></li>
                </ul>
                <ul class="enter-object">
                    <li class="tit">검색대상 :</li>
                    <li><label><input type="radio" name="searchCondition" value="1" <c:if test="${searchVO.searchCondition eq 1 || searchVO.searchCondition == ''}">checked="checked"</c:if>  >전체</label></li>
                    <li><label><input type="radio" name="searchCondition" value="2" <c:if test="${searchVO.searchCondition eq 2 }">checked="checked"</c:if>>제목</label></li>
                    <li><label><input type="radio" name="searchCondition" value="4" <c:if test="${searchVO.searchCondition eq 4 }">checked="checked"</c:if>>내용</label></li>
                </ul>
            </div>


            <p class="totalSch-num">
			<c:if test="${searchVO.searchKeyword ne '' }">
                <span class="viColor fwB">"${searchVO.searchKeyword}"</span>에 대한 검색 결과는 총 <span class="blColor fwB">${totCnt}</span>건 입니다.
            </c:if>
            </p>
            <div class="totalSch-result">
                <h4>
                <c:if test="${searchTy eq 'TT' }">${searchTyNm}</c:if>
                <c:if test="${searchTy eq 'TN' }">
						<c:if test="${searchTyNm eq 1 }">아카데미 소식</c:if><c:if test="${searchTyNm eq 3 }">영상갤러리</c:if>
						<c:if test="${searchTyNm eq 4 }">동문회 소식</c:if>
                </c:if>
                 (검색 수: ${totCnt}건)</h4>
                <ul>
                <c:forEach items="${resultList }" var="resultList" varStatus="status">
                    <li <c:if test="${status.count >= 4 }">class="hiddenList" style="display:none;"</c:if> >
                        <p class="locati">HOME &gt; 소식 &gt;
                        	${resultList.bbsName }
                        </p>
                        <p class="tit"><span class="tittext"><a href="javascript:selectBoardView('${resultList.smenuNo }','${resultList.bbsId}', '${resultList.nttId}')">${resultList.title }</a></span><span class="date">${resultList.frstDate }</span></p>
                        <div class="content" style="overflow: hidden; height:80px; margin-top:10px;">
                        <p class="content">${resultList.content }</p>
                        </div>
                    </li>
                </c:forEach>
                	<c:if test="${totCnt >= 4 }">
					<li class="hiddenMoreBtn">
                        <p class="plus"><a href="javascript:hiddenToggle();" title="더보기">더보기 +</a></p>
                    </li>
                	</c:if>
                </ul>
            </div>

            <div class="page_num <c:if test="${totCnt >= 4 }">hiddenList</c:if>" <c:if test="${totCnt >= 4 }">style="display:none;"</c:if>>
	            <span class="num">
	            	<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="fnLinkPage"	/>
	            </span>
            </div>

        </div> <!-- sub_cont (e)-->

<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form>