<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<div class="usrposit"><!-- usrposit (s) -->
    <h3>콘텐츠 관리</h3>
    <ul>
       <!--  <li>사이트 관리</li>
        <li>콘텐츠 관리</li> -->
    </ul>
</div>


<div id="maininner"><!-- maininner (s)-->
<div class="tbl_wrp">
	<table class="tbl_lst">
		<caption>table1</caption>
		<colgroup>
			<col width="8%" />
			<col width="30%"/>
			<col width="30%"/>
			<col width="22%"/>
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>콘텐츠코드</th>
				<th>콘텐츠명</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${list}" varStatus="varStatus">
				<tr>
					<td>${paginationInfo.totalRecordCount - paginationInfo.firstRecordIndex - varStatus.index }</td>
					<td><a
						href="javascript:contentRegView('${result.contentId }');">${result.contentId }</a></td>
					<td>${result.contentName }</td>
					<td>${result.regDate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<form name="contentFrm" method="post">
		<input type="hidden" name="contentId" value="" />
	</form>
</div>	
     <div class="tblbottombar clfix"><!-- tblbottombar (s)-->
            <div class="rightpot">
                <ul class="clfix">
                    <li>
                        <button type="button" onclick="javascript:contentRegView('');"
                                class="abtn indbtn" title="등록">등록
                        </button>   
                    </li>
                </ul>
            </div>
        </div>
     <div class="page_num">
         <span class="num">
         	<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="fnLinkPage"	/>
         </span>
    </div>
    <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
		
</div>

<script type="text/javascript">
	function contentRegView(contentId) {
		document.contentFrm.action = '/mng/content/contentRegView.do';
		document.contentFrm.contentId.value = contentId
		document.contentFrm.submit();
	}
</script>