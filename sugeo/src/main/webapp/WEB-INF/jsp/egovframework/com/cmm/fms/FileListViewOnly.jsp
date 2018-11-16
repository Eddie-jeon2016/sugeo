<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%

/**
  * @Class Name : FileListViewOnly.jsp
  * @Description : 파일 목록화면 출력전용
  * @Modification Information
  * @
  * @  수정일   		수정자		수정내용
  * @ ----------	------		---------------------------
  * @ 2013.10.01	임정찬		최초 생성
  *
  *  @author 개발팀 임정찬
  *  @since 2009.03.26
  *  @version 1.0
  *  @see
  *
  */
%>

<div>
	<ul class="addnoticfile">
	<c:forEach var="fileVO" items="${fileList}" varStatus="status">
		<li>
			<c:choose>
				<c:when test="${updateFlag=='Y'}">
					<c:choose>
						<c:when test="${not empty domain && domain ne '' }">
							<a href="javascript:fn_egov_downFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>','${domain }')">
								${fileVO.orignlfileName}&nbsp;[${fileVO.fileMg}&nbsp;byte]
							</a>
							<a href="#" onClick="fn_deleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>','<c:out value="${atchFileCnt}"/>','${domain }'); return false;">
								<img src="<c:url value='/images/mediacenter/com/cmm/fms/icon/bu5_close.gif' />"	width="19" height="18" alt="파일삭제">
							</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:fn_egov_downFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>','')">
								${fileVO.orignlfileName}&nbsp;[${fileVO.fileMg}&nbsp;byte]
							</a>
							<a href="#" onClick="fn_deleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>','<c:out value="${atchFileCnt}"/>',''); return false;">
								<img src="<c:url value='/images/mediacenter/com/cmm/fms/icon/bu5_close.gif' />"	width="19" height="18" alt="파일삭제">
							</a>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${not empty domain && domain ne '' }">
						<img src="/images/sub/icon_file01.gif" alt="파일첨부" title="파일첨부" style="float:left"/>
							<a href="javascript:fn_egov_downFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>','${domain }')" class="folm">
								<c:out value="${fileVO.orignlfileName}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]
							</a>
						</c:when>
						<c:otherwise>
							<img src="/images/sub/icon_file01.gif" alt="파일첨부" title="파일첨부" style="float:left"/>
							<a href="javascript:fn_egov_downFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>','')" class="folm">
								 <c:out value="${fileVO.orignlfileName}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]
							</a>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</li>
	</c:forEach>
	</ul>
</div>