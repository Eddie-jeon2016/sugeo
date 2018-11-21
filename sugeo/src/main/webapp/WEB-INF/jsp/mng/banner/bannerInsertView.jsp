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
<script type="text/javascript">
//글 등록
function writeSubmit(form){
	
	var _arrExt = new Array("jpg", "jpeg", "png","JPG","JPEG","PNG");
	var check = true;
 	var fileArray = new Array();
 	
	 for(var n = 1; n < 4; n++) {
		var filePath = $('.file_'+n).val();
		if(filePath == "") {
			continue;
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
	} 
	if(!check) {
		alert('사용할 수 없는 확장자입니다.');
		return false;	

	}else {
		$.ajax({
	        url     : "<c:url value='/com/loginConfirm.do'/>",
	        type    : "POST",
	        dataType: "text",
	        success : function(data) {
	            // JSON 객체로 변환
	           	var jData = JSON.parse(data);
	            if(jData.result_cd == "200") {
					form.action = "/mng/banner/bannerInsert.do";
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
		/* $.ajax({
      	 url     : "<c:url value='/mng/banner/bannerInsert.do'/>",
       	 type    : "POST",
       	 dataType: "text",
       	 //data	: params,
       	data : {fileId1 : fileArray[0], fileId2 : fileArray[1], fileId3 : fileArray[2]},
       	 success : function(data) {
        	    // JSON 객체로 변환
         	  	var jData = JSON.parse(data);
         	   if(jData.code == "200") {
					alert("저장되었습니다.")
          	  } else {
          	  	alert("비동기 들어와서 오류인데 일어날 수 없지");
          	  }
       	 },
       	 error : function(xhr,status,error) {
       			alert(error);
       	 }
    	}); */
	}
}


</script>
<div class="usrposit"><!-- usrposit (s) -->

    <h3>메인 관리</h3>
   <!--  <ul>
        <li>메인 관리</li>
    </ul> -->

</div>
<div id="maininner"><!-- maininner (s)-->
<form name="writeMainForm" id="writeMainForm" method="post" enctype="multipart/form-data">
    <table class="tbl_lst tdleft">
        <caption>게시판 상세</caption>
        <colgroup>
            <col style="width: 15%;">
            <col style="width: 30%;">
            <col style="width: 15%;">
            <col style="width: 30%;">
        </colgroup>
        <tbody>
			
			
			<tr>
				<th scope="row"><label>메인 이미지</label></th>
				<td colspan="3"><input name="file_1" id="egovComFileUploader" type="file" class="file_1"
					title="첨부파일입력" /></td>

			</tr>
			<!-- <tr>
				<th scope="row"><label>메인 이미지2</label></th>
				<td colspan="3"><input name="file_2" id="egovComFileUploader" type="file" class="file_2"
					title="첨부파일입력" /></td>
			</tr>

			<tr>
				<th scope="row"><label>메인 이미지3</label></th>
				<td colspan="3"><input name="file_3" id="egovComFileUploader" type="file" class="file_3"
					title="첨부파일입력" /></td>
			</tr> -->

			
		</tbody>
    </table>
</form>
    <div class="centerbtnwrp">
       
                <span><a href="javascript:writeSubmit(document.writeMainForm);" class="abtn indbtn">저장</a></span>
            
    </div>
</div>
