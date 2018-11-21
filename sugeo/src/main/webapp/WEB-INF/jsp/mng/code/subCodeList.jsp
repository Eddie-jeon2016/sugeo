<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
				<td class="tl">${codeList.code }</td>
			</tr>
			<tr>
				<td scope="row">코드명</td>
				<td class="tl">${codeList.codeName }</td>
			</tr>
			<tr>
				<td scope="row">등록일</td>
				<td class="tl">${codeList.regDate }</td>
			</tr>
			<tr>
				<td scope="row">수정일</td>
				<td class="tl">${codeList.modDate }</td>
			</tr>
			<tr>
				<td scope="row">등록인</td>
				<td class="tl">${codeList.regId }</td>
			</tr>
			<tr>
				<td scope="row">사용여부</td>
				<td class="tl"><c:if test="${codeList.useYn == 'Y'}">사용</c:if>
					<c:if test="${codeList.useYn== 'N'}">미사용</c:if></td>
			</tr>
			<tr>
				<td scope="row">코드설명</td>
				<td class="tl"><textarea cols="50" rows="10"
						readonly="readonly" class="w100ipt">${codeList.codeDesc }</textarea></td>
			</tr>

		</tbody>
	</table>
</div>
<br/><br/>
<div class="tbl_wrp">
	<table class="tbl_lst">
		<caption>서브코드</caption>
		<colgroup>
			<col />
			<col />
			<col />
		</colgroup>
		<thead>
			<tr>
				<th>순번</th>
				<th>코드</th>
				<th>코드명</th>
				<th>등록일</th>
				<th>수정일</th>
				<th>등록인</th>
				<th>사용여부</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty codeList.subCodes }">
				<tr>
					<td colspan="7">코드 목록이 없습니다.</td>
				<tr>
			</c:if>
			<c:if test="${not empty codeList.subCodes }">
				<c:forEach items="${codeList.subCodes }" var="subCode"
					varStatus="loop">
					<tr>
						<td>${fn:length(codeList.subCodes)- loop.index }</td>
						<td>${subCode.subCode }</td>
						<td><a href="javascript:subCodeView('${subCode.subCode }');">${subCode.subCodeName }</a></td>
						<td>${subCode.regDate }</td>
						<td>${subCode.modDate }</td>
						<td>${subCode.regId }</td>
						<td><c:if test="${subCode.useYn == 'Y'}">사용</c:if> <c:if
								test="${subCode.useYn== 'N'}">미사용</c:if></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
<form name="subCodeFrm" id="subCodeFrm" method="post">
	<input type="hidden" name="subCode" value="">
</form>

<form name="codeFrm" id="codeFrm" method="post">
	<input type="hidden" name="code" value="${codeList.code }">
</form>



<div class="btnbox">
	<span><a href="javascript:codeUpdateView('${codeList.code }');" class="ltbtn ylbtn">메인코드
			수정</a></span> <span><a href="javascript:codeDelete('${codeList.code }');" class="ltbtn ylbtn">메인코드
			삭제</a></span><span> <a
		href="javascript:subCodeRegView('${codeList.code }');" class="ltbtn ylbtn">서브코드 추가</a></span><span><a
		href="/mng/code/codeList.do" class="ltbtn whbtn">코드목록</a></span>
</div>

<script type="text/javascript">
	function codeUpdateView(code) {
		document.codeFrm.action = '/mng/code/codeUpdateView.do';
		document.codeFrm.code.value = code;
		document.codeFrm.submit();
	}

	function codeDelete(code) {
		document.codeFrm.action = '/mng/code/codeDelete.do';
		document.codeFrm.code.value = code;
		document.codeFrm.submit();
	}

	function subCodeRegView(code) {
		document.codeFrm.action = '/mng/code/subCodeRegView.do';
		document.codeFrm.code.value = code;
		document.codeFrm.submit();
	}

	function subCodeView(subCode) {
		document.subCodeFrm.action = '/mng/code/subCodeView.do';
		document.subCodeFrm.subCode.value = subCode;
		document.subCodeFrm.submit();
	}
</script>