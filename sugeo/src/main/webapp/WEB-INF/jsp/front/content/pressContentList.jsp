
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript">
//검색
function goSearch() {
  document.listForm.action = "<c:out value='/front/pressContent/pressContentList.do'/>";
  document.listForm.pageIndex.value = 1;
  document.listForm.submit();
}

$(function(){
  $("input[name=searchKeyword]").keypress(function(e){
    if(e.keyCode == 13) {
      goSearch();
    }
  });
});

function selected() {
	var a =$("#searchCondition option:selected").val();
	if(a ==1) {
		$("#selectLabel").text("전체");
	}
	if(a ==2) {
		$("#selectLabel").text("제목");
	}
	if(a ==3) {
		$("#selectLabel").text("출처");
	}
}
</script>


   <form id="listForm" name="listForm" method="post">    
   		<input type="hidden" name="seearchCondition" value="${searchVO.searchCondition}">
        <div class="searchWrap">
            <div class="search">
              <div class="select">
              <label for="searchlabel" id="selectLabel">
              		<c:if test="${searchVO.searchCondition eq '' }">전체</c:if>
              		<c:if test="${searchVO.searchCondition eq '1' }">전체</c:if>
              		<c:if test="${searchVO.searchCondition eq '2' }">제목</c:if>
              		<c:if test="${searchVO.searchCondition eq '3' }">출처</c:if>
              </label>
                <select class="searchopt" title="검색옵션" name="searchCondition" id="searchCondition" onchange="selected();">
                  <option value="1" <c:if test="${searchVO.searchCondition eq '1' }">selected="selected"</c:if>>전체</option>
                  <option value="2" <c:if test="${searchVO.searchCondition eq '2' }">selected="selected"</c:if>>제목</option>
                  <option value="3" <c:if test="${searchVO.searchCondition eq '3' }">selected="selected"</c:if>>출처</option>
                </select>
              </div>
              <div class="textbox">
                <label for="ex_input"></label> 
                <input type="text" id="ex_input" name="searchKeyword" value="${searchVO.searchKeyword}" placeholder="검색어를 입력해주세요."> 
                <input alt="검색" src="/images/sub/btn_search.gif" type="image" class="btnSearch" onclick="javascript:goSearch();">
              </div>
            </div>
        </div>

        <div class="cnt2">
            <div class="subIn">
                  <div class="tbl_wrp">
                      <table class="tbl_lst">
                          <caption>공지사항</caption>
                          <colgroup>
                              <col style="width: 10%;" class="rmv_10">
                              <col style="width: 70%;" class="rmv_75">
                              <col style="width: 10%;" class="rmv_35">
                              <col style="width: 15%;" class="attach">
                          </colgroup>
                          <thead>
                              <tr class="line" style="height: 47px;">
                                  <th scope="col" class="rmv">번호</th>
                                  <th scope="col">제목</th>
                                  <th scope="col" class="rmvfile">출처</th>
                                  <th scope="col" class="attach">기사등록일</th>
                              </tr>
                          </thead>
                          <tbody>
                              <c:if test="${empty resultList and empty searchVO.searchKeyword}">
                                <tr>
                                  <td colspan="4">게시글이 존재하지 않습니다.</td>
                                </tr>
                              </c:if> 
                              <c:if test="${empty resultList and not empty searchVO.searchKeyword}">
                                <tr>
                                  <td colspan="4">'${searchVO.searchKeyword}'에 대한 검색 결과가 없습니다.</td>
                                </tr>
                              </c:if> 
                              <c:if test="${not empty resultList }">   
                                <c:forEach var="result" items="${resultList}" varStatus="status">
                                    <tr>                          
                                        <td class="numbold">${paginationInfo.totalRecordCount - paginationInfo.firstRecordIndex - status.index }</td>
                                        <td class="subjectwo">
                                        	<c:choose>
                                        		<c:when test="${fn:indexOf(result.url, 'http://') == -1 && fn:indexOf(result.url, 'https://') == -1}">
	                                        		<a target="_blank" href="http://${result.url}" title="바로가기" >${result.title}</a>                                    		
                                        		</c:when>
                                        		<c:otherwise>
		                                            <a target="_blank" href="${result.url}" title="바로가기" >${result.title}</a>
                                        		</c:otherwise>
                                        	</c:choose>
		                              	</td>                                                          		
                                        <td>${result.pressName}</td>
                                        <td class="day">${fn:substring(result.regDate,0,10)}</td>
                                    </tr>
                                </c:forEach>
                               </c:if>
                          </tbody>
                      </table>
                  </div>
                  
                  <div class="mbtableList">
                      <div class="listItem">
                          <table>
                              <caption>게시판</caption>
                              <colgroup>
                                  <col class="col_tit"></col>
                                  <col class="col_btn"></col>
                              </colgroup>
                              <tbody>
	                              <c:if test="${empty resultList and empty searchVO.searchKeyword}">
	                                <tr>
	                                  <td colspan="4">게시글이 존재하지 않습니다.</td>
	                                </tr>
	                              </c:if> 
	                              <c:if test="${empty resultList and not empty searchVO.searchKeyword}">
	                                <tr>
	                                  <td colspan="4">'${searchVO.searchKeyword}'에 대한 검색 결과가 없습니다.</td>
	                                </tr>
	                              </c:if> 
	                              <c:if test="${not empty resultList }">  
	                              	<c:forEach var="result" items="${resultList}" varStatus="status">
		                                  <tr class="bbtr clfix">
		                                      <td class="lefttd dotdot">
													<c:choose>
														<c:when test="${fn:indexOf(result.url, 'http://') == -1 && fn:indexOf(result.url, 'https://') == -1}">
															<a target="_blank" href="http://${result.url}" title="바로가기" >${result.title}</a>                                    		
														</c:when>
														<c:otherwise>
															<a target="_blank" href="${result.url}" title="바로가기" >${result.title}</a>
														</c:otherwise>
													</c:choose>
		                                          <span>${result.pressName}<i class="mbdate">${fn:substring(result.regDate,0,10)}</i></span>
		                                      </td>
		                                      <td class="rigtd"><a target="_blank" href="${result.url}" title="바로가기 버튼"><img src="/images/sub/btn_list.png" alt="바로가기 버튼"></a></td>
		                                  </tr> 
	                                  </c:forEach>
	                              </c:if>   
                              </tbody>
                          </table>  
                      </div>
                  </div>
                  
                  <div class="page_num">
                     <ui:pagination paginationInfo = "${paginationInfo}"  type="image" jsFunction="fnLinkPage"  />
                  </div>
            </div>
        </div>
          <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>

</form>