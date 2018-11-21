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
    <h3>이벤트 공지등록</h3>
    <!-- <ul>
        <li>이벤트 공지등록</li>
    </ul> -->
</c:if>
<c:if test="${cmd ne 'insert' }">
    <h3>이벤트 공지수정</h3>
    <!-- <ul>
        <li>이벤트 공지수정</li>
    </ul> -->
</c:if>
</div>

<div id="maininner"><!-- maininner (s)-->

    <table class="tbl_lst tdleft">
        <caption>게시판 상세</caption>
        <colgroup>
            <col style="width: 15%;">
            <col style="width: 30%;">
            <col style="width: 15%;">
            <col style="width: 30%;">
        </colgroup>
        <tbody>
        <form name="${cmd eq 'insert' ? 'insertForm' : 'updateForm'}"
              id="${cmd eq 'insert' ? 'insertForm' : 'updateForm'}" method="post" enctype="multipart/form-data">
                <input type="hidden" name="bbsId" value="${ bbsManageVO.bbsId }"/>
                <input type="hidden" name="menuNo" value="${ bbsManageVO.menuNo }"/>
                <input type="hidden" name="nttId" value="${ bbsManageVO.nttId }"/>
                <input type="hidden" name="pageIndex" value="${searchVO.pageIndex}"/>
                <input type="hidden" name="params" />
           <c:if test="${ cmd eq 'update' }">
            </c:if> 
               <tr>
         		<c:if test="${cmd eq 'insert' }">
                <th scope="row">상단노출</th>
                <td class="tl" colspan="3">
					<input type="radio" name="noticeYn" value="N" checked="checked"  >N 일반게시물로 등록<br/>
                    <input type="radio" name="noticeYn" value="Y" onclick="javascript:checkYCnt();" >Y 게시판 상단에 노출
                </td>
                </c:if>
                <c:if test="${cmd eq 'update' }">
                <th scope="row">상단노출</th>
                <td class="tl" colspan="3">
                	<c:choose>
                		<c:when test="${result.noticeYn eq 'Y' }">
							 <input type="radio" name="noticeYn" value="N"  >N 일반게시물로 등록<br/>
                			 <input type="radio" name="noticeYn" value="Y" checked="checked">Y 게시판 상단에 노출
                		</c:when>
                		<c:otherwise>
							 <input type="radio" name="noticeYn" value="N" checked="checked" >N 일반게시물로 등록<br/>
                			<input type="radio" name="noticeYn" value="Y" onclick="javascript:checkYCnt();" >Y 게시판 상단에 노출
                		</c:otherwise>
					</c:choose>
                </td>
                </c:if>
         </tr>
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
                <span><a href="javascript:backEventNoticeList('${searchVO.pageIndex}');" class="abtn indbtn">목록</a></span>
                <span><a href="javascript:writeSubmit(document.insertForm);" class="abtn indbtn">등록</a></span>
            </c:when>
            <c:otherwise>
                <span><a href="javascript:deleteSubmit('${result.nttId }');" class="abtn indbtn">삭제</a></span>
                <span><a href="javascript:backEventNoticeList('${searchVO.pageIndex}');" class="abtn indbtn">목록</a></span>
                <span><a href="javascript:updateSubmit(document.updateForm);" class="abtn indbtn">저장</a></span>
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
				sendPopupFile(files[0], this);
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
	var text = form.content.value;
	text.replace(/ /g,"");
	$("#smnoteTr").remove();
	
	if(form.title.value ==""){
		alert("제목을 입력 하십시오.");
		form.title.focus();
		return ; //focus 커서이동
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
		form.content.value = $('#summernote').summernote('code');
		var text = form.content.value;
		text.replace(/ /g,"");
		$("#smnoteTr").remove();
		
		if(form.title.value==""){
			alert("제목을 입력 하십시오.");
			form.title.focus();
			return ; //focus 커서이동
		}
		if($('#summernote').summernote('isEmpty')) { 
	        alert("내용을 입력하세요."); 
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
						form.action = "/front/bbs/BbsUpdate.do";
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
	
//목록버튼 눌렀을 때	
function backEventNoticeList(pageNo) {
	if (confirm("해당 내용은 저장되지 않습니다. 목록으로 돌아가시겠습니까?")) {
		location.href = "/mng/notice/BbsDetailList.do?BbsId=8&menuNo=1000008&pageIndex="+pageNo;
	} else
		return false;
}

function checkYCnt() {
	$.ajax({
		url : "<c:url value='/mng/event/checkYCnt.do'/>",
		type : "POST",
		dataType : "text",
		success : function(data) {
			// JSON 객체로 변환
			var jData = JSON.parse(data);
			if (jData.result_cd == "200") {
				return false;
			} else {
				alert("상단게시글을 더 이상 등록할 수 없습니다.");
				$("input:radio[name='noticeYn']:radio[value='Y']").prop("checked",false);
				$("input:radio[name='noticeYn']:radio[value='N']").prop("checked",true);
				return false;
			}
		},
		error : function(xhr, status, error) {
			//alert(error);
		}
	});
}
</script>