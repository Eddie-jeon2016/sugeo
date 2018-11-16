<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


  <!-- HEADER (S) -->
      <div class="header">
        <div class="hdtop"><!--hdtop(s)-->
          <div class="topinner">
            <ul class="outlink">
              <li><a href="javascript:;" title="페이스북 바로가기"><img src="/images/common/link_fb.png" alt="페이스북"></a></li>
              <li><img src="/images/common/linkbar.png" alt="분리 바"></li>
              <li class="sclink"><a href="https://www.notm.or.kr/" title="개인정보보호 자율감시센터 바로가기" target="_blank">개인정보보호 자율감시센터</a></li>
            </ul>
          </div>
        </div><!--hdtop(e)-->
        <div class="hdbtm"><!--hdbtm(s)-->
          <div class="bG"></div>
          <div class="btminner clfix">
            <h2 class="blind">주메뉴</h2>
            <h1>
              <a href="/index.do" title="홈"><img src="/images/common/logo_1.png" alt="로고"></a>
            </h1>
            <nav id="gnb"><!--#gnb(s)-->
              <ul class="gnb">
	              <c:forEach var="result" items="${menues }">
	              	<li class="gnbbg">
	              		<a href="${result.url }" class="gnbbt" title="${result.title }">${result.title}</a>
	              			<ul class="gnb_depth">
	                    		<c:forEach var="result1" items="${result.subMenues }" varStatus="status">
			                        <li><a href="${result1.url }" title="${result1.title }">${result1.title }</a></li>
			                    </c:forEach>
	                  		</ul>
	              	</li>
	              </c:forEach>
              </ul>
            </nav><!--#gnb(e)-->
            <div class="gnb_bt"><!--gnb_bt(s)-->
              <a class="button">
                <span class="mu"></span>
              </a>
            </div><!--gnb_bt(e)-->
          </div>
        </div>
      </div><!--hdbtm(e)-->

      <!-- 180906 (s)-->
      <div class="gnbTotalBack" style="display:none"></div>


      <div id="totalview"> <!-- totalview (s)-->
      	  <div class="gnb_bt close"><!--gnb_bt(s)-->
              <a class="button">
                <span class="mu"></span>
              </a>
            </div><!--gnb_bt(e)-->
          <div class="openmenu">
              <ul class="allmenu">
                <c:forEach var="result" items="${menues }">
                	<li class="d1 <c:if test="${result.title eq 'FAQ'}"> faq</c:if>">
                		<a href="${result.title eq 'FAQ'? '/front/faq/faqList.do' : '#' }" title="${result.title }" >${result.title }</a>
                			<ul class="gnb_depth">
                				<c:forEach var="result1" items="${result.subMenues }" varStatus="status">
                					<c:if test="${result1.title ne '자주 묻는 질문과 답변' }">
                						<li><a href="${result1.url }" title="${result1.title }">${result1.title }</a></li>
                					</c:if>
                				</c:forEach>
                			</ul>
                	</li>
                </c:forEach>
              </ul>
          </div>
    </div><!-- totalview (e)-->

      <!-- //HEADER (E) -->

     



