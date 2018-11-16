<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<form id="cmtForm" name="cmtForm" method="post">
<input type="hidden" name="ntt_id" id="ntt_id" value="${searchVO.nttId }"/>
<input type="hidden" name="bbs_id" id="bbs_id" value="${searchVO.bbsId }"/>
<input type="hidden" name="selectId" value=""/>
<p class="totalReply">댓글 ${paginationInfo.totalRecordCount}개 </p>
					<ul class="replayList">
						<c:forEach var="result" items="${resultList}" varStatus="status">
							<c:choose>
								<c:when test="${result.pAnswerNo eq '0'}">
									<li class="rp">
										<div class="icon">
												<img src="/images/mediacenter/usr/temp/temp_icon.gif" alt="" />
										</div>
										<div class="txt">
											<strong class="name">${result.nickname}</strong> <span class="cdate">${result.frstDateTime }</span>
											<div class="contWrap">
												<div class="cont">
													${result.answer}
												</div>
												<div class="btn">
													<c:if test="${loginVO.role eq 'ROLE_ADMIN' }">
														<a href="#" onClick="javascript:fnCommentUpdate('${result.answerNo}'); return false;"><img src="/images/mediacenter/usr/common/btn_modify.gif" alt="수정" /></a>
														<a href="#" onClick="javascript:fnCommentDelete('${result.answerNo}'); return false;"><img src="/images/mediacenter/usr/common/btn_del.gif" alt="삭제" /></a>
														<a href="#" onClick="javascript:fnCommentAnswer('${result.answerNo}'); return false;"><img src="/images/mediacenter/usr/common/btn_reply.gif" alt="답글" /></a>
													</c:if>
													<c:if test="${loginVO.role ne 'ROLE_ADMIN' && result.wrter_id eq loginVO.userId}">
														<a href="#" onClick="javascript:fnCommentUpdate('${result.answerNo}'); return false;"><img src="/images/mediacenter/usr/common/btn_modify.gif" alt="수정" /></a>
														<a href="#" onClick="javascript:fnCommentDelete('${result.answerNo}'); return false;"><img src="/images/mediacenter/usr/common/btn_del.gif" alt="삭제" /></a>
														<a href="#" onClick="javascript:fnCommentAnswer('${result.answerNo}'); return false;"><img src="/images/mediacenter/usr/common/btn_reply.gif" alt="답글" /></a>
													</c:if>
												</div>
											</div>
											<div class="update_${result.answerNo}"></div>
										</div>
									</li>
								</c:when>
							<c:otherwise>
						    	<li class="rp reRp">
									<div class="txt">
										<strong class="name">${result.nickname}</strong><span class="cdate">${result.frstDateTime }</span>
										<div class="contWrap">
											<div class="cont">
												${result.answer} 
											</div>
											<div class="btn">
												<a href="#" onClick="javascript:fnCommentAnsUpdate('${result.answerNo}'); return false;"><img src="/images/mediacenter/usr/common/btn_modify.gif" alt="수정" /></a>
												<a href="#" onClick="javascript:fnCommentDelete('${result.answerNo}'); return false;"><img src="/images/mediacenter/usr/common/btn_del.gif" alt="삭제" /></a>
											</div>
										</div>
										<div class="answer_${result.answerNo}"></div>
									</div>
								</li>
						    </c:otherwise>
						  </c:choose>
						</c:forEach>
						<c:if test="${empty resultList }" >
						    	<li class="rp reRp">
									<div class="txt" style="text-align:center;size:15px;">
										검색된 데이터가 없습니다.
									</div>
								</li>
						</c:if>
					</ul>
					<!-- paging -->
					<div class="pagingWrap">
						<span>
							<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="CmtLinkPage"	/>		
						</span>
					</div>
					<!--// paging -->

					<div class="replyWrite">
						<textarea rows="6" cols="" name="answer" id="answer"></textarea>
						<div class="btn">
							<span class="btnGoLinkA"><a href="#" onClick="fnCommentSubmit();return false;" >댓글입력</a></span>
						</div>
					</div>
					
</form>