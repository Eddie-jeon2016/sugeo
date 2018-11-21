<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader("Expires",0);
%>

<script> 

$(document).ready(function(){
	$('#searchGubun').on('change',function(){
		goSearch();
	});
});


//페이지 번호 클릭시
function fnLinkPage(pageNo) { 
    document.listForm.pageIndex.value = pageNo; 
    document.listForm.action = "<c:out value='/mng/bbs/faqMng/faqMngList.do'/>"; 
    document.listForm.cmd.value = "list"; 
    document.listForm.submit(); 
} 

//글등록 페이지로 이동
function PressContentInsertView() {
	document.listForm.action = "<c:out value='/mng/bbs/faqMng/faqMngInsertView.do'/>";
	document.listForm.submit();
}


//수정페이지로 이동
function updateBoardView(faqId){	
	document.listForm.select_faqId.value = faqId;
	document.listForm.action = "<c:out value='/mng/bbs/faqMng/FaqMngUpdateView.do'/>";
	document.listForm.submit();
}


//검색
function goSearch() {
	document.listForm.action = "<c:out value='/mng/bbs/faqMng/faqMngList.do'/>";
	document.listForm.pageIndex.value = 1;
	document.listForm.submit();
}

$(function(){
	$("input[name=searchKeyword]").keypress(function(e){
		if(e.keyCode == 13) {
			goSearch();
		}
	});
});



</script> 

<form id="listForm" name="listForm" method="post">
    <input type="hidden" name="cmd"/>
    <input type="hidden" name="select_faqId" />
    <input type="hidden" name="params" />

    <div class="usrposit"><!-- usrposit (s) -->
        <h3>FAQ 관리</h3>
    </div>
    <div id="maininner"><!-- maininner (s)-->
        <div class="tbl_wrp">
            <table class="tbl_lst">
            
            <div class="totalSch">
			    
			      <dd>
			      <select name="searchGubun" id="searchGubun">
			      	<option value=""  <c:if test="${searchVO.searchGubun eq '' }">selected="selected"</c:if>>분류전체</option>
			      	<option value="FAQ0001" <c:if test="${searchVO.searchGubun eq 'FAQ0001' }">selected="selected"</c:if>>개인정보</option>
			      	<option value="FAQ0002" <c:if test="${searchVO.searchGubun eq 'FAQ0002' }">selected="selected"</c:if>>이벤트관련</option>
			      	<option value="FAQ0003" <c:if test="${searchVO.searchGubun eq 'FAQ0003' }">selected="selected"</c:if>>일반</option>
			      </select>
			          <select name="searchCondition">
			              <option value="1" <c:if test="${searchVO.searchCondition eq '1' }">selected="selected"</c:if>>전체</option>
			              <option value="2" <c:if test="${searchVO.searchCondition eq '2' }">selected="selected"</c:if>>제목</option>
			              <option value="3" <c:if test="${searchVO.searchCondition eq '3' }">selected="selected"</c:if>>내용</option>
			          </select>
			      <input type="text" class="text_base" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword }">
			      <a title="검색" href="#" onclick="javascript:goSearch();" class="defaultBtn2" ><img src="/images/common/srchbtn.gif" alt="right"></a></dd>
			   
		    </div>
            
            
                <caption>게시판 관리</caption>
                <colgroup>
                    <col style="width: 8%;">
                    <col style="width: 15%;">
                    <col style="width: auto;">
                    <col style="width: 15%;">
                </colgroup>
                <thead>
                <tr>
                    <th scope="col">번호</th>
                    <th scope="col">분류</th>                 
                    <th scope="col">제목</th>
                    <th scope="col">작성일</th>
                </tr>
                </thead>
                <tbody>
	                <c:if test="${empty resultList }">
						<tr>
							<td colspan="4">해당 게시물이 없습니다.</td>
						<tr>
					</c:if>
		            <c:if test="${not empty resultList }">   
		                <c:forEach var="result" items="${resultList}" varStatus="status">
		                    <tr>
		                        <td>${paginationInfo.totalRecordCount - paginationInfo.firstRecordIndex - status.index }</td>
		                        <td>${result.typeName}</td>
		                        <td class="tl"><a href="javascript:updateBoardView('${result.faqId}');">${result.quest}</a>
												<%-- 	<c:if test="${result.cmtCount != '0'}">${result.cmtCount}</c:if> --%>
											</a></td>                  
		                        <td>${fn:substring(result.regDate,0,10)}</td>
		                    </tr>
		                </c:forEach>
		            </c:if>
                </tbody>
            </table>
        </div>

        <%--<c:if test="${bbsManageVO.bbsTyCode ne 'BBSTYCODE4'}">--%>
        <div class="tblbottombar clfix"><!-- tblbottombar (s)-->
            <div class="rightpot">
                <ul class="clfix">
                    <li>
                        <button type="button" onclick="javascript:PressContentInsertView();" 
                                class="abtn indbtn" title="등록">등록
                        </button>   
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <%--</c:if>--%>
    <div class="page_num">
         <span class="num">
         	<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="fnLinkPage"	/>
         </span>
    </div>
    <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form>