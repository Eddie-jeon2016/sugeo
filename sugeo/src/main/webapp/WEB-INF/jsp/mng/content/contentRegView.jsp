<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="/js/summernote/summernote.css" rel="stylesheet">
<script src="/js/summernote/summernote.js"></script>
<script src="/js/summernote/summerCustom.js"></script>
<div class="usrposit">
	<h3>콘텐츠 관리</h3>
 </div>

<div id="maininner"> 
<form name="contentFrm" id="contentFrm" method="post">
<input type="hidden" name="contentId" value="${ViewContent.contentId }" /> 
<input type="hidden" name="content" value="<c:out value="${ViewContent.content }"/>" />
<div class="tbl_wrp">
	<table class="tbl_lst tdleft">

				<caption>콘텐츠 등록/수정</caption>
				<colgroup>
					<col width="10%" />
					<col width="90%" />
				</colgroup>
				<tbody>
				<tr>
					<th scope="row">제목</th>
					<td class="tl">
						<input type="text" name="contentName" value="${ViewContent.contentName }" class="onetp445"/>
					</td>
				</tr>
				<tr>	
					<th scope="row">
					내용
					</th>
					<td class="tl">
						<div id="summernote" class="w100ipt">${ViewContent.content }</div>
					</td> 
				</tr>
				</tbody>
		</table>
</div>
		</form>
</div>
		<div class="centerbtnwrp">
			<c:if test="${empty ViewContent}">
				<span><a href="javascript:contentInsert();" class="abtn indbtn">입력</a></span>
			</c:if>
			<c:if test="${!empty ViewContent}">
				<span><a href="javascript:contentUpdate();" class="abtn indbtn">수정</a></span>
			</c:if>
			<span><a href="/mng/content/contentList.do" class="abtn indbtn">목록</a></span>
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
	
	
	function contentInsert(){
		if(document.contentFrm.contentName.value.length == 0){
			alert('Please Insert ContentName');
			return
		}
		
		$('input[name=content]').val($('#summernote').summernote('code'));
		document.contentFrm.action='/mng/content/contentInsert.do';
		document.contentFrm.submit();
	}
	
	function contentUpdate(){
		$('input[name=content]').val($('#summernote').summernote('code'));
		document.contentFrm.action='/mng/content/contentUpdate.do';
		document.contentFrm.submit();
	}
</script>