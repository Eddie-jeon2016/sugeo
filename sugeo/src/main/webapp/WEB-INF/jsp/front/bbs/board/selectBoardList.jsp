<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader("Expires",0);
%>
<%
/**
  * @Class Name : BbsCafeManageList.jsp
  * @Description : 카페 게시판 목록 화면
  * @Modification Information
  * @
  * @  	수정일      			수정자           	 수정내용
  * @ ---------------        --------    	---------------------------
  * @ 2013.08.29     		조은태         		최초 생성
  *
  *  @author (주)거산디에스엔 조은태
  *  @since 2013.08.29
  *  @version 1.0
  *  @see
  *
  */
%>
<!-- <script>
$(document).ready(function(){
	
    $('.content').dotdotdot();
    
});
history.pushState(null, null, location.href); 
window.onpopstate = function(event) {
// 	console.log(event.target.nodeName);
	history.go(-1); 
// 	location.href='/front/bbs/BbsMain.do?menuNo=${searchVO.menuNo}&searchCateId=${searchVO.searchCateId}';
}
</script> -->
<script>
function goSearch() {
	var menuNo = $('#menuNo').val();
	document.listForm.pageIndex.value = 1;
	//이벤트공지에서 검색했을 때
	/* document.listForm.action = "<c:out value='/front/bbs/BbsMain.do'/>"; */ 
	document.listForm.action = "<c:out value='/front/bbs/BbsMain.do?menuNo='/>${searchVO.menuNo}"; 
	document.listForm.submit();

	// 관련글에서 검색했을 때
	if(menuNo == "1000001") {
		document.listForm2.action = "<c:out value='/mng/bbs/relatedContent/BbsDetailList.do'/>"; 
		document.listForm2.submit();
	}
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
<input type="hidden" name="cmd" />
<input type="hidden" name="selectId" value="" />
<input type="hidden" name="selectbbsId" value="" />
<%-- <input type="hidden" name="menuNo" id="menuNo" value="${bbsManageVO.menuNo}" /> --%>

<div id="subcontainer" class="w100contnr"><!-- subcontainer (s)--><!-- 170822 클래스 w100contnr 추가-->
<!-- 
		<div class="tbltb_cont clfix">
			tbltb_cont
			<div class="left clfix">
				<p class="pagon">
					전체 <b>20건</b> (1페이지/2페이지)
				</p>
			</div>
		</div>
		 -->
		
		<div class="searchWrap">
            <div class="search">
              <div class="select">
              <label for="searchlabel" id="selectLabel">
              		<c:if test="${searchVO.searchCondition eq '' }">전체</c:if>
              		<c:if test="${searchVO.searchCondition eq '1' }">전체</c:if>
              		<c:if test="${searchVO.searchCondition eq '2' }">제목</c:if>
              		<c:if test="${searchVO.searchCondition eq '3' }">내용</c:if>
              </label>
                <select class="searchopt" title="검색옵션" name="searchCondition" id="searchCondition" onchange="selected();">
                  <option value="1" label="전체" <c:if test="${searchVO.searchCondition eq '1' }">selected="selected"</c:if>>전체</option>
                  <option value="2" label="제목" <c:if test="${searchVO.searchCondition eq '2' }">selected="selected"</c:if>>제목</option>
                  <option value="3" label="내용" <c:if test="${searchVO.searchCondition eq '3' }">selected="selected"</c:if>>내용</option>
                </select>
              </div>
              <div class="textbox">
                <label for="ex_input"></label> 
                <input type="text" id="ex_input" name="searchKeyword" placeholder="검색어를 입력해주세요." value="${searchVO.searchKeyword }"> 
                <input alt="검색" src="/images/sub/btn_search.gif" type="image" class="btnSearch" onclick="javascript:goSearch();">
              </div>
            </div>
        </div>
		
		
		<!--일반 게시판  -->
		<c:if test="${type ne 'relatedContent'}">
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
                                  <th scope="col" class="rmvfile">작성일</th>
                                  <th scope="col" class="attach">조회수</th>
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
                               <!-- 공지글이 없을 때 -->
							<c:if test="${ noticeList eq null }">
							<c:forEach var="result" items="${resultList}" varStatus="status">
							
								<tr>
									<td class="mbnone">${paginationInfo.totalRecordCount - paginationInfo.firstRecordIndex - status.index }</td>

									<td class="tl"><a href="#"
										onClick="javascript:selectBoardView('${bbsManageVO.bbsId}', '${result.nttId}'); return false;">${result.title}
											<%-- <c:if test="${result.cmtCount != '0'}">[${result.cmtCount}]</c:if> --%>
									</a></td>
									<td>
										<p class="datep">${result.frstDate}</p>
									</td>
									<td class="mbnone">${result.rdcnt}</td>
								</tr>
							</c:forEach>
							</c:if>
								
								<!-- 공지글이 존재할 때 -->
								<c:if test="${ noticeList ne null }">
							
								<c:forEach var="result" items="${noticeList}" varStatus="status">		
											
								<tr class="impor">
									<td class="mbnone">공지</td>

									<td class="tl"><a href="#"
										onClick="javascript:selectBoardView('${bbsManageVO.bbsId}', '${result.nttId}'); return false;">${result.title}
											<%-- <c:if test="${result.cmtCount != '0'}">[${result.cmtCount}]</c:if> --%>
									</a></td>
									<td>
										<p class="datep">${result.frstDate}</p>
									</td>
									<td class="mbnone">${result.rdcnt}</td>
								</tr>
							</c:forEach>
								<c:if test="${!empty resultList }">
										
								<c:forEach var="result" items="${resultList}" varStatus="status">
									
									<%-- <c:if test="${result.noticeYn ne 'Y' }"> --%>
									<tr>
										<td class="mbnone">	${paginationInfo.totalRecordCount - paginationInfo.firstRecordIndex - status.index}</td>

										<td class="tl"><a href="#"
											onClick="javascript:selectBoardView('${bbsManageVO.bbsId}', '${result.nttId}'); return false;">${result.title}
												<%-- <c:if test="${result.cmtCount != '0'}">[${result.cmtCount}]</c:if> --%>
										</a></td>
										<td>
											<p class="datep">${result.frstDate}</p>
										</td>
										<td class="mbnone">${result.rdcnt}</td>
									</tr>
<%-- 									</c:if> --%>			
							
							</c:forEach>
								</c:if>
							</c:if>
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
                               <!-- 공지글이 없을 때 -->
								<c:if test="${ noticeList eq null }">
	                              	<c:forEach var="result" items="${resultList}" varStatus="status">
		                                  <tr class="bbtr clfix">
		                                      <td class="lefttd dotdot">
		                                          <a href="#" onClick="javascript:selectBoardView('${bbsManageVO.bbsId}', '${result.nttId}'); return false;" title="바로가기">${result.title}</a>
		                                          <span>${result.frstDate}</span>
		                                      </td>
		                                  </tr> 
	                                  </c:forEach>
	                              </c:if> 
	                              <!-- 공지글이 존재할 때 -->
									<c:if test="${ noticeList ne null }">
		                              	<c:forEach var="result" items="${noticeList}" varStatus="status">
			                                  <tr class="bbtr clfix">
			                                      <td class="lefttd dotdot">
			                                          <a href="#" onClick="javascript:selectBoardView('${bbsManageVO.bbsId}', '${result.nttId}'); return false;" title="바로가기">${result.title}</a>
			                                          <span>${result.frstDate}</span>
			                                      </td>
			                                  </tr> 
		                                  </c:forEach>
									</c:if> 
									<c:if test="${!empty resultList }">
		                              	<c:forEach var="result" items="${resultList}" varStatus="status">
			                                  <tr class="bbtr clfix">
			                                      <td class="lefttd dotdot">
			                                          <a href="#" onClick="javascript:selectBoardView('${bbsManageVO.bbsId}', '${result.nttId}'); return false;" title="바로가기">${result.title}</a>
			                                          <span>${result.frstDate}</span>
			                                      </td>
			                                  </tr> 
		                                  </c:forEach>
									
									
									</c:if>
								</c:if>
                              </tbody>
                              
                          </table>  
                      </div>
                  </div>                 
		</c:if>
		
		
		
		
		<!--이미지형 게시판  -->
		<c:if test="${type eq 'relatedContent'}">
<%-- 			 <div class="dream_repwit">
	                 <ul class="clfix">	                
	                  	<c:forEach var="result" items="${resultList}" varStatus="status">
	                  	  <c:set var="imgSrc" value=""/>
			                <c:choose>
			                    <c:when test="${result.thumbAtchFileId!='0'&& result.thumbAtchFileId!='' && result.thumbAtchFileId!=null}">
			                        <c:set var="imgSrc" value="/cmm/fms/getImage.do?atchFileId=${result.thumbAtchFileId}&fileSn=0"/>
			                    </c:when>			                 
			                    <c:otherwise>
			                        <c:set var="imgSrc" value="/images/sub/blank2.gif"/>
			                    </c:otherwise>
			                </c:choose>
	                       <li>                        	
	                       	<div class="imgbox"><a href="sub04_06-detail.html" title="아인세 미션 상세" onClick="javascript:selectBoardView('${result.bbsId}', '${result.nttId}'); return false;" ><img src="${imgSrc}"></a></div>   
	                       	<dl>
	                       	  <dt><a href="44_03_02-detail.html" title="기사쓰기 상세" onClick="javascript:selectBoardView('${result.bbsId}', '${result.nttId}'); return false;">${result.title}</a></dt>
	                          <dd class="nttCn"><span>${result.content}</span></dd>
	                          <dd class="date"><span>${result.frstDate}</span></dd>
	                       	</dl>                            								
	                       </li>
						</c:forEach>
	                 </ul>
	         </div> --%>
	         
	         
	    <!-- 180904 수정 (S) -->
        <div class="subIn">
            <div class="galleryBoard"> <!--galleryBoard(s)-->
              <ul>
              <c:if test="${empty resultList and  empty searchVO.searchKeyword}">
              	<h1 style="font-size: 22px; text-align: center; margin-bottom: 70px; color: #334d72">해당 게시물이 없습니다.</h1>
              </c:if>
              <c:if test="${empty resultList and not empty searchVO.searchKeyword}">
              	<h1 style="font-size: 22px; text-align: center; margin-bottom: 70px; color: #334d72">'${searchVO.searchKeyword}'에 대한 검색결과가 없습니다.</h1>
              </c:if>
              <c:if test="${not empty resultList}">
                <c:forEach var="result" items="${resultList}" varStatus="status">
                  <li class="in clfix"> 
                       <a href="#" onClick="javascript:selectBoardView('${result.bbsId}', '${result.nttId}'); return false;">
                        <div class="bd_box bx2">
                              <div class="bximg">
                              		 <c:choose>
					                    <c:when test="${result.thumbAtchFileId!='0'&& result.thumbAtchFileId!='' && result.thumbAtchFileId!=null}">
					                    	<img src="/cmm/fms/getImage.do?atchFileId=${result.thumbAtchFileId}&fileSn=0" alt="관련글"> 
					                    </c:when>			                 
					                    <c:otherwise> 
					                        <img src="/images/sub/thumb1.jpg" alt="관련글"> 
					                    </c:otherwise>
					                </c:choose>
                              </div>  
                              <div class="bxtxt">
                                  <h3 class="dotdot">${result.title}</h3>
                                  <p class="dotdot">${result.content}</p>
                                  <span class="date">${result.frstDate}</span>
                              </div>
                        </div>
                     </a>
                  </li>
                 </c:forEach>             
              </c:if>   
              </ul>
            </div><!--galleryBoard(e)-->
        </div>
        <!-- //180904 수정 (E) -->
		</c:if>
		

		<!-- <div class="page_num">page_num  모바일에선 5단위씩보이게
                <a href="#none" class="pn"><img src="../../images/sub_common/page_front.gif" alt="left"></a>
                <a href="#none" class="pn left"><img src="../../images/sub_common/page_prev.gif" alt="left"></a>
                
                <a href="#none" class="pn right"><img src="../../images/sub_common/page_next.gif" alt="right"></a>
                <a href="#none" class="pn"><img src="../../images/sub_common/page_back.gif" alt="right"></a>
            </div>// page_num -->
             <div class="page_num">
	            <span class="num">
	            	<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="fnLinkPage"	/>
	            </span>
            </div>
	</div> <!-- subcontainer (E)-->
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form>