<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader("Expires",0);
%>
<script type="text/javaScript" language="javascript">
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
/**
* 첨부파일 삭제 처리
*/
function fn_deleteFile(atchFileId, fileSn, atchFileCnt) {
	if(confirm('<spring:message code="common.delete.msg" />')){
		form = document.updateForm;
		form.atchFileId.value = atchFileId;
// 		form.fileSn.value = fileSn;
		form.action = "<c:url value='/cmm/fms/deleteFileInfs.do'/>";
		form.submit();
	}
}
/**
* 페이지 이동 함수
*/
function fnLinkPage(pageNo){
	 	document.listForm.pageIndex.value = pageNo;
	    document.listForm.action = "<c:out value='/front/bbs/BbsMain.do?menuNo='/>${searchVO.menuNo}";
	    document.listForm.cmd.value="list";
	    document.listForm.submit();
}
/* ********************************************************
* 조회 처리
******************************************************** */
function fnSearch(){
	document.listForm.pageIndex.value = 1;
	document.listForm.cmd.value="list";
	document.listForm.action = "<c:out value='/front/bbs/BbsMain.do?menuNo='/>${searchVO.menuNo}";
  	document.listForm.submit();
}
//수정페이지에서 목록버튼
function UpListBack(bbsid){
	document.updateForm.cmd.value="list";
	document.updateForm.action = "<c:out value='/front/bbs/BbsMain.do?menuNo='/>${searchVO.menuNo}";
	document.updateForm.submit();
}
//등록페이지에서 목록버튼
function WiListBack(bbsid){
	document.writeForm.cmd.value="list";
	document.writeForm.action = "<c:out value='/front/bbs/BbsMain.do?menuNo='/>${searchVO.menuNo}";
	document.writeForm.submit();
}
//수정P 에서 목록으로 이동
function listBack(bbsid){
	document.selectForm.cmd.value="list";
	document.selectForm.action = "<c:out value='/front/bbs/BbsMain.do?menuNo='/>${searchVO.menuNo}";
	document.selectForm.submit();
}
function AnswerListBack(bbsid){
	document.answerForm.cmd.value="list";
	document.answerForm.action = "<c:out value='/front/bbs/BbsMain.do?menuNo='/>${searchVO.menuNo}";
	document.answerForm.submit();
}
/* ********************************************************
* 말머리 별 검색
******************************************************** */
function fnCateSearch(id){
	document.listForm.searchCateId.value = id;
	document.listForm.pageIndex.value = 1;
	document.listForm.cmd.value="list";
	document.listForm.action = "<c:out value='/front/bbs/BbsMain.do?menuNo='/>${searchVO.menuNo}";
  	document.listForm.submit();
  	return false;
}
//등록페이지로 이동
function writeForm(bbs_id){
	var params = "bbsId="+ bbs_id;
	$.ajax({
        url     : "<c:url value='/com/loginConfirm.do'/>",
        type    : "POST",
        dataType: "text",
        success : function(data) {
            // JSON 객체로 변환
           	var jData = JSON.parse(data);
            if(jData.result_cd == "200") {
				$.ajax({
					url     : "<c:url value='/front/bbs/BbsInfoValidator.do'/>",
					type    : "POST",
					data    : params,
					dataType: "text",
					success : function(data) {
					// JSON 객체로 변환
					var jData = JSON.parse(data);
						if(jData.result_cd == "200") {
							document.listForm.cmd.value = "write";
							document.listForm.action = "<c:out value='/front/bbs/BbsMain.do?menuNo='/>${searchVO.menuNo}";
							document.listForm.submit();
						} else {
							alert(jData.result_msg);
						}
					},
			       error : function(xhr,status,error) {
			           //alert(error);
			       }
			   });
            } else {
            	alert(jData.result_msg);
            	document.listForm.action="<c:out value='/front/bbs/BbsMain.do?menuNo='/>${searchVO.menuNo}";
            	document.listForm.submit();
            }
        },
        error : function(xhr,status,error) {
            //alert(error);
        }
    });
}
//등록 버튼
function writeSubmit(form, atchCount){
	form.content.value = $('#summernote').summernote('code');
	$("#smnoteTr").remove();
	if(form.title.value ==""){
		alert("제목을 입력 하십시오.");
		form.title.focus();
		return ; //focus 커서이동
	}
// 	if(cn < 1){
// 		alert("내용을 입력 하십시오.");
// 		form.ntt_cn.focus();
// 		return ; //focus 커서이동
// 	}

	var limitExt = ['gif','png','jpg','jpeg','pdf','xls','xlsx','hwp','txt','doc','docs','ppt','pptx','zip'];
	var fileCount = $('#fileCount').val();

	for(var i=0; i<=fileCount; i++) {
		var fileName = $("input[name=file_"+i+"]").val();
		var fileExt = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
		if(i == 0) {
			if($.inArray(fileExt, ['gif','png','jpg','jpeg']) == -1 && fileName != '') {
				alert('썸네일은 gif, png, jpg확장자만 업로드 할수 있습니다.');
			    return;
			}
		} else {
			if($.inArray(fileExt, limitExt) == -1 && fileName != '') {
				alert('첨부파일은 이미지 및 문서파일만 업로드 할수 있습니다.');
			    return;
			}
		}
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
//상세보기 이동
function selectBoardView(bbsid, id){

	document.listForm.selectId.value = id;
	document.listForm.selectbbsId.value = bbsid;
	document.listForm.cmd.value = "view";
	document.listForm.action = "<c:out value='/front/bbs/BbsMain.do?menuNo=${searchVO.menuNo}'/>";
	document.listForm.submit();
}
//수정페이지로 이동
function updateBoardView(bbsid, id){
	document.selectForm.nttId.value = id;
	document.selectForm.cmd.value = "update";
	document.selectForm.action = "<c:out value='/front/bbs/BbsMain.do?menuNo=${searchVO.menuNo}'/>";
	document.selectForm.submit();
}
function faqUpdateBoardView(bbsid, id){
	document.listForm.ntt_id.value = id;
	document.listForm.cmd.value = "update";
	document.listForm.action = "<c:out value='/front/bbs/BbsMain.do?menuNo=${searchVO.menuNo}'/>";
	document.listForm.submit();
}
//답글페이지로 이동
function insertBbsAnswerView(bbsid, id){
	$.ajax({
        url     : "<c:url value='/com/loginConfirm.do'/>",
        type    : "POST",
        dataType: "text",
        success : function(data) {
            // JSON 객체로 변환
           	var jData = JSON.parse(data);
            if(jData.result_cd == "200") {
				document.selectForm.ntt_id.value = id;
				document.selectForm.cmd.value = "answer";
				document.selectForm.action = "<c:out value='/front/bbs/BbsMain.do?menuNo=${searchVO.menuNo}'/>";
				document.selectForm.submit();
            } else {
            	alert(jData.result_msg);
                document.selectForm.action="/usr/uia/LoginUsr.do";
                document.selectForm.submit();
            }
        },
        error : function(xhr,status,error) {
            //alert(error);
        }
    });
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

		var limitExt = ['gif','png','jpg','jpeg','pdf','xls','xlsx','hwp','txt','doc','docs','ppt','pptx','zip'];
		var fileCount = $('#fileCount').val();

		for(var i=0; i<=fileCount; i++) {
			var fileName = $("input[name=file_"+i+"]").val();
			var fileExt = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
			if(i == 0) {
				if($.inArray(fileExt, ['gif','png','jpg','jpeg']) == -1 && fileName != '') {
					alert('썸네일은 gif, png, jpg확장자만 업로드 할수 있습니다.');
				    return;
				}
			} else {
				if($.inArray(fileExt, limitExt) == -1 && fileName != '') {
					alert('첨부파일은 이미지 및 문서파일만 업로드 할수 있습니다.');
				    return;
				}
			}
		}
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
	            //alert(error);
	        }
	    });
	}
//select view 삭제 시도
	function deleteSubmit(ntt_id){
		$.ajax({
	        url     : "<c:url value='/com/loginConfirm.do'/>",
	        type    : "POST",
	        dataType: "text",
	        success : function(data) {
	            // JSON 객체로 변환
	           	var jData = JSON.parse(data);
	            if(jData.result_cd == "200") {
					if(confirm('<spring:message code="common.delete.msg" />')){
						document.selectForm.selectId.value=ntt_id;
						document.selectForm.cmd.value = "list";
						document.selectForm.action = "/front/bbs/BbsDelete.do?menuNo=${searchVO.menuNo}";
						document.selectForm.submit();
					}
	            } else {
	            	alert(jData.result_msg);
	                document.selectForm.action="/usr/uia/LoginUsr.do";
	                document.selectForm.submit();
	            }
	        },
	        error : function(xhr,status,error) {
	            //alert(error);
	        }
	    });
	}
//수 정페이지에서 삭제 처리
	function deleteUpSubmit(ntt_id){
		$.ajax({
	        url     : "<c:url value='/com/loginConfirm.do'/>",
	        type    : "POST",
	        dataType: "text",
	        success : function(data) {
	            // JSON 객체로 변환
	           	var jData = JSON.parse(data);
	            if(jData.result_cd == "200") {
					if(confirm('<spring:message code="common.delete.msg" />')){
						document.updateForm.selectId.value=ntt_id;
						document.updateForm.cmd.value = "list";
						document.updateForm.action = "/front/bbs/BbsDelete.do'/>";
						document.updateForm.submit();
					}
	            } else {
	            	alert(jData.result_msg);
	                document.selectForm.action="/usr/uia/LoginUsr.do";
	                document.selectForm.submit();
	            }
	        },
	        error : function(xhr,status,error) {
	            //alert(error);
	        }
	    });
	}
//FAQ 리스트페이지에서 삭제 처리
function faqUdeleteSubmit(ntt_id){
	$.ajax({
	       url     : "<c:url value='/com/loginConfirm.do'/>",
	       type    : "POST",
	       dataType: "text",
	       success : function(data) {
	           // JSON 객체로 변환
	          	var jData = JSON.parse(data);
	           if(jData.result_cd == "200") {
					if(confirm('<spring:message code="common.delete.msg" />')){
						document.listForm.selectId.value=ntt_id;
						document.listForm.cmd.value = "list";
						document.listForm.action = "/front/bbs/BbsDelete.do";
						document.listForm.submit();
					}
	           } else {
	           	alert(jData.result_msg);
	               document.listForm.action="/usr/uia/LoginUsr.do";
	               document.listForm.submit();
	           }
	       },
	       error : function(xhr,status,error) {
	           //alert(error);
	       }
	   });
	}
//공유여부 체크확인
function share_at_check(){
	var cmd = $("#cmd").val();
	if(cmd == 'write'){
		if ( document.writeForm.ntt_share_at_chk.checked ) {
			document.writeForm.ntt_share_at.value = 'Y';
		} else {
			document.writeForm.ntt_share_at.value = 'N';
		}
	}else{
		if ( document.updateForm.ntt_share_at_chk.checked ) {
			document.updateForm.ntt_share_at.value = 'Y';
		} else {
			document.updateForm.ntt_share_at.value = 'N';
		}
	}
}
//공지여부 체크확인
function notice_at_check(){
	var cmd = $("#cmd").val();
	if(cmd == 'write'){
		if ( document.writeForm.notice_at_chk.checked ) {
			document.writeForm.notice_at.value = 'Y';
		} else {
			document.writeForm.notice_at.value = 'N';
		}
	}else{
		if ( document.updateForm.notice_at_chk.checked ) {
			document.updateForm.notice_at.value = 'Y';
		} else {
			document.updateForm.notice_at.value = 'N';
		}
	}
}
//비밀글여부 체크 확인
function sec_at_check(){
	var cmd = $("#cmd").val();
	if(cmd == 'write'){
		if ( document.writeForm.sec_at_chk.checked ) {
			document.writeForm.sec_at.value = 'Y';
		} else {
			document.writeForm.sec_at.value = 'N';
		}
	}else{
		if ( document.updateForm.sec_at_chk.checked ) {
			document.updateForm.sec_at.value = 'Y';
		} else {
			document.updateForm.sec_at.value = 'N';
		}
	}
}
//댓글입력
function fnCommentSubmit() {
	if($("#answer").val().length < 1){
		alert("댓글을 입력해 주세요");
		return false;
	}
	var params = {
		ntt_id : $("#ntt_id").val(),
		bbs_id : "<c:out value='${searchVO.bbsId}' />",
		answer : $("#answer").val(),
		ty_code :  $("#ty_code").val()
	};
	$.ajax({
        url     : "<c:url value='/com/loginConfirm.do'/>",
        type    : "POST",
        dataType: "text",
        success : function(data) {
            // JSON 객체로 변환
           	var jData = JSON.parse(data);
            if(jData.result_cd == "200") {
			   $.ajax({
			       url     : "<c:url value='/front/bbs/cmt/insertBbsComment.do'/>",
			       type    : "POST",
			       data    : params,
			       dataType: "text",
			       success : function(data) {
			           // JSON 객체로 변환
			          	var jData = JSON.parse(data);
			           if(jData.result_cd == "200") {
							alert("댓글을 등록하였습니다.");
				            $('#BbsCommentArea').load("<c:url value='/front/bbs/cmt/BbsCommentList.do'/>", params);
			           } else {
							alert(jData.result_msg);
			           }
			       },
			       error : function(xhr,status,error) {
			           //alert(error);
			       }
			   });
            } else {
            	alert(jData.result_msg);
                document.selectForm.action="/usr/uia/LoginUsr.do";
                document.selectForm.submit();
            }
        },
        error : function(xhr,status,error) {
            //alert(error);
        }
    });
}
//수정버튼 클릭 시
function fnCommentUpdate(ano){
	var param = {
			"answer_no" : ano
	};
	$(".answerInput").hide();
	$.ajax({
	        url     : "<c:url value='/front/bbs/cmt/selectCmtAnswer.do'/>",
	        type    : "POST",
	        data    : param,
	        dataType: "text",
	        success : function(data) {
	            // JSON 객체로 변환
	           	var jData = JSON.parse(data);
	            if(jData.result_cd == "200") {
	            	var addTr = "" +
			    			"<div class='replyWrite answerInput'>"+
			    			"<input type='hidden' name='answer_no' id='answer_no' value='"+jData.result_answer_no+"' />"+
			    			"<textarea rows='6'  name='answer' id='answer'>"+jData.result_answer+"</textarea>"+
			    			"<div class='btn'>"+
			    				"<span class='btnGoLinkE'><a href='#' onClick='javascript:fnCommentUpdateSubmit(); return false;' >댓글입력</a>"+"</span>"+
			    				"&nbsp;&nbsp;<span class='btnGoLinkB'><a href='#' onClick='javascript:fnEsc(); return false;' >닫기</a></span>"+
			    			"</div>"+
			    		"</div>";
			    	$(".update_"+ano).html(addTr);
	            } else {
	                alert(jData.result_msg);
	            }
	        },
	        error : function(xhr,status,error) {
	            //alert(error);
	        }
	    });
}
//댓글수정
function fnCommentUpdateSubmit() {
       	var params = {
      			ntt_id : $("#ntt_id").val(),
      			bbs_id : "<c:out value='${searchVO.bbsId}' />",
      			//bbs_id : $("#bbs_id").val(),
      			answer : $("#answer").val(),
      			answer_no : $("#answer_no").val()
      		};
      	    $.ajax({
      	        url     : "<c:url value='/front/bbs/cmt/updateBbsComment.do'/>",
      	        type    : "POST",
      	        data    : params,
      	        dataType: "text",
      	        success : function(data) {
      	            // JSON 객체로 변환
      	           	var jData = JSON.parse(data);
      	            if(jData.result_cd == "200") {
      	            	alert("댓글을 수정하였습니다.");
      	            	$('#BbsCommentArea').load("<c:url value='/front/bbs/cmt/BbsCommentList.do'/>", params);
      	            } else {
      	                alert(jData.result_msg);
      	            }
      	        },
      	        error : function(xhr,status,error) {
      	            //alert(error);
      	        }
      	    });
}
//댓글삭제
function fnCommentDelete(ano){
	var params = {
			ntt_id : $("#ntt_id").val(),
			bbs_id : "<c:out value='${searchVO.bbsId}' />",
			//bbs_id : $("#bbs_id").val(),
			"answer_no" : ano
  		};

  	    $.ajax({
  	        url     : "<c:url value='/front/bbs/cmt/deleteBbsComment.do'/>",
  	        type    : "POST",
  	        data    : params,
  	        dataType: "text",
  	        success : function(data) {
  	            // JSON 객체로 변환
  	           	var jData = JSON.parse(data);
  	            if(jData.result_cd == "200") {
  	            	alert("댓글을 삭제하였습니다.");
  	            	$('#BbsCommentArea').load("<c:url value='/front/bbs/cmt/BbsCommentList.do'/>", params);
  	            } else {
  	                alert(jData.result_msg);
  	            }
  	        },
  	        error : function(xhr,status,error) {
  	            //alert(error);
  	        }
  	    });
}
//댓글에 답변 버튼 클릭 시
function fnCommentAnswer(ano){
	var param = {
			"answer_no" : ano
	};
	$(".answerInput").hide();
	$.ajax({
	        url     : "<c:url value='/front/bbs/cmt/selectCmtAnswer.do'/>",
	        type    : "POST",
	        data    : param,
	        dataType: "text",
	        success : function(data) {
	            // JSON 객체로 변환
	           	var jData = JSON.parse(data);
	            if(jData.result_cd == "200") {
	            	var addTr = "" +
			    			"<div class='replyWrite answerInput'>"+
			    			"<input type='hidden' name='answer_no' id='answer_no' value='"+jData.result_answer_no+"' />"+
			    			"<textarea rows='6'  name='answer' id='answer'></textarea>"+
			    			"<div class='btn'>"+
			    				"<span class='btnGoLinkE'><a href='#' onClick='javascript:fnCommentAnswerSubmit(); return false;' >답글입력</a>"+"</span>"+
			    				"&nbsp;&nbsp;<span class='btnGoLinkB'><a href='#' onClick='javascript:fnEsc(); return false;' >닫기</a></span>"+
			    			"</div>"+
			    		"</div>";
			    	$(".update_"+ano).html(addTr);
	            } else {
	                alert(jData.result_msg);
	            }
	        },
	        error : function(xhr,status,error) {
	            //alert(error);
	        }
	    });
}
//댓글에 답변 등록 버튼
function fnCommentAnswerSubmit(){
	var params = {
			ntt_id : $("#ntt_id").val(),
			bbs_id : "<c:out value='${searchVO.bbsId}' />",
  			//bbs_id : $("#bbs_id").val(),
  			answer : $("#answer").val(),
  			answer_no : $("#answer_no").val()
  		};
  	    $.ajax({
  	        url     : "<c:url value='/front/bbs/cmt/insertCommentAnswer.do'/>",
  	        type    : "POST",
  	        data    : params,
  	        dataType: "text",
  	        success : function(data) {
  	            // JSON 객체로 변환
  	           	var jData = JSON.parse(data);
  	            if(jData.result_cd == "200") {
  	            	alert("답글을 등록하였습니다.");
  	            	$('#BbsCommentArea').load("<c:url value='/front/bbs/cmt/BbsCommentList.do'/>", params);
  	            } else {
  	                alert(jData.result_msg);
  	            }
  	        },
  	        error : function(xhr,status,error) {
  	            //alert(error);
  	        }
  	    });
}
//답글에 수정버튼 클릭 시
function fnCommentAnsUpdate(ano){
	var param = {
			"answer_no" : ano
	};
	$(".answerInput").hide();
	$.ajax({
	        url     : "<c:url value='/front/bbs/cmt/selectCmtAnswer.do'/>",
	        type    : "POST",
	        data    : param,
	        dataType: "text",
	        success : function(data) {
	            // JSON 객체로 변환
	           	var jData = JSON.parse(data);
	            if(jData.result_cd == "200") {
	            	var addTr = "" +
			    			"<div class='replyWrite answerInput'>"+
			    			"<input type='hidden' name='answer_no' id='answer_no' value='"+jData.result_answer_no+"' />"+
			    			"<textarea rows='6'  name='answer' id='answer'>"+jData.result_answer+"</textarea>"+
			    			"<div class='btn'>"+
			    				"<span class='btnGoLinkE'><a href='#' onClick='javascript:fnCommentUpdateSubmit(); return false;' >댓글입력</a>"+"</span>"+
			    				"&nbsp;&nbsp;<span class='btnGoLinkB'><a href='#' onClick='javascript:fnEsc(); return false;' >닫기</a></span>"+
			    			"</div>"+
			    		"</div>";
			    	$(".answer_"+ano).html(addTr);
	            } else {
	                alert(jData.result_msg);
	            }
	        },
	        error : function(xhr,status,error) {
	            //alert(error);
	        }
	    });
}
//게시물 답글 등록 버튼.
function bbsAnswerSubmit(form){
	form.ntt_cn.value = Editor.getContent();
	if(form.ntt_cn.value==""){
		alert("내용을 입력 하십시오.");
		form.ntt_cn.focus();
		return ; //focus 커서이동
	}
	$.ajax({
	    url     : "<c:url value='/com/loginConfirm.do'/>",
	    type    : "POST",
	    dataType: "text",
	    success : function(data) {
	        // JSON 객체로 변환
	       	var jData = JSON.parse(data);
	        if(jData.result_cd == "200") {
				form.action = "/front/bbs/insertBbsAnswer.do";
				form.submit();
	        } else {
	        	alert(jData.result_msg);
	            form.action="/usr/uia/LoginUsr.do";
	            form.submit();
	        }
	    },
	    error : function(xhr,status,error) {
	        //alert(error);
	    }
	});
}

/**
 * 첨부파일 다운로드
 */
function fn_egov_downFile(atchFileId, fileSn){
	window.open("/cmm/fms/FileDown.do?atchFileId=" + atchFileId + "&fileSn=" + fileSn + "");
}

/* ********************************************************
* 서버 처리 후 메세지 화면에 보여주기
******************************************************** */
<c:if test="${!empty resultMsg}">alert("<spring:message code="${resultMsg}" />");</c:if>
</script>
<%--  <h2 class="sub_bigtit">${title}</h2> --%>
					<!-- //page title area -->
					<c:choose>
						<c:when test="${searchVO.cmd eq 'view' }">
							<c:import url="/front/bbs/BbsSelectView.do" charEncoding="utf-8"  >
								<c:param name="bbsId" value="${bbsVO.bbsId }"/>
								<c:param name="bbsTyCode" value="${bbsVO.bbsTyCode }"/>
							</c:import>
						</c:when>
						<c:when test="${searchVO.cmd eq 'update' }">
							<c:import url="/front/bbs/BbsSelectUpdtView.do" charEncoding="utf-8"  >
								<c:param name="bbsId" value="${bbsVO.bbsId }"/>
								<c:param name="bbsTyCode" value="${bbsVO.bbsTyCode }"/>
							</c:import>
						</c:when>
						<c:when test="${searchVO.cmd eq 'write' }">
							<c:import url="/front/bbs/BbsInsertView.do" charEncoding="utf-8"  >
								<c:param name="bbsId" value="${bbsVO.bbsId }"/>
								<c:param name="bbsTyCode" value="${bbsVO.bbsTyCode }"/>
							</c:import>
						</c:when>
						<c:when test="${searchVO.cmd eq 'answer' }" >
							<c:import url="/front/bbs/BbsInsertAnswerView.do" charEncoding="utf-8"  >
								<c:param name="bbs_id" value="${bbsVO.bbsId }"/>
								<c:param name="bbs_ty_code" value="${bbsVO.bbsTyCode }"/>
							</c:import>
						</c:when>
						<c:otherwise>
							<c:import url="/front/bbs/BbsSelectList.do" charEncoding="utf-8" >
								<c:param name="bbsId" value="${bbsVO.bbsId }"/>
								<c:param name="bbsTyCode" value="${bbsVO.bbsTyCode }"/>
							</c:import>
						</c:otherwise>
					</c:choose>
