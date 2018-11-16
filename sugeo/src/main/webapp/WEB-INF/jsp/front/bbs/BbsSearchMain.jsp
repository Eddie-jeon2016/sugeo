<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<script type="text/javaScript" language="javascript">
/**
* 페이지 이동 함수
*/
function fnLinkPage(pageNo){
	 	document.listForm.pageIndex.value = pageNo;
	    document.listForm.action = "<c:out value='/front/SearchMain.do'/>";
	    document.listForm.cmd.value="list";
	    document.listForm.submit();
}
/* ********************************************************
* 조회 처리
******************************************************** */
function fnSearch(bbsId){
	document.listForm.selectbbsId.value = bbsId;
	document.listForm.pageIndex.value = 1;
	document.listForm.cmd.value="list";
	document.listForm.action = "<c:out value='/front/SearchMain.do'/>";
  	document.listForm.submit();
}
/* ********************************************************
* 조회 처리
******************************************************** */
function fnSearch1(){
// 	document.listForm.selectbbsId.value = document.listForm.tmpbbsId.value;
	document.listForm.pageIndex.value = 1;
	document.listForm.cmd.value="list";
	document.listForm.action = "<c:out value='/front/SearchMain.do'/>";
  	document.listForm.submit();
}
/* ********************************************************
* 말머리 별 검색
******************************************************** */
function fnCateSearch(id){
	document.listForm.searchCateId.value = id;
	document.listForm.pageIndex.value = 1;
	document.listForm.cmd.value="list";
	document.listForm.action = "<c:out value='/front/bbs/BbsMain.do?menuNo='/>${searchVO.menuNo}";
  	document.listForm.submit();
  	return false;
}
//상세보기 이동
function selectBoardView(menu,bbsid, id){
	document.listForm.selectId.value = id;
	document.listForm.selectbbsId.value = bbsid;
	document.listForm.cmd.value = "view";
	document.listForm.action = "/front/bbs/BbsMain.do?menuNo="+menu;
	document.listForm.submit();
}
function hiddenToggle(){
	$('.hiddenMoreBtn').hide();
	$('.hiddenList').show();
}


/* ********************************************************
* 서버 처리 후 메세지 화면에 보여주기
******************************************************** */
<c:if test="${!empty resultMsg}">alert("<spring:message code="${resultMsg}" />");</c:if>
</script>

							<c:import url="/front/SearchBbsList.do" charEncoding="utf-8" >
								<c:param name="bbsId" value="${bbsVO.bbsId }"/>
							</c:import>
