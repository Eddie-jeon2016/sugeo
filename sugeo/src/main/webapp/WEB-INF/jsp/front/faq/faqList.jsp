<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
/*FAQ*/
$(document).ready(function() {
	$('dl.faq_list dd').css('display','none');
	/* $('dl.faq_list dd').first().css('display','block');
	$('dl.faq_list dt').first().addClass('on') */
	$('dl.faq_list dt').click(function(){
		/* 
		        이 조건문은 클릭했던 질문을 다시 클릭했을 때 사라지게 하고
		        다시 볼 수 있게쯤 해주기 위한 조건문		
		*/
		if($(this).hasClass('on')) {
			$('+dd',this).slideUp('fast');
			$(this).removeClass('on');
			return false;
		}
	    $('dl.faq_list dd').slideUp('fast');
	    $('dl.faq_list dt').removeClass('on')
	    $(this).addClass('on')
	
	    if ($(this).hasClass('on')){
	        $('+dd',this).slideDown('fast');
	    }else{
	        $('+dd',this).slideUp('fast');
	    }
	});
	$("#searchKeyword").keypress(function(e) { 
	    if (e.keyCode == 13){
	        goFind();
	    }    
	});
	
});
function goFind() {
	document.listForm.pageIndex.value = 1;
	document.listForm.action = "<c:out value='/front/faq/faqList.do'/>";
	document.listForm.submit();
}
function fnLinkPage(pageNo){
 	document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:out value='/front/faq/faqList.do'/>";
    document.listForm.submit();
}

function selected() {
	var a =$("#searchCondition option:selected").val();
	if(a ==1) {
		$("#selectLabel").text("전체");
	}
	if(a ==2) {
		$("#selectLabel").text("제목");
	}
	if(a ==3) {
		$("#selectLabel").text("내용");
	}
}
</script>

<form id="listForm" name="listForm" method="post">
	<input type="hidden" name="seearchCondition" value="${comVO.searchCondition}">
	 
	<div class="searchWrap">
            <div class="search">
              <div class="select">
              <label for="searchlabel" id="selectLabel">
              		<c:if test="${comVO.searchCondition eq '' }">전체</c:if>
              		<c:if test="${comVO.searchCondition eq '1' }">전체</c:if>
              		<c:if test="${comVO.searchCondition eq '2' }">제목</c:if>
              		<c:if test="${comVO.searchCondition eq '3' }">내용</c:if>
              </label>
              
                <select class="searchopt" title="검색옵션" name="searchCondition" id="searchCondition" onchange="selected();">
                  <option value="1"
							<c:if test="${comVO.searchCondition eq '1' }">selected="selected"</c:if>>전체</option>
						<option value="2"
							<c:if test="${comVO.searchCondition eq '2' }">selected="selected"</c:if>>제목</option>
						<option value="3"
							<c:if test="${comVO.searchCondition eq '3' }">selected="selected"</c:if>>내용</option>
                </select>
              </div>
              <div class="textbox">
                <label for="ex_input"></label> <input type="text" id="ex_input" name="searchKeyword" value="${comVO.searchKeyword}" placeholder="검색어를 입력해주세요."> <input alt="검색" src="/images/sub/btn_search.gif" type="image" class="btnSearch" onclick="javascript:goFind();">
              </div>
            </div>
        </div>
      		  <!-- 180904 수정 (S) -->
  	    	 <div class="subIn">
       		 <div class="faq cf"><!--faq(s)-->
             <dl class="faq_list">
								<ul>
                	<li class="faq_title_1">상태</li>
                  <li class="faq_title_2">제목</li>
                  <li class="faq_title_3">펼치기</li>
                </ul>
                <dt>
                	<%-- <c:if test="${empty faqList and empty comVO.searchKeyword}">
						<p>게시글이 존재하지 않습니다.</p>  
                	</c:if>
                	<c:if test="${empty faqList and not empty comVO.searchKeyword}">          		
						<p>'${comVO.searchKeyword}'에 대한 검색결과가 없습니다.</p>
                	</c:if>        --%>
                	<c:if test="${not empty faqList}">
	                	<c:forEach var="faq" items="${faqList }">
	                		<dt><p class="font_bold">
	                			<a href="javascript:;">[${faq.typeName }]${faq.quest }</a>
		                    	<span class="search_btn"></span></p>
		                    </dt>
		                    	<dd style="display: none;">${faq.answer }</dd>
	                    </c:forEach>
                    </c:if>
              </dl>                       
           </div>
          </div>
           <!--faq(e)-->

             <div class="page_num">
	            <span class="num">
	            	<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="fnLinkPage"	/>
	            </span>
            </div>
		<input type="hidden" name="pageIndex">
	
</form>        
 