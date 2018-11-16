<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

	<div class="inquiryBox" style="display: inline;">
		<div class="pop-head">
			<h2><c:out value="말머리" /></h2>
			<p>
				<span class="b-close"><img src="/images/common/popupclose.png"
					alt="팝업 닫기" onclick="popClose(); return false;"></span>
			</p>
		</div>
		<!-- 팝업 (s) -->
		
		<form name="cateInsertForm" id="cateInsertForm" method="post" >
				<input type="hidden" id="bbs_id" name="bbsId" value="<c:out value="${bbsId }"/>" />
				<input type="hidden" name="bbscateName" id="bbscate_nm" />
		</form>
		<form name="cateUpdtForm" id="cateUpdtForm" method="post" >
			<input type="hidden" name="bbsId" value="<c:out value="${bbsId}"/>" />
			<input type="hidden" name="bbscateId" />
			<input type="hidden" name="bbscateName" />
		</form>
		<div class="pop-cont">
			<div class="tbl_wrp">
				<table class="tbl_lst">
				<caption>리스트</caption>
				<colgroup>
					<col style="width: 20%;">
					<col style="width: 64%;">
					<col style="width: 16%;">
				</colgroup>
				<tbody>
					<tr>
					<ul class="layerInput">
						<li>
							<td scope="row"><span class="tit"><label for="subj0101">분류 추가</label></span></td>
							<td class="tl"><input maxlength="20" type="text" name="bbscateName2" id="bbscate_nm2" class="inputTxt" style="width:280px;" /></td>
							<td class="tl"><a href="#" onclick="javascript:cateSubmit(document.cateInsertForm);" class="ltbtn whbtn"><span>저장</span></a></td>
						</li>
					</ul>
					<tr>
				</tbody>
				</table>
				<br>
				<table class="tbl_wrp">
				<colgroup>
					<col style="width: auto;">
					<col style="width: 28%;">
				</colgroup>
				<thead>
                    <tr>
                        <th scope="col" class="first">분류이름</th>
                        <th scope="col">구분</th>
                    </tr>
                </thead>
					<tbody>
						<ul class="subjList">
							<c:forEach var="result" items="${cateResultList}" varStatus="status">
								<tr>
								<li>
									<td><input type="text" class="inputTxt" name="bbscate_nm_tmp" id="bbscate_nm<c:out value="${result.bbscateId }"/>" style="width:300px;" value="${result.bbscateName }" /></td>
									<td><a href="#" onclick="javascript:CateUpdate(document.cateUpdtForm, '<c:out value="${result.bbscateId }"/>');" class="ltbtn whbtn"><span>수정</span></a>
									<a href="#" onclick="javascript:CateDelete(document.cateUpdtForm, '<c:out value="${result.bbscateId }"/>');" class="ltbtn whbtn"><span>삭제</</span></a></td>
								</li>
								</tr>
							</c:forEach>
						</ul>
					</tbody>
				</table>
			</div>
		</div>
	</div>
