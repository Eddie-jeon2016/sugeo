<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function insertOrder(){
	
 	var year = $("#selectYear option:selected").val();
	var month = $("#selectMonth option:selected").val();
	var date = $("#selectDate option:selected").val();
	var time = $("#selectTime option:selected").val();
	
	if($("#memNm").val() == '') {
		alert("이름을 입력해주세요.");
		$("#memNm").focus();
		return ;
	}
	if($("#mobile").val() == '') {
		alert("휴대폰번호를 입력해주세요.");
		$("#mobile").focus();
		return ;
	}
	var postCode = $("#postCode").val();
	var addr1 = $("#address1").val();
	var addr2 = $("#address2").val();
	if(postCode =='') {
		alert("우편번호를 입력하세요.");
		$("#postCode").focus();
		return ;
	}
	if(addr1 =='') {
		alert("주소를 입력하세요.");
		$("#address1").focus();
		return ;
	}
	if(addr2 =='') {
		alert("주소를 입력하세요.");
		$("#address2").focus();
		return ;
	}
	var address = postCode+" "+addr1+" "+addr2; 
	console.log(selectYear);
	console.log(selectMonth);
	console.log(selectDate);
	$("#visitDttm").val(year+":"+month+":"+date);
	$("#addr").val(address);
	
	$("#orderForm").ajaxSubmit({
		url : "<c:url value='/front/order/insertOrder.do'/>",
		type : "POST",
		dataType : "json",
		success : function(data) {
			if(data.result_code == '200') {
				$("#orderId").text(data.resultOrderVO.orderNo);
				$("#orderMemNm").html(data.resultOrderVO.memNm);
				$("#orderMobile").text(data.resultOrderVO.mobile);
				$("#orderAddr").text(data.resultOrderVO.addr);
				$("#orderTime").text(time);
				$("#orderCmpyNm").text(data.resultOrderVO.cmpyNm);
				go_popup('1');
			}
		},
		error: function(xhr, status, error) {
			alert(error);
		}
	});
	
	/* alert("확인을 누르면 비동기 요청으로 예약을 추가하고 다시 메인으로??");
	$("#orderForm").attr("action","/front/item/itemView.do");
	$("#orderForm").submit(); */
	
}


function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = ''; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                fullAddr = data.roadAddress;

            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                fullAddr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
            if(data.userSelectedType === 'R'){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postCode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('address1').value = fullAddr;

            // 커서를 상세주소 필드로 이동한다.
            document.getElementById('address2').focus();
        }
    }).open();
}
</script>
	<div id="wrap">
	<jsp:include page="../include/top.jsp"/>
                <div class="container">
        	<!-- 방문예약 달력 (S) -->
 			<div class="daypicker">
       			 <div class="daypicker_weekHeader">
       			 	<ul>
						<li> 일 </li>
						<li> 월 </li>
						<li> 화 </li>
						<li> 수 </li>
						<li> 목 </li>
						<li> 금 </li>
						<li> 토 </li>
       			 	</ul>
       			</div>
       			
       			<div class="daypicker_Month">
					<div class="caption">
						<a title="이전달" href="#">이전달</a>
						<strong>2018. 10</strong>
						<a title="다음달" href="#">다음달</a>
					</div>
       			
					<table class="daypicker_table">  
						<tbody>
							<tr>
							<td></td><!-- 공휴일 .holiday 클래스 추가  / 당일 스타일 추가 / 선택시 .select , 당일 스타일 삭제 -->
							<td class="blocked_out" role="button" aria-label="Not available. 월요일, 2018년 10월 1일" tabindex="-1">1</td>
							<td class="blocked_out" role="button" aria-label="Not available. 화요일, 2018년 10월 2일" tabindex="-1">2</td>
							<td class="blocked_out" role="button" aria-label="Not available. 수요일, 2018년 10월 3일" tabindex="-1">3</td>
							<td class="blocked_out" role="button" aria-label="Not available. 목요일, 2018년 10월 4일" tabindex="-1">4</td>
							<td class="blocked_out" role="button" aria-label="Not available. 금요일, 2018년 10월 5일" tabindex="-1">5</td>
							<td class="blocked_out" role="button" aria-label="Not available. 토요일, 2018년 10월 6일" tabindex="-1">6</td>
							</tr>

							<tr>
							<td class="blocked_out" role="button" aria-label="Not available. 일요일, 2018년 10월 7일" tabindex="-1">7</td>
							<td class="blocked_out" role="button" aria-label="Not available. 월요일, 2018년 10월 8일" tabindex="-1">8</td>
							<td class="blocked_out holiday" role="button" aria-label="Not available. 화요일, 2018년 10월 9일" tabindex="-1">9</td>
							<td class="blocked_out" role="button" aria-label="Not available. 수요일, 2018년 10월 10일" tabindex="-1">10</td>
							<td role="button" aria-label="Choose 목요일, 2018년 10월 11일 as your check-in date. It's available." tabindex="0" style="border: 3px solid #05c879;">11</td>
							<td role="button" aria-label="Choose 금요일, 2018년 10월 12일 as your check-in date. It's available." tabindex="-1">12</td>
							<td role="button" aria-label="Choose 토요일, 2018년 10월 13일 as your check-in date. It's available." tabindex="-1">13</td>
							</tr>

							<tr>
							<td role="button" aria-label="Choose 일요일, 2018년 10월 14일 as your check-in date. It's available." tabindex="-1">14</td>
							<td class="select" role="button" aria-label="Choose 월요일, 2018년 10월 15일 as your check-in date. It's available." tabindex="-1">15</td>
							<td role="button" aria-label="Choose 화요일, 2018년 10월 16일 as your check-in date. It's available." tabindex="-1">16</td>
							<td role="button" aria-label="Choose 수요일, 2018년 10월 17일 as your check-in date. It's available." tabindex="-1">17</td>
							<td role="button" aria-label="Choose 목요일, 2018년 10월 18일 as your check-in date. It's available." tabindex="-1">18</td>
							<td role="button" aria-label="Choose 금요일, 2018년 10월 19일 as your check-in date. It's available." tabindex="-1">19</td>
							<td role="button" aria-label="Choose 토요일, 2018년 10월 20일 as your check-in date. It's available." tabindex="-1">20</td>
							</tr>

							<tr>
							<td role="button" aria-label="Choose 일요일, 2018년 10월 21일 as your check-in date. It's available." tabindex="-1">21</td>
							<td role="button" aria-label="Choose 월요일, 2018년 10월 22일 as your check-in date. It's available." tabindex="-1">22</td>
							<td role="button" aria-label="Choose 화요일, 2018년 10월 23일 as your check-in date. It's available." tabindex="-1">23</td>
							<td role="button" aria-label="Choose 수요일, 2018년 10월 24일 as your check-in date. It's available." tabindex="-1">24</td>
							<td role="button" aria-label="Choose 목요일, 2018년 10월 25일 as your check-in date. It's available." tabindex="-1">25</td>
							<td role="button" aria-label="Choose 금요일, 2018년 10월 26일 as your check-in date. It's available." tabindex="-1">26</td>
							<td role="button" aria-label="Choose 토요일, 2018년 10월 27일 as your check-in date. It's available." tabindex="-1">27</td>
							</tr>

							<tr>
							<td role="button" aria-label="Choose 일요일, 2018년 10월 28일 as your check-in date. It's available." tabindex="-1">28</td>
							<td role="button" aria-label="Choose 월요일, 2018년 10월 29일 as your check-in date. It's available." tabindex="-1">29</td>
							<td role="button" aria-label="Choose 화요일, 2018년 10월 30일 as your check-in date. It's available." tabindex="-1">30</td>
							<td role="button" aria-label="Choose 수요일, 2018년 10월 31일 as your check-in date. It's available." tabindex="-1">31</td>
							<td></td>
							<td></td>
							<td></td>
							</tr>
						</tbody>
					</table>
       			</div>
			</div><!-- 방문예약 달력 (E) -->
			        	
		   	<form action="orderForm" id="orderForm" name="orderForm" method="post">
		   	<input type="hidden" name="itemNo" id="itemNo" value="${itemNo}">
		   	<input type="hidden" name="addr" id="addr">
		   	<input type="hidden" name="visitDttm" id="visitDttm">
		   	<input type="hidden" name="expPoint" id="expPoint" value="${totalPoint}">
		   	<input type="hidden" name="expRecycleQuty" id="expRecycleQuty" value= <c:if test="${itemKg == 0}">"${itemCnt}"</c:if>
		   																		  <c:if test="${itemCnt == 0}">"${itemKg}"</c:if>>
		   	<input type="hidden" name="cmpyNo" id="cmpyNo">
        	<div class="select_day">
        		<ul>
					<li style="margin-right:1.5%">
						<label class="d_none">년도</label>
						<select title="년도 선택" id="selectYear" name="selectYear">
							<option value="2018">2018년</option>
							<option value="2019">2019년</option>
							<option value="2020">2020년</option>
						</select>
                    </li>
                    <li style="margin-right:1.5%">
						<label class="d_none">월</label>
						<select title="월 선택" id="selectMonth" name="selectMonth">
							<option value="10">10월</option>
							<option value="11">11월</option>
							<option value="12">12월</option>
						</select>
                    </li>
                    <li>
						<label class="d_none">일</label>
						<select title="일 선택" id="selectDate" name="selectDate">
							<option value="15">15일</option>
							<option value="16">16일</option>
							<option value="17">17일</option>
						</select>
                    </li>
                    <li>
						<label class="d_none">방문시간</label>
						<select title="방문시간 선택" id="selectTime" name="selectTime">
							<option value="오전(09:00 ~ 12:00)">오전(09:00 ~ 12:00) 방문시간</option> 
						</select>
                    </li>
				</ul>
			</div>
       	
       		<div class="area_info">
       			<ul>
					<li>
						<input title="이름 입력" type="text" placeholder="이름" name="memNm" id="memNm">
					</li>
					<li>
                     	<input title="휴대폰 번호 입력" type="text" placeholder="휴대폰번호" name="mobile" id="mobile"> 
                    </li>
                    <li class="num mgt10">
                     	<input title="우편번호" type="text" placeholder="우편번호"  id="postCode">
                     	<button type="button" class="btn gray small"  onclick="sample6_execDaumPostcode()"> 검색 </button>
                    </li>
                    <li>
                    	<input title="주소 입력" type="text" placeholder="주소" id="address1" >
                    	<input title="상세주소 입력" type="text" placeholder="상세주소" class="mgt3" id="address2">
					</li>
				</ul>
			</div>
			</form>
        	
        	<div class="btn_wrap f_l w100p">
				<!-- <a title="완료" href="#" class="btn green" onClick="go_popup('1')">완료</a> -->
				<a title="완료" href="#" class="btn green" onClick="javascript:insertOrder();">완료</a>
			</div>
			
			<div class="inquiryBox reserv"  id="popup1" style="display:  ;">
				<div class="inqu_top">  
					<p><strong>예약확인</strong></p>
					<span class="b-close"><img src="/images/icon/btn_del.png" alt="닫기" height="15"></span>
				</div>
                    
                <ul class="reserv_ul">
					<li><strong>예약번호</strong><span id="orderId">RV00410968</span></li>
					<li><strong>이름</strong><span id="orderMemNm">최순석</span></li> 
					<li><strong>전화번호</strong><span id="orderMobile">010-1234-5678</span></li>
					<li><strong>주소</strong><span id="orderAddr">(13522)서울 강남구 강남대로298(역삼동,푸르덴에셈타워)</span></li>   
					<li><strong>수거날짜</strong><span id="orderTime">2018-10-15 오전</span></li>  
					<li><strong>수거지점</strong><span id="orderCmpyNm">김포 대강</span></li>  
				</ul>
				
				<p class="mgt10 txt_c f_wB txt_gray5 pdb10">해당정보가 맞는지 확인해주세요.</p>
				
				<div class="btn_wrap f_l w100p txt_c pdb0">
					<a title="확인" href="/front/item/itemView.do" class="btn">확인</a>
				</div>
			</div>
 		</div><!-- container (E) -->
 	</div><!-- wrap (E) -->