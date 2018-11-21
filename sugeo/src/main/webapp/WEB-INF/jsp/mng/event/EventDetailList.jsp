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
function fnLinkPage(pageNo) { 
    document.listForm.pageIndex.value = pageNo; 
    document.listForm.action = "<c:out value='/mng/bbs/BbsDetailList.do'/>"; 
    document.listForm.cmd.value = "list"; 
    document.listForm.method = "post"; 
    
    document.listForm.submit(); 
} 

//수정페이지로 이동
function updateBoardView(eventNo){
	document.listForm.eventNo.value = eventNo;
	document.listForm.action = "<c:out value='/mng/event/eventDetailUpdateView.do'/>";
	document.listForm.submit();
}
function writeForm() {
	document.listForm.action = "<c:out value='/mng/event/eventInsertView.do'/>";
	document.listForm.submit();
	
}

$(function(){
	$("#checkAll").click(function() {
		if( $("#checkAll").is(':checked') ){
	      $("input[name=nttId]").prop("checked", true);
	    }else{
	      $("input[name=nttId]").prop("checked", false);
	    }
    });

});

//select view 삭제 시도
function deleteSubmit(ntt_id){
	if(!$("input[name=nttId]").is(":checked")) {
		alert("게시물을 선택하세요.");
		return false;
	}
	var params = "";
	$("input[name=nttId]:checked").each(function(index) {	
		if(index != 0) {params += ","}
		params += $(this).val();
	});
	alert(params);
	 
	document.listForm.action = "<c:out value='/mng/bbs/deleteBbsList.do'/>"; 
	document.listForm.params.value = params;
	document.listForm.submit();
}


</script> 

<form id="listForm" name="listForm" method="post">
    <input type="hidden" name="eventNo" />
    <input type="hidden" name="url" />
    <input type="hidden" name="startDate" />
    <input type="hidden" name="endDate" />
    <input type="hidden" name="eventType" />
    <input type="hidden" name="openYn" />
    <input type="hidden" name="content" />
   <!--  <input type="hidden" name="pageIndex" /> -->


    <div class="usrposit"><!-- usrposit (s) -->
        <h3>이벤트 등록 및 관리</h3>
        <!-- <ul>
            <li>이벤트 등록 및 관리(텍스트임)</li>
        </ul> -->
    </div>

    <div id="maininner"><!-- maininner (s)-->
        <div class="tbl_wrp">
            <table class="tbl_lst">
                <caption>게시판 관리</caption>
                <colgroup>
                  	<col style="width: 7%;">
                    <col style="width: 7%;">
                    <col style="width: auto%;">
                    <col style="width: 30%;">
                    <col style="width: 7%;">
                    <col style="width: 7%;">
                    <col style="width: 10%;">
                </colgroup>
                <thead>
                <tr>
               		<!-- <th scope="col"><input type="checkbox" id="checkAll" title="전체선택"></th> -->
                    <th scope="col">번호</th>
                    <th scope="col">구분</th>
                    <th scope="col">제목</th>                 
                    <th scope="col">접수기간</th>
                    <th scope="col">접수상태</th>
                    <th scope="col">공개여부</th>
                    <th scope="col">등록일</th>
                </tr>
                </thead>
                 <tbody>
                 
                 <c:if test="${empty resultList}">
                 	<td colspan="7">등록된 이벤트가 없습니다.</td>
                 </c:if>
                 <c:if test="${not empty resultList}">
               		<c:forEach var="result" items="${resultList}" varStatus="status">
                    		<tr>
                    			<%-- <td><input type="checkbox" title="선택" name="eventNo" value="${result.eventNo }">${result.eventNo }</td> --%>
                      		  <td>${paginationInfo.totalRecordCount - paginationInfo.firstRecordIndex - status.index }</td>
                      		  <td>${result.eventType}</td>
                       		 <td class="tl"><a href="javascript:updateBoardView('${result.eventNo}');">${result.title}</a></td>                  
                       		 <td>${result.startDate} ~ ${result.endDate}</td>
                       		 <td>${result.eventStmt}</td>
                       		 <td>${result.openYn}</td>
                      		  <td>${result.regDate}</td>
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
                        <button type="button" onclick="javascript:writeForm();"
                                class="abtn indbtn" title="등록">등록
                        </button>   
                    </li>
                    <!-- <li>
                    	<button type="button" onclick="javascript:deleteSubmit('');"
                                class="btntg indbtn" title="삭제">삭제
                        </button>   
                    </li> -->
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