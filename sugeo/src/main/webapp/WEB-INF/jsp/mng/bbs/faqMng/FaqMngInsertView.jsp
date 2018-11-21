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
<c:if test="${cmd eq 'insert' }">
    <h3>Faq 관리 등록</h3>
    <ul>
     <!--    <li>Faq 관리</li> -->
    </ul>
</c:if>
<c:if test="${cmd eq 'update' }">
    <h3>Faq 관리 수정</h3>
    <ul>
      <!--   <li>Faq 관리</li> -->
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
            <input type="hidden" id="cmd" name="cmd" value="${cmd}"/>
			<input type="hidden" name="faqId"  value="${resultList.faqId}" />

            <c:if test="${cmd eq 'update' }">
            	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
            </c:if>
             <tr>
                <th scope="row">분류</th>
                <td class="tl" colspan="3">
                    <select name="faqType" id="faqType">
			      	<option value="FAQ0001" <c:if test="${resultList.faqType eq 'FAQ0001' }">selected="selected"</c:if>>개인정보</option>
			      	<option value="FAQ0002" <c:if test="${resultList.faqType eq 'FAQ0002' }">selected="selected"</c:if>>이벤트관련</option>
			      	<option value="FAQ0003" <c:if test="${resultList.faqType eq 'FAQ0003' }">selected="selected"</c:if>>일반</option>
			      </select>
                </td>
            </tr>
            <tr>
                <th scope="row">제목</th>
                <td class="tl" colspan="3">
                    <c:set var="DQUOT">"</c:set>
                    <c:set var="string2" value="${fn:replace(resultList.quest, DQUOT, '&#34;')}"/>
                    <input  type="text" name="quest" id="quest" class="onetp445" value="${string2 }"/>
                </td>
            </tr>
            <tr>
                <th scope="row">내용</th>
                <td class="tl" colspan="3">
                   <textarea rows="15" cols="" id="answer" style="width:70%;" name="answer">${resultList.answer}</textarea>
                </td>
            </tr>
            

        </tbody>
    </table>

    <div class="centerbtnwrp">
        <c:choose>
            <c:when test="${cmd eq 'insert'}">
                <span><a href="javascript:WiListBack();" class="abtn grybtn">목록</a></span>
                <span><a href="javascript:writeSubmit(document.writeForm);" class="abtn indbtn">저장</a></span>
            </c:when>
            <c:otherwise>
                <span><a href="javascript:UpListBack();" class="abtn grybtn">목록</a></span>
                <span><a href="javascript:deleteSubmit('${resultList.faqId}');" class="abtn indbtn">삭제</a></span>
                <span><a href="javascript:updateSubmit(document.updateForm);" class="abtn indbtn">저장</a></span>
            </c:otherwise>
        </c:choose>
    </div>
</div>


<script type="text/javascript">
//글 등록
/* function writeSubmit(writeForm){
	if($("input[name=quest]").val() == '') {
		alert("제목을 입력하세요.");
		$("input[name=quest]").focus();
		return false;
	}
	if($('#answer').val() < 1) {
		alert("내용을 입력하세요");
		$('#answer').focus();
		return false;
	} 
	
	if(confirm("저장하시겠습니까???")){
		document.writeForm.action="<c:out value='/mng/bbs/faqMng/insertFaqMng.do'/>";
		document.writeForm.submit();
	}

} */

function writeSubmit(){
	if($("input[name=quest]").val() == '') {
		alert("제목을 입력하세요.");
		$("input[name=quest]").focus();
		return false;
	}
	if($('#answer').val() < 1) {
		alert("내용을 입력하세요");
		$('#answer').focus();
		return false;
	} 
	
	if(confirm("저장하시겠습니까?")){
		document.writeForm.action='/mng/bbs/faqMng/insertFaqMng.do';
		document.writeForm.submit();		
	}

}


//글 수정
function updateSubmit(){
	if(confirm("저장하시겠습니까?")){		
		document.updateForm.action='/mng/bbs/faqMng/updateFaqMng.do';
		document.updateForm.submit();
	}

}

//등록페이지에서 목록버튼
function WiListBack(){
	if (confirm("해당 내용은 저장되지 않습니다.\n 목록으로 돌아가시겠습니까?")) {
		location.href = "/mng/bbs/faqMng/faqMngList.do";
	} else
		return false;
}

//수정페이지에서 목록버튼
function UpListBack(){
	if (confirm("해당 내용은 저장되지 않습니다.\n목록으로 돌아가시겠습니까?")){
		document.updateForm.action = "<c:out value='/mng/bbs/faqMng/faqMngList.do'/>";
		document.updateForm.submit();
	}	
}

//글삭제
function deleteSubmit(faqId){
	if(confirm("삭제 하시겠습니까?")){	
		document.updateForm.action = "<c:out value='/mng/bbs/faqMng/deleteFaqMng.do'/>"; 
		document.updateForm.submit();					
	} 

}

</script>