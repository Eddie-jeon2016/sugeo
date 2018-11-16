<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>
<form id="layerPopupForm" name="layerPopupForm" method="post">
	<div id="layerPopup" class="layer01">
		<input type="hidden" name="bbsId" value="${updateResult.bbsId}" /> <input
			type="hidden" name="writeAuth" /> <input type="hidden"
			name="readAuth" /> <input type="hidden" name="replyAuth" /> <input
			type="hidden" name="answerAuth" />
		<!-- 170310 추가 -->
		<!-- 팝업 (s) -->
		<div class="inquiryBox" style="display: inline;">
			<div class="pop-head">
				<h2>
					<c:out value="게시판수정"></c:out>
				</h2>
				<p>
					<span class="b-close"><img
						src="/images/common/popupclose.png" alt="팝업 닫기"
						onclick="popClose(); return false;"></span>
				</p>
			</div>

			<div class="pop-cont">
				<div class="tbl_wrp">
					<table class="tbl_lst">
						<caption>리스트</caption>
						<colgroup>
							<col style="width: 30%;">
							<col style="width: 70%;">
						</colgroup>
						<tbody>
							<tr>
								<td scope="row"><label for="modify0101">제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목
										: </label></td>
								<td class="tl"><input onkeydown="maxLengthCheck(event, 50)"
									type="text" id="BBS_NAME" class="w100ipt"
									value="${updateResult.bbsName}" name="bbsName" /></td>
							</tr>
							<tr>
								<td class="row"><span class="tit">타입 : </span></td>
								<td class="tl"></td>
							</tr>
							<tr>
								<td scope="row">사용여부 :</td>
								<td class="tl"><input type="radio" id="use_at"
									class="inputRadio" name="useYn" value="Y"
									<c:if test="${updateResult.useYn == 'Y'}"> checked</c:if> /><label
									for="modify0301"> 사용</label> <input type="radio" id="use_at"
									class="inputRadio mgl15" name="useYn" value="N"
									<c:if test="${updateResult.useYn == 'N'}"> checked</c:if> /><label
									for="modify0302"> 미사용</label></td>
							</tr>
							<!-- 							<tr> -->
							<!-- 								<td scope="row">댓글사용 :</td> -->
							<!-- 								<td class="tl"><input type="radio" id="ANSWER_YN" -->
							<!-- 									class="inputRadio" name="answerYn" value="Y" -->
							<%-- 									<c:if test="${updateResult.answerYn == 'Y'}"> checked="checked"</c:if> /><label --%>
							<!-- 									for="modify0401"> 사용</label> <input type="radio" id="ANSWER_YN" -->
							<!-- 									class="inputRadio mgl15" name="answerYn" value="N" -->
							<%-- 									<c:if test="${updateResult.answerYn == 'N'}"> checked="checked"</c:if> /><label --%>
							<!-- 									for="modify0402"> 미사용</label></td> -->
							<!-- 							</tr> -->
							<tr>
								<td scope="row">파일사용 :</td>
								<td class="tl"><input type="radio" id="FILE_ATCH_POSBL_YN"
									class="inputRadio" name="fileAtchPosblYn" value="Y"
									<c:if test="${updateResult.fileAtchPosblYn == 'Y'}"> checked="checked"</c:if> /><label
									for="text0401"> 사용</label> <input type="radio"
									id="FILE_ATCH_POSBL_YN" class="inputRadio mgl15"
									name="fileAtchPosblYn" value="N"
									<c:if test="${updateResult.fileAtchPosblYn == 'N'}"> checked="checked"</c:if> /><label
									for="text0402"> 미사용</label></td>
							</tr>
							<tr>
								<td scope="row"><label for="text0101">파일개수 : </label></td>
								<td class="tl"><select name="atchPosblFileNumber"
									id="atch_posbl_file_number" title="파일개수" class="w100ipt">
										<option value="" label="--선택하세요--" />
										<option value="1"
											<c:if test="${fn:contains(updateResult.atchPosblFileNumber, '1') }">selected="selected" </c:if>
											label="1개" />
										<option value="2"
											<c:if test="${fn:contains(updateResult.atchPosblFileNumber, '2') }">selected="selected" </c:if>
											label="2개" />
										<option value="3"
											<c:if test="${fn:contains(updateResult.atchPosblFileNumber, '3') }">selected="selected" </c:if>
											label="3개" />
										<option value="4"
											<c:if test="${fn:contains(updateResult.atchPosblFileNumber, '4') }">selected="selected" </c:if>
											label="4개" />
								</select></td>
							</tr>
							<tr>
								<td scope="row">쓰기권한 :</td>
								<td class="tl"><c:forEach var="result" items="${authList}"
										varStatus="status">
										<input type="checkbox" id="write_auth_ck${status.count }"
											class="inputChk" name="write_auth_ck"
											value='${result.roleCode }'
											<c:if test="${fn:contains(updateResult.writeAuth, result.roleCode) }">checked="checked" </c:if> />
										<label for="write_auth_ck${status.count }">
											${result.roleName }</label>
									</c:forEach></td>
							</tr>
							<tr>
								<td scope="row">읽기권한 :</td>
								<td class="tl"><c:forEach var="result" items="${authList}"
										varStatus="status">
										<input type="checkbox" id="read_auth_ck${status.count }"
											class="inputChk" name="read_auth_ck"
											value='${result.roleCode }'
											<c:if test="${fn:contains(updateResult.readAuth, result.roleCode) }">checked="checked" </c:if> />
										<label for="read_auth_ck${status.count }">
											${result.roleName }</label>
									</c:forEach></td>
							</tr>
							<!-- 							<tr> -->
							<!-- 								<td scope="row">답글권한 :</td> -->
							<%-- 								<td class="tl"><c:forEach var="result" items="${authList}" --%>
							<%-- 										varStatus="status"> --%>
							<%-- 										<input type="checkbox" id="reply_auth_ck${status.count }" --%>
							<!-- 											class="inputChk" name="reply_auth_ck" -->
							<%-- 											value='${result.roleCode }' --%>
							<%-- 											<c:if test="${fn:contains(updateResult.replyAuth, result.roleCode) }">checked="checked" </c:if> /> --%>
							<%-- 										<label for="reply_auth_ck${status.count }"> --%>
							<%-- 											${result.roleName }</label> --%>
							<%-- 									</c:forEach></td> --%>
							<!-- 							</tr> -->
							<!-- 							<tr> -->
							<!-- 								<td scope="row">댓글권한 :</td> -->
							<%-- 								<td class="tl"><c:forEach var="result" items="${authList}" --%>
							<%-- 										varStatus="status"> --%>
							<%-- 										<input type="checkbox" id="answer_auth_ck${status.count }" --%>
							<!-- 											class="inputChk" name="answer_auth_ck" -->
							<%-- 											value='${result.roleCode }' --%>
							<%-- 											<c:if test="${fn:contains(updateResult.answerAuth, result.roleCode) }">checked="checked" </c:if> /> --%>
							<%-- 										<label for="answer_auth_ck${status.count }"> --%>
							<%-- 											${result.roleName }</label> --%>
							<%-- 									</c:forEach></td> --%>
							<!-- 							</tr> -->
							<tr>
								<td scope="row"><label for="modify0101">메뉴매칭 : </label></td>
								<td class="tl"><input type="text" id="menuNo"
									class="w100ipt" value="${updateResult.menuNo}" name="menuNo" /></td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="btnbox">
					<span> </span>
				</div>

			</div>
			<div class="inq-btnb">
				<a class="b-close popc-close"
					href="javascript:updateSubmit(document.layerPopupForm);"><span>저장</span></a>
				<a class="b-close popc-close" href="#none"
					onClick="BbsclosePop(); return false;"><span>닫기</span></a>
			</div>
		</div>
		<!-- 팝업 (e) -->
		<!-- // 170310 추가 -->

	</div>
</form>