<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="/js/summernote/summernote.css" rel="stylesheet">
<script src="/js/summernote/summernote.js"></script>
<script src="/js/summernote/summerCustom.js"></script>
<link href="/js/foundation/foundation-datepicker.min.css"
	rel="stylesheet">
<script src="/js/foundation/foundation-datepicker.min.js"></script>
<link href="//cdnjs.cloudflare.com/ajax/libs/foundicons/3.0.0/foundation-icons.css" rel="stylesheet">
<script src="/js/foundation/locale/foundation-datepicker.ko.js"></script>

<div class="usrposit"><!-- usrposit (s) -->
<c:if test="${cmd eq 'insert' }">
    <h3>관련글 관리 등록</h3>
    <ul>
     <!--    <li>관련글 관리 등록</li> -->
    </ul>
</c:if>
<c:if test="${cmd ne 'insert' }">
    <h3>관련글 관리 수정</h3>
    <ul>
        <!-- <li>관련글 관리 수정</li> -->
    </ul>
</c:if>
</div>

<div id="maininner"><!-- maininner (s)-->

    <table class="tbl_lst tdleft">
        <caption>게시판 상세</caption>
        <colgroup>
            <col style="width: 15%;">
            <col style="width: 15%;">
            <col style="width: 30%;">
        </colgroup>
        <tbody>
        <form name="${cmd eq 'insert' ? 'insertForm' : 'updateForm'}"
              id="${cmd eq 'insert' ? 'insertForm' : 'updateForm'}" method="post" enctype="multipart/form-data">
               <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
                <input type="hidden" name="bbsId" value="${ bbsManageVO.bbsId }"/>
                <input type="hidden" name="menuNo" value="${ bbsManageVO.menuNo }"/>
                <input type="hidden" name="nttId" value="${ bbsManageVO.nttId }"/>
                <input type="hidden" name="params" />
                <input type="hidden" name="oldFileId" value="${thumbFile.atchFileId}" />
           <c:if test="${ cmd eq 'update' }">
            </c:if> 
            <tr>
                <th scope="row">제목</th>
                <td class="tl" colspan="3">
                    <c:set var="DQUOT">"</c:set>
                    <c:set var="string2" value="${fn:replace(result.title, DQUOT, '&#34;')}"/>
                    <input onkeydown="maxLengthCheck(event, 70);" type="text" name="title" id="title"
                           class="onetp445"
                           value="${string2 }"/>
                </td>
            </tr>
            <tr>
            	<th scope="row"><label>썸네일</label></th>
            	
            		<c:choose>
            		<c:when test="${searchVO.cmd ne 'update' }">
							<td colspan="3"><input type="hidden" name="file_sn"
							value="${thumbFile.fileSn}" /> <input type="file" name="file_0"
							id="egovComFileUploader" title="파일"
							onchange="loadname(this,'preThumbnail',0)">        			
            			</c:when>
            			<c:otherwise>
								<td class="file">					
									<input id="fileName" type="text" value="${thumbFile.orignlfileName}" readonly="readonly"  style="width: 60%"/>
									<a href="#" onclick="fn_imgFileDel(); return false;" class="btn1">
										삭제
									</a>
									<!-- 이미지 수정 확인용 값 -->
									<input type="hidden" id="newFileYn" name="newFileYn" value="N"/>
			                       </td>
								<td class="newFile" style="display: none;">
									<input  name="file_0" id="newFilename" type="file" title="첨부파일입력"/>
								</td>
            			</c:otherwise>
            		
            		</c:choose>
							<%-- <c:if test="${searchVO.cmd eq 'update' && result.thumbAtchFileId!='0' && result.thumbAtchFileId!=''  }">
								<div id="thumb">
									<br> ${thumbFile.orignlfileName}}[<span style="color: red">${thumbFile.fileMg}</span> kb] <a href="#"
										onclick="fileDel2('thumb','<c:out value="${thumbFile.atchFileId}"/>','<c:out
	                                        value="${thumbFile.fileSn}"/>'); return false;">
										파일삭제 </a>
								</div>
							</c:if> --%>
							<!-- 
	                        <ul >
	                            <li>사이즈: 176x118px</li>
	                            <li>포맷: JPG, GIF, PNG</li>
	                        </ul> --></td>
            </tr>
  					<textarea rows="15" cols="" id="content" style="width:100%; 
            			display:none;" name="content" ></textarea>

	        <tr>
	            <td colspan="4">
	                <div id="summernote">${result.content }</div>
	            </td>
	        </tr>
        </tbody>
        </form>
    </table>

    <div class="centerbtnwrp">
        <c:choose>
            <c:when test="${cmd eq 'insert'}">
                <span><a href="javascript:WiListBack();" class="abtn grybtn">목록</a></span>
                <span><a href="javascript:writeSubmit(document.insertForm);" class="abtn indbtn">등록</a></span>
            </c:when>
            <c:otherwise>
                <span><a href="javascript:deleteSubmit('${result.nttId }');" class="abtn whibtn">삭제</a></span>
                <span><a href="javascript:UpListBack();" class="abtn grybtn">목록</a></span>
                <span><a href="javascript:updateSubmit(document.updateForm);" class="abtn indbtn">수정</a></span>
            </c:otherwise>
        </c:choose>
    </div>
</div>


<script type="text/javascript">

$(document).ready(function() {
	$('#summernote').summernote({
		width: '97%'
		, height : 300
		,                callbacks : {
			onImageUpload: function(files, editor, welEditable) {
				sendFile(files[0], this);
			}
		}
	});
});
	
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

//등록 버튼
function writeSubmit(form){
	form.content.value = $('#summernote').summernote('code');
	$("#smnoteTr").remove();
	if(form.title.value ==""){
		alert("제목을 입력 하세요.");
		form.title.focus();
		return ; //focus 커서이동
	}
	
	if(form.file_0.value == "") {
        alert("썸네일을 추가하지 않았습니다.");
        return;
    }
	if($('#summernote').summernote('isEmpty')) {
        alert("내용을 입력하세요.");
        return;
    }
	
	$.ajax({
        url     : "<c:url value='/com/loginConfirm.do'/>",
        type    : "POST",
        dataType: "text",
        success : function(data) {
            // JSON 객체로 변환
           	var jData = JSON.parse(data);
            if(jData.result_cd == "200") {
				form.action = "/front/bbs/BbsInsert.do";
				form.submit();
            } else {
            	alert(jData.result_msg);
                form.action="/front/uia/LoginUsr.do";
                form.submit();
            }
        },
        error : function(xhr,status,error) {
            //alert(error);
        }
    });
}

//삭제버튼 눌렀을 때
function deleteSubmit(nttId){
	if(confirm("삭제하시겠습니까?")) {
		document.updateForm.params.value = nttId;
		document.updateForm.action = "<c:out value='/mng/bbs/deleteBbsList.do'/>";
		document.updateForm.submit();
	}else 
		return false;
}




//수정P 에서 저장 버튼
function updateSubmit(form){
		if(form.title.value==""){
			alert("제목을 입력 하십시오.");
			form.title.focus();
			return ; //focus 커서이동
		}
		form.content.value = $('#summernote').summernote('code');
		$("#smnoteTr").remove();
		if(form.content.value==""){
			alert("내용을 입력 하십시오.");
			form.content.focus();
			return ; //focus 커서이동
		}
		if(form.file_0.value == "" && $("input[name=oldFileId]").val()=="") {
	        alert("썸네일을 추가하지 않았습니다.");
	        return;
	    }
		if(confirm("저장하시겠습니까?")) {
			$.ajax({
	      	  url     : "<c:url value='/com/loginConfirm.do'/>",
	      	  type    : "POST",
	       	 dataType: "text",
	       	 success : function(data) {
	        	    // JSON 객체로 변환
	         	  	var jData = JSON.parse(data);
	          	  if(jData.result_cd == "200") {
						form.action = "/mng/bbs/relatedContent/updateRelatedContent.do";
						form.submit();
	          	  } else {
	            		alert(jData.result_msg);
	           	     form.action="/usr/uia/LoginUsr.do";
	           	     form.submit();
	           	 }
	       	 },
	        	error : function(xhr,status,error) {
	        	}
	    	});
		}else
			return false;
	}
	
//등록 화면 목록버튼
function WiListBack(){
	if (confirm("해당 내용은 저장되지 않습니다.\n목록으로 돌아가시겠습니까?")) {
		location.href = "/mng/bbs/relatedContent/BbsDetailList.do?BbsId=1&menuNo=1000001";
	} else
		return false;
}
//수정 화면 목록버튼
function UpListBack(){
	if (confirm("해당 내용은 저장되지 않습니다.\n목록으로 돌아가시겠습니까?")) {
		document.updateForm.action ="/mng/bbs/relatedContent/BbsDetailList.do?BbsId=1&menuNo=1000001";
		document.updateForm.submit();
	} 
	
}



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
function fn_imgFileDel(){
	if(confirm("이미지를 삭제하시겠습니까?") == true){
		
		$('.file').hide();
		$('.newFile').show();	
		$("input[name=oldFileId]").val("")
		
	
		
		
		
	}else{

	}
}
</script>