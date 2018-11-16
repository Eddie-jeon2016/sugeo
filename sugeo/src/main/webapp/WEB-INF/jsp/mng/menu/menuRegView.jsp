<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2 class="bigtit" style="text-align:center;">메뉴 관리</h2>

<div class="tbl_wrp">
	<form name="menuFrm" id="menuFrm" method="post">
		<input type="hidden" name="menuId" value="${ViewMenu.menuId }" />
		<table class="tbl_lst">
			<caption>메뉴 관리</caption>
            <colgroup>
                <col style="width: 30%;">
                <col style="width: 70%;">
            </colgroup>
			<tbody>
				<tr>
					<td scope="row">메뉴명</td>
					<td class="tl"><input type="text" name="title"
						value="${ViewMenu.title }" class="w100ipt"/></td>
				</tr>
				<tr>
					<td scope="row">부모메뉴</td>
					<td class="tl"><input type="text" name="menuScId"
						value="${ViewMenu.menuScId }" class="w100ipt"/></td>
				</tr>
				<tr>
					<td scope="row">메뉴설명</td>
					<td class="tl"><input type="text" name="menuDesc"
						value="<c:out value="${ViewMenu.menuDesc}"/>" class="w100ipt"/></td>
				</tr>
				<tr>
					<td scope="row">메뉴URL</td>
					<td class="tl"><input type="text" name="url"
						value="<c:out value="${ViewMenu.url}"/>" class="w100ipt"/></td>
				</tr>
				<tr>
					<td scope="row">메뉴Depth</td>
					<td class="tl"><input type="text" name="menuDepth"
						value="<c:out value="${ViewMenu.menuDepth}"/>" class="w100ipt"/></td>
				</tr>
				<tr>
					<td scope="row">순서</td>
					<td class="tl"><select name="seq" class="w100ipt">
							<option value="0">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
					</select></td>
				</tr>

			</tbody>
		</table>
	</form>
</div>

<div class="btnbox">
	<c:if test="${empty ViewMenu}">
		<span><a href="javascript:menuInsert();" class="ltbtn ylbtn">입력</a></span>
	</c:if>
	<c:if test="${!empty ViewMenu}">
		<span><a href="javascript:menuUpdate();" class="ltbtn ylbtn">수정</a></span>
	</c:if>
	<span><a href="/mng/menu/menuList.do" class="ltbtn whbtn">목록</a></span>
</div>
<script>
	
	function menuInsert(){
		if(document.menuFrm.title.value.length == 0){
			alert('Please Insert title');
			return
		}
		
		document.menuFrm.action='/mng/menu/menuInsert.do';
		document.menuFrm.submit();
	}
	
	function menuUpdate(){
		document.menuFrm.action='/mng/menu/menuUpdate.do';
		document.menuFrm.submit();
	}
</script>