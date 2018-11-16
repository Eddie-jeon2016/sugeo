<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
		
	function regAdmin() {
		
		if(confirm("등록하시겠습니까?")){
			var idReg = /^[a-z]+[a-z0-9]{9,14}$/g;
	        
			//아이디 확인
			if($("input[name=userId]").val() == '') {
				alert("아이디를 입력 해주세요");
				$("input[name=userId]").focus();
				return;
			}
			if($("#overlap").val() == 'Y') {
				alert("중복되는 아이디로 등록할 수 없습니다.");
				$("input[name=userId]").focus();
				return;
			}
			
			if($("input[name=pswd]").val() == '') {
				alert("비밀번호를 입력 해주세요");
				$("input[name=pswd]").focus();
				return;
			} 
			/* var passLength = $("input[name=pswd]").val().length;
			if(!(passLength>=4 && passLength<=16)) {
				alert("비밀번호는 4~16자리로 입력해주세요.");
				return;
			} */
			
			if($("input[name=pswd2]").val() == '') {
				alert("비밀번호 확인을 입력 해주세요");
				$("input[name=pswd2]").focus();
				return;
			}
			// 이름 확인
			if($("input[name=userName]").val() == '') {
				alert("이름 입력 해주세요");
				$("input[name=userName]").focus();
				return;
			}
			
			// 사용여부 확인
			if($("input[name=useYn]:checked").length <= 0) {
				alert("사용여부 입력 해주세요");
				$("input[value=Y]").focus();
				return;
			}
			
			// 아이디 입력값 검사
			if( !idReg.test( $("input[name=userId]").val() ) ) {
	            alert("아이디는 영문자 또는 숫자 10~15글자만 가능합니다.");
	            return;
	        }
			
			//비밀번호 확인
			if($("input[name=pswd]").val() != $("input[name=pswd2]").val()){
	    		alert("<spring:message code="fail.user.passwordUpdate2" />");
	    		$("input[name^=pswd]").val('');
	    		return;
	    	}
			
			
			document.authorForm.action='/mng/auth/insertAuthorUser.do';
			document.authorForm.submit();
		}
		
	}
	
	function updateAdmin() {
		if(confirm("수정하시겠습니까?")) {
			var params = $("#authorForm").serialize();
			//현재 비밀번호 확인
			$.ajax({
				url			: "<c:url value='/mng/auth/updateAuthorUserCheck.do'/>",
				type		: "POST",
				data		: params,
				datatype 	: "json",
				success		: function(data) {
					var obj = JSON.parse(data);
					if(obj.status == 'success') {
						
						// 이름 확인
						if($("input[name=userName]").val() == '') {
							alert("이름 입력 해주세요");
							$("input[name=userName]").focus();
							return;
						}
						// 이메일 확인
						if($("input[name=email]").val() == '') {
							alert("이메일 입력 해주세요");
							$("input[name=email]").focus();
							return;
						}
						
						// 사용여부 확인
						if($("input[name=useYn]:checked").length <= 0) {
							alert("사용여부 입력 해주세요");
							$("input[value=Y]").focus();
							return;
						}
						/* if($("input[name=pswd]").val() != "") {
							var passLength = $("input[name=pswd]").val().length;
							if(!(passLength>=4 && passLength<=16)) {
								alert("비밀번호는 4~16자리로 입력해주세요.");
								return;
							}
						} */
						//비밀번호 확인
						if($("input[name=pswd]").val() != $("input[name=pswd2]").val()){
				    		alert("<spring:message code="fail.user.passwordUpdate2" />");
				    		$("input[name^=pswd]").val('');
				    		return false;
				    	}
						
						document.authorForm.action = '/mng/auth/updateAuthorUser.do';
						document.authorForm.submit();
					} 
				},
				error		: function(xhr,status,error) {
					alert(error);
				}
			});
		}
	}
	
	function delAdmin() {
		if(confirm("삭제하시겠습니까?")) {
			var params = $("#authorForm").serialize();
			//현재 비밀번호 확인
			$.ajax({
				url			: "<c:url value='/mng/auth/updateAuthorUserCheck.do'/>",
				type		: "POST",
				data		: params,
				datatype 	: "json",
				success		: function(data) {
					var obj = JSON.parse(data);
					if(obj.status == 'success') {
						document.authorForm.action = '/mng/auth/deleteAuthorUser.do';
						document.authorForm.submit();
					} else {
						alert('현재비밀번호가 틀렸습니다.');
						$("input[name^=pswd]").val('');
						$("input[name=nowPswd]").val('');
						$("input[name=nowPswd]").focus();
						return false;
					}
				},
				error		: function(xhr,status,error) {
					alert(error);
				}
			});
		}
	}
	
function idCheck() {
	if($("input[name=userId]").val() == '') {
		alert("아이디를 입력 해주세요");
		$("input[name=userId]").focus();
		return false;
	}
	var idReg = /^[a-z]+[a-z0-9]{9,14}$/g;
	
	// 아이디 입력값 검사
	if( !idReg.test( $("input[name=userId]").val() ) ) {
        alert("아이디는 영문자 또는 숫자 10~15글자만 가능합니다.");
        return false;
    }
	
	var params = $("#authorForm").serialize();
	$.ajax({
		url			: "<c:url value='/mng/auth/idCheck.do'/>",
		type		: "POST",
		data		: params,
		datatype 	: "json",
		success		: function(data) {
			var obj = JSON.parse(data);
			if(obj.code == '200') {
				alert('사용가능한 아아디입니다.');
			
			} else {
				alert('중독되는 아이디입니다.');
				$('#overlap').val("Y");
			}
			$("input[name=userId]").focus();
		},
		error		: function(xhr,status,error) {
			alert(error);
		}
	});
}

function authList(pageIndex) {

	if(confirm("해당내용은 저장되지 않습니다.목록으로 돌아가시겠습니까?")) {
		location.href = "/mng/auth/selectAuthorUserList.do?pageIndex="+pageIndex;
	}
}
</script>
<div class="usrposit">
	<c:if test="${empty result }">
		<h3>관리자  등록</h3>
	</c:if>
	<c:if test="${!empty result }">
		<h3>관리자  수정</h3>
	</c:if>
</div>

<div id="maininner">
<form name="authorForm" id="authorForm" method="post">
<input type="hidden" name="uniqId" value="${result.uniqId }">
<input type="hidden" name="tempId" value="${result.userId }">
<input type="hidden" name="pageIndex" id="pageIndex" value="${comVO.pageIndex}">
<input type="hidden" name=overlap  id="overlap"value="N">

	<table class="tbl_lst tdleft">
		<caption>관리자 권한 관리</caption>
		<colgroup>
			<col style="width: 25%;">
            <col style="width: 75%;">
		</colgroup>
		<tbody>
			<tr>
				<th><span>관리자 ID<span></th>
				<td style="text-align:left;"><input type="text" required="required" name="userId" value="${result.userId }" style="width:400px;">
					<c:if test="${empty result }">
					<span> <a href="javascript:idCheck();" class="abtn indbtn">중복검사</a></span>
					</c:if>
				</td>
			</tr>
			<tr>
				<th><span>비밀번호</span></th>
				<td style="text-align:left;">
					<input type="password" required="required" name="pswd" style="width:400px;" value="">
				</td>
			</tr>
			<tr>
 			<th><span>비밀번호 확인</span></th>
				<td style="text-align:left;">
					<input type="password" required="required" name="pswd2" style="width:400px;" value="">
				</td>
			</tr>
			<tr>
				<th><span>이름</span></th>
				<td style="text-align:left;"><input type="text" required="required" name="userName" value="${result.userName }" style="width:400px;"></td>
			</tr>
			
			<tr>
				<th><span>사용여부</span></th>
				<td style="text-align:left;"><input type="radio" name="useYn" value="Y"<c:if test="${result.useYn == 'Y'}">checked="checked"</c:if>>사용&nbsp;&nbsp;&nbsp;&nbsp;
																													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="useYn" value="N"<c:if test="${result.useYn == 'N'}">checked="checked"</c:if>>미사용</td>
			</tr>
		</tbody>
	</table>
</form>
</div>

	    <div class="centerbtnwrp">
         <span><a href="javascript:authList('${comVO.pageIndex}');" class="abtn indbtn">목록</a></span>
         <c:if test="${empty result }">
         <span><a href="#none" onclick="javascript:regAdmin();" class="abtn indbtn">등록</a></span>
         </c:if>
         <c:if test="${not empty result }">
         <span><a href="#none" onclick="javascript:delAdmin();" class="abtn indbtn">삭제</a></span>
         <span><a href="#none" onclick="javascript:updateAdmin();" class="abtn indbtn">저장</a></span>
         </c:if>
     </div>
