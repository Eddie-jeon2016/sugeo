<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java" %>
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

<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="/js/foundation/foundation.min.css">
<script src="/js/foundation/foundation.min.js"></script>
<link href="/js/foundation/foundation-datepicker.min.css"
	rel="stylesheet">
<script src="/js/foundation/foundation-datepicker.min.js"></script>
<link href="//cdnjs.cloudflare.com/ajax/libs/foundicons/3.0.0/foundation-icons.css" rel="stylesheet">
<script src="/js/foundation/locale/foundation-datepicker.ko.js"></script>


<div class="usrposit"><!-- usrposit (s) -->
    <h3>작업 로그</h3>
   <!--  <ul>
        <li>권한 관리</li>
        <li>작업 로그</li>
    </ul> -->
</div>

<div id="maininner"><!-- maininner (s)-->
<form name="listFrm" id="listFrm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="logId">
	<input name="pageUnit" type="hidden" value="${comVO.pageUnit}">
	<div class="totalSch">
	   <dl class="clfix">
	      <dd>
	      	작업 일시 :
	      	 <input type="text" name="searchStartDate" id="searchStartDate" value="${comVO.searchStartDate}" class="searchStartDate"> ~ <input type="text" name="searchEndDate" id="searchEndDate" value="${comVO.searchEndDate}" class="searchEndDate">
	      
	      	검색 : 
	          <select name="searchCondition">
	              <option value="1" <c:if test="${comVO.searchCondition eq '1' }">selected="selected"</c:if>>전체</option>
	              <option value="2" <c:if test="${comVO.searchCondition eq '2' }">selected="selected"</c:if>>접속ID</option>
	              <option value="3" <c:if test="${comVO.searchCondition eq '3' }">selected="selected"</c:if>>접속IP</option>
	          </select>
	      <input type="text" class="text_base" id="searchKeyword" name="searchKeyword" value="${comVO.searchKeyword }">
	      <a title="검색" href="#" onclick="javascript:goSearch();" class="defaultBtn2" ><img src="/images/common/srchbtn.gif" alt="right"></a></dd>
	    </dl>
    </div>
    
    <div class="excel">
    	<a href="/mng/auth/workList/selectWorkLogListExcel.do" >엑셀다운로드</a>
    	<select id="pageUnit" >
                        <option value="10" <c:if test="${comVO.pageUnit==10}">selected="selected"</c:if>>10개</option>
                        <option value="20" <c:if test="${comVO.pageUnit==20}">selected="selected"</c:if>>20개</option>
                        <option value="30" <c:if test="${comVO.pageUnit==30}">selected="selected"</c:if>>30개</option>
         </select>
    	
    </div>
    
<div class="tbl_wrp">
	<table class="tbl_lst">
		<caption>작업로그</caption>
		<colgroup>
			<col style="width:8%"/>
			<col style="width:20%"/>
			<col style="width:20%"/>
			<col style="width:35%"/>
			<col style="width:35%"/>
			<col style="width:20%"/>

		</colgroup>
		<thead>
			<tr>
				<th>No</th>
				<th>작업 ID</th>
				<th>접속 IP</th>
				<th>작업 URL</th>
				<th>작업내용</th>
				<th>작업일시</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${empty resultList }">
				<tr>
					<td colspan="6">작업로그 이력이 없습니다.</td>
				<tr>
			</c:if>
			<c:if test="${not empty resultList }">
				<c:forEach var="result" items="${resultList}" varStatus="varStatus">
					<tr>
						<td><c:out value="${paginationInfo.totalRecordCount - (comVO.pageUnit * (paginationInfo.currentPageNo-1 )) - (varStatus.count-1) }"/>
						</td>
						<td><a href="javascript:updateBoardView('${result.logId}');">${result.userId }</a></td>
						<td>${result.accessIp }</td>
						<td>${result.url }</td>
						<td>${result.content}</td>
						<td>${result.logDate}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
	 <div class="page_num">
         <span class="num">
         	<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="fnLinkPage"/>
         </span>
    </div>
    <input name="pageIndex" type="hidden" value="<c:out value='${comVO.pageIndex}'/>"/>
</form>
</div>

<script type="text/javascript">

var nowTemp = new Date();
var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp
		.getDate(), 0, 0, 0, 0);
var checkin = $('#searchStartDate').fdatepicker({
	format : 'yyyy-mm-dd',
	/* pickTime : true, */
	startView : 2,
	language : 'ko',
	onRender : function(date) {
	}
}).on('changeDate', function(ev) {
	if (ev.date.valueOf() > checkout.date.valueOf()) {
		var newDate = new Date(ev.date)
		newDate.setDate(newDate.getDate() + 1);
		checkout.update(newDate);
	}
}).data('datepicker');
var checkout = $('#searchEndDate').fdatepicker({
	format : 'yyyy-mm-dd',
	/* pickTime : true, */
	startView : 2,
	language : 'ko',
	onRender : function(date) {
		return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
	}
}).on('changeDate', function(ev) {
}).data('datepicker');




$(function(){
	$("#searchKeyword").keypress(function(e){
		if(e.keyCode == 13) {
			goSearch();
		}
	});
});
function goSearch() {
 
	document.listFrm.action = '/mng/auth/workList/selectWorkLogList.do';
	document.listFrm.pageIndex.value = 1;
	document.listFrm.submit();
}

function fnLinkPage(pageNo){
 	document.listFrm.pageIndex.value = pageNo;
    document.listFrm.action = "<c:out value='/mng/auth/workList/selectWorkLogList.do'/>";
    document.listFrm.submit();
}

//상세보기 페이지로 이동
function updateBoardView(logId){
	document.listFrm.logId.value = logId;
	document.listFrm.action = "<c:out value='/mng/auth/workList/selectWorkLogOneList.do'/>";
	document.listFrm.submit();
}

$(document).on('change','#pageUnit',function(){
    $('#pageIndex').val(1);
    $('input[name=pageUnit]').val($('#pageUnit').val());
    $('#listFrm').attr('action','<c:url value="/mng/auth/workList/selectWorkLogList.do"/>');
    $('#listFrm').submit();
});


$(function(){
	$("input[name=searchKeyword]").keypress(function(e){
		if(e.keyCode == 13) {
			goSearch();
		}
	});
});

</script>

