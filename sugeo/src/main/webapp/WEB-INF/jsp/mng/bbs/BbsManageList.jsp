<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
/**
  * @Class Name : BbsManageList.jsp
  * @Description : 게시판 목록 화면
  * @Modification Information
  * @
  * @  	수정일      			수정자           	 수정내용
  * @ ---------------        --------    	---------------------------
  *
  */
%>
<script type="text/javascript" src="/js/layerPop.js" ></script>
<script type="text/javascript">

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

/** 게시판추가에서 추가버튼 클릭 시*/
function writeSubmit(form){
	if($('#BBS_NAME').val().length < 1){
		alert("제목을 입력 하십시오.");
		$('#BBS_NAME').focus();
		return;
	}
	if($(":input:radio[name=FILE_ATCH_POSBL_YN]:checked").val() == "Y"){
		if($('#atch_posbl_file_number').val().length < 1){
			alert("파일개수를 선택하여 주십시오.");
			$('#atch_posbl_file_number').focus();
			return;
		}
	}
	var writeField = form.write_auth_ck;
	var readField = form.read_auth_ck;
// 	var replyField = form.reply_auth_ck;
// 	var answerField = form.answer_auth_ck;
    var writeIds = "";
    var readIds = "";
//     var replyIds = "";
//     var answerIds = "";

    writeIds =  checkSum(writeField);
    readIds =  checkSum(readField);
//     replyIds =  checkSum(replyField);
//     answerIds =  checkSum(answerField);

    if(writeIds.length < 1){alert("쓰기권한을 한개이상 체크해주세요.");return;}
	if(readIds.length < 1){alert("읽기권한을 한개이상 체크해주세요.");return;}
// 	if(replyIds.length < 1){alert("답글권한을 한개이상 체크해주세요.");return;}
// 	if(answerIds.length < 1){alert("댓글권한을 한개이상 체크해주세요.");return;}

    if(writeIds.length > 0) {
    	form.writeAuth.value=writeIds;
    }
    if(readIds.length > 0) {
    	form.readAuth.value=readIds;
	}
//     if(replyIds.length > 0) {
//     	form.replyAuth.value=replyIds;
// 	}
//     if(answerIds.length > 0) {
//     	form.answerAuth.value=answerIds;
// 	}
    if(confirm("<spring:message code='common.regist.msg'/>")){
			form.action = "<c:url value='/mng/bbs/insertBbsManage.do'/>";
			form.submit();
		}
	}

/** 수정창에서 저장버튼 클릭 시 */
function updateSubmit(form){
	if($('#BBS_NAME').val().length < 1){
		alert("제목을 입력 하십시오.");
		$('#BBS_NAME').focus();
		return;
	}
	if($(":input:radio[name=fileAtchPosblYn]:checked").val() == "Y"){
		if($('#atch_posbl_file_number').val().length < 1){
			alert("파일개수를 선택하여 주십시오.");
			$('#atch_posbl_file_number').focus();
			return;
		}
	}
	var writeField = form.write_auth_ck;
	var readField = form.read_auth_ck;
// 	var replyField = form.reply_auth_ck;
// 	var answerField = form.answer_auth_ck;
    var writeIds = "";
    var readIds = "";
//     var replyIds = "";
//     var answerIds = "";

    writeIds =  checkSum(writeField);
    readIds =  checkSum(readField);
//     replyIds =  checkSum(replyField);
//     answerIds =  checkSum(answerField);

    if(writeIds.length < 1){alert("쓰기권한을 한개이상 체크해주세요.");return;}
	if(readIds.length < 1){alert("읽기권한을 한개이상 체크해주세요.");return;}
// 	if(replyIds.length < 1){alert("답글권한을 한개이상 체크해주세요.");return;}
// 	if(answerIds.length < 1){alert("댓글권한을 한개이상 체크해주세요.");return;}

    if(writeIds.length > 0) {
    	form.writeAuth.value=writeIds;
    }
    if(readIds.length > 0) {
    	form.readAuth.value=readIds;
	}
//     if(replyIds.length > 0) {
//     	form.replyAuth.value=replyIds;
// 	}
//     if(answerIds.length > 0) {
//     	form.answerAuth.value=answerIds;
//     }
	if(confirm("<spring:message code='common.update.msg'/>")){
			form.action = "<c:url value='/mng/bbs/updateBbsManage.do'/>";
			form.submit();
	}
}
/** 체크....  */
function checkSum(checkId) {
	var checkedIds = "";
    var checkedCount = 0;
    if(checkId) {
        if(checkId.length > 1) {
            for(var i=0; i < checkId.length; i++) {
                if(checkId[i].checked) {
                    checkedIds += ((checkedCount==0? "" : "|") + checkId[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkId.checked) {
                checkedIds = checkId.value;
            }
        }
    }
    return checkedIds;
}
//삭제 이벤트
function delManage(bbs_id){
	if(confirm('<spring:message code="common.delete.msg" />')){
		document.deleteForm.bbsId.value = bbs_id;

		$('#deleteForm').ajaxSubmit( {
	        url      : "<c:url value='/mng/bbs/DelManage.do'/>",
	        type     : "post",
	        dataType : "text",
	        success : function(data) {
	            data = data.replace(/[<][^>]*[>]/gi, '');
	            var jData = JSON.parse(data);

	            if ( jData.result_cd == "200" ) {
	           		alert("삭제 되었습니다.");
	           		location.reload();
	            } else {
	                alert(jData.result_msg);
	            }
	        },
	        error : function(xhr,status,error) {
	            //alert(error);
	        }
		});

	}
}

/* 말머리 팝업창 */
function categoryPop(bbs_id){
	$("#layerPop").css("width", "350px" , "height" , "300px");
	fnLayerCenter("#layerPop");
	var url = "<c:url value='/mng/bbs/cate/BbsCateListPopup.do'/>";
	var param = {		//파라미터 값에 bbs_id 값을 넣는다
		"bbsId" : bbs_id
	};
	$('#layerPop').show();
	$('<div class="layerBg"></div>').appendTo('body');
	$('#layerPop').load(url, param);
}
function cateSubmit(form){
	if($("#bbscate_nm2").val() == ""){
		alert("말머리명을 입력하세요");
		return;
	}
	var bbs_id = form.bbsId.value;
	form.bbscateName.value = $("#bbscate_nm2").val();

	if(!confirm("등록를 하시겠습니까?")) return false;

	$('#cateInsertForm').ajaxSubmit( {
        url      : "<c:url value='/mng/bbs/cate/insertBbsCate.do'/>",
        type     : "post",
        dataType : "text",
        success : function(data) {
            data = data.replace(/[<][^>]*[>]/gi, '');
            var jData = JSON.parse(data);

            if ( jData.result_cd == "200" ) {
           		alert("추가 되었습니다.");
           		var url = "<c:url value='/mng/bbs/cate/BbsCateListPopup.do'/>";
           		var param = {		//파라미터 값에 bbs_id 값을 넣는다
           			"bbsId" : bbs_id
           		};
           		$('#layerPop').load(url, param);
            } else {
                alert(jData.result_msg);
            }
        },
        error : function(xhr,status,error) {
            //alert(error);
        }
	});
 }
/** 수정 클릭 시*/
function CateUpdate(form, bbscate_id){
	if($('#bbscate_nm'+bbscate_id).val() == ""){
		alert("말머리명을 입력하세요");
		return;
	}
	var bbs_id = form.bbsId.value;

	if(!confirm("수정를 하시겠습니까?")) return false;

	form.bbscateId.value = bbscate_id;
	form.bbscateName.value = $("#bbscate_nm"+bbscate_id).val();

	$('#cateUpdtForm').ajaxSubmit( {
        url      : "<c:url value='/mng/bbs/cate/updateBbsCate.do'/>",
        type     : "post",
        dataType : "text",
        success : function(data) {
            data = data.replace(/[<][^>]*[>]/gi, '');
            var jData = JSON.parse(data);

            if ( jData.result_cd == "200" ) {
           		alert("수정 되었습니다.");
           		var url = "<c:url value='/mng/bbs/cate/BbsCateListPopup.do'/>";
           		var param = {		//파라미터 값에 bbs_id 값을 넣는다
           			"bbsId" : bbs_id
           		};
           		$('#layerPop').load(url, param);
            } else {
                alert(jData.result_msg);
            }
        },
        error : function(xhr,status,error) {
            //alert(error);
        }
	});
}
/** 삭제 클릭 시*/
function CateDelete(form, bbscate_id){

	var bbs_id = form.bbsId.value;

	if(confirm('말머리를 삭제하시겠습니까?')){
		form.bbscateId.value = bbscate_id;
		$('#cateUpdtForm').ajaxSubmit( {
	        url      : "<c:url value='/mng/bbs/cate/deleteBbsCate.do'/>",
	        type     : "post",
	        dataType : "text",
	        success : function(data) {
	            data = data.replace(/[<][^>]*[>]/gi, '');
	            var jData = JSON.parse(data);

	            if ( jData.result_cd == "200" ) {
	            	alert("삭제 되었습니다.");
	           		var url = "<c:url value='/mng/bbs/cate/BbsCateListPopup.do'/>";
	           		var param = {		//파라미터 값에 bbs_id 값을 넣는다
	           			"bbsId" : bbs_id
	           		};
	           		$('#layerPop').load(url, param);
	            } else {
	                alert(jData.result_msg);
	            }
	        },
	        error : function(xhr,status,error) {
	            //alert(error);
	        }
		});
	}
}
//수정팝업창
function updatePop(bbs_id){
	$("#layerPop").css("width", "700px" , "height" , "500px");
	fnLayerCenter("#layerPop");
	var url = "<c:url value='/mng/bbs/BbsManageDetailPopup.do'/>";
	var param = {		//파라미터 값에 bbs_id 값을 넣는다
		"bbsId" : bbs_id
	};
	$('#layerPop').show();
	$('<div class="layerBg"></div>').appendTo('body');
	$('#layerPop').load(url, param);	//id가 layerPop div 영역안에 수정페이지를 로딩한다.
}
//추가팝업창
function writePop(){
	$("#layerPop").css("width", "700px" , "height" , "500px");
	fnLayerCenter("#layerPop");

	var url = "<c:url value='/mng/bbs/BbsMngInsertViewPopup.do'/>";
	$('#layerPop').show();
	$('<div class="layerBg"></div>').appendTo('body');
	$('#layerPop').load(url);
}
//팝업창 닫기
function BbsclosePop(){
	$('.layerPopup').hide();
	$('.layerBg').remove();
	$("#layerPop").css("display", "none");
	$("#layerPop").empty();
	return false;
}
</script>

<div class="usrposit"><!-- usrposit (s) -->
    <h3>게시판 관리</h3>
    <ul>
        <li>게시판 관리</li>
        <li>게시판 관리</li>
    </ul>
</div>

<div id="maininner"><!-- maininner (s)-->	
	<form name="deleteForm" id="deleteForm" >
	<input type="hidden" name="bbsId" />
	</form>
	<form name="listForm" method="post">
	<input type="hidden" name="bbs_id" id="bbs_id" value="" />
	<input type="hidden" name="cafe_id" id="cafe_id" value="" />
	<input type="hidden" name="cmd" id="cmd" value="" />
	<input type="hidden" name="bid" value="" />
				<table class="tbl_lst clfix">
					<caption>게시판 관리</caption>
					<colgroup>
						<col width="8%">
						<col width="22%" />
						<col width="10%" />
	<%-- 					<col width="5%" /> --%>
						<col width="10%" />
						<col width="10%" />
	<%-- 					<col width="8%" /> --%>
	<%-- 					<col width="8%" /> --%>
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">제목</th>
							<th scope="col">사용여부</th>
	<!-- 						<th scope="col">댓글</th> -->
							<th scope="col">쓰기권한</th>
							<th scope="col">읽기권한</th>
	<!-- 						<th scope="col">답글권한</th> -->
	<!-- 						<th scope="col">댓글권한</th> -->
							<th scope="col">글목록</th>
							<th scope="col">수정</th>
							<th scope="col">글분류</th>
							<th scope="col">삭제</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${empty resultList}">
					<tr>
						<td colspan="8">게시판이 없습니다.</td>
	
					</tr>
					</c:if>
					<c:forEach var="result" items="${resultList}" varStatus="status">
						<c:set var="write_auth" value="${fn:split(result.writeAuth,'|')}" />
						<c:set var="read_auth" value="${fn:split(result.readAuth,'|')}" />
	<%-- 					<c:set var="reply_auth" value="${fn:split(result.replyAuth,'|')}" /> --%>
	<%-- 					<c:set var="answer_auth" value="${fn:split(result.answerAuth,'|')}" /> --%>
	
						<tr>
							<td>${fn:length(resultList)- status.index }</td>
							<td>${result.bbsName}</td>
							<td><c:out value="${result.useYn }"/></td>
	<%-- 						<td><c:out value="${result.answerYn}"/></td> --%>
	
							<!-- 쓰기권한 -->
							<td>
							<c:forEach var="writerAuthName" items="${write_auth}" varStatus="index">
								<c:if test="${writerAuthName eq 'ROLE_ANONY'}" ><img src="<c:out value="/images/bbs/contents/img_auth06.gif"/>" width="13"></c:if>
								<c:if test="${writerAuthName eq 'ROLE_USER'}" ><img src="<c:out value="/images/bbs/contents/img_auth01.gif"/>" width="13"></c:if>
								<c:if test="${writerAuthName eq 'ROLE_ADMIN'}" ><img src="<c:out value="/images/bbs/contents/img_auth10.png"/>" width="13"></c:if>
							</c:forEach>
							</td>
							<!-- 읽기권한 -->
							<td>
							<c:forEach var="readAuthName" items="${read_auth}" varStatus="index">
								<c:if test="${readAuthName eq 'ROLE_ANONY'}" ><img src="<c:out value="/images/bbs/contents/img_auth06.gif"/>" width="13"></c:if>
								<c:if test="${readAuthName eq 'ROLE_USER'}" ><img src="<c:out value="/images/bbs/contents/img_auth01.gif"/>" width="13"></c:if>
								<c:if test="${readAuthName eq 'ROLE_ADMIN'}" ><img src="<c:out value="/images/bbs/contents/img_auth10.png"/>" width="13"></c:if>
							</c:forEach>
							</td>
							<!-- 답글권한 -->
	<!-- 						<td> -->
	<%-- 						${replyAuth } --%>
	<%-- 						<c:forEach var="replyAuthName" items="${reply_auth}" varStatus="index"> --%>
	<%-- 							<c:if test="${replyAuthName eq 'ROLE_ANONY'}" ><img src="<c:out value="/images/bbs/contents/img_auth06.gif"/>" width="13"></c:if> --%>
	<%-- 							<c:if test="${replyAuthName eq 'ROLE_USER'}" ><img src="<c:out value="/images/bbs/contents/img_auth01.gif"/>" width="13"></c:if> --%>
	<%-- 							<c:if test="${replyAuthName eq 'ROLE_ADMIN'}" ><img src="<c:out value="/images/bbs/contents/img_auth10.png"/>" width="13"></c:if> --%>
	<%-- 						</c:forEach> --%>
	<!-- 						</td> -->
							<!-- 답글권한 -->
	<!-- 						<td> -->
	<%-- 						<c:forEach var="answerAuthName" items="${answer_auth}" varStatus="index"> --%>
	<%-- 							<c:if test="${answerAuthName eq 'ROLE_ANONY'}" ><img src="<c:out value="/images/bbs/contents/img_auth06.gif"/>" width="13"></c:if> --%>
	<%-- 							<c:if test="${answerAuthName eq 'ROLE_USER'}" ><img src="<c:out value="/images/bbs/contents/img_auth01.gif"/>" width="13"></c:if> --%>
	<%-- 							<c:if test="${answerAuthName eq 'ROLE_ADMIN'}" ><img src="<c:out value="/images/bbs/contents/img_auth10.png"/>" width="13"></c:if> --%>
	<%-- 						</c:forEach> --%>
	<!-- 						</td> -->
							<td><span><a href="/mng/bbs/BbsDetailList.do?BbsId=${result.bbsId}&menuNo=${result.menuNo}"  title="새창" class="ltbtn grbtn">보기</a></span></td>
							<td><span><a href="javascript:updatePop('<c:out value="${result.bbsId}"/>');" class="ltbtn grbtn">수정</a></span></td>
							<td><span><a href="javascript:categoryPop('<c:out value="${result.bbsId}"/>');" class="ltbtn grbtn">분류</a></span></td>
							<td><span><a href="javascript:delManage('<c:out value="${result.bbsId}"/>');" class="ltbtn grbtn">삭제</a></span></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
					<div>
						<ul>
							<li><!-- 관리자 , 일반, 대기, 미납정, 정 , 전문가 , 미디어센터 -->
								<img src="/images/bbs/contents/img_auth10.png" alt="관리자회원" /> 관리자회원 &nbsp;&nbsp;<img src="/images/bbs/contents/img_auth06.gif" alt="비회원" /> 비회원
								 &nbsp;&nbsp;<img src="/images/bbs/contents/img_auth01.gif" alt="일반회원" /> 일반회원
							</li>
						</ul>
						<div class="btnbox">
							<span><a href="#" onClick="javascript:writePop();" class="btntg indbtn" >게시판 추가</a></span>
						</div>
					</div>
	</form>
</div>

<div id="layerPop"  class="layerPopup"></div>
<div id="layerPopSub" class="layerPopup"></div>