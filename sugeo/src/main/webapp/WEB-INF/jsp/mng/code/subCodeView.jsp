<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h2 class="bigtit" style="text-align:center;">코드 관리</h2>
	<div class="tbl_wrp">
		<table class="tbl_lst">
			<caption>부모코드</caption>
			<colgroup>
				<col style="width: 30%;">
				<col style="width: 70%;">
			</colgroup>
			<tbody>
				<tr>
					<td scope="row">코드</td>
					<td class="tl">${subCode.subCode }</td>
				</tr>
				<tr>
					<td scope="row">코드명</td>
					<td class="tl">${subCode.subCodeName }</td>
				</tr>
				<tr>
					<td scope="row">등록일</td>
					<td class="tl">${subCode.regDate }</td>
				</tr>
				<tr>
					<td scope="row">수정일</td>
					<td class="tl">${subCode.modDate }</td>
				</tr>
				<tr>
					<td scope="row">등록인</td>
					<td class="tl">${subCode.regId }</td>
				</tr>
				<tr>
					<td scope="row">사용여부</td>
					<td class="tl">
					<c:if test="${subCode.useYn == 'Y'}">사용</c:if>
							<c:if test="${subCode.useYn== 'N'}">미사용</c:if>
					</td>
				</tr>
				<tr>
					<td scope="row">코드설명</td>
					<td class="tl"><textarea cols="50" rows="10" readonly="readonly" class="w100ipt">${subCode.subcodeDesc }</textarea></td>
				</tr>

			</tbody>
		</table>
	</div>

<form name="subCodeFrm" id="subCodeFrm" method="post">
	<input type="hidden" name="subCode" value="">
</form>


<div class="btnbox">
	<span><a href="javascript:subCodeUpdateView('${subCode.subCode }');" class="ltbtn ylbtn">서브코드 수정</a></span>
	<span><a href="javascript:subCodeDelete('${subCode.subCode }');" class="ltbtn ylbtn">서브코드 삭제</a></span>
	<span><a href="/mng/code/subCodeList.do?code=${subCode.code }" class="ltbtn whbtn">서브코드목록</a></span>
</div>

<script type="text/javascript">
	
	function subCodeUpdateView(subCode){
		document.subCodeFrm.action='/mng/code/subCodeUpdateView.do';
		document.subCodeFrm.subCode.value = subCode;
		document.subCodeFrm.submit();
	}	
	
	function subCodeDelete(subCode){
		document.subCodeFrm.action='/mng/code/subCodeDelete.do';
		document.subCodeFrm.subCode.value = subCode;
		document.subCodeFrm.submit();
	}
</script>