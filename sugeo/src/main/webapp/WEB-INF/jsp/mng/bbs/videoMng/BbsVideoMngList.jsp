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

//페이지 번호 클릭시
function fnLinkPage(pageNo) { 
    document.listForm.pageIndex.value = pageNo; 
    document.listForm.action = "<c:out value='/mng/bbs/videoMng/videoMngList.do'/>"; 
    document.listForm.cmd.value = "list"; 
    document.listForm.submit(); 
} 

//글등록 페이지로 이동
function VideoMngInsertView() {
	document.listForm.action = "<c:out value='/mng/bbs/videoMng/insertVideoMngView.do'/>";
	document.listForm.submit();
}


//수정페이지로 이동
function updateBoardView(videoId){
	document.listForm.select_videoId.value = videoId;
	document.listForm.action = "<c:out value='/mng/bbs/videoMng/VIdeoMngUpdtView.do'/>";
	document.listForm.submit();
}


//체크박스 전체선택
$(function(){
	$("#checkAll").click(function() {
		if( $("#checkAll").is(':checked') ){
	      $("input[name=videoId]").prop("checked", true);
	    }else{
	      $("input[name=videoId]").prop("checked", false);
	    }
    });

});

//글목록에서 삭제
function deleteSubmit(videoId){
	if(!$("input[name=videoId]").is(":checked")) {
		alert("동영상이 선택되지 않았습니다.");
		return false;
	}else{
		if(confirm("선택한 동영상을 삭제하시겠습니까?")){
			var params = "";
			$("input[name=videoId]:checked").each(function(index) {	
				if(index != 0) {params += ","}
				params += $(this).val();
			});
			 
			document.listForm.action = "<c:out value='/mng/bbs/videoMng/deleteVideoMng.do'/>"; 
			document.listForm.params.value = params;
			document.listForm.submit();			
		}
	}
}

//검색
function goSearch() {
	document.listForm.action = "<c:out value='/mng/bbs/videoMng/videoMngList.do'/>";
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
    <input type="hidden" name="select_videoId" />
    <input type="hidden" name="params" />

    <div class="usrposit"><!-- usrposit (s) -->
        <h3>동영상 관리</h3>
    </div>
    <div id="maininner"><!-- maininner (s)-->
        <div class="tbl_wrp">
            <table class="tbl_lst">
            
            <div class="totalSch">
			     <dl class="clfix">
			      <dd style="text-align: right;">
			          <span style="font-size: 14px">제목</span>	
			      <input  type="text" class="text_base" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword }" >
			      <a title="검색" href="#" onclick="javascript:goSearch();" class="defaultBtn2"  ><img src="/images/common/srchbtn.gif" alt="right"></a></dd>
			    </dl> 
		    </div>
            
            
                <caption>게시판 관리</caption>
                <colgroup>
                  	<col style="width: 4%;">
                    <col style="width: 8%;">
                    <col style="width: auto;">
                    <col style="width: 8%;">
                    <col style="width: 15%;">
                </colgroup>
                <thead>
                <tr>
               		<th scope="col"><input type="checkbox" id="checkAll" title="전체선택"></th>
                    <th scope="col">번호</th>
                    <th scope="col">제목</th>                 
                    <th scope="col">작성일</th>
                    <th scope="col">노출여부</th>
                </tr>
                </thead>
                <tbody>
	                <c:if test="${empty resultList }">
						<tr>
							<td colspan="5">해당 게시물이 없습니다.</td>
						<tr>
					</c:if>
		            <c:if test="${not empty resultList }">   
		                <c:forEach var="result" items="${resultList}" varStatus="status">
		                    <tr>
		                    	<td><input type="checkbox" title="선택" name="videoId" value="${result.videoId }"></td>
		                        <td>${paginationInfo.totalRecordCount - paginationInfo.firstRecordIndex - status.index }</td>
		                        <td class="tl"><a href="javascript:updateBoardView('${result.videoId}');">${result.title}</a>
												<%-- 	<c:if test="${result.cmtCount != '0'}">${result.cmtCount}</c:if> --%>
											</a></td>                  
		                        <td>${fn:substring(result.regDate,0,10)}</td>
		                        <td>${result.noticeYn}</td>
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
                        <button type="button" onclick="javascript:VideoMngInsertView();" 
                                class="abtn indbtn" title="등록">등록
                        </button>   
                    </li>
                    <li>
                    	<button type="button" onclick="javascript:deleteSubmit('${result.videoId}');"
                                class="abtn indbtn" title="삭제">삭제 
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