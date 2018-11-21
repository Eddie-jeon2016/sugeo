<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h2 class="bigtit" style="text-align:center;">코드 관리</h2>

<div class="tbl_wrp">
	<table class="tbl_lst">
		<caption>코드 관리</caption>
		<colgroup>
			<col />
			<col />
			<col />
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>코드</th>
				<th>코드명</th>
				<th>등록일</th>
				<th>서브코드 갯수</th>
				<th>수정일</th>
				<th>사용여부</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${empty codeList }">
				<tr>
					<td colspan="6">코드 목록이 없습니다.</td>
				<tr>
			</c:if>
			<c:if test="${not empty codeList }">
				<c:forEach var="result" items="${codeList}" varStatus="varStatus">
					<tr>
						<td>${fn:length(codeList)- varStatus.index }</td>
						<td>${result.code }</td>
						<td><a href="javascript:subCodeListView('${result.code }');">${result.codeName }</a></td>
						<td>${result.regDate }</td>
						<td>${result.countSubCode }</td>
						<td>${result.modDate }</td>
						<td><c:if test="${result.useYn == 'Y'}">사용</c:if>
							<c:if test="${result.useYn== 'N'}">미사용</c:if></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
<form name="codeFrm" id="codeFrm" method="post">
	<input type="hidden" name="code" value="">
</form>

	<div class="btnbox">
         <span><a href="/mng/code/codeRegView.do" class="ltbtn ylbtn">메인코드 추가</a></span>
     </div>


<script type="text/javascript">
		
	function subCodeListView(code){
		document.codeFrm.action='/mng/code/subCodeList.do';
		document.codeFrm.code.value = code;
		document.codeFrm.submit();
	}
</script>