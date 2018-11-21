<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<h2 class="bigtit" style="text-align:center;">메뉴 관리</h2>

<div class="tbl_wrp">
	<table class="tbl_lst">
		<caption>메뉴 관리</caption>
		<colgroup>
			<col />
			<col />
			<col />
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>메뉴코드</th>
				<th>부모코드</th>
				<th>메뉴명</th>
				<th>메뉴설명</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${list}" varStatus="varStatus">
				<tr>
					<td>${fn:length(list)- varStatus.index }</td>
					<td><a href="javascript:menuRegView('${result.menuId }');">${result.menuId}</a></td>
					<td>${result.menuScId }</td>
					<td>${result.title }</td>
					<td>${result.menuDesc }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<form name="menuFrm" method="post">
	<input type="hidden" name="menuId" value="" />
</form>

<div class="btnbox">
	<span><a href="javascript:menuRegView('');" class="ltbtn ylbtn">등록</a></span>
</div>

<script type="text/javascript">
	function menuRegView(menuId) {
		document.menuFrm.action = '/mng/menu/menuRegView.do';
		document.menuFrm.menuId.value = menuId
		document.menuFrm.submit();
	}
</script>