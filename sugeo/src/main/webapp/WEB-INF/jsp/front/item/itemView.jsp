<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script>
function next(){
	alert("수거예약하러 ㄱㄱ");
	$("#itemForm").attr("action","/front/order/insertOrderView.do");
	$("#itemForm").submit();
}
function uploadOrder(itemNo){
	$("#itemNo").val(itemNo);
	$("#itemForm").attr("action","/front/order/insertUploadOderView.do");
	$("#itemForm").submit();
}
$(document).ready(function(){
	$(".plus").on('click',function(){
		
		$(".item_unit").removeClass("active");
		$(this).parent().addClass("active");
		
		var itemcnt = 0;
		// 아이템 수량
		itemcnt = $(this).parent().find('.item_cnt').val();
		itemcnt++ ;
		
		// 아이템 수량을 다시 저장
		$(this).parent().find('.item_cnt').val(itemcnt);
		
		// 아이템 수량 보여주기
		$(this).parent().find('.window_cnt').text(itemcnt);
		
		// 포인트 계산
		var price = $(this).parent().find('.item_price').val();
		
		// 총 포인트 계산
		var total = $("#total").text();
		total = Number(total);
		var multi = 1 * price;
		total = total + multi;
		
		//화면에 보여주기
		$("#total").text(total);
		$(".item_sum").css("display","block");
		
		// 보낼 데이터 저장
		var item_no = $(this).parent().find('.item_no').val();
		var item_unit = $(this).parent().find('.item_unit').val();
		$("#param"+item_no).val(item_no+","+itemcnt+","+item_unit);
		
	});	
	
	$(".minus").click(function() {
		
		$(".item_unit").removeClass("active");
		$(this).parent().addClass("active");
		var itemcnt = 0;
		// 아이템 수량
		itemcnt = $(this).parent().find('.item_cnt').val();
		itemcnt-- ;
		
		if(itemcnt < 0) {
			alert("수량은 0이상이여야합니다.");
			return ;
		}
		// 아이템 수량을 다시 저장
		$(this).parent().find('.item_cnt').val(itemcnt);
		
		// 아이템 수량 보여주기
		$(this).parent().find('.window_cnt').text(itemcnt);
		
		// 포인트 계산
		var price = $(this).parent().find('.item_price').val();
		
		// 총 포인트 계산
		var total = $("#total").text();
		total = Number(total);
		var multi = 1 * price;
		total = total - multi;
		
		//화면에 보여주기
		$("#total").text(total);
		$(".item_sum").css("display","block");
		
		// 보낼 데이터 저장
		var item_no = $(this).parent().find('.item_no').val();
		var item_unit = $(this).parent().find('.item_unit').val();
		$("#param"+item_no).val(item_no+","+itemcnt+","+item_unit);
	});
});
</script>
<form name="itemForm" id="itemForm" method="post">
	<input type="hidden" id="itemNo" name="itemNo">
	<c:forEach var="item" items="${itemList}" varStatus="status">
		<input type="hidden" name="params" id="param${item.itemNo}" value="">
	</c:forEach>
</form>
	<div id="wrap">
	<jsp:include page="../include/top.jsp"/>
        <div class="container active"><!-- // [6개이상품목] item에 값이 있을때 클래스 .active 활성화 -->
        	<div class="main_Box">
        		<c:forEach var="item" items="${itemList}" varStatus="status">
        		<c:if test="${status.count % 2 == 0 }">
        			<dl class="mgl3p">
        		</c:if>
        		<c:if test="${status.count % 2 == 1 }">
        			<dl>        		
        		</c:if>
        				<dt>
	        				<img src="/images/icon/bg_item1.png" height="48" alt="icon">
							<a title="상세설명" href="#" onClick="go_popup('${status.count}')"> 상세설명</a>
        				</dt>
        				<dd class="item_txt">
        				<strong>${item.itemNm}</strong><span>${item.itemSimpDesc}</span></dd>
        				<c:if test="${item.itemTp eq '100'}">
	        				<dd class="item_unit" id="${item.itemNo}" <%-- onclick="javascript:change('${item.itemNo}');" --%>><!-- //활성화시 .active -->
	        					<input type="hidden" name="item_no" class="item_no" value="${item.itemNo}">
	        					<input type="hidden" name="price" class="item_price" value="${item.price}">
	        					<input type="hidden" name="unit" class="item_unit" value="${item.unit}">
	        					<input type="hidden" name="unit" class="item_cnt" value="0">
								<input type="button" title="수치 내리기" name="minus" class="minus active" <%-- onclick="javascript:quty('${item.itemNo}','m');" --%>>
								<strong><i id="quty_${item.itemNo}"><span id="cnt_${status.count}" class="window_cnt">0</span></i>${item.unit}</strong>
		                        <input type="button" title="수치 올리기" name="plus" class="plus" <%-- onclick="javascript:quty('${item.itemNo}','p');" --%>>
							</dd>
						</c:if>
						<c:if test="${item.itemTp eq '200'}">
							<dd class="item_unit" id="${item.itemNo}" onclick="javascript:change('${item.itemNo}');"> <!-- //활성화시 .active -->
								<button type="button" title="바로 판매하기" name="sale" class="sale" onclick="javascript:uploadOrder('${item.itemNo}');"> 바로 판매하기</button>
							</dd>
						</c:if>
        			</dl>
        			
        			<div class="inquiryBox"  id="popup${status.count}" style="display:  ;">
 						<div class="inqu_top item1"> <!-- // .item1 ~ .item6 현재 시안기준 -->
                    		<p><strong>${item.itemNm}</strong> <i>${item.price}P / ${item.unit}</i></p>
                    		<span class="b-close"><img src="images/icon/btn_del.png" alt="닫기" height="15"></span>
                    	</div>
                    
                    	<dl>
	                    	<dt>${item.itemDescArr[0]}</dt>
	                        <dd>${item.itemDescArr[1]}</dd>
	                    
	                    	<dt>${item.itemDescArr[2]}</dt>
	                        <dd>${item.itemDescArr[3]}</dd>
	                        
	                        <dt class="inqu_bottom"><img src="images/icon/bg_pop_txt.png" alt="두껍아 두껍아 헌 옷 줄게 새 옷 다오~!" height="16"></dt>
	                        <dd class="inqu_bottom">헌 옷을 주시면 새 옷으로 드립니다.</dd>                        
                    	</dl>
				</div>
        			
        		</c:forEach>
        
			</div>
            
            <div class="item_sum" style="display:none  ">
            	<p>
                	<span class="be_p"><em><strong>5,000</strong>P</em> 이상 방문 가능</span>
                    <span class="total_p">TOTAL <i><strong id="total">0</strong>P</i></span>
                </p>
                <div class="btn_wrap pdtb0">
                    <a title="다음" href="javascript:next();" class="btn green">다음</a>
                </div>
            </div>
        
 		</div><!-- container (E) -->
 	</div><!-- wrap (E) -->
