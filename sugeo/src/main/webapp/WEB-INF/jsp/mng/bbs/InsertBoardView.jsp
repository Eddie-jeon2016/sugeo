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
  *  @version 1.0ㅁㄴㅇ
  *  @see
  *
  */
%>
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="/js/summernote/summernote.css" rel="stylesheet">
<script src="/js/summernote/summernote.js"></script>
<script src="/js/summernote/summerCustom.js"></script>

<form name="${searchVO.cmd eq 'write' ? 'writeForm' : 'updateForm'}" id="${searchVO.cmd eq 'write' ? 'writeForm' : 'updateForm'}" method="post" enctype="multipart/form-data">
<input type="hidden" id="cmd" name="cmd" value="${searchVO.cmd}"/>
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input type="hidden" name="searchCateId" value="${searchVO.searchCateId}"/>
<input type="hidden" name="bbsId"  value="${searchVO.bbsId}" />
<input type="hidden" name="atchFileId" value="${result.atchFileId}" />
<input type="hidden" name="thumbAtchFileId" value="${result.thumbAtchFileId}" />
<input type="hidden" name="selectId" value="" />
<input type="hidden" name="fileCount" value="${bbsManageVO.atchPosblFileNumber }" id="fileCount">
<input type="hidden" name="content">

<c:if test="${ searchVO.cmd eq 'update' }" >
<input type="hidden" name="nttId" value="${ result.nttId }" />
</c:if>
<input type="hidden" name="nttShareAt"  value="${empty result.nttShareAt ? 'N' : result.nttShareAt}" />
<c:if test="${result.thumbAtchFileId!='0'||result.thumbAtchFileId!=''}">
	<c:set var="thumbnail" value="/cmm/fms/getImage.do?atchFileId=${result.thumbAtchFileId}&fileSn="/>
</c:if>
<c:if test="${result.thumbAtchFileId=='0' || result.thumbAtchFileId == ''}">
	<c:set var="thumbnail" value="/images/sub/blank2.gif"/>
</c:if>

        <div class="sub_cont"> <!-- sub_cont (s)-->


            <div class="tbl_wrp">
                <table class="tbl_lst">
                    <caption>관리자 등록</caption>
                    <colgroup>
                        <col style="width: 15%;">
                        <col style="width: 30%;">
                        <col style="width: 15%;">
                        <col style="width: 30%;">
                    </colgroup>
                    <tbody>
                        <tr>
                            <th scope="row">구분</th>
                            <td class="tl" colspan="3">
								<select name="bbscateId" id="bbscate_id" title="말머리 선택" style="margin-bottom:5px;width:150px">
									<c:forEach var="cate" items="${cateList}" varStatus="status">
										<option value="${cate.bbscateId}" <c:if test="${cate.bbscateId eq result.bbscateId}" >selected="selected"</c:if>>${cate.bbscateName}</option>
									</c:forEach>
								</select>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">제목</th>
                            <td class="tl" colspan="3">
	                            <c:set var="DQUOT">"</c:set>
                             	<c:set var = "string2" value = "${fn:replace(result.title, DQUOT, '&#34;')}" />
	                            <input onkeydown="maxLengthCheck(event, 70)" type="text" name="title" id="write0101" class="w100inp" value="${string2 }" />
                            </td>
                        </tr>
					<tr>
						<th scope="row"><label for="write0401">썸네일</label></th>
						<td colspan="3">
						<input type="hidden" name="file_sn" value="${thumbFile.fileSn}"/>
							<input type="file" name="file_0" id="egovComFileUploader" title="파일" onchange="loadname(this,'preThumbnail',0)">
							<img style="width: 200px;height: 200px;" src="${searchVO.cmd eq 'write' ? '/images/sub/blank2.gif' : thumbnail}" name="preThumbnail" class="preThumbnail" id="preThumbnail" alt="">
							<c:if test="${searchVO.cmd eq 'update' && result.thumbAtchFileId!='0' && result.thumbAtchFileId!=''  }">
							<div id="thumb"><br>
							${thumbFile.orignlfileName}[<span style="color:red">${thumbFile.fileMg}</span> kb]
							<a href="#" onclick="fileDel2('thumb','<c:out value="${thumbFile.atchFileId}"/>','<c:out value="${thumbFile.fileSn}"/>'); return false;">
								<img src="<c:url value='../../images/egovframework/com/cmm/icon/bu5_close.gif' />"	width="19" height="18" alt="파일삭제">
							</a>
							</div>
							</c:if>
							<c:if test="${searchVO.cmd eq 'write' }">
								<br>
								<a class="fileDel" onclick="fileDel('0')">파일 초기화</a>
							</c:if>
							<ul class="mgt10">
								<li>사이즈: 176x118px</li>
								<li>포맷: JPG, GIF, PNG</li>
							</ul>
						</td>
					</tr>
										<!-- 게시판관리 파일개수 제한이 0보다 크고 파일사용여부가 Y인 경우 -->
										<c:if test="${bbsManageVO.atchPosblFileNumber > 0 && bbsManageVO.fileAtchPosblYn ne 'N'}">
										<c:choose>
											<c:when test="${searchVO.cmd eq 'update' }">
												<c:forEach var="file" items="${fileList}" varStatus="status">
														<tr>
															<th scope="row">첨부파일 ${status.count}</th>
															<td class="tl" colspan="3">
																<input type="hidden" name="file_sn" value="${file.fileSn}"/>
																<input type="file" name="file_${status.count}" id="egovComFileUploader" title="파일" ><div id="attach_${status.count }"><br>
																${file.orignlfileName}[<span style="color:red">${file.fileMg}</span> kb]
																<a href="#" class="attachCk" onclick="fileDel2('attach','<c:out value="${file.atchFileId}"/>','<c:out value="${file.fileSn}"/>'); return false;">
																	<img src="<c:url value='../../images/egovframework/com/cmm/icon/bu5_close.gif' />"	width="19" height="18" alt="파일삭제">
																</a>
																<div>
															</td>
														</tr>
												</c:forEach>
												<c:if test="${fn:length(fileList) < bbsManageVO.atchPosblFileNumber }">
													<c:forEach begin="${fn:length(fileList)+1}" end="${bbsManageVO.atchPosblFileNumber }" step="1" varStatus="status">
														<tr>
															<th scope="row">첨부파일 ${status.count+fn:length(fileList)}</th>
														<td class="tl" colspan="3">
														<input type="file" name="file_${status.count+fn:length(fileList)}" id="egovComFileUploader" title="파일">
															</td>
														</tr>
													</c:forEach>
												</c:if>
											</c:when>
											<c:otherwise>
												<c:forEach begin="1" end="${bbsManageVO.atchPosblFileNumber }" step="1" varStatus="status">
													<tr>
														<th scope="row">첨부파일 ${status.count}</th>
													<td class="tl" colspan="3">
													<input type="file" name="file_${status.count}" id="egovComFileUploader" title="파일">
													<a class="fileDel" onclick="fileDel('${status.count}')">파일 초기화</a>
														</td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
										</c:if>

                        <tr>
                            <th scope="row">작성자</th>
                            <td class="tl" colspan="3">
                         		<c:choose>
									<c:when test="${searchVO.cmd eq 'write' }">
										<c:out value="${loginVO.name }"/>
									</c:when>
									<c:otherwise>
										${result.nickname}
									</c:otherwise>
								</c:choose>
                        	</td>
                        </tr>

                        <tr id="smnoteTr">
                            <td colspan="4" style="text-align:left;">
								<div id="summernote">${result.content }</div>
                            </td>
                        </tr>

                    </tbody>
                </table>
            </div>

            <div class="btnbox">
				<c:choose>
					<c:when test="${searchVO.cmd eq 'write'}">
		                <p class="right"><a href="javascript:WiListBack('${bbsManageVO.bbsId}');" class="btnwrp vibtn" title="등록">목록</a></p>
						<p class="right"><a href="javascript:writeSubmit(document.writeForm);" class="btnwrp vibtn">등록</a></p>
<!-- 						<p class="right"><a href="javascript:history.back();" class="btnwrp vibtn">취소</a></p> -->
					</c:when>
					<c:otherwise>
		                <p class="right"><a href="javascript:UpListBack('${bbsManageVO.bbsId}');" class="btnwrp vibtn" title="등록">목록</a></p>
						<p class="right"><a href="javascript:updateSubmit(document.updateForm);" class="btnwrp vibtn">저장</a></p>
					</c:otherwise>
				</c:choose>
            </div>

        </div> <!-- sub_cont (e)-->


</form>
<script type="text/javascript">
$(document).ready(function() {
	$('#summernote').summernote({
		height : 350
//			,lang: 'ko-KR'
		,fontNames: ['맑은 고딕','돋움','신명조','굴림','궁서' ,'sans-serif', 'verdana','Arial'], fontNamesIgnoreCheck: ['맑은 고딕','돋움','신명조','굴림','궁서']
		,                callbacks : {
			onImageUpload: function(files, editor, welEditable) {
				sendFile(files[0], this);
			}
		}
	});
	$(".attachCk").click(function(){
		$(this).parent().empty();
	});
});
function fileDel(count) {
	$("input[name=file_"+count+"]").val('');
	if(count == "0") {
		$("#preThumbnail").attr('src', '/images/sub/blank2.gif');
	}
}
//첨부파일 삭제
function fileDel2(type, file_id, file_sn){
	var str ="";
	str += "<input type='hidden' name='"+type+"_id' value='"+file_id+"'/><br>"; /* 1개 */
	str += "<input type='hidden' name='"+type+"_sn' value='"+file_sn+"'/> "; /* 여러개 */
	$("#updateForm").append(str);

	if(type=="thumb"){
		$("#preThumbnail").attr('src', '/images/sub/blank2.gif');
		$("#thumb").remove();
	}else if(type="attach"){
// 		$("#attach_"+file_sn).empty();
	}

}
function loadname(img, review_img, num) {

    var is_ie = (navigator.appName=="Microsoft Internet Explorer"),
        path = img.value,
        ext = path.substring(path.lastIndexOf('.') + 1).toLowerCase();

    var txt = '<div>' + 'is_ie - ' + is_ie + '</div>'+
        '<div>' + 'path - ' + path + '</div>'+
        '<div>' + 'ext - ' + ext + '</div>';
    $('#text' + num).html(txt);

    if (ext == "gif" || ext == "jpeg" || ext == "jpg" ||  ext == "png") {
        if (is_ie) {
            img.select();
            var img_path = document.selection.createRangeCollection()[0].text.toString() + '?' + (new Date().getTime().toString());
            $('#'+ review_img).attr('src', img_path);
            $('a#'+review_img).attr('href', img_path);
        } else {
            if (img.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#'+ review_img).attr('src', e.target.result);
                }
                reader.readAsDataURL(img.files[0]);
            }
        }
    }
}
</script>