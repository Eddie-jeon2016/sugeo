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
					<td class="tl"><input type="text" name="code" class="w100ipt" /></td>
				</tr>
				<tr>
					<td scope="row">코드명</td>
					<td class="tl"><input type="text" name="codeName" class="w100ipt"/></td>
				</tr>
				<tr>
					<td scope="row">사용여부</td>
					<td class="tl"><select name="useYn" id="useYn" class="w100ipt">
							<option value="Y" label="사용함" />
							<option value="N" label="사용안함" />
					</select></td>
				</tr>
				<tr>
					<td scope="row">코드설명</td>
					<td class="tl"><textarea name="codeDesc" rows="10" cols="50"></textarea></td>
				</tr>

			</tbody>
		</table>
	</div>


</form>

<div class="btnbox">

	<span><a href="javascript:codeInsert();" class="ltbtn ylbtn">등록</a></span>
	<span><a
		href="/mng/code/codeList.do" class="ltbtn whbtn">목록</a></span>

</div>
<script>
	function codeInsert() {
		document.codeFrm.action = '/mng/code/codeInsert.do';
		document.codeFrm.submit();
	}
</script>