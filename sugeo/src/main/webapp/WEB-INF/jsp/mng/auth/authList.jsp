<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader("Expires",0);
%>

<script>
$(function(){
	
	$("#searchKeyword").keypress(function(e){
		if(e.keyCode == 13) {
			goSearch();
		}
	});
});
function goUpdateView(uniqId) {
	document.listFrm.action = '/mng/auth/authorUserUpdateView.do';
	document.listFrm.uniqId.value = uniqId; 
	document.listFrm.submit(); 
}

function fnLinkPage(pageIndex){
 	document.listFrm.pageIndex.value = pageIndex;
    document.listFrm.action = "<c:out value='/mng/auth/selectAuthorUserList.do'/>";
    document.listFrm.submit();
}

function goInsertView() {
	document.listFrm.action = "<c:out value='/mng/auth/authorUserRegView.do'/>";
	document.listFrm.submit();
	
}
</script>

<div class="usrposit"><!-- usrposit (s) -->
    <h3>관리자  관리</h3>
    <!-- <ul>
        <li>권한 관리</li>
        <li>관리자 권한  관리</li>
    </ul> -->
</div>

<div id="maininner"><!-- maininner (s)-->
<form name="listFrm" method="post">
	<input type="hidden" name="uniqId">
	<input type="hidden" name="pageIndex" value="${comVO.pageIndex}">
<div class="tbl_wrp">
	<table class="tbl_lst">
		<caption>관리자 권한 관리</caption>
		<colgroup>
			<col width="8%" />
			<col width="auto%"/>
			<col width="auto%"/>
			<col width="18%"/>
			<col width="18%"/>
		</colgroup>
		<thead>
			<tr>
				<th>NO</th>
				<th>아이디</th>
				<th>이름</th>
				<th>최근 로그인</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${empty resultList }">
				<tr>
					<td colspan="6">관리자 권한 목록이 없습니다.</td>
				<tr>
			</c:if>
			<c:if test="${not empty resultList }">
				<c:forEach var="result" items="${resultList}" varStatus="varStatus">
					<tr>
						<td><c:out value="${paginationInfo.totalRecordCount - (comVO.pageUnit * (paginationInfo.currentPageNo-1 )) - (varStatus.count-1) }"/></td>
						<td><a href="#none" onclick="javascript:goUpdateView('${result.uniqId}')">${result.userId }</a></td>
						<td>${result.userName }</td>
						<td>${result.logDate }</td>
						<td>${result.regDate }</td>
						
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>

         <div class="tblbottombar clfix"><!-- tblbottombar (s)-->
            <div class="rightpot">
                <ul class="clfix">
                    <li>
                        <button type="button" onclick="javascript:goInsertView();"
                                class="abtn indbtn" title="등록">등록
                        </button>   
                    </li>
                   
                </ul>
            </div>
        </div>

</form>
	<div class="page_num">
		<span class="num">
    	<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="fnLinkPage"	/>
    	</span>
    </div>
	
</div>    
