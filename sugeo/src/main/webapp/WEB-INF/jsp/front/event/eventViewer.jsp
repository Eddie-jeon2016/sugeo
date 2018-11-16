<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<div class="sub_cont">
            <div class="subIn">
              <!-- 181109 (S) -->
              <div class="eventwrap">
                <div class="eventinfo">
  					${result.content}
                </div>
                <div class="btnEnvet">
                <c:if test="${nowDate >= stDateC && nowDate <= edDateC}">	
                  <a href="https://${result.url}" title="이벤트참여">
                    <img src="/images/sub/btn_event.png" alt="이벤트참여하기">
                  </a>
                  </c:if>
                </div>
              </div>
              <!-- //181109 (E) -->
            </div>
        </div>




<br><br>
<%-- <div>
	<c:if test="${nowDate > edDateC}"><div class="listBtn"><a href="/front/bbs/BbsMain.do?menuNo=1000008">당첨자확인버튼</a></div></c:if>
	<c:if test="${nowDate >= stDateC && nowDate <= edDateC}"><div class="listBtn"><a href="https://${result.url}" target="blank">접수버튼</a></div> </c:if>
</div> --%>

 <jsp:useBean id="now" class="java.util.Date"/>
    <fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nowDate"/>
    <fmt:parseDate value="${result.startDate}" var="stDate" pattern="yyyy-MM-dd"/>
    <fmt:parseDate value="${result.endDate}" var="edDate" pattern="yyyy-MM-dd"/>
    <fmt:formatDate value="${stDate}" pattern="yyyy-MM-dd" var="stDateC"/>
    <fmt:formatDate value="${edDate}" pattern="yyyy-MM-dd" var="edDateC"/>

