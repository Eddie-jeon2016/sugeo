<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
  * @Class Name : BbsCafeManageList.jsp
  * @Description : 카페 게시판 목록 화면
  * @Modification Information
  * @
  * @  	수정일      			수정자           	 수정내용
  * @ ---------------        --------    	---------------------------
  * @ 2013.08.29     		조은태         		최초 생성
  *
  *  @author (주)거산디에스엔 조은태
  *  @since 2013.08.29
  *  @version 1.0
  *  @see
  *
  */
%>
<form name = "answerForm" method = "post" >
<input type="hidden" name="cmd" value=""/>
<input type="hidden" name="no"  value="${searchVO.no}" />
<input type="hidden" name="bbs_id"  value="${searchVO.bbs_id}" />
<input type="hidden" name="smenuNo"  value="${searchVO.smenuNo}" />
<input type="hidden" name="ntt_id" value="${ bbsVO.ntt_id }" />
<input type="hidden" name="sec_at" value="${ bbsVO.sec_at }" />
<input type="hidden" name="atch_file_id" value="${result.atch_file_id}" />
						<fieldset>
							<legend>자유게시판 답글</legend>
							<div class="bbsWriteWrap">
								<table class="bbsWrite01" summary="자유게시판 답글을 나타내는 표">
									<caption>자유게시판 답글</caption>
									<colgroup>
										<col width="15%" />
										<col width="auto" />
									</colgroup>
									<tbody>
										<tr>
											<%-- <th scope="row"><label for="write0101">${searchVO.cmd eq 'wirte' ? '' : result.ntt_sj} </label></th> --%>
											<th scope="row"><label for="write0101">제목</label></th>
											<td>
												<input onkeydown="maxLengthCheck(event, 50)" type="text" name="ntt_sj" id="write0101" class="iTxt" style="width:420px" value="${bbsVO.ntt_sj }" />
											</td>
										</tr>
										<tr>
											<th scope="row"><label for="write0201">작성자</th>
											<td>
													<c:out value="${loginVO.name }"/>
											</td>
										</tr>
										<tr>
											<th scope="row"><label for="write0301">내용</th>
											<td class="cont">
												<textarea rows="15" cols="" id="ntt_cn" style="width:100%; display:none;" name="ntt_cn"><c:if test="${bbsVO.ntt_cn != null }">${bbsVO.ntt_cn } 
												<br/>=============================================================================== <br/><br/>
</c:if></textarea>
												<c:import url="/daumeditor/editor.jsp" charEncoding="utf-8" />
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</fieldset>
					<div class="btnWrap">
						<div class="fl">
							<span class="btnGoLinkC"><a href="#" onClick="javascript:AnswerListBack('${bbsManageVO.bbs_id}'); return false;">목록</a></span>
						</div>
						<div class="fr">
							<span class="btnGoLinkB"><a href="#" onClick="javascript:bbsAnswerSubmit(document.answerForm); return false;">등록</a></span>
							<span class="btnGoLinkD"><a href="#" onClick="javascript:history.back(); return false;">취소</a></span>
						</div>
					</div>
</form>
<script type="text/javascript">
	function loadContent() {
		/* 저장된 컨텐츠를 불러오기 위한 함수 호출 */
		Editor.modify({
			"content": document.getElementById("ntt_cn") /* 내용 문자열, 주어진 필드(textarea) 엘리먼트 */
		});
	}
	loadContent();
</script>