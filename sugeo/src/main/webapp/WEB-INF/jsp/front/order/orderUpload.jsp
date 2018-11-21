<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
$(document).ready(function(){
	deleteBind();
});

function deleteBind() {
	$(".pictureDel").on('click',function(){ 
		$(this).parent().find('img').attr("src","");
		$(this).addClass("picture");
		$(this).removeClass("pictureDel");
	});
}
function fileClick(idx){
	if($('#photos'+idx).parent().find('.pictureBtn').hasClass('picture')){
		$('#photos'+idx).click();
	}
}
function loadname(img, review_img, num) {
	var divParent = $(img).closest("div");
	var button = divParent.children(".picture");
	button.addClass("pictureDel");
	button.unbind();
	button.removeClass("picture");
	deleteBind();

    var is_ie = (navigator.appName == "Microsoft Internet Explorer"),
            path = img.value,
            ext = path.substring(path.lastIndexOf('.') + 1).toLowerCase();

    var txt = '<div>' + 'is_ie - ' + is_ie + '</div>' +
            '<div>' + 'path - ' + path + '</div>' +
            '<div>' + 'ext - ' + ext + '</div>';

    if (ext == "gif" || ext == "jpeg" || ext == "jpg" || ext == "png") {
        if (is_ie) {
            img.select();
            var img_path = document.selection.createRangeCollection()[0].text.toString() + '?' + (new Date().getTime().toString());
            $('#' + review_img).attr('src', img_path);
            $('a#' + review_img).attr('href', img_path);
        } else {
            if (img.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#' + review_img).attr('src', e.target.result);
                }
                reader.readAsDataURL(img.files[0]);
            }
        }
    }
}

function insertOrderUpload(){
	  $('#orderUploadForm').ajaxSubmit({
          url: '<c:url value="/front/order/insertUploadOder.do"/>',
          type: 'POST',
          dataType: 'json',
          success: function (data) {
              if(data.result_code == '200') {
    			  alert(data.msg);        	  
	              document.location.href = "/front/item/itemView.do";
              }else {
            	  alert(data.msg);
              }
          },
          error: function (xhr, error) {
              console.log(xhr);
              console.log(error);
          },
      });
	
}

function addUpload(idx) {
	/* var resultStr = '';
	for(var i = idx; i < idx+2; i++) {
		var str ='';
		str += '<div>';
		str += '<button title="사진첨부" onclick="fileClick('+i+');" class="pictureBtn picture" type="button">사진첨부</button>';
		str += ' <input type="file" name="uploadFile'+i+'" id="photos'+i+'" title="이미지 선택"  style="visibility:hidden; margin-top:0" onchange=loadname(this,&#39;preview'+i+'&#39;,0);">';
		str += '<img src="" alt="" id="preview'+i+'">';
		str += '</div>';
		resultStr += str;
	}
	$('#uploadDiv').after(resultStr); */
	
	$(".addUpload").css("display","block");
	
}
</script>
	<div id="wrap">
	<jsp:include page="../include/top.jsp"/>
            <div class="container"> 
        	<form name="orderUploadForm" id="orderUploadForm" enctype="multipart/form-data" method="post">
        	<input type="hidden" id="itemNo" name="itemNo" value="${itemNo}">
        	<div class="request_Box"> 
				<div> <!-- 20181013 이미지 들어갔을 시 삭제 버튼 -->
					<!-- <button title="사진 첨부" href="#" class="pictureDel">사진 삭제</button> -->
					<button title="사진 첨부" onclick="fileClick(1);" class="pictureBtn picture" type="button">사진첨부</button>
					  <input type="file" name="uploadFile1" id="photos1" title="이미지 선택"
                           style="visibility:hidden; margin-top:0" onchange="loadname(this,'preview1',0);">
					<img src="" alt="" id="preview1">
				</div>
				<div> <!-- 20181013 이미지 들어갔을 시 삭제 버튼 -->
					<!-- <button title="사진 첨부" href="#" class="pictureDel">사진 삭제</button> -->
					<button title="사진 첨부" onclick="fileClick(2);" class="pictureBtn picture" type="button">사진 첨부</button>
					  <input type="file" name="uploadFile2" id="photos2" title="이미지 선택"
                           style="visibility:hidden; margin-top:0" onchange="loadname(this,'preview2',0);">
					<img src="" alt="" id="preview2">
				</div>
				<div> <!-- 20181013 이미지 들어갔을 시 삭제 버튼 -->
					<!-- <button title="사진 첨부" href="#" class="pictureDel">사진 삭제</button> -->
					<button title="사진 첨부" onclick="fileClick(3);" class="pictureBtn picture" type="button">사진 첨부</button>
					  <input type="file" name="uploadFile3" id="photos3" title="이미지 선택"
                           style="visibility:hidden; margin-top:0" onchange="loadname(this,'preview3',0);">
					<img src="" alt="" id="preview3">
				</div>
				<div id="uploadDiv"> <!-- 20181013 이미지 들어갔을 시 삭제 버튼 -->
					<!-- <button title="사진 첨부" href="#" class="pictureDel">사진 삭제</button> -->
					<button title="사진 첨부" onclick="fileClick(4);" class="pictureBtn picture" type="button">사진 첨부</button>
					  <input type="file" name="uploadFile4" id="photos4" title="이미지 선택"
                           style="visibility:hidden; margin-top:0" onchange="loadname(this,'preview4',0);">
					<img src="" alt="" id="preview4">
				</div>
				<div class="addUpload" style="display: none;"> 
					<button title="사진 첨부" onclick="fileClick(5);" class="pictureBtn picture" type="button">사진 첨부</button>
					  <input type="file" name="uploadFile5" id="photos5" title="이미지 선택"
                           style="visibility:hidden; margin-top:0" onchange="loadname(this,'preview5',0);">
					<img src="" alt="" id="preview5">
				</div>
				<div class="addUpload" style="display: none;"> 
					<button title="사진 첨부" onclick="fileClick(6);" class="pictureBtn picture" type="button">사진 첨부</button>
					  <input type="file" name="uploadFile6" id="photos6" title="이미지 선택"
                           style="visibility:hidden; margin-top:0" onchange="loadname(this,'preview6',0);">
					<img src="" alt="" id="preview6">
				</div>
				<!-- <div>
					<button title="사진 삭제" href="#" class="pictureDel" type="button">사진 삭제</button>
					<img src="/images/img/test_img.jpg" alt="">
				</div> -->
			</div>
            
            <div class="btn_wrap pdtb f_l w100p">
				<button title="사진 더 첨부하기"class="btn gray icon_camera" type="button" onclick="javascript:addUpload(5);"><span></span> 사진 더 첨부하기</button>
			</div>
        
			<p>간단한 사진을 통해 견적을 도와드립니다. <br>
			사진과 설명을 주시면 최대한 빠르고 자세한 견적가와 안내를 도와드리겠습니다. 감사합니다.</p>
			
			<textarea id="reqDesc" name="reqDesc" cols="30" rows="10" title="상세정보 등록" placeholder="상세정보를 적어주세요~^^" style="height:75px; margin:5px 0 55px 0"></textarea>
			
			<div class="p_f w100p">
				<button title="견적 문의" class="btn green" onclick="javascript:insertOrderUpload();" type="button">견적 문의</button>
			</div>
		</form>
 		</div><!-- container (E) --> 
 	</div><!-- wrap (E) -->