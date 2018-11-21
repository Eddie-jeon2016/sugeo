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
    <h3>이벤트 등록</h3>
   <!--  <ul>
        <li>이벤트 등록 및 관리</li>
    </ul> -->
</c:if>
<c:if test="${cmd ne 'insert' }">
    <h3>이벤트 수정</h3>
   <!--  <ul>
        <li>이벤트 등록 및 관리</li>
    </ul> -->
</c:if>
</div>

<%-- <form name="listForm" id="listForm" method="post">
    <input type="hidden" name="bbsId"/>
</form>
    
<c:if test="${result.thumbAtchFileId!='0'||result.thumbAtchFileId!=''}">
    <c:set var="thumbnail" value="/cmm/fms/getImage.do?atchFileId=${result.thumbAtchFileId}&fileSn="/>
</c:if>
<c:if test="${result.thumbAtchFileId=='0' || result.thumbAtchFileId == ''}">
    <c:set var="thumbnail" value="/images/sub/blank2.gif"/>
</c:if> --%>
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
                
              
              <c:if test="${ cmd eq 'insert' }">
                <input type="hidden"  id="evnetTypeYCnt" name="evnetTypeYCnt" value=""/>
            </c:if> 
           <c:if test="${ cmd eq 'update' }">
           <input type="hidden"  id="pageIndex" name="pageIndex" value="${result.pageIndex}"/>
                <input type="hidden" name="eventNo" value="${ result.eventNo }"/>
            </c:if> 
                <tr>
                    <th scope="row">구분</th>
                    <td class="tl" colspan="3">
                        <select name="eventType" id="eventType" title="구분 선택" style="margin-bottom:5px;width:150px">
                        		<option value="">구분선택</option>
                            <c:forEach var="subCode" items="${subCodeList}" varStatus="status">
                                <option value="${subCode.subCode}"
                                        <c:if test="${cmd eq 'update' && subCode.subCode eq result.eventType}">selected="selected"</c:if>>${subCode.subCodeName}</option>
                            </c:forEach>
                        </select>
                    </td>
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
            
            
             <tr>
					<th scope="row">노출기간</th>
					<td class="tl" colspan="3">
						<input type="text" name="startDate" value="${cmd eq 'update' ? result.startDate : ''}" id="startDate">
						<c:if test="${cmd eq 'insert'}">
						<input type="text" name = "startHour" placeholder="00:00" id="startHour">							
						</c:if>
						<c:if test="${cmd ne 'insert'}">
						<input type="text" name = "startHour" id="startHour" value="${result.startHour}">							
						</c:if>
						
							 ~ 
						<input type="text" name="endDate"   value="${cmd eq 'update' ? result.endDate : ''}" id="endDate">
						<c:if test="${cmd eq 'insert'}">
							<input type="text" name = "endHour" placeholder="23:59" id="endHour">
						</c:if>
						<c:if test="${cmd ne 'insert'}">
							<input type="text" name = "endHour" id="endHour" value="${result.endHour}">					
						</c:if>
			</tr> 
			

            <textarea rows="15" cols="" id="content" style="width:100%; 
            			display:none;" name="content" ></textarea>
     
        <tr>
            <td colspan="4">
                <div id="summernote">${result.content }</div>
            </td>
        </tr>
        <tr>
                <th scope="row">설문지 신청 url</th>
                <td class="tl" colspan="3">
                    <c:set var="DQUOT">"</c:set>
                    <c:set var="string2" value="${fn:replace(result.url, DQUOT, '&#34;')}"/>
                    <input onkeydown="maxLengthCheck(event, 70);" type="text" name="url" id="url"
                           class="onetp445"
                           value="${string2 }"/>
                </td>
         </tr>
         <tr>
         		<c:if test="${cmd eq 'insert' }">
                <th scope="row">공개여부</th>
                <td class="tl" colspan="3">
                    <input type="radio" name="openYn" value="Y" checked="" onclick="javascript:checkYCnt(document.insertForm);">공개<br/>
					<input type="radio" name="openYn" value="N" checked="checked" >비공개
                </td>
                </c:if>
                <c:if test="${cmd eq 'update' }">
                <th scope="row">공개여부</th>
                <td class="tl" colspan="3">
                	<c:choose>
                		<c:when test="${result.openYn eq 'Y' }">
                			 <input type="radio" name="openYn" value="Y" checked="checked">공개<br/>
							 <input type="radio" name="openYn" value="N"  >비공개
                		</c:when>
                		<c:otherwise>
                			<input type="radio" name="openYn" value="Y" >공개<br/>
							 <input type="radio" name="openYn" value="N" checked="checked" >비공개
                		</c:otherwise>
					</c:choose>
                </td>
                </c:if>
         </tr>

        </tbody>
        </form>
    </table>

    <div class="centerbtnwrp">
        <c:choose>
            <c:when test="${cmd eq 'insert'}">
            <!-- <span><a href="#" onclick="window.open('http://www.naver.com', '_blank', 'width=550 height=500')" class="abtn grybtn">미리보기</a></span> -->
            <span><a href="javascript:openPreView();" class="abtn indbtn">미리보기</a></span>
                <span><a href="javascript:backEventDetailList_insert();" class="abtn indbtn">목록</a></span>
                <span><a href="javascript:writeSubmit(document.insertForm);" class="abtn indbtn">등록</a></span>
            </c:when>
            <c:otherwise>
            	<span><a href="javascript:openPreView();" class="abtn indbtn">미리보기</a></span>
                <span><a href="javascript:deleteSubmit('${result.eventNo }');" class="abtn indbtn">삭제</a></span>
                <span><a href="javascript:backEventDetailList_update('${result.pageIndex }');" class="abtn indbtn">목록</a></span>
                <span><a href="javascript:updateSubmit(document.updateForm,'${result.openYn}');" class="abtn indbtn">저장</a></span>
            </c:otherwise>
        </c:choose>
    </div>
</div>


<div id="mainPopupWrp">
       		
	            <div class="mainPopup" id="preview" style="display: none;">
	                <div class="innercont" >
	                	
		                	<a id="preview_url" target="_blank">
		                		<div id ="preview_name"  align="center">
		                		이벤트 내용들<br/>
		                		</div>
			                    <div id="popup_display" align="center">
		                		<img style="width: 200px;height: 200px;" src="/images/sub/blank2.gif" name="preThumbnail" class="preThumbnail" id="preThumbnail" alt="">
								
			                    </div>
		                    </a>
	                 <%--    <div id="popup_display_${popup.popupId }">

	                    </div> --%>
	                    <div class="closebtn" style="text-align: center;">
	                        
	                        <span><a href="javascript:closePopup();">닫기</a></span>
	                    </div>
	                </div>
	            </div>
            
         </div>

 
<script type="text/javascript">


$(document).ready(function() {
	$('#summernote').summernote({
		width: '97%'
		, height : 300
		, callbacks : {
			onImageUpload: function(files, editor, welEditable) {
				sendPopupFile(files[0], this);
			}
		}
	});
});
	var nowTemp = new Date();
	var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp
			.getDate(), 0, 0, 0, 0);
	var checkin = $('#startDate').fdatepicker({
		format : 'yyyy-mm-dd',
		//pickTime : true,
		startView : 2,
		language : 'ko',
		onRender : function(date) {
		}
	}).on('changeDate', function(ev) {
		if (ev.date.valueOf() > checkout.date.valueOf()) {
			var newDate = new Date(ev.date)
			newDate.setDate(newDate.getDate() + 1);
			checkout.update(newDate);
		}
	}).data('datepicker');
	var checkout = $('#endDate').fdatepicker({
		format : 'yyyy-mm-dd',
		//pickTime : true,
		startView : 2,
		language : 'ko',
		onRender : function(date) {
			return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
		}
	}).on('changeDate', function(ev) {
	}).data('datepicker');
	
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

//등록 버튼
function writeSubmit(form){
	form.content.value = $('#summernote').summernote('code');
	var text = form.content.value;
	text.replace(/ /g,"");
	$("#smnoteTr").remove();
	if(form.eventType.value =="") {
		alert("구분을 선택하지 않았습니다.");
		form.eventType.focus();
		return ; //focus 커서이동
	}
	if(form.title.value ==""){
		alert("제목을 입력 하십시오.");
		form.title.focus();
		return ; //focus 커서이동
	}
	
	if(form.startDate.value =="") {
		alert("시작날짜를 입력하세요.");
		form.startDate.focus();
		return ;
	}
	if(form.endDate.value =="") {
		alert("종료날짜를 입력하세요.");
		form.endDate.focus();
		return ;
	}
	
	if($('#summernote').summernote('isEmpty')) { 
        alert("내용을 입력하세요."); 
        return; 
    } 
	
	
	if(form.url.value =="") {
		alert("설문지 신청URL을 입력하지 않았습니다.");
		form.url.focus();
		return ;
	}
	

	
	if(form.openYn.value =="") {
		alert("공개여부를 입력하지 않았습니다.");
		form.openYn.focus();
		return ;
	}
	
	if(form.openYn.value =="Y" &&  form.evnetTypeYCnt.value == 1) {
		if(!confirm("이전 이벤트는 비공개로 바뀝니다.\n 현재 이벤트를 공개로 저장하시겠습니까?"))
			return ;
	}
	
	if(form.startHour.value =="")
		form.startDate.value = form.startDate.value+" 00:00";
	else
		form.startDate.value = form.startDate.value+" "+form.startHour.value;
	
	if(form.endHour.value =="")
		form.endDate.value = form.endDate.value+" 23:59";
	else
		form.endDate.value = form.endDate.value+" "+form.endHour.value;

	$.ajax({
        url     : "<c:url value='/com/loginConfirm.do'/>",
        type    : "POST",
        dataType: "text",
        success : function(data) {
            // JSON 객체로 변환
           	var jData = JSON.parse(data);
            if(jData.result_cd == "200") {
				form.action = "/mng/event/eventInsert.do";
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
function deleteSubmit(eventNo){

	document.updateForm.eventNo.value = eventNo;
	document.updateForm.action = "<c:out value='/mng/event/eventDelete.do'/>";
	document.updateForm.submit();
}

//수정P 에서 저장 버튼
function updateSubmit(form, openYn){
	
	if(form.eventType.value =="") {
		alert("구분을 선택하지 않았습니다.");
		form.eventType.focus();
		return ; //focus 커서이동
	}

	if (form.title.value == "") {
			alert("제목을 입력 하십시오.");
			form.title.focus();
			return; //focus 커서이동
		}
		form.content.value = $('#summernote').summernote('code');
		$("#smnoteTr").remove();
		if (form.content.value == "") {
			alert("내용을 입력 하십시오.");
			form.content.focus();
			return; //focus 커서이동
		}
		if(form.startDate.value == "" || form.endDate.value == ""){
			alert("접수기간을 입력하지 않았습니다.");
			form.startDate.focus();
			return ;
		}
		
		if(form.url.value =="") {
			alert("설문지 신청URL을 입력하지 않았습니다.");
			form.url.focus();
			return ;
		}
		var nextStep = true;
		if(openYn == 'N' && openYn != form.openYn.value ) {
			if(!confirm("현재 수정중인 이벤트가 노출되고, 최근 이벤트가 비공개로 바뀝니다.\n저장하시겠습니까?"))
				return ;
		}
		else if(openYn == 'Y' && openYn != form.openYn.value ) {
			if(!confirm("현재 수정중인 이벤트가 비공개되고,최근 이벤트가 공개로 바뀝니다.\n저장하시겠습니까?"))
				return ;
		}else {
			if(!confirm("저장하시겠습니까?"))
				return ;
		}
		if (nextStep) {
			$.ajax({
				url : "<c:url value='/com/loginConfirm.do'/>",
				type : "POST",
				dataType : "text",
				success : function(data) {
					// JSON 객체로 변환
					var jData = JSON.parse(data);
					if (jData.result_cd == "200") {
						form.action = "/mng/event/eventUpdate.do";
						form.submit();
					} else {
						alert(jData.result_msg);
						form.action = "/usr/uia/LoginUsr.do";
						form.submit();
					}
				},
				error : function(xhr, status, error) {
					//alert(error);
				}
			});
		}else
			return false;
	}

	// 목록버튼 눌렀을 때	
	function backEventDetailList_insert() {
		if (confirm("해당 내용은 저장되지 않습니다. 목록으로 돌아가시겠습니까?")) {
			location.href = "/mng/event/eventList.do";
		} else
			return false;
	}
	function backEventDetailList_update(page) {
		if (confirm("해당 내용은 저장되지 않습니다. 목록으로 돌아가시겠습니까?")) {
			location.href = "/mng/event/eventList.do?pageIndex="+page;
		} else
			return false;
	}
	function checkYCnt(form) {
		var param = form.eventType.value;

		$.ajax({
			url : "<c:url value='/mng/event/eventTypeCheckYCnt.do'/>",
			type : "POST",
			dataType : "text",
			data : {eventType : param},
			success : function(data) {
				// JSON 객체로 변환

				var jData = JSON.parse(data);
				if (jData.result_cd == "200") {
					return ;
				} else {
					$("#evnetTypeYCnt").val("1");
					return ;
				}
			},
			error : function(xhr, status, error) {
				//alert(error);
			}
		});
	}
	
	
	function openPreView() {
		var viewForm;
		if('${cmd}' =='insert')
			viewForm = document.insertForm;
		if('${cmd}' =='update')
			viewForm = document.updateForm;

		viewForm.content.value = $('#summernote').summernote('code');
		
		var url = "/front/event/eventPreview.do";
		var eventPreviewWin;
		eventPreviewWin = window.open("","eventPreview","width=1000,height=800,scrollorbars=yes,resizable=yes");
		viewForm.action = url;
		viewForm.method = "post";
		viewForm.target = "eventPreview";
		viewForm.submit();
		/* var text =  $('#summernote').summernote('code'); 
		var w = window.open("","미리보기","width=1000, height=500, menubar=no, status=no,scrollbars=yes"+
							"toolbar=no, location=no left="+(screen.availWidth-600)/2+",top="+(screen.availHeight-400)/2);
		w.document.write(text);
		w.document.body.scroll = "auto";
		w.document.close(); */
	}


	/**
	* MaxLength 이벤트 걸기.
	*/
	function maxLengthCheck(e, max) {
	   var obj = e.srcElement||e.target;
	   var key = e.keyCode;
	   var mx = max;
	   if(key == 8 ) return;
	   if (key < 32 || key > 126) {
	     mx++;
	     setTimeout(function(){
	       if(obj.value.length > max) {
	         obj.blur();
	         obj.value = obj.value.substring(0, max);
	         obj.focus();
	       }
	     },30);
	   }
	   if(obj.value.length >= mx) {
	     obj.blur();
	     setTimeout(function() {
	       obj.focus();
	     }, 30);
	   }
	 }
	
</script>