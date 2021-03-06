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

<div class="usrposit"><!-- usrposit (s) -->
<c:if test="${cmd ne 'update' }">
    <h3>언론보도자료 관리 등록</h3>
    <ul>
       <!--  <li>언론보도자료 관리</li> -->
    </ul>
</c:if>
<c:if test="${cmd eq 'update' }">
    <h3>언론보도자료 관리 수정</h3>
    <ul>
       <!--  <li>언론보도자료 관리</li> -->
    </ul>
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
        <form name="${cmd eq 'insert' ? 'writeForm' : 'updateForm'}"
              id="${cmd eq 'insert' ? 'writeForm' : 'updateForm'}" method="post" enctype="multipart/form-data">
            <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
            <input type="hidden" id="cmd" name="cmd" value="${cmd}"/>
			<input type="hidden" name="selectId" value="" />
			<input type="hidden" name="params" />

            <c:if test="${cmd eq 'update' }">
                <input type="hidden" name="pcId" value="${resultList.pcId }"/>
            </c:if>
             <tr>
                <th scope="row">제목</th>
                <td class="tl" colspan="3">
                    <c:set var="DQUOT">"</c:set>
                    <c:set var="string2" value="${fn:replace(resultList.title, DQUOT, '&#34;')}"/>
                    <input onkeydown="maxLengthCheck(event, 70);" type="text" name="title" id="write0101"
                           class="onetp445"
                           value="${string2 }"/>
                </td>
            </tr>
            <tr>
                <th scope="row">언론사</th>
                <td class="tl" colspan="3">
                    <c:set var="DQUOT">"</c:set>
                    <c:set var="string2" value="${fn:replace(resultList.pressName, DQUOT, '&#34;')}"/>
                    <input onkeydown="maxLengthCheck(event, 70);" type="text" name="pressName" id="write0101"
                           class="onetp445"
                           value="${string2 }"/>
                </td>
            </tr>
            <tr>
                <th scope="row">url</th>
                <td class="tl" colspan="3">
                    <c:set var="DQUOT">"</c:set>
                    <c:set var="string2" value="${fn:replace(resultList.url, DQUOT, '&#34;')}"/>
                    <input onkeydown="maxLengthCheck(event, 70);" type="text" name="url" id="write0101"
                           class="onetp445"
                           value="${string2 }"/>
                </td>
            </tr>
            

        </tbody>
    </table>

    <div class="centerbtnwrp">
        <c:choose>
            <c:when test="${cmd eq 'insert'}">
                <span><a href="javascript:WiListBack();" class="abtn indbtn">목록</a></span>
                <span><a href="javascript:writeSubmit(document.writeForm);" class="abtn indbtn">저장</a></span>
            </c:when>
            <c:otherwise>
                <span><a href="javascript:deleteSubmit('${resultList.pcId}');" class="abtn indbtn">삭제</a></span>
                <span><a href="javascript:UpListBack();" class="abtn indbtn">목록</a></span>
                <span><a href="javascript:updateSubmit(document.updateForm);" class="abtn indbtn">저장</a></span>
            </c:otherwise>
        </c:choose>
    </div>
</div>


<script type="text/javascript">
//글 등록
function writeSubmit(){
	if($("input[name=title]").val() == '') {
		alert("제목을 입력하세요");
		$("input[name=title]").focus();
		return;
	}
	if($("input[name=pressName]").val() == '') {
		alert("언론사를 입력하세요");
		$("input[name=pressName]").focus();
		return;
	}
	if($("input[name=url]").val() == '') {
		alert("url을 입력하세요");
		$("input[name=url]").focus();
		return;
	}
	
	var siteReg = new RegExp("(http|https)://([-/.a-zA-Z0-9_~#%$?&=:200-377()]+)", "gi");
	if ($("input[name=url]").val().length > 0 && !siteReg.test($('input[name=url]').val())) {
		alert('url은 http://, https:// 를 포함해서 입력하세요.');
		$("input[name=url]").focus();		
		return;
	}

	document.writeForm.action='/mng/bbs/pressContent/insertPressContent.do';
	document.writeForm.submit();	

	

}
//글 수정
function updateSubmit(){
	if($("input[name=title]").val() == '') {
		alert("제목을 입력하세요");
		$("input[name=title]").focus();
		return;
	}
	if($("input[name=pressName]").val() == '') {
		alert("언론사를 입력하세요");
		$("input[name=pressName]").focus();
		return;
	}
	if($("input[name=url]").val() == '') {
		alert("url을 입력하세요");
		$("input[name=url]").focus();
		return;
	}
	
	var siteReg = new RegExp("(http|https)://([-/.a-zA-Z0-9_~#%$?&=:200-377()]+)", "gi");
	if ($("input[name=url]").val().length > 0 && !siteReg.test($('input[name=url]').val())) {
		alert('url은 http://, https:// 를 포함해서 입력하세요.');
		$("input[name=url]").focus();		
		return;
	}
	if(confirm("저장하시겠습니까?")){		
		document.updateForm.action='/mng/bbs/pressContent/updatePressContent.do';
		document.updateForm.submit();
	}

}

//등록 화면 목록버튼
function WiListBack(){
	if (confirm("해당 내용은 저장되지 않습니다.\n목록으로 돌아가시겠습니까?")) {
		location.href = "/mng/bbs/pressContent/pressContentList.do";
	} else
		return false;
}
//수정 화면 목록버튼
function UpListBack(){
	if (confirm("해당 내용은 저장되지 않습니다.\n목록으로 돌아가시겠습니까?")) {
		document.updateForm.action ="/mng/bbs/pressContent/pressContentList.do";
		document.updateForm.submit();
	} 
	
}

//글삭제
function deleteSubmit(pcId){
	if(confirm("삭제 하시겠습니까?")){	
		var params = pcId;
		document.updateForm.action = "<c:out value='/mng/bbs/pressContent/deletePressContent.do'/>"; 
		document.updateForm.params.value = params;
		document.updateForm.submit();					
	} 

}

</script>