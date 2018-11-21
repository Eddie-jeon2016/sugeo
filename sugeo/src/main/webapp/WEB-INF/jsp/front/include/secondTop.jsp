<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <link href="/css/sub.css" rel="stylesheet" type="text/css"/> 
<script type="text/javascript">
$(document).ready(function() {
	var menuCnt =0;
	<c:forEach items="${menuMap.secondMenu}" var="popup" varStatus="status">
		menuCnt = menuCnt + 1;
	</c:forEach>
	document.getElementById("secondTopmenu").setAttribute("class","menu"+menuCnt);
	if(menuCnt == 0) {
		$(".submenu").css("display","none");
	}
});
</script>
   <div class="wrap">
        <!-- div class="visualSub">
            <div class="visucnt">
                <img src="/images/sub/bg_depmenu.png" alt="소메뉴배경">
            </div>
        </div> -->
		  <!-- 180906 (s)-->
		  
       <div class="visualSub">
            <div class="visucnt">
                <div class="visuTitle">
                  <h2>언론보도자료</h2>
                  <p>언론보도자료 페이지입니다.소개소개소개</p>
                </div>
            </div>
        </div>
        <!-- 180906 (e)-->
		
			
	        <div class="submenu <c:if test="${menuMap.fMenu.menuId eq 'MENU_0000000004'}">longht</c:if>">
	            <div class="sbmInner">
	                <ul id = "secondTopmenu">

	                    <c:forEach items="${menuMap.secondMenu}" var="second">
							<li class="<c:if test="${second.menuId eq menuMap.currentMenuId}">on</c:if>"><a href="${second.url}" title="${second.title}" <c:if test="${second.menuId eq menuMap.currentMenuId}">style="font-weight:bold;"</c:if>  >${second.title}</a></li>
	            		</c:forEach>
	               
	                </ul> 
	            </div>
	        </div>
        
  