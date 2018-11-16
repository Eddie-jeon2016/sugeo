<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- 단일값일 경우에는 히든으로 넘기지 않아도 됨.  -->
<form id="layerPopupForm" name="layerPopupForm" method="post">
<input type="hidden" name="writeAuth"  />
<input type="hidden" name="readAuth"  />
<input type="hidden" name="replyAuth"  />
<input type="hidden" name="answerAuth"  />
<div class="inquiryBox" style="display: inline;">
	<div class="pop-head">
		<h2 class="title">게시판 추가</h2>
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
						<td class="row">
							<span class="tit"><label for="text0101">제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목 : </label></span>
						</td>
						<td class="tl">
							<input onkeydown="maxLengthCheck(event, 50)" type="text" id="BBS_NAME" name="bbsName" class="inputTxt" style="width:295px;" />
						</td>
					</tr>
					<tr>
						<td class="row">
							<span class="tit">타입 : </span>
						</td>
						<td class="tl">
						</td>
					</tr>
					<tr>
						<td class="row">
							<span class="tit">사용여부 : </span>
						</td>
						<td class="tl">
							<input type="radio" id="use_at" class="inputRadio" name="useYn" checked="checked"  value="Y" /><label for="text0301"> 사용 </label>
							<input type="radio" id="use_at" class="inputRadio mgl15" name="useYn"  value="N" /><label for="text0302"> 미사용 </label>
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td class="row"> -->
<!-- 							<span class="tit">답글사용 : </span> -->
<!-- 						</td> -->
<!-- 						<td class="tl"> -->
<!-- 							<input type="radio" id="ANSWER_YN" class="inputRadio" name="answerYn" checked="checked"   value="Y"/><label for="text0401"> 사용</label> -->
<!-- 							<input type="radio" id="ANSWER_YN" class="inputRadio mgl15" name="answerYn"  value="N"/><label for="text0402"> 미사용</label> -->
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<td class="row">
							<span class="tit">파일사용 : </span>
						</td>
						<td class="tl">
							<input type="radio" id="FILE_ATCH_POSBL_YN" class="inputRadio" name="fileAtchPosblYn" checked="checked"  value="Y"/><label for="text0401"> 사용</label>
							<input type="radio" id="FILE_ATCH_POSBL_YN" class="inputRadio mgl15" name="fileAtchPosblYn"   value="N"/><label for="text0402"> 미사용</label>
						</td>
					</tr>
					<tr>
						<td class="row">
							<span class="tit"><label for="text0101">파일개수 : </label></span>
						</td>
						<td class="tl">
							<select name="atchPosblFileNumber" id="atch_posbl_file_number" title="파일개수" class="w100ipt">
								<option value="" label="--선택하세요--"/>
								<option value="1" >1개</option>
								<option value="2" >2개</option>
								<option value="3" >3개</option>
								<option value="4" >4개</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="row">
							<span class="tit">쓰기권한 : </span>
						</td>
						<td class="tl">
							<c:forEach var="result" items="${authList}" varStatus="status" >
								<input type="checkbox" id="write_auth_ck${status.count }" class="inputChk" name="write_auth_ck" value='${result.roleCode }' /><label for="write_auth_ck${status.count }"> ${result.roleName }</label>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td class="row">
							<span class="tit">읽기권한 : </span>
						</td>
						<td class="tl">
							<c:forEach var="result" items="${authList}" varStatus="status" >
								<input type="checkbox" id="read_auth_ck${status.count }" class="inputChk" name="read_auth_ck" value='${result.roleCode }' /><label for="read_auth_ck${status.count }"> ${result.roleName }</label>
							</c:forEach>
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td class="row"> -->
<!-- 							<span class="tit">답글권한 : </span> -->
<!-- 						</td> -->
<!-- 						<td class="tl"> -->
<%-- 							<c:forEach var="result" items="${authList}" varStatus="status" > --%>
<%-- 								<input type="checkbox" id="reply_auth_ck${status.count }" class="inputChk" name="reply_auth_ck" value='${result.roleCode }' /><label for="reply_auth_ck${status.count }"> ${result.roleName }</label> --%>
<%-- 							</c:forEach> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td class="row"> -->
<!-- 							<span class="tit">댓글권한 : </span> -->
<!-- 						</td> -->
<!-- 						<td class="tl"> -->
<%-- 							<c:forEach var="result" items="${authList}" varStatus="status" > --%>
<%-- 								<input type="checkbox" id="answer_auth_ck${status.count }" class="inputChk" name="answer_auth_ck" value='${result.roleCode }' /><label for="answer_auth_ck${status.count }"> ${result.roleName }</label> --%>
<%-- 							</c:forEach> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
						<tr>
							<td class="row">
								<span class="tit"><label for="text0101">메뉴매칭</label></span>
							</td>
							<td class="tl">
								<input type="text" id="menuNo" name="menuNo" class="inputTxt" style="width:295px;" />
							</td>
						</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="inq-btnb">
		<a class="b-close popc-close"
			href="javascript:writeSubmit(document.layerPopupForm);"><span>추가</span></a>
		<a class="b-close popc-close" href="#none"
			onClick="BbsclosePop(); return false;"><span>닫기</span></a>
	</div>
</div>
</form>