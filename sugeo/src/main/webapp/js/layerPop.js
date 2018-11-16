/**
 * 페이지 이동 함수
 */
function fnPopLinkPage(pageNo){
	var url = "/mng/space/SpaceRentalUserPopup.do";
	var param = {
			pageIndex : pageNo,
			role : $("select[name=popRole]").val(),
			searchCondition : $("#popSearchCondition").val(),
			searchKeyword : $("#popSearchKeyword").val()
	};
	$('#layerPop').load(url, param);
}
function fnPopRoleChangeList(){
	var url = "/mng/space/SpaceRentalUserPopup.do";
	var param = {
			role : $("select[name=popRole]").val(),
			searchCondition : $("#popSearchCondition").val(),
			searchKeyword : $("#popSearchKeyword").val()
	};
	$('#layerPop').load(url, param);
}
/**
 * 리스트 검색 함수
 */
function fnPopSearch(){
	var url = "/mng/space/SpaceRentalUserPopup.do";
	var param = {
			role : $("select[name=popRole]").val(),
			searchCondition : $("#popSearchCondition").val(),
			searchKeyword : $("#popSearchKeyword").val()
	};
	$('#layerPop').load(url, param);
}

/**
 * 값 전달함수
 */
/**
 * 값 전달함수
 */
function fnReturnUserInfo( userId, emailId, name, mobile, role ){
	mobile = mobile.replace(/-/gi,"");
	var phone_number = mobile.substring(0,3) + "-" + mobile.substring(3,7) + "-" + mobile.substring(7,11);
	$("#email_id_display").text(emailId);
	$("#user_name_display").text(name);
	$("#mobile_display").text(phone_number);
	$("#user_id").val(userId);
	$("#role").val(role);
	$("#recipient").val(name);
	$("#recipient_phone").val(mobile.substring(0,3));
	$("#recipient_phone_middle").val(mobile.substring(3,7));
	$("#recipient_phone_last").val(mobile.substring(7,11));
	discount_money_change();
	popClose();
	return false;
}
function fnReturnUserInfoSub( userId, emailId, name, mobile, role ){
	mobile = mobile.replace(/-/gi,"");
	var phone_number = mobile.substring(0,3) + "-" + mobile.substring(3,7) + "-" + mobile.substring(7,11);
	document.rentalAllForm.user_id.value = userId;
	document.rentalAllForm.role.value = role;
	document.getElementById("email_id_display").innerHTML = name + " | " + phone_number;
	popCloseSub();
	return false;
}
function popClose(){
	$('.layerPopup').hide();
	$('.layerBg').remove();
	$("#layerPop").css("display", "none");
	$("#layerPop").empty();
	return false;
}
function popCloseSub(){
	$('.layerPopupSub').hide();
	$('.layerBgSub').remove();
	$("#layerPopSub").css("display", "none");
	$("#layerPopSub").empty();
	return false;
}
/*강좌부분*/
/*강좌부분*/

/**
 * 페이지 이동 함수
 */
function fnLecPopLinkPage(pageNo){
	var url = "/mng/user/MberSelectListPop.do";
	var param = {
			pageIndex : pageNo,
			role : $("select[name=popRole]").val(),
			searchCondition : $("#popSearchCondition").val(),
			searchKeyword : $("#popSearchKeyword").val()
	};
	$('#layerPop').load(url, param);
	fnLayerCenter("#layerPop");
}

/**
 * 리스트 검색 함수
 */
function fnLecPopSearch(){
	var url = "/mng/user/MberSelectListPop.do";
	var param = {
			role : $("select[name=popRole]").val(),
			listGubun : $("#listGubun").val(),
			searchCondition : $("#popSearchCondition").val(),
			searchKeyword : $("#popSearchKeyword").val()
	};
	$('#layerPop').load(url, param);
	$("div#layerPop").css("width", "620px");
	fnLayerCenter("#layerPop");
}

/**
 * 값 전달함수
 */
function lecUserAddValidate(id){
	var lec_id = $("#lect_sch_id").val(); 
	var params = {
			"lec_sch_id" : lec_id,
			"userId" : id
	};
	
	if(confirm("수강신청자를 추가하시겠습니까?")){
	    $.ajax({
	        url     : "/mng/lec/app/LectureApplyInfoValidate.do",
	        type    : "POST",
	        data    : params,
	        dataType: "text",
	        success : function(data) {
	            // JSON 객체로 변환
	           	var jData = JSON.parse(data);
	            if(jData.result_cd == "200") {
	            	lecUserAddSubmit(lec_id,id);
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
function lecUserAddSubmit(lec_id, id){
	var params = {
			"selectedId" : lec_id,
			"selectedUserId" : id
	};
	
    $.ajax({
        url     : "/mng/lec/app/LectureApplyAdd.do",
        type    : "POST",
        data    : params,
        dataType: "text",
        success : function(data) {
            // JSON 객체로 변환
           	var jData = JSON.parse(data);
            if(jData.result_cd == "200") {
            	document.LectForm.lect_sch_id.value=lec_id;
            	document.LectForm.pageIndex.value = 1;
            	document.LectForm.cmd.value ="aList";
            	document.LectForm.action = "/mng/lec/app/CourseManage.do";
            	document.LectForm.submit();
            } else {
                alert(jData.result_msg);
            }
        },
        error : function(xhr,status,error) {
            //alert(error);
        }
    });
}
function fnLecRoleChangeList(){
	var url = "/mng/lec/reg/InsSearchPopup.do";
	var param = {
			role : $("select[name=popRole]").val(),
			listGubun : $("#listGubun").val(),
			searchCondition : $("#searchCondition").val(),
			searchKeyword : $("#searchKeyword").val()
	};
	$('#layerPop').load(url, param);
}


function fnUserRoleChangeList(){
	var url = "/mng/user/MberSelectListPop.do";
	var param = {
			role : $("#popRole").val(),
			listGubun : $("#listGubun").val(),
			searchCondition : $("#popSearchCondition").val(),
			searchKeyword : $("#popSearchKeyword").val()
	};
	$('#layerPop').load(url, param);
}

/*
 * 레이어를 화면 중앙에 위치시크는 함수
 */
function fnLayerCenter(id){
	$("div#"+id).css("top", ((jQuery(window).height() - $("div#"+id).outerHeight()) / 6) + jQuery(window).scrollTop() + "px");
	$("div#"+id).css("left", ((jQuery(window).width() - $("div#"+id).outerWidth()) / 2) + jQuery(window).scrollLeft() + "px");
}

/* layerPop.js add Start */
/**
 * 강좌 강사 검색창
 * 값 전달함수
 */
function fnLecInsReturnUserInfoTemp( num ){
	var size = $(".media_uId").length;
	
	var insName = $("#insName_"+num).val();
	var history = $("#history_"+num).val();
	var media_user_id = $("#media_user_id_"+num).val();
	var checkRoleChecked = "checked=\"checked\"";
    
    if($('input:radio[name=checkRole]:checked').length > 0) {
   		checkRoleChecked = "";
    }
	var addTr = "" +
	"<tr>" +
		"<td class='alignM'>" +
			"<input type='hidden' class='media_uId' name='media_uId' value='" + media_user_id + "' />" +
			"<input type='radio' id='text0301' class='inputRadio' name='checkRole' value='"+media_user_id+"' "+checkRoleChecked+"/><label for='text0301'></label>" +
		"</td>" +
		"<td>"+insName+"</td>" +
		"<td class='agl' >"+history+"</td>" +
		"<td class='alignM' ><a href='#' class='inDel'><img src='/images/mediacenter/btn/btn_del.gif' alt='삭제' /></a></td>" +
 	"</tr>";
	if($('.media_uId').val() == media_user_id){
		alert("이미 등록된 강사입니다.");
		return;
	}else {
		if ( size == 0 ) {
			$("#midia_tbody").html(addTr);
			popClose();
		} else {
			$("#midia_tbody").append(addTr);
			popClose();
		}
	}
}

/***
 * 강사검색 팝업창
 */
function InsSearchPopup(){
	$("div#layerPop").css("width", "620px");
	fnLayerCenter("#layerPop");
	var url = "/mng/lec/reg/InsSearchPopup.do";
	$('#layerPop').show();
	$('<div class="layerBg"></div>').appendTo('body');
	$('#layerPop').load(url);
}

/**
 * 강좌 강사 검색창
 * 페이지 이동
 */
function fnLecInsPopLinkPage(pageNo){
	var url = "/mng/lec/reg/InsSearchPopup.do";
	var param = {
			pageIndex : pageNo,
			searchCondition : $("#searchCondition").val(),
			searchKeyword : $("#searchKeyword").val()
	};
	$('#layerPop').load(url, param);
}

/**
 * 강좌 강사 검색창
 * 리스트 검색 함수
 */
function fnLecInsPopSearch(){
	var url = "/mng/lec/reg/InsSearchPopup.do";
	var param = {
			searchCondition : $("#searchCondition").val(),
			searchKeyword : $("#searchKeyword").val()
	};
	$('#layerPop').load(url, param);
}

function userPop(){ 
	 $("div#layerPop").css("width", "620px");
		fnLayerCenter("#layerPop");
		var url = "/mng/user/MberSelectListPop.do";
		$('#layerPop').show();
		$('<div class="layerBg"></div>').appendTo('body');
		$('#layerPop').load(url); //
	}
function fnUserPopLinkPage(pageNo){
	var url = "/mng/user/MberSelectListPop.do";
	var param = {
			pageIndex : pageNo,
			role : $("#popRole").val(),
			searchCondition : $("#popSearchCondition").val(),
			searchKeyword : $("#popSearchKeyword").val()
	};
	$('#layerPop').load(url, param);
}

function fnUserPopSearch(){
	var url = "/mng/user/MberSelectListPop.do";
	var param = {
			role : $("#popRole").val(),
			searchCondition : $("#popSearchCondition").val(),
			searchKeyword : $("#popSearchKeyword").val()
	};
	$('#layerPop').load(url, param);
}
/**
 * 강좌 강사 검색창
 * 리스트 검색 함수
 */
function fnLecInsPopSearch(){
	var url = "/mng/lec/reg/InsSearchPopup.do";
	var param = {
			searchCondition : $("#searchCondition").val(),
			searchKeyword : $("#searchKeyword").val()
	};
	$('#layerPop').load(url, param);
}
/**
 * 회원 아이디 중복검사 팝업
 */
function fnIdCheck(){
	$("div#layerPop").css("width", "440px");
	fnLayerCenter("#layerPop");
	var url = "/usr/auth/IdDplctCnfirmView.do";
	
	if($("#emailId").val() == ""){
		alert("아이디를 입력해주세요");
		$("#emailId").focus();
		return;
	}
	var param = {
			gubun : "1"
			, emailId : $("#emailId").val()
			, checkValue : $("#emailId").val()
	};
	if(fnCheckNotKorean($("#emailId").val()) && fnCheck($("#emailId").val())){
		$('#layerPop').show();
		$('<div class="layerBg"></div>').appendTo('body');
		$('#layerPop').load(url, param);
	}
}
function fnCheckEmailId(){
	var url = "/usr/auth/IdDplctCnfirmView.do";
	
	if($("#checkValue").val() == ""){
		alert("아이디를 입력해주세요");
		$("#checkValue").focus();
		return;
	}
	if($("#checkValue").val().length > 30){
		alert("아이디를 30자이내로 입력해주세요");
		$("#checkValue").focus();
		return;
	}
	var param = {
			gubun : $("#gubun").val()
			, checkValue : $("#checkValue").val()
	};
	if(fnCheckNotKorean($("#checkValue").val()) && fnCheck($("#checkValue").val())){
		$('#layerPop').load(url, param);
	}
	
}
/**
 * 강좌 강사 검색창
 * 값 전달함수
 */
function fnIdCheckReturnInfo(){
	
	var usedCnt = $("#usedCnt").val();
	var emailId = $("#resultId").val();
	
	if(usedCnt == 0){
		$("input[name=emailId]").val(emailId);
		$("input[name=emailIdDplct]").val(usedCnt);
		$("input[name=emailId]").attr("readonly",true);
		
		popClose();		
	}else if(usedCnt == 1){
		alert("이미사용중인 아이디입니다.");
		return;
	}else{
		alert("먼저 중복확인을 실행하십시오");
        return;
	}
}
/**
 * 회원 닉네임 중복검사 팝업
 */
function fnNickCheck(){
	$("div#layerPop").css("width", "440px");
	fnLayerCenter("#layerPop");
	var url = "/usr/auth/NickDplctCnfirmView.do";
	
	if($("#nickname").val() == ""){
		alert("닉네임을 입력해주세요");
		$("#nickname").focus();
		return;
	}
	var param = {
			gubun : "2"
			, nickname : $("#nickname").val()
			, checkValue : $("#nickname").val()
	};
	if(fnCheck($("#nickname").val())){
		$('#layerPop').show();
		$('<div class="layerBg"></div>').appendTo('body');
		$('#layerPop').load(url, param);
	}
}
function fnCheckNick(){
	var url = "/usr/auth/NickDplctCnfirmView.do";
	
	if($("#checkValue").val() == ""){
		alert("닉네임을 입력해주세요");
		$("#checkValue").focus();
		return;
	}
	var param = {
			gubun : $("#gubun").val()
			, checkValue : $("#checkValue").val()
	};
	if(fnCheck($("#checkValue").val())){
		$('#layerPop').load(url, param);
	}
	
}
/**
 * 강좌 강사 검색창
 * 값 전달함수
 */
function fnNickCheckReturnInfo(){
	
	var usedCnt = $("#usedCnt").val();
	var emailId = $("#resultNick").val();
	
	if(usedCnt == 0){
		$("input[name=nickname]").val(emailId);
		$("input[name=nicknameDplct]").val(usedCnt);
		$("input[name=nickname]").attr("readonly",true);
		
		popClose();		
	}else if(usedCnt == 1){
		alert("이미사용중인 아이디입니다.");
		return;
	}else{
		alert("먼저 중복확인을 실행하십시오");
        return;
	}
}
/**
 * 회원 이메일 중복검사 팝업
 */
function fnEmailCheck(){
	$("div#layerPop").css("width", "440px");
	fnLayerCenter("#layerPop");
	var url = "/usr/auth/EmailDplctCnfirmView.do";
	var checkValue = "";
	if($("#email1").val() == ""){
		alert("이메일을 입력해주세요");
		$("#email1").focus();
		return;
	}
	if($("#email2").val() == ""){
		alert("이메일 도메인을 입력해주세요");
		$("#email2").focus();
		return;
	}
	checkValue = $("#email1").val() + "@" + $("#email2").val();
	var param = {
			gubun : "3"
			, email1 : $("#email1").val()
			, email2 : $("#email2").val()
			, checkValue : checkValue
	};
	if(fnCheckNotKorean(checkValue) && fnCheck(checkValue) && fnCheckEmailValue(checkValue)){
		$('#layerPop').show();
		$('<div class="layerBg"></div>').appendTo('body');
		$('#layerPop').load(url, param);
	}
}
/**
 * 회원 이메일 중복검사
 */
function fnCheckEmail(){
	var url = "/usr/auth/EmailDplctCnfirmView.do";
	var checkValue = "";
	if($("#popEmail1").val() == ""){
		alert("이메일을 입력해주세요");
		$("#popEmail1").focus();
		return;
	}
	if($("#popEmail2").val() == ""){
		alert("이메일 도메인을 입력해주세요");
		$("#popEmail2").focus();
		return;
	}
	checkValue = $("#popEmail1").val() + "@" + $("#popEmail2").val();
	var param = {
			gubun : $("#gubun").val()
			, email1 : $("#popEmail1").val()
			, email2 : $("#popEmail2").val()
			, checkValue : checkValue
	};
	if(fnCheckNotKorean(checkValue) && fnCheck(checkValue) && fnCheckEmailValue(checkValue)){
		$('#layerPop').load(url, param);
	}
}
/**
 * 회원 이메일 값 전달함수
 */
function fnEmailCheckReturnInfo(){
	
	var usedCnt = $("#usedCnt").val();
	var email = $("#resultEmail").val();
	var email1 = fnStringSplit(email, "@",0);
	var email2 = fnStringSplit(email, "@",1);
	if(usedCnt == 0){
		$("input[name=email]").val(email);
		$("input[name=email1]").val(email1);
		$("input[name=email2]").val(email2);
		$("input[name=emailDplct]").val(usedCnt);
		$("input[name=email1]").attr("readonly",true);
		$("input[name=email2]").attr("readonly",true);
		
		popClose();		
	}else if(usedCnt == 1){
		alert("이미사용중인 이메일입니다.");
		return;
	}else{
		alert("먼저 중복확인을 실행하십시오");
        return;
	}
}
/**
 * 회원 휴대폰 중복검사 팝업
 */
function fnMobileCheck(){
	$("div#layerPop").css("width", "440px");
	fnLayerCenter("#layerPop");
	var url = "/usr/auth/MobileDplctCnfirmView.do";
	var checkValue = "";
	if($("#firstMobileno").val() == ""){
		alert("휴대폰 첫번째 번호를 입력해주세요");
		$("#firstMobileno").focus();
		return;
	}
	if($("#middleMobileno").val() == ""){
		alert("휴대폰 중간 번호를 입력해주세요");
		$("#middleMobileno").focus();
		return;
	}
	if($("#endMobileno").val() == ""){
		alert("휴대폰 마지막 번호를 입력해주세요");
		$("#endMobileno").focus();
		return;
	}
	checkValue = $("#firstMobileno").val() + $("#middleMobileno").val() + $("#endMobileno").val();
	var param = {
			gubun : "4"
			, mobile1 : $("#firstMobileno").val()
			, mobile2 : $("#middleMobileno").val()
			, mobile3 : $("#endMobileno").val()
			, checkValue : checkValue
	};
	if(fnCheckMobileValue($("#firstMobileno").val(), $("#middleMobileno").val(), $("#endMobileno").val()) && fnCheck(checkValue)){
		$('#layerPop').show();
		$('<div class="layerBg"></div>').appendTo('body');
		$('#layerPop').load(url, param);
	}
}
/**
 * 회원 휴대폰 중복검사
 */
function fnCheckMobile(){
	var url = "/usr/auth/MobileDplctCnfirmView.do";
	var checkValue = "";
	if($("#mobile1").val() == ""){
		alert("휴대폰 첫번째 번호를 입력해주세요");
		$("#mobile1").focus();
		return;
	}
	if($("#mobile2").val() == ""){
		alert("휴대폰 중간 번호를 입력해주세요");
		$("#mobile2").focus();
		return;
	}
	if($("#mobile3").val() == ""){
		alert("휴대폰 마지막 번호를 입력해주세요");
		$("#mobile3").focus();
		return;
	}
	checkValue = $("#mobile1").val() + $("#mobile2").val() + $("#mobile3").val();
	
	var param = {
			gubun : $("#gubun").val()
			, mobile1 : $("#mobile1").val()
			, mobile2 : $("#mobile2").val()
			, mobile3 : $("#mobile3").val()
			, checkValue : checkValue
	};
	if(fnCheckMobileValue($("#mobile1").val(), $("#mobile2").val(), $("#mobile3").val()) && fnCheck(checkValue)){
		$('#layerPop').load(url, param);
	}
}
/**
 * 회원 휴대폰 값 전달함수
 */
function fnMobileCheckReturnInfo(){
	
	var usedCnt = $("#usedCnt").val();
	var mobile1 = $("#mobile1").val();
	var mobile2 = $("#mobile2").val();
	var mobile3 = $("#mobile3").val();
	
	if(usedCnt == 0){
		$("input[name=firstMobileno]").val(mobile1);
		$("input[name=middleMobileno]").val(mobile2);
		$("input[name=endMobileno]").val(mobile3);
		$("input[name=mobileDplct]").val(usedCnt);
		$("input[name=firstMobileno]").attr("readonly",true);
		$("input[name=middleMobileno]").attr("readonly",true);
		$("input[name=endMobileno]").attr("readonly",true);
		
		popClose();		
	}else if(usedCnt == 1){
		alert("이미사용중인 이메일입니다.");
		return;
	}else{
		alert("먼저 중복확인을 실행하십시오");
        return;
	}
}

function fnCafeSearchSub(){
	var url = "/mng/bbs/cafe/MberSearchPopup.do";
	alert($("#searchCondition").val());
	alert($("#searchKeyword").val());
	var param = {
			searchCondition : $("#searchCondition").val(),
			searchKeyword : $("#searchKeyword").val()
	};
	$('#layerPopSub').load(url, param);
}
function fnCafeLinkPageSub(pageNo){
	var url = "/mng/bbs/cafe/MberSearchPopup.do";
	var param = {
			pageIndex : pageNo
			, searchCondition : $("#searchCondition").val()
			, searchKeyword : $("#searchKeyword").val()
	};
	$('#layerPopSub').load(url, param);
}
function fnReturnCafeUserInfo( userId, name ){
	$("#oprtr_nm").val(name);
	$("#oprtr_id").val(userId);
	popCloseSub();
	return false;
}


function fnCheckNotKorean(koreanStr){
    for(var i=0;i<koreanStr.length;i++){
        var koreanChar = koreanStr.charCodeAt(i);
        if( !( 0xAC00 <= koreanChar && koreanChar <= 0xD7A3 ) && !( 0x3131 <= koreanChar && koreanChar <= 0x318E ) ) {
        }else{
            //hangul finding....
           	alert("한글은 사용할 수 없습니다.");
            return false;
        }
    }
    return true;
}
// 2011.10.25 보안점검 후속조치
function fnCheck(str){
    var blank_pattern = /[\s]/g;
    var special_pattern = /[`~!#$%^&*|\\\'\";:\/?]/gi;

    if( special_pattern.test(str) == true || blank_pattern.test(str) == true ){
        alert("공백 및 특수문자는 사용할 수 없습니다.");
        return false;
    } else {
   		return true;
	}
}
function fnCheckEmailValue(checkValue){
	var t = escape(checkValue);
	if(t.match(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i) == null){
		alert("이메일을 올바르게 입력해 주세요.");
		document.checkForm.checkValue.focus();
	    return false;
	} 
	return true;
}
function emailSvrChg(emailSvf){
	$("#popEmail2").val(emailSvf);
}
function fnCheckMobileValue(mobile1,mobile2,mobile3) {
	var frm = document.checkForm;
	// 휴대폰번호를 입력시 올바른 휴대폰 번호인지 체크  
	if(mobile1 != "") { 
		var rgEx = /(01[016789])[-](\d{4}|\d{3})[-]\d{4}$/g;  
		var strValue = mobile1 + "-" + mobile2 + "-" + mobile3;
		var chkFlg = rgEx.test(strValue);
		if(!chkFlg){
			alert("올바른 휴대폰번호가 아닙니다.");
			return false; 
		}
	}
	return true;
}
/*
 * 레이어를 화면 중앙에 위치시크는 함수
 */
function fnLayerCenter(id){
	$(id).css("top", ((jQuery(window).height() - $(id).outerHeight()) / 6) + jQuery(window).scrollTop() + "px");
	$(id).css("left", ((jQuery(window).width() - $(id).outerWidth()) / 2) + jQuery(window).scrollLeft() + "px");
}
/*
 * 레이어를 화면 중앙에 위치시크는 함수
 */
function fnStringSplit(str, gubun, num){
	var strArray = str.split(gubun);
	return strArray[num];
}
