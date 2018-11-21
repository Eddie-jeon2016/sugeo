<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

<h2 class="bigtit" style="text-align:center;">코드 관리</h2>
<form name="codeFrm" id="codeFrm" method="post">
<input type="hidden" name="code" value="${subCode.code }"/>
<input type="hidden" name="subCode" value="${subCode.subCode }"/>
	<div class="tbl_wrp">
		<table class="tbl_lst">
			<caption>부모코드</caption>
			<colgroup>
				<col style="width: 30%;">
				<col style="width: 70%;">
			</colgroup>
			<tbody>
				<tr>
					<td scope="row">부모</td>
					<td class="tl">${subCode.code }</td>
				</tr>
				<tr>
					<td scope="row">코드</td>
					<td class="tl">${subCode.subCode }</td>
				</tr>
				<tr>
					<td scope="row">코드명</td>
					<td class="tl"><input type="text" name="subCodeName" value="${subCode.subCodeName }" class="w100ipt"></td>
				</tr>
				<tr>
					<td scope="row">코드설명</td>
					<td class="tl">
					<textarea name="subcodeDesc" class="w100ipt">${subCode.subcodeDesc }</textarea>
					</td>
				</tr>
				<tr>
					<td scope="row">사용여부 / ${subCode.useYn }</td>
					<td class="tl">
					<select name="useYn" id="useYn" class="w100ipt">
		<option <c:if test="${subCode.useYn eq 'Y' }">selected="selected"</c:if> value="Y" label="사용함" />
		<option <c:if test="${subCode.useYn eq 'N' }">selected="selected"</c:if> value="N" label="사용안함"/>
	</select>
					</td>
				</tr>

			</tbody>
		</table>
	</div>
</form>

<div class="btnbox">
	<span><a href="javascript:subCodeUpdate();" class="ltbtn ylbtn">수정</a></span> 
	<span><a href="/mng/code/subCodeList.do?code=${subCode.code }" class="ltbtn whbtn">서브코드목록</a></span>
</div>
<script>
	function subCodeUpdate() {
		document.codeFrm.action = '/mng/code/subCodeUpdate.do';
		document.codeFrm.submit();
	}
</script>