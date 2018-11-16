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
    document.listForm.submit(); 
 
} 

// 이벤트 공지 페이지 이동
function fnLinkPage2(pageNo) { 
    	document.listForm.pageIndex.value = pageNo;  
    	document.listForm.action = "<c:out value='/mng/notice/BbsDetailList.do'/>";
    	document.listForm.cmd.value = "list"; 
    	document.listForm.submit(); 
    
} 


//수정페이지로 이동
function updateNoticeView(bbsid, id){
	document.listForm.select_nttId.value = id;
	document.listForm.cmd.value = "update";
	document.listForm.action = "<c:out value='/mng/notice/BbsSelectDetailUpdtView.do'/>"; 
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

function writeNoticeForm(bbsId) {
	document.listForm.action = "<c:out value='/mng/notice/eventNoticeInsertView.do'/>"; 
	document.listForm.submit();
	return false;
}

function goSearch() {
	var menuNo = $('#menuNo').val();
	document.listForm.pageIndex.value = 1;
	//이벤트공지에서 검색했을 때
	if(menuNo == "1000008") {
		document.listForm.action = "<c:out value='/mng/notice/BbsDetailList.do'/>"; 
		document.listForm.submit();
	}
	// 관련글에서 검색했을 때
	if(menuNo == "1000001") {
		document.listForm2.action = "<c:out value='/mng/bbs/relatedContent/BbsDetailList.do'/>"; 
		document.listForm2.submit();
	}
}


// 관련글 페이지 이동
function fnLinkPage3(pageNo) { 
    	document.listForm2.pageIndex.value = pageNo; 
    	document.listForm2.action = "<c:out value='/mng/bbs/relatedContent/BbsDetailList.do'/>"; 
    	document.listForm2.cmd.value = "list"; 
    	document.listForm2.submit(); 
    
} 

//관련글 등록페이지로 이동
function writeRelatedContent(bbsId) {
	document.listForm2.action = "<c:out value='/mng/bbs/relatedContent/relatedContentInsertView.do'/>"; 
	document.listForm2.submit();
	return false;
}

//관련글 수정페이지로 이동
function updateRelatedContentView(bbsid, id){
	document.listForm2.select_nttId.value = id;
	document.listForm2.cmd.value = "update";
	document.listForm2.action = "<c:out value='/mng/bbs/relatedContent/BbsSelectDetailUpdtView.do'/>"; 
	document.listForm2.submit();
}

//관련글 삭제
function deleteRelatedContent(nttId){
	if(!$("input[name=nttId]").is(":checked")) {
		alert("관련글이 체크되지 않았습니다.");
		return false;
	}else{
		if(confirm("선택한 관련글을 삭제하시겠습니까?")){
			var params = "";
			$("input[name=nttId]:checked").each(function(index) {	
				if(index != 0) {params += ","}
				params += $(this).val();
			});
			document.listForm2.action = "<c:out value='/mng/bbs/deleteBbsList.do'/>"; 
			document.listForm2.params.value = params;
			document.listForm2.submit();		
		}
	}
}

$(function(){
	$("input[name=searchKeyword]").keypress(function(e){
		if(e.keyCode == 13) {
			goSearch();
		}
	});
});




</script> 
<c:choose>
	<c:when test="${type eq 'eventNotice'}">
		<form id="listForm" name="listForm" method="post">
	    <input type="hidden" name="cmd"/>
	    <input type="hidden" name="bbsId" value="${searchVO.bbsId}"/>
	    <input type="hidden" name = "menuNo" id="menuNo" value="${searchVO.menuNo}"/>
	    <input type="hidden" name="select_nttId" />
	    <input type="hidden" name="params" />
	
	
	    <div class="usrposit"><!-- usrposit (s) -->
	        <h3>${bbsManageVO.bbsName}</h3>
	        <%-- <ul>
	            <li>${bbsManageVO.bbsName} 관리</li>
	        </ul> --%>
	    </div>
	
	    <div id="maininner"><!-- maininner (s)-->
	        <div class="tbl_wrp">
	            <table class="tbl_lst">
	             <div class="totalSch">
				     <dl class="clfix">
				      <dd style="text-align: right;">
				          <span style="font-size: 14px">제목</span>	
				      <input type="text" class="text_base" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword }">
				      <a title="검색" href="#" onclick="javascript:goSearch();" class="defaultBtn2" ><img src="/images/common/srchbtn.gif" alt="right"></a></dd>
				    </dl>
			    </div>
	                <caption>게시판 관리</caption>
	                <colgroup>
	                  	
	                    <col style="width: 7%;">
	                    <col style="width: auto;">
	                    <col style="width: 8%;">
	                    <col style="width: 15%;">
	                </colgroup>
	                <thead>
	                <tr>
	               		
	                    <th scope="col">번호</th>
	                    <th scope="col">제목</th>                 
	                    <th scope="col">등록일</th>
	                    <th scope="col">상단노출</th>
	                </tr>
	                </thead>
	                <tbody>
							<c:if test="${empty resultList}">
								<tr>
									<td colspan="4" align="center">게시글이 없습니다.</td>
								</tr>
							</c:if>
							<c:if test="${!empty resultList}">

								<c:forEach var="result" items="${resultList}" varStatus="status">
									<tr>

										<td>${paginationInfo.totalRecordCount - paginationInfo.firstRecordIndex - status.index}</td>
										<td class="tl"><a
											href="javascript:updateNoticeView('${result.bbsId}', '${result.nttId}');">${result.title}</a>
											<%-- 	<c:if test="${result.cmtCount != '0'}">${result.cmtCount}</c:if> --%>
											</a></td>
										<td>${result.frstDate}</td>
										<td>${result.noticeYn }</td>
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
	                        <button type="button" onclick="javascript:writeNoticeForm('${bbsManageVO.bbsId}');"
	                                class="abtn indbtn" title="등록">등록
	                        </button>   
	                    </li>
	                 <%--    <li>
	                    	<button type="button" onclick="javascript:deleteSubmit('${result.nttId}');"
	                                class="abtn indbtn" title="삭제">삭제
	                        </button>   
	                    </li> --%>
	                </ul>
	            </div>
	        </div>
	    </div>
	    <%--</c:if>--%>
	    <div class="page_num">
	         <span class="num">
	         	<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="fnLinkPage2"	/>
	         </span>
	    </div>
	    <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	</form>
	</c:when>
	
	<c:when test="${type eq 'relatedContent'}">
		<form id="listForm2" name="listForm2" method="post">
		    <input type="hidden" name="cmd"/>
		    <input type="hidden" name="bbsId" value="${searchVO.bbsId}"/>
		    <input type="hidden" name = "menuNo" id="menuNo" value="${searchVO.menuNo}"/>
		    <input type="hidden" name="select_nttId" />
		    <input type="hidden" name="params" />
		
		
		    <div class="usrposit"><!-- usrposit (s) -->
		        <h3>${bbsManageVO.bbsName} 관리</h3>
		        <ul>
		          <%--   <li>${bbsManageVO.bbsName} 관리</li> --%>
		        </ul>
		    </div>
		
		    <div id="maininner"><!-- maininner (s)-->
		        <div class="tbl_wrp">
		            <table class="tbl_lst">

		            <div class="totalSch">
					     <dl class="clfix">
					      <dd style="text-align: right;">		
					          <select name="searchCondition">
					              <option value="1" <c:if test="${searchVO.searchCondition eq '1' }">selected="selected"</c:if>>전체</option>
					              <option value="2" <c:if test="${searchVO.searchCondition eq '2' }">selected="selected"</c:if>>제목</option>
					              <option value="3" <c:if test="${searchVO.searchCondition eq '3' }">selected="selected"</c:if>>내용</option>
					          </select>
					      <input type="text" class="text_base" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword }">
					      <a title="검색" href="#" onclick="javascript:goSearch();" class="defaultBtn3" ><img src="/images/common/srchbtn.gif" alt="right"></a></dd>
					    </dl>
				    </div>
		                <caption>게시판 관리</caption>
		                <colgroup>
		                  	
		                    <col style="width: 4%;">
		                    <col style="width: 8%;">
		                    <col style="width: auto;">
		                    <col style="width: 15%;">
		                </colgroup>
		                <thead>
		                <tr>
		               		<th scope="col"><input type="checkbox" id="checkAll" title="전체선택"></th>
		                    <th scope="col">번호</th>
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
				                    	<td><input type="checkbox" title="선택" name="nttId" value="${result.nttId}"></td>
				                        <td>${paginationInfo.totalRecordCount - paginationInfo.firstRecordIndex - status.index}</td>
				                        <td class="tl"><a href="javascript:updateRelatedContentView('${result.bbsId}', '${result.nttId}');">${result.title}</a>
														<%-- 	<c:if test="${result.cmtCount != '0'}">${result.cmtCount}</c:if> --%>
													</a></td>                  
				                        <td>${result.frstDate}</td>
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
		                        <button type="button" onclick="javascript:writeRelatedContent('${bbsManageVO.bbsId}');"
		                                class="abtn indbtn" title="등록">등록
		                        </button>   
		                    </li>
		                    <li>
		                    	<button type="button" onclick="javascript:deleteRelatedContent('${result.nttId}');"
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
		         	<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="fnLinkPage3"	/>
		         </span>
		    </div>
		    <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
		</form>
	
	</c:when>
	
	
	
	
							
	<c:otherwise>		
		<form id="listForm" name="listForm" method="post">
	    <input type="hidden" name="cmd" value="update"/>
	    <input type="hidden" name="bbsId" value="${searchVO.bbsId}"/>
	    <input type="hidden" name = "menuNo" value="${searchVO.menuNo}"/>
	    <input type="hidden" name="select_nttId" />
	    <input type="hidden" name="params" />
	
	
	    <div class="usrposit"><!-- usrposit (s) -->
	        <h3>${bbsManageVO.bbsName} 관리</h3>
	        <ul>
	            <li>${bbsManageVO.bbsName} 관리</li>
	        </ul>
	    </div>
	
	    <div id="maininner"><!-- maininner (s)-->
	        <div class="tbl_wrp">
	            <table class="tbl_lst">
	                <caption>게시판 관리</caption>
	                <colgroup>
	                  	<col style="width: 3%;">
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
	                    <th scope="col">조회수</th>
	                    <th scope="col">등록일</th>
	                </tr>
	                </thead>
	                <tbody>
	                <c:forEach var="result" items="${resultList}" varStatus="status">
	                    <tr>
	                    	<td><input type="checkbox" title="선택" name="nttId" value="${result.nttId }">${result.nttId }</td>
	                        <td>${status.index+1}</td>
	                        <td class="tl"><a href="javascript:updateBoardView('${result.bbsId}', '${result.nttId}');">${result.title}</a>
											<%-- 	<c:if test="${result.cmtCount != '0'}">${result.cmtCount}</c:if> --%>
										</a></td>                  
	                        <td>${result.rdcnt}</td>
	                        <td>${result.frstDate}</td>
	                    </tr>
	                </c:forEach>
	                </tbody>
	            </table>
	        </div>
	
	        <%--<c:if test="${bbsManageVO.bbsTyCode ne 'BBSTYCODE4'}">--%>
	        <div class="tblbottombar clfix"><!-- tblbottombar (s)-->
	            <div class="rightpot">
	                <ul class="clfix">
	                    <li>
	                        <button type="button" onclick="javascript:writeForm('${bbsManageVO.bbsId}');"
	                                class="abtn indbtn" title="등록">등록
	                        </button>   
	                    </li>
	                    <li>
	                    	<button type="button" onclick="javascript:deleteSubmit('${result.nttId}');"
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
	</c:otherwise>

</c:choose>
