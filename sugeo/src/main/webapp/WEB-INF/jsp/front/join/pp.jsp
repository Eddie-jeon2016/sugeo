<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="/WEB-INF/jsp/front/include/head.jsp"/>
<body class="bg_white">
	<div id="wrap">
		<div class="loginBox">
			<button title="뒤로" href="javascript:history.back(-2);"> <img src="images/common/btn_back.png" alt="뒤로" height="20"></button>
            <span class="idps_back">아이디/비밀번호 설정</span> 
            <a title="건너뛰기" href="00_join3.html" class="nextimg"><img src="images/common/btn_nexT.gif" alt="건너뛰기" height="15"></a>
			
			<div class="login_top">
				<h1><img src="images/common/bg_lv2.png" alt="img" height="82"></h1>
				<p class="f_14">수거짱 예약시 필요한 <strong class="txt_gray3">개인정보</strong>를 입력해주세요. </p>
			</div>
			
			<div class="join_info_input">
				<ul>
					<li><input title="이름 입력" type="text" placeholder="이름"> </li> 
                    <li class="p_r mgb0">
                        <input title="휴대폰번호 입력" type="text" placeholder="휴대폰번호"> 
                        <button title="인증" href="" class="btn grayL s_small" style="width:78px">인증</button> 
                    </li> 
                    <li class="p_r mgt-1">
                    	<input title="인증번호 입력" type="text" placeholder="인증번호"> 
                        <button title="확인" href="" class="btn grayL s_small" style="width:78px">확인</button> 
                    </li> 
                    
                    <li class="bank p_r mgb0">
                    	<label class="d_none">은행선택</label>
                            <select title="은행선택" style="width:110px" class="p_a">
                                <option value="bank_select">은행선택</option> 
                            </select>
                    	<input title="예금주" type="text" placeholder="예금주"> 
                    </li> 
                    <li class="mgt-1"><input title="계좌번호" type="text" placeholder="계좌번호"> </li> 
                    
                    <li class="p_r mgb0">
                    	<input title="우편번호 입력" type="text" placeholder="우편번호" disabled="disabled"> 
                        <button title="우편번호 검색" href="" class="btn grayL s_small" style="width:105px">우편번호 검색</button> 
                    </li> 
                    <li class="mgt-1 mgb0"><input title="주소" type="text" placeholder="주소" disabled="disabled"> </li> 
                    <li class="mgt-1"><input title="상세주소" type="text" placeholder="상세주소" disabled="disabled"> </li> 
				</ul>
                
                <p class="txt">이름과 전화번호를 제외한 나머지 항목은 수거예약 과정에서도 입력이 가능하므로, <span class="txt_green">‘건너뛰기’</span><strong>를 누르시면 개인정보 입력 없이 가입이 완료</strong>됩니다.</p>
                
                <dl>
                	<dt class="agree mgt10">서비스 이용약관 <i>(필수)</i> 
                    	<span class="rd_ck"><input type="radio" name="rd1" id="r1_1" checked> <label for="r1_1" class="f_14"></label></span>
                    </dt>
                    <dd>
                    	<ul class="agree_box">
                        	<li>수거짱(이하 “회사”)은 고객의 개인정보를 소중히 다루고 있으며, 『정보통신망이용 촉진 및 정보 보호 등에 관한 법률 』 상의 개인정보보호 규정 및 정보통신부가 제정한 『 개인정보보호 및 취급방침』을 준수하고 있습니다. 회사는 개인정보취급방침을 통해 아래와 같이 상담신청시 제공해주시는 개인정보의 수집 및 이용목적과 개인정보보호를 위한 회사의 조치 사항을 고지합니다. </li>
							<li>1. 개인정보 수집 · 이용 · 제공 등 에 대한 동의 회사는 고객이 개인정보취급방침 내용에 대하여 동의 여부를 선택할 수 있는 절차를 두고 있으며, 고객이 제공한 정보는 개인정보취급방침에 명시된 내용 이외의 목적으로는 사용되지 아니합니다. </li>
                        	
                            <li>2. 수집하는 개인정보의 범위 및 수집방법 회사에서는 고객의 상담신청 및 서비스신청 등을 위한 필수적인 정보를 입력 받고 있습니다. 
                                <span> - 이벤트 참여시 받는 필수 정보 : 이름, 연락처, 이메일 등 수거예약 과정에서 필요한 사항 등</span>
                                <span> - 서비스 이용과정이나 처리 과정에서 자동으로 생성되어 수집될 수 있는 정보 : IP Address, 유입경로 등 </span>
                           </li>
                        </ul>
                    </dd>
                </dl>
                
                <dl>
                	<dt class="agree">개인정보 수집 및 이용 안내 <i>(필수)</i> 
                    	<span class="rd_ck"><input type="radio" name="rd1" id="r1_1"> <label for="r1_1" class="f_14"></label></span>
                    </dt>
                    <dd>
                    	<ul class="agree_box">
                        	<li>정보통신망법 규정에 따라 수거짱에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인 정보의 보유 및 이용기간을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다. </li>
                        </ul>
                    </dd>
                </dl>
			</div>
            
            <div class="btn_wrap col2 mgt10">
            	<button title="이전" href="00_join.html" class="btn"> 이전 </button>
                <button title="다음" href="00_join2.html" class="btn green"> 다음 </button>
            </div>
		</div>
 	</div><!-- wrap (E) -->
     
</body>
</html>