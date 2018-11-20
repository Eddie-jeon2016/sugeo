<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript" src="/js/jquery.vticker.min.js"></script>
	<script type="text/javascript">
	  $(document).ready(function(){
		$(".rolling_banner").vTicker(); 
	   });
	</script>
	<div id="wrap">
	<jsp:include page="/WEB-INF/jsp/front/include/top.jsp"/>
        <div class="container active"><!-- // [6개이상품목] item에 값이 있을때 클래스 .active 활성화 -->
        	<div class="main_Box">
            	<!-- item1 헌 의류 (S) -->
        		<dl>
					<dt>
						<img src="images/icon/bg_item1.png" height="48" alt="icon">
						<a title="상세설명" href="#" onClick="go_popup('1')"> 상세설명</a>
					</dt>
					<dd class="item_txt">
					<strong>헌 의류</strong><span>헌 옷, 이불, 커튼, 신발 등</span></dd>
					<dd class="item_unit"><!-- //활성화시 .active -->
						<input type="button" title="수치 내리기" name="minus" class="minus active">
						<strong><i>20</i>Kg</strong>
                        <input type="button" title="수치 올리기" name="plus" class="plus">
					</dd>
 				</dl>
 				
 				<div class="inquiryBox"  id="popup1" style="display:  ;">
 					<div class="inqu_top item1"> <!-- // .item1 ~ .item6 현재 시안기준 -->
                    	<p><strong>헌 의류</strong> <i>500P / Kg</i></p>
                    	<span class="b-close"><img src="images/icon/btn_del.png" alt="닫기" height="15"></span>
                    </div>
                    
                    <dl>
                    	<dt>수거 가능 품목</dt>
                        <dd>면 티 / 나시티 / 스커트 / 바지/ 와이셔츠(남방) / 턱시도 / 드레스 / 침의(잠옷) / 핸드백 / 토트백 / 크로스백 / 백팩 / 학생용 가방 / 서류 가방 / 맨즈백 / 가죽 가방 / 운동화 / 구두(남성용, 여성용) / 하이힐 / 등산화 / 단화 / 샌들 / 런닝화 / 축구화 / 누비이불 / 극세사이불</dd>
                    
                    	<dt>수거 불가능 품목</dt>
                        <dd>손상된 옷(곰팡이, 악취, 페인트 칠, 구멍난 옷 등) / 한복 / 단체 매각의류(단체복, 유니폼, 가운, 찜질방 옷) / 원단(자 투리 천) / 솜이불 / 베개 / 차렵이불 / 여행용가방(캐리어) / 하드케이스가방 / 부츠 / 슬리퍼 / 인라인스케이트</dd>
                        
                        <dt class="inqu_bottom"><img src="images/icon/bg_pop_txt.png" alt="두껍아 두껍아 헌 옷 줄게 새 옷 다오~!" height="16"></dt>
                        <dd class="inqu_bottom">헌 옷을 주시면 새 옷으로 드립니다.</dd>
                                        
                    </dl>
				</div>
				<!-- item1 헌 의류 (E) -->
                
                <!-- item2 종이류 (S) -->
				<dl class="mgl3p"> <!-- //짝수 품목 .mgl3p -->
					<dt>
						<img src="images/icon/bg_item2.png" alt="icon">
						<a title="상세설명" href="#" onClick="go_popup('2')"> 상세설명</a>
					</dt>
					<dd class="item_txt">
					<strong>종이류</strong><span>책, 이면지, A4용지 등</span></dd>
					<dd class="item_unit"> <!-- //활성화시 .active -->
						<input type="button" title="수치 내리기" name="minus" class="minus">
						<strong><i>0</i>Kg</strong>
                        <input type="button" title="수치 올리기" name="plus" class="plus">
					</dd>
 				</dl>
                
                <div class="inquiryBox"  id="popup2" style="display:  ;">
 					<div class="inqu_top item2"> <!-- // .item1 ~ .item6 현재 시안기준 -->
                    	<p><strong>종이류</strong> <i>50원 / Kg</i></p>
                    	<span class="b-close"><img src="images/icon/btn_del.png" alt="닫기" height="15"></span>
                    </div>
                    
                    <dl>
                    	<dt>수거 가능 품목</dt>
                        <dd>책 / 잡지 / 이면지 / A4용지 / 전집 / 자습서 / 사전 / 동화책</dd>
                    
                    	<dt>수거 불가능 품목</dt>
                        <dd>신문 / 박스 / 도배지 / 젖은종이 / 골판지</dd>
                        
                        <dt class="inqu_bottom"><img src="images/icon/bg_pop_txt.png" alt="두껍아 두껍아 헌 옷 줄게 새 옷 다오~!" height="16"></dt>
                        <dd class="inqu_bottom">정리가 되지 않은 상태일 경우 수거가 불가능할 수 있어요. 꼭 박스포장 또는 끈으로 묶어주세요.</dd>
                                        
                    </dl>
				</div>
                <!-- item2 종이류 (E) -->
                
                <!-- item3 컴퓨터 (S) -->
 				<dl> 
					<dt>
						<img src="images/icon/bg_item3.png" alt="icon">
						<a title="상세설명" href="#" onClick="go_popup('3')"> 상세설명</a>
					</dt>
					<dd class="item_txt">
					<strong>컴퓨터</strong><span>LCD모니터, 컴퓨터 본체 등</span></dd>
					<dd class="item_unit active"> <!-- //활성화시 .active -->
						<input type="button" title="수치 내리기" name="minus" class="minus">
						<strong><i>0</i>개</strong>
                        <input type="button" title="수치 올리기" name="plus" class="plus">
					</dd>
 				</dl> 
                
                <div class="inquiryBox"  id="popup3" style="display:  ;">
 					<div class="inqu_top item3"> <!-- // .item1 ~ .item6 현재 시안기준 -->
                    	<p><strong>컴퓨터</strong> <i>3000원 / 개</i></p>
                    	<span class="b-close"><img src="images/icon/btn_del.png" alt="닫기" height="15"></span>
                    </div>
                    
                    <dl>
                    	<dt>수거 가능 품목</dt>
                        <dd>본체 / 노트북 / LCD / LED모니터 / CRT모니터(무상수거) / 램, 하드없는 본체(무상수거)</dd>
                    
                    	<dt>수거 불가능 품목</dt>
                        <dd>HDD / 손상된 모니터 / 프린터기 / 복사기 / 주변기기(마우스,키보드등..)</dd>
                        
                        <dt class="inqu_bottom"><img src="images/icon/bg_pop_txt.png" alt="두껍아 두껍아 헌 옷 줄게 새 옷 다오~!" height="16"></dt>
                        <dd class="inqu_bottom">그 외 소형가전제품도 무상처리가 가능합니다. 수거 기사님께 문의하세요.</dd>
                                        
                    </dl>
				</div>
                <!-- item3 컴퓨터 (E) -->
                
                <!-- item4 가전제품 (S) -->
 				<dl class="mgl3p"> <!-- //짝수 품목 .mgl3p --> 
					<dt>
						<img src="images/icon/bg_item4.png" alt="icon">
 					</dt>
					<dd class="item_txt">
					<strong>가전제품</strong><span>냉장고, 세탁기, 에어컨 등</span></dd>
					<dd class="item_unit"> <!-- //활성화시 .active -->
						<button type="button" title="바로 판매하기" name="sale" class="sale"> 바로 판매하기</button>
					</dd>
 				</dl> 
                <!-- item4 가전제품 (E) -->
 				
                <!-- item5 휴대폰 (S) -->
 				<dl> 
					<dt>
						<img src="images/icon/bg_item5.png" alt="icon">
						<a title="상세설명" href="#" onClick="go_popup('5')"> 상세설명</a>
					</dt>
					<dd class="item_txt">
					<strong>휴대폰</strong><span>2G/3G폰, 피쳐폰, 폴더폰 등</span></dd>
					<dd class="item_unit"> <!-- //활성화시 .active -->
						<input type="button" title="수치 내리기" name="minus" class="minus">
						<strong><i>0</i>개</strong>
                        <input type="button" title="수치 올리기" name="plus" class="plus">
					</dd>
 				</dl>
                
                <div class="inquiryBox"  id="popup5" style="display:  ;">
 					<div class="inqu_top item5"> <!-- // .item1 ~ .item6 현재 시안기준 -->
                    	<p><strong>컴퓨터</strong> <i>700원 / 개</i></p>
                    	<span class="b-close"><img src="images/icon/btn_del.png" alt="닫기" height="15"></span>
                    </div>
                    
                    <dl>
                    	<dt>수거 가능 품목</dt>
                        <dd>폐 폰 / 피쳐 폰 / 2G 폰 / 폴더 폰 / 슬라이드 폰</dd>
                    
                    	<dt>수거 불가능 품목</dt>
                        <dd>깨진 폰 / 망가진 폰 / 부숴진 폰 / 침수 폰 / CD PLAYER / MP3</dd>
                        
                        <dt class="inqu_bottom"><img src="images/icon/bg_pop_txt.png" alt="두껍아 두껍아 헌 옷 줄게 새 옷 다오~!" height="16"></dt>
                        <dd class="inqu_bottom">스마트 폰의 경우 고가로 수거하고 있습니다. 고객센터로 연락하여 주세요.</dd>
                                        
                    </dl>
				</div>
                <!-- item5 휴대폰 (E) -->
 				
                <!-- item6 고철 (S) -->
 				<dl class="mgl3p"> <!-- //짝수 품목 .mgl3p -->
					<dt>
						<img src="images/icon/bg_item6.png" alt="icon">
						<a title="상세설명" href="#" onClick="go_popup('6')"> 상세설명</a>
					</dt>
					<dd class="item_txt">
					<strong>고철</strong><span>아령, 숟가락, 젓가락 등</span></dd>
					<dd class="item_unit"> <!-- //활성화시 .active -->
						<input type="button" title="수치 내리기" name="minus" class="minus">
						<strong><i>0</i>Kg</strong>
                        <input type="button" title="수치 올리기" name="plus" class="plus">
					</dd>
 				</dl>
                
                 <div class="inquiryBox"  id="popup6" style="display:  ;">
 					<div class="inqu_top item6"> <!-- // .item1 ~ .item6 현재 시안기준 -->
                    	<p><strong>고철</strong> <i>400원 / Kg</i></p>
                    	<span class="b-close"><img src="images/icon/btn_del.png" alt="닫기" height="15"></span>
                    </div>
                    
                    <dl>
                    	<dt>수거 가능 품목</dt>
                        <dd>냄비 / 프라이팬 / 스테인리스 그릇,샷시 / 양은냄비 / 주전자</dd>
                    
                    	<dt>수거 불가능 품목</dt>
                        <dd>도자기류(사기그릇,도자기,자기,토기) / 플라스틱류</dd>
                        
                        <dt class="inqu_bottom"><img src="images/icon/bg_pop_txt.png" alt="두껍아 두껍아 헌 옷 줄게 새 옷 다오~!" height="16"></dt>
                        <dd class="inqu_bottom">비철은 수거 가능한 품목이며, 고철과 다르게 자석에 붙지 않는 제품입니다.</dd>
                                        
                    </dl>
				</div>
                <!-- item6 고철 (E) -->
                
                
			</div>
            
            <div class="item_sum" style="display:  ">
            	<p>
                	<span class="be_p"><em><strong>5,000</strong>P</em> 이상 방문 가능</span>
                    <span class="total_p">TOTAL <i><strong>100,000</strong>P</i></span>
                </p>
                <div class="btn_wrap pdtb0">
                    <a title="다음" href="02_4sub.html" class="btn green">다음</a>
                </div>
            </div>
        
 		</div><!-- container (E) -->
 	</div><!-- wrap (E) -->
<jsp:include page="/WEB-INF/jsp/front/include/hideMenu.jsp"/>    