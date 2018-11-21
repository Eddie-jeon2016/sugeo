<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <style>
#preThumbnail {
	max-height: 500px;
	max-width: 300px;
}
</style>
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="/js/summernote/summernote.css" rel="stylesheet">
<script src="/js/summernote/summernote.js"></script>
<script src="/js/summernote/summerCustom.js"></script>

<link href="/js/foundation/foundation.min.css">
<script src="/js/foundation/foundation.min.js"></script>
<link href="/js/foundation/foundation-datepicker.min.css"
	rel="stylesheet">
<script src="/js/foundation/foundation-datepicker.min.js"></script>
<link href="//cdnjs.cloudflare.com/ajax/libs/foundicons/3.0.0/foundation-icons.css" rel="stylesheet">
<script src="/js/foundation/locale/foundation-datepicker.ko.js"></script>
 <link href="/css/main.css" rel="stylesheet" type="text/css" /> 
 <div class="usrposit">
	<h3>팝업 등록</h3>
 </div>

<style>
#popup_display {
	max-height: 500px;
	max-width: 300px;
}
</style>
<div id="maininner">
<form name="popupFrm" id="popupFrm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="content" id="content" />
	<div class="tbl_wrp">
		<table class="tbl_lst tdleft">
			<caption>리스트</caption>
			<colgroup>
				<col style="width: 15%;">
            	<col style="width: 85%;">
			</colgroup>
			<!-- <thead>
				<tr>
					<th scope="col" style="text-align: center" class="first">번호</th>
					<th scope="col" style="text-align: center">구분</th>
				</tr>
			</thead> -->
			<tbody>
				<tr>
					<th scope="row">팝업명</th>
					<td class="tl"><input type="text" name="popupName" id="popupName"
						class="onetp445" /></td>
				</tr>
				<tr>
					<th scope="row">URL</th>
					<td class="tl"><input type="text" name="url" class="onetp445"></td>
				</tr>
				<tr>
					<th scope="row"><label>이미지 첨부</label></th>
					<td colspan="3"><input type="hidden" name="file_sn"
						value="${thumbFile.fileSn}" /> <input type="file" name="file_0" 
						id="egovComFileUploader" title="파일"
						onchange="loadname(this,'preThumbnail',0)">  <br> 
						<a class="fileDel" onclick="fileDel('0')">파일 초기화</a> 
                       </td>
				</tr>

				<!-- <tr>
					<th scope="row">노출기간</th>
					<td class="tl"><input type="text" name="startDate"
						id="startDate"> ~ <input type="text" name="endDate"
						id="endDate"></td>
				</tr> -->
				
				<tr>
					<th scope="row">노출기간</th>
					<td class="tl" colspan="3">
						<input type="text" name="startDate" id="startDate">
						<input type="text" name = "startHour" placeholder="00:00" id="startHour">							
							 ~ 
						<input type="text" name="endDate"  id="endDate">
						<input type="text" name = "endHour" placeholder="23:59" id="endHour">
	
				</tr> 
				<tr>
					<th scope="row" >닫기유형</th>
					<td colspan="3" align="center"><input type="radio" name="closeType" value="T"
						checked="checked">오늘 하루 열지 않음&nbsp;&nbsp; <input
						type="radio" name="closeType" value="W" checked="checked">일주일동안
						열지 않음&nbsp;&nbsp; <input type="radio" name="closeType" value="F"
						style="padding-left: 10px;">다시 열지 않음</td>
				</tr>
			</tbody>
		</table>
	</div>
</form>

<div class="centerbtnwrp">

	<span><a href="javascript:openPopup(document.popupFrm);" class="abtn indbtn">미리보기</a></span>
    <span><a href="javascript:popupList();" class="abtn indbtn">목록</a></span>
	<span><a href="javascript:popupInsert(document.popupFrm);" class="abtn indbtn">등록</a></span>
</div>

 <section id="main_wrp"> <!-- main_wrp (s)-->
			<!-- 팝업   (s)-->
<div id="mainPopupWrp">
       		
	            <div class="mainPopup" id="preview" style="display: none;" >
	                <div class="innercont" >
	                	<div class="txtR" > 
				<a href="#" class="btnClose lpThisClose ppclosetop" onclick="closePopup('01'); return false;" > 
					<img src="/images/common/btn_popCls1.jpg" alt="닫기" title="닫기" align="right">
				</a>
			</div>
		                		<br/>
		                	<!-- <a id="preview_url" target="_blank"> -->
		                		<div id ="preview_name"  align="center">
		                		</div>
			                    <div id="popup_display" align="center" >
		                		<img src="/images/sub/blank2.gif"  name="preThumbnail" class="preThumbnail" id="preThumbnail" alt="">
								
			                    </div>
		                    <!-- </a> -->
	                 <%--    <div id="popup_display_${popup.popupId }">

	                    </div> --%>
	                    <!-- <div class="closebtn" style="text-align: center;">
	                        
	                        <span><a href="javascript:closePopup();">닫기</a></span>
	                    </div> -->
	                </div>
	            </div>
            
         </div>
			<!-- 팝업   (e)-->
		
 </section>
</div>

<script type="text/javascript">

var nowTemp = new Date();
var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp
		.getDate(), 0, 0, 0, 0);
var checkin = $('#startDate').fdatepicker({
	format : 'yyyy-mm-dd',
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
	startView : 2,
	language : 'ko',
	onRender : function(date) {
		return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
	}
}).on('changeDate', function(ev) {
}).data('datepicker');



	function popupInsert(form) {
		 if(form.popupName.value =="") {
			alert("팝업제목을 입력하세요.");
			form.popupName.focus();
			return fasle;
		} 
		if(form.url.value =="") {
			alert("URL을 입력하세요.");
			form.url.focus();
			return fasle;
		}
		
		var _arrExt = new Array("jpg", "jpeg", "png","JPG","JPEG","PNG");
		var check = true;
	 	var fileArray = new Array();
		var filePath = $("#egovComFileUploader").val();
		if(filePath == "") {
			alert("이미지를 첨부해주세요.");
			return false;
		}else {
			var fileExt = filePath.substring(filePath.lastIndexOf('.')+1);
			for(var i =0; i < _arrExt.length; i++) {
				if(fileExt == _arrExt[i]) {
					check = true;
					break;
				}else {
					check = false;
				}
			}
		}
		if(!check) {
			alert('사용할 수 없는 확장자입니다.');
			return false;	
		}
		if(form.startDate.value =="") {
			alert("시작날짜를 입력하세요.");
			return ;
		}
		if(form.endDate.value =="") {
			alert("종료날짜를 입력하세요.");
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
		
		document.popupFrm.action = '/mng/pop/popupInsert.do';
		document.popupFrm.submit();
	}
	
	function popupList(){
		if(confirm("해당내용은 저장되지 않습니다.목록으로 돌아가시겠습니까?")) {			
			location.href = "/mng/pop/popupList.do";
		}else {
			return false;
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
//	 		$("#attach_"+file_sn).empty();
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


	function openPopup(form){
		var filePath = $("#egovComFileUploader").val();
		if(filePath == "") {
			alert("이미지를 첨부해주세요.");
			return false;
		}
		/* $("#preview_name").html(form.popupName.value); */
		$("#preview").show();
	}
	function closePopup(){
		$("#preview").hide();
	}
	
	
</script>