<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader("Expires",0);
%>

<div class="usrposit"><!-- usrposit (s) -->
    <h3>팝업 관리</h3>
    <!-- <ul>
        <li>사이트 관리</li>
        <li>팝업 관리</li>
    </ul> -->
</div>

<div id="maininner"><!-- maininner (s)-->
<div class="tbl_wrp">
	<table class="tbl_lst">
		<caption>팝업 관리</caption>
		<colgroup>
			<col style="width: 8%;">
			<col style="width: 20%;">
			 <col style="width: auto%;">
			<col style="width: 20%;">
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>팝업명</th>
				<th>게시기간</th>
				<th>닫기유형</th>
				
			</tr>
		</thead>
		<tbody>

			<c:if test="${empty popupList }">
				<tr>
					<td colspan="8">팝업 목록이 없습니다.</td>
				<tr>
			</c:if>
			<c:if test="${not empty popupList }">
				<c:forEach var="result" items="${popupList}" varStatus="varStatus">
					<tr>
						<%-- <td>${fn:length(popupList)- varStatus.index }</td> --%>
						<td>${paginationInfo.totalRecordCount - paginationInfo.firstRecordIndex - varStatus.index }</td>
						<td><a href="javascript:popupUpdateView('${result.popupId}','${paginationInfo.currentPageNo}')">${result.popupName }</a></td>
						<td>${result.startDate } ~ ${result.endDate }</td>
						<c:if test="${result.closeType eq 'T' }">
							<td>오늘 하루 열지 않음</td>
						</c:if>
						<c:if test="${result.closeType eq 'W' }">
							<td>일주일동안 열지 않음</td>
						</c:if>
						<c:if test="${result.closeType eq 'F' }">
							<td>다시 열지 않음</td>
						</c:if>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
	<form name="popupFrm" id="popupFrm" method="post">
		<input type="hidden" name="popupId">
      	<input type="hidden" id = "pageIndex" name = "pageIndex" value="${paginationInfo.currentPageNo}">
	</form>
	<div class="tblbottombar clfix">
	<div class="rightpot">
                <ul class="clfix">
                    <li>
                        <button type="button" onclick="javascript:popupRegView()"
                                class="abtn indbtn" title="등록">등록
                        </button>   
                    </li>
                   
                </ul>
            </div>
	<!-- 	<span><a href="javascript:popupRegView();" style="margin-left: 300px;">팝업추가</a></span> -->
	</div>
</div>
	 <div class="page_num">
         <span class="num">
         	<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="fnLinkPage"	/>
         </span>
    </div> 


	<script type="text/javascript">
		function popupRegView() {
			document.popupFrm.action = '/mng/pop/popupRegView.do';
			document.popupFrm.submit();
		}
		
		function fnLinkPage(pageNo) {
		    document.listForm.pageIndex.value = pageNo;		    
		    document.listForm.method = "post";
		    document.listForm.action = "<c:out value='/mng/pop/popupList.do'/>";
		    document.listForm.cmd.value = "list";
		    document.listForm.submit();
		}
		
		function popupUpdateView(popId,pageIndex) {

			document.popupFrm.popupId.value = popId;
			document.popupFrm.pageIndex.value = pageIndex;
			document.popupFrm.action = '/mng/pop/popupUpdateView.do';
			document.popupFrm.submit();
		}
		
	</script>