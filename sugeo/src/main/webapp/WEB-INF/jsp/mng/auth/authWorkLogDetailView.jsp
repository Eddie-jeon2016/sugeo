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
    <h3>작업로그 상세</h3>
    <ul>
       <!--  <li>작업로그 상세</li> -->
    </ul>

</div>
<div id="maininner"><!-- maininner (s)-->

    <table class="tbl_lst tdleft">
        <caption>게시판 상세</caption>
        <colgroup>
            <col style="width: 15%;">
            <col style="width: 15%;">
            <col style="width: 15%;">
            <col style="width: 15%;">
            <col style="width: 15%;">
            <col style="width: 15%;">
            <col style="width: 15%;">
           
        </colgroup>
        <tbody>
        <form name="ListForm"
              id="ListForm" method="post" enctype="multipart/form-data">
            <input name="pageIndex" type="hidden" value="<c:out value='${comVO.pageIndex}'/>"/>  
            <input type="hidden" id="cmd" name="cmd" value="${cmd}"/>
			<input type="hidden" name="logId" value="logId" />
             <tr>
                <th scope="row">작업 일련번호</th>
                <td class="tl" colspan="3">
                    ${authVO.logId}
                </td>
            </tr>
             <tr>
                <th scope="row">작업 ID</th>
                <td class="tl" colspan="3">
                    ${authVO.userId }
                </td>
            </tr>
             <tr>
                <th scope="row">작업 IP</th>
                <td class="tl" colspan="3">
                    ${authVO.accessIp }
                </td>
            </tr>
             <tr>
                <th scope="row">작업 URL</th>
                <td class="tl" colspan="3">
                    ${authVO.url }
                </td>
            </tr>
             <tr>
                <th scope="row">작업 내용</th>
                <td class="tl" colspan="3">
                    ${authVO.content }
                </td>
            </tr>
             <tr>
                <th scope="row">작업 일시</th>
                <td class="tl" colspan="3">
                    ${authVO.logDate }
                </td>
            </tr>
             <tr>
                <th scope="row">작업 분류</th>
                <td class="tl" colspan="3">
                    ${authVO.logType}
                </td>
            </tr>
           
        </tbody>
    </table>

    <div class="centerbtnwrp">
                <span><a href="javascript:ListBack();" class="abtn grybtn">목록</a></span>
    </div>
</div>


<script type="text/javascript">
//목록버튼
function ListBack(){
	document.ListForm.action = "/mng/auth/workList/selectWorkLogList.do";
	document.ListForm.submit();
}


</script>